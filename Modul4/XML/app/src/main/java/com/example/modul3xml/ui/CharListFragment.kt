package com.example.modul3xml.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.modul3xml.R
import com.example.modul3xml.databinding.FragmentCharListBinding
import com.example.modul3xml.model.Char
import kotlinx.coroutines.launch

class CharListFragment : Fragment(R.layout.fragment_char_list) {

    private var _binding: FragmentCharListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CharListViewModel by viewModels {
        CharListViewModelFactory("CharListFragment")
    }

    private var featuredAdapter: CharAdapter? = null
    private var allAdapter: CharAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentCharListBinding.bind(view)

        binding.rvFeatured.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.HORIZONTAL,
            false
        )
        binding.rvAll.layoutManager = LinearLayoutManager(requireContext())

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.featuredChars.collect { chars ->
                        bindFeaturedList(chars)
                    }
                }
                launch {
                    viewModel.allChars.collect { chars ->
                        bindAllList(chars)
                    }
                }
                launch {
                    viewModel.wikiClick.collect { char ->
                        requireContext().openUrl(char.wikiUrl)
                    }
                }
                launch {
                    viewModel.detailClick.collect { char ->
                        val args = Bundle().apply { putString("id", char.id) }
                        findNavController().navigate(
                            R.id.action_charListFragment_to_charDetailFragment,
                            args
                        )
                    }
                }
            }
        }
    }

    private fun bindFeaturedList(chars: List<Char>) {
        val adapter = featuredAdapter
        if (adapter == null) {
            featuredAdapter = CharAdapter(
                items = chars,
                mode = CharAdapter.Mode.FEATURED,
                onWikiClicked = viewModel::onWikiClicked,
                onDetailClicked = viewModel::onDetailClicked
            )
            binding.rvFeatured.adapter = featuredAdapter
        } else {
            adapter.updateItems(chars)
        }
    }

    private fun bindAllList(chars: List<Char>) {
        val adapter = allAdapter
        if (adapter == null) {
            allAdapter = CharAdapter(
                items = chars,
                mode = CharAdapter.Mode.ALL,
                onWikiClicked = viewModel::onWikiClicked,
                onDetailClicked = viewModel::onDetailClicked
            )
            binding.rvAll.adapter = allAdapter
        } else {
            adapter.updateItems(chars)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        featuredAdapter = null
        allAdapter = null
        _binding = null
    }
}
