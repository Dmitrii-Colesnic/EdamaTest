package com.example.edamatest.ui.recipe_search

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.edamatest.R
import com.example.edamatest.databinding.ChipMacronutrientsBinding

data class NutrientsModel(val name: String, val serverName: String, val range: String)

class NutrientsRecyclerViewAdapter(
    private val context: Context,
    private val nutrients: List<NutrientsModel>,
    private val onItemClick: (NutrientsModel) -> Unit,
) : RecyclerView.Adapter<NutrientsRecyclerViewAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ChipMacronutrientsBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ChipMacronutrientsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item = nutrients[position]

        holder.binding.tvMain.text = item.name

        if (item.range.isNotEmpty()) {
            holder.binding.ivMain.setImageDrawable(
                ContextCompat.getDrawable(
                    context,
                    R.drawable.baseline_check_mark_outline_24_green
                )
            )

            holder.binding.tvRange.text = item.range
        }

        holder.binding.parentLayout.setOnClickListener { onItemClick.invoke(item) }

    }

    override fun getItemCount(): Int = nutrients.size

}