package com.example.edamatest.ui.nutrition_analysis

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.edamatest.R
import com.example.edamatest.databinding.EditTextLayoutBinding

data class EditTextState(val id: Int, var input: String = "", var btnIsActive: Boolean = true)

class AnalysisEditTextRecyclerViewAdapter(
    private val deleteItemClickListener: (Int) -> Unit,
) : RecyclerView.Adapter<AnalysisEditTextRecyclerViewAdapter.ViewHolder>() {

    private lateinit var btn: FrameLayout
    private lateinit var editText: EditText

    private val differCallback = object : DiffUtil.ItemCallback<EditTextState>() {
        override fun areItemsTheSame(oldItem: EditTextState, newItem: EditTextState): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: EditTextState, newItem: EditTextState): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, differCallback)


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
        val item = differ.currentList[position]
        editText = holder.binding.textInputEditText
        btn = holder.binding.frameLayoutButton

        btn.setOnClickListener { deleteItemClickListener.invoke(position) }

        editText.requestFocus()

        editText.addTextChangedListener {
            item.input = it.toString()
        }

    }
}