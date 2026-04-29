package com.example.modul3xml.ui

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.modul3xml.databinding.ItemCharBinding
import com.example.modul3xml.model.Char
import kotlin.math.roundToInt

class CharAdapter(
    private val items: List<Char>,
    private val mode: Mode,
    private val onWikiClicked: (Char) -> Unit,
    private val onDetailClicked: (Char) -> Unit
) : RecyclerView.Adapter<CharAdapter.CharViewHolder>() {

    enum class Mode { FEATURED, ALL }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCharBinding.inflate(inflater, parent, false)
        return CharViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: CharViewHolder, position: Int) {
        holder.bind(items[position], mode, onWikiClicked, onDetailClicked)
        applyItemSize(holder.itemView, holder.itemView.context)
    }

    private fun applyItemSize(root: View, context: Context) {
        val lp = root.layoutParams
        if (lp is RecyclerView.LayoutParams) {
            if (mode == Mode.FEATURED) {
                lp.width = dpToPx(context, 330)
                lp.height = ViewGroup.LayoutParams.WRAP_CONTENT
            } else {
                lp.width = ViewGroup.LayoutParams.MATCH_PARENT
                lp.height = ViewGroup.LayoutParams.WRAP_CONTENT
            }
            root.layoutParams = lp
        }
    }

    private fun dpToPx(context: Context, dp: Int): Int {
        return (dp * context.resources.displayMetrics.density).roundToInt()
    }

    class CharViewHolder(
        private val binding: ItemCharBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            char: Char,
            mode: Mode,
            onWikiClicked: (Char) -> Unit,
            onDetailClicked: (Char) -> Unit
        ) {
            val bgColor = if (mode == Mode.FEATURED) "#DDF0FF" else "#EAF4FF"
            binding.cardRoot.setCardBackgroundColor(Color.parseColor(bgColor))

            binding.ivChar.setImageResource(char.imageResId)
            binding.tvName.text = char.name
            binding.tvSeries.text = char.series

            val isFeatured = mode == Mode.FEATURED
            binding.tvSeries.visibility = if (isFeatured) View.GONE else View.VISIBLE

            binding.tvFeatureTitle.text = char.featureTitle
            binding.tvFeatureDesc.text = char.featureDescription
            binding.tvFeatureDesc.maxLines = if (isFeatured) 3 else 2

            binding.btnWiki.setOnClickListener { onWikiClicked(char) }
            binding.btnDetail.setOnClickListener { onDetailClicked(char) }
        }
    }
}

