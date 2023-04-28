package com.example.edamatest.ui.nutrition_analysis.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.edamatest.databinding.NutritionAnalysisIngredientItemBinding
import com.example.edamatest.ui.nutrition_analysis.model.IngredientModel

class NutritionAnalysisRecyclerViewAdapter :
    RecyclerView.Adapter<ViewHolder>() {

    val differUtil = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        NutritionAnalysisIngredientItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = differUtil.currentList[position]

        holder.binding.textViewFood.text = "${item.quantity} ${item.measure} ${item.food}"

        holder.binding.textViewCalories.text =
            "${item.nutrients.ENERC_KCAL.quantity} ${item.nutrients.ENERC_KCAL.unit}"

        holder.binding.textViewWeight.text = "${item.weight} g"
    }

    override fun getItemCount(): Int = differUtil.currentList.size

}

class ViewHolder(val binding: NutritionAnalysisIngredientItemBinding) :
    RecyclerView.ViewHolder(binding.root)

private val differCallBack = object : DiffUtil.ItemCallback<IngredientModel>() {
    override fun areItemsTheSame(oldItem: IngredientModel, newItem: IngredientModel): Boolean {
        return oldItem.foodId == newItem.foodId
    }

    override fun areContentsTheSame(oldItem: IngredientModel, newItem: IngredientModel): Boolean {
        return oldItem == newItem
    }
}