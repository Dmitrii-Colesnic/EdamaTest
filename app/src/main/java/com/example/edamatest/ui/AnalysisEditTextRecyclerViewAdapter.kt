package com.example.edamatest.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.edamatest.databinding.EditTextLayoutBinding

class AnalysisEditTextRecyclerViewAdapter(
    private val context: Context, private val itemClickListener: () -> Unit
) : RecyclerView.Adapter<AnalysisEditTextRecyclerViewAdapter.ViewHolder>() {

    private var items = ArrayList<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            EditTextLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (holder.getDataFromEditText().isNotEmpty()) {
            itemClickListener
        }
    }

    public fun setItem() {

    }

    inner class ViewHolder(private val binding: EditTextLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun getDataFromEditText(): String = binding.textInputEditText.text.toString()
        fun bindItem() {

        }
    }
}