package com.example.edamatest.ui.nutrition_analysis

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.edamatest.R
import com.example.edamatest.databinding.EditTextLayoutBinding
import com.example.edamatest.databinding.FragmentNutritionAnalysisBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class NutritionAnalysisFragment : Fragment() {

    private val TAG = "NutritionAnalysisFragment"

    private var _binding: FragmentNutritionAnalysisBinding? = null
    private val binding get() = _binding!!

    private val item = "item_"
    private var itemNumber = 1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel = ViewModelProvider(this)[NutritionAnalysisViewModel::class.java]

        _binding = FragmentNutritionAnalysisBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
    }

    private fun setListeners() {
        binding.fabAdd.setOnClickListener {
            setItemListeners()
            createTextView()
        }
    }

    private fun setItemListeners() {
        val itemsCount = binding.scrollViewLayout.childCount

        if (itemsCount > 1) {
            val lastChild = binding.scrollViewLayout.getChildAt(itemsCount)
            val fab = lastChild.findViewById<FloatingActionButton>(R.id.fab)
            fab.backgroundTintList =
                activity?.let { ContextCompat.getColor(it, R.color.blue_light_light) }
                    ?.let { ColorStateList.valueOf(it) };
        }
    }

    private fun createTextView() {
        val itemsCount = binding.scrollViewLayout.childCount

        if (itemsCount > 1) {
            val lastChild = binding.scrollViewLayout.getChildAt(itemsCount)
            val textView = lastChild.findViewById<EditText>(R.id.textInputEditText)
            if (textView.text.toString().isNotEmpty()) {
                val view = EditTextLayoutBinding.inflate(LayoutInflater.from(activity))
                view.fab.setOnClickListener {

                }
                binding.scrollViewLayout.addView(view.root)
            } else if (textView.text.toString().isEmpty()) {
                // todo: show pop up
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}