package com.example.edamatest.ui.recipe_search.result_flow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.edamatest.databinding.RecipeSearchResultItemBinding

class ResultRecyclerViewAdapter : RecyclerView.Adapter<ResultRecyclerViewAdapter.ViewHolder>() {

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

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int = differ.currentList.size

}