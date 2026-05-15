package com.example.modul3xml.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.modul3xml.R
import com.example.modul3xml.databinding.FragmentCharDetailBinding
import com.example.modul3xml.model.Char
import kotlinx.coroutines.launch

class CharDetailFragment : Fragment(R.layout.fragment_char_detail) {

    private var _binding: FragmentCharDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CharDetailViewModel by viewModels {
        CharDetailViewModelFactory(requireArguments().getString("id").orEmpty())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentCharDetailBinding.bind(view)

        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.char.collect { char ->
                    if (char == null) {
                        showEmptyState()
                    } else {
                        showChar(char)
                    }
                }
            }
        }
    }

    private fun showEmptyState() {
        binding.emptyState.visibility = View.VISIBLE
        binding.contentState.visibility = View.GONE
    }

    private fun showChar(char: Char) {
        binding.emptyState.visibility = View.GONE
        binding.contentState.visibility = View.VISIBLE

        binding.ivDetailChar.setImageResource(char.imageResId)
        binding.tvDetailName.text = char.name
        binding.tvDetailSeries.text = char.series
        binding.tvDetailFeature.text = "${char.featureTitle} ${char.featureDescription}"

        binding.btnWikiDetail.setOnClickListener {
            requireContext().openUrl(char.wikiUrl)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
