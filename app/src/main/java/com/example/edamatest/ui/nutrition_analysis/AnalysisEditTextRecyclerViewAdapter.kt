package com.example.edamatest.ui.nutrition_analysis

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.edamatest.R
import com.example.edamatest.databinding.EditTextLayoutBinding

data class EditTextState(val id: Int, val input: String, val btnIsActive: Boolean)

class AnalysisEditTextRecyclerViewAdapter(
    private val context: Context,
    private val onClick: (Int) -> Unit
) : RecyclerView.Adapter<AnalysisEditTextRecyclerViewAdapter.ViewHolder>() {

    var plusOneVisibility: ((Boolean) -> Unit)? = null

    inner class ViewHolder(val binding: EditTextLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            EditTextLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val editText = holder.binding.textInputEditText
        val btn = holder.binding.frameLayoutButton

        position.also {
            if (differ.currentList.size == it - 1) {
                makeLayoutClickable(btn, true)
            } else {
                makeLayoutClickable(btn, false)
            }
        }

    }

    private fun makeLayoutClickable(frameLayout: FrameLayout, boolean: Boolean) {
        if (boolean) {
            frameLayout.apply {
                background =
                    ContextCompat.getDrawable(context, R.drawable.bg_red_circle_ripple)
                isFocusable = true
                isClickable = true
                elevation = 5f
                setOnClickListener {
                    onClick(position)
                }
            }
        } else {
            frameLayout.apply {
                background = ContextCompat.getDrawable(context, R.drawable.bg_gray_circle)
                isFocusable = false
                isClickable = false
                elevation = 0f
            }
        }
    }

    private val differCallback = object : DiffUtil.ItemCallback<EditTextState>() {
        override fun areItemsTheSame(oldItem: EditTextState, newItem: EditTextState): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: EditTextState, newItem: EditTextState): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, differCallback)
}