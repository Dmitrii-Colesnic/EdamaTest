package com.example.edamatest.ui.recipe_search.result_flow

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.edamatest.databinding.RecipeSearchResultItemBinding
import com.example.edamatest.ui.recipe_search.loadPictures

class RecipeSearchResultRecyclerViewAdapter :
    RecyclerView.Adapter<RecipeSearchResultRecyclerViewAdapter.ViewHolder>() {

    private val callbackDiffer = object : DiffUtil.ItemCallback<RecipeSearchResultItem>() {
        override fun areItemsTheSame(
            oldItem: RecipeSearchResultItem,
            newItem: RecipeSearchResultItem
        ): Boolean {
            TODO("compare id's")
        }

        override fun areContentsTheSame(
            oldItem: RecipeSearchResultItem,
            newItem: RecipeSearchResultItem
        ): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this, callbackDiffer)

    inner class ViewHolder(val binding: RecipeSearchResultItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RecipeSearchResultItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = differ.currentList[position]

        holder.binding.ivMain.loadPictures(item.image)
        holder.binding.tvTitle.text = item.label
        holder.binding.tvCategories.text = item.healthLabels.mapToString()

        holder.binding.tvKcal.text = item.calories.toString()

        holder.binding.tvProtein.text = item.macroNutrients.PROCNT.label
        holder.binding.tvProteinValue.text =
            item.macroNutrients.PROCNT.quantity.toString() + item.macroNutrients.PROCNT.unit
        holder.binding.tvFat.text = item.macroNutrients.FAT.label
        holder.binding.tvFatValue.text =
            item.macroNutrients.FAT.quantity.toString() + item.macroNutrients.FAT.unit
        holder.binding.tvCarbs.text = item.macroNutrients.CHOCDF.label
        holder.binding.tvCarbsValue.text =
            item.macroNutrients.CHOCDF.quantity.toString() + item.macroNutrients.CHOCDF.unit

        holder.binding.tvSugar.text = item.microNutrients.SUGAR.label
        holder.binding.tvSugarValue.text =
            item.microNutrients.SUGAR.quantity.toString() + item.microNutrients.SUGAR.unit
        holder.binding.tvCholesterol.text = item.microNutrients.CHOLE.label
        holder.binding.tvCholesterolValue.text =
            item.microNutrients.CHOLE.quantity.toString() + item.microNutrients.CHOLE.unit
        holder.binding.tvSodium.text = item.microNutrients.NA.label
        holder.binding.tvSodiumValue.text =
            item.microNutrients.NA.quantity.toString() + item.microNutrients.NA.unit
        holder.binding.tvCalcium.text = item.microNutrients.CA.label
        holder.binding.tvCalciumValue.text =
            item.microNutrients.CA.quantity.toString() + item.microNutrients.CA.unit
        holder.binding.tvMagnesium.text = item.microNutrients.MG.label
        holder.binding.tvMagnesiumValue.text =
            item.microNutrients.MG.quantity.toString() + item.microNutrients.MG.unit
        holder.binding.tvPotassium.text = item.microNutrients.K.label
        holder.binding.tvPotassiumValue.text =
            item.microNutrients.K.quantity.toString() + item.microNutrients.K.unit
        holder.binding.tvIron.text = item.microNutrients.FE.label
        holder.binding.tvIronValue.text =
            item.microNutrients.FE.quantity.toString() + item.microNutrients.FE.unit

    }

    override fun getItemCount(): Int = differ.currentList.size

}

private fun List<String>.mapToString(): String {
    var result = ""
    for (item in this) {
        result += "$item • "
    }
    return result
}
