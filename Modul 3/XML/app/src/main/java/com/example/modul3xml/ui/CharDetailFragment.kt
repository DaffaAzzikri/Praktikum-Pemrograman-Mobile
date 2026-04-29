package com.example.modul3xml.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.modul3xml.R
import com.example.modul3xml.databinding.FragmentCharDetailBinding
import com.example.modul3xml.model.Char
import com.example.modul3xml.model.CharsData

class CharDetailFragment : Fragment(R.layout.fragment_char_detail) {

    private var _binding: FragmentCharDetailBinding? = null
    private val binding get() = _binding!!

    private val allChars: List<Char> = CharsData.all

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentCharDetailBinding.bind(view)

        val id = arguments?.getString("id").orEmpty()
        val char: Char? = allChars.firstOrNull { it.id == id }

        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        if (char == null) {
            binding.emptyState.visibility = View.VISIBLE
            binding.contentState.visibility = View.GONE
            return
        }

        binding.emptyState.visibility = View.GONE
        binding.contentState.visibility = View.VISIBLE

        binding.ivDetailChar.setImageResource(char.imageResId)
        binding.tvDetailName.text = char.name
        binding.tvDetailSeries.text = char.series
        val combinedFeature = "${char.featureTitle} ${char.featureDescription}"
        binding.tvDetailFeature.text = combinedFeature

        binding.btnWikiDetail.setOnClickListener {
            requireContext().openUrl(char.wikiUrl)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

