package com.example.modul3xml.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.modul3xml.R
import com.example.modul3xml.databinding.FragmentCharListBinding
import com.example.modul3xml.model.Char
import com.example.modul3xml.model.CharsData

class CharListFragment : Fragment(R.layout.fragment_char_list) {

    private var _binding: FragmentCharListBinding? = null
    private val binding get() = _binding!!

    private val allChars: List<Char> = CharsData.all
    private val featuredChars: List<Char> = allChars

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentCharListBinding.bind(view)

        val wikiClick: (Char) -> Unit = { char ->
            requireContext().openUrl(char.wikiUrl)
        }

        val detailClick: (Char) -> Unit = { char ->
            val args = Bundle().apply { putString("id", char.id) }
            findNavController().navigate(
                R.id.action_charListFragment_to_charDetailFragment,
                args
            )
        }

        binding.rvFeatured.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.HORIZONTAL,
            false
        )
        binding.rvFeatured.adapter = CharAdapter(
            items = featuredChars,
            mode = CharAdapter.Mode.FEATURED,
            onWikiClicked = wikiClick,
            onDetailClicked = detailClick
        )

        binding.rvAll.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )
        binding.rvAll.adapter = CharAdapter(
            items = allChars,
            mode = CharAdapter.Mode.ALL,
            onWikiClicked = wikiClick,
            onDetailClicked = detailClick
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

