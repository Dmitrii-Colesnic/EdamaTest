package com.example.edamatest.ui.recipe_search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.edamatest.R
import com.example.edamatest.databinding.ChipMacronutrientsBinding

data class DietModel(
    val name: String,
    var isChecked: Boolean = false,
)

class DietsRecyclerViewAdapter(val onItemClick: (Int, Boolean) -> Unit) : RecyclerView.Adapter<DietsRecyclerViewAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ChipMacronutrientsBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<DietModel>() {
        override fun areItemsTheSame(oldItem: DietModel, newItem: DietModel): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: DietModel, newItem: DietModel): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, differCallback)

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
        var item = differ.currentList[position]

        val ivMain = holder.binding.ivMain
        val tvMain = holder.binding.tvMain
        val parentLayout = holder.binding.parentLayout

        tvMain.text = item.name

        if (item.isChecked) {
            ivMain.setImageDrawable(
                ContextCompat.getDrawable(
                    parentLayout.context,
                    R.drawable.baseline_check_mark_outline_24_green
                )
            )
        } else {
            ivMain.setImageDrawable(
                ContextCompat.getDrawable(
                    parentLayout.context,
                    R.drawable.baseline_add_circle_outline_24
                )
            )
        }

        parentLayout.setOnClickListener {
            onItemClick.invoke(position, item.isChecked)
        }
    }

    override fun getItemCount(): Int = differ.currentList.size
}