package com.example.edamatest.ui.recipe_search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.edamatest.R
import com.example.edamatest.databinding.ChipMacronutrientsBinding

data class NutrientsModel(
    val id: Int,
    val name: String,
    val serverName: String,
    var serverRange: String = ""
)

class NutrientsRecyclerViewAdapter(
    private val onItemClick: (Int, String) -> Unit,
    private val onItemClearClick: (Int) -> Unit,
) : RecyclerView.Adapter<NutrientsRecyclerViewAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ChipMacronutrientsBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<NutrientsModel>() {
        override fun areItemsTheSame(oldItem: NutrientsModel, newItem: NutrientsModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: NutrientsModel, newItem: NutrientsModel): Boolean {
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
        val tvRange = holder.binding.tvRange
        val ivClear = holder.binding.ivClear
        val parentLayout = holder.binding.parentLayout

        tvMain.text = item.name

        if (item.serverRange.isNotEmpty()) {
            ivMain.setImageDrawable(
                ContextCompat.getDrawable(
                    parentLayout.context,
                    R.drawable.baseline_check_mark_outline_24_green
                )
            )
            tvRange.visibility = View.VISIBLE
            tvRange.text = convertRangeToDisplayFormatEx(item.serverRange)

            ivClear.visibility = View.VISIBLE
        } else {
            ivMain.setImageDrawable(
                ContextCompat.getDrawable(
                    parentLayout.context,
                    R.drawable.baseline_add_circle_outline_24
                )
            )
            tvRange.visibility = View.GONE
            ivClear.visibility = View.GONE
        }
        tvMain.text = item.name

        ivClear.setOnClickListener { onItemClearClick.invoke(position) }

        parentLayout.setOnClickListener { onItemClick.invoke(position, item.serverRange) }
    }

    override fun getItemCount(): Int = differ.currentList.size
}