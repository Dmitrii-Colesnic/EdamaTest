package com.example.edamatest.ui.nutrition_analysis

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.edamatest.R
import com.example.edamatest.databinding.FragmentNutritionAnalysisBinding

class NutritionAnalysisFragment : Fragment() {

    private var _binding: FragmentNutritionAnalysisBinding? = null
    private val binding get() = _binding!!

    private val viewModel: NutritionAnalysisViewModel by lazy(LazyThreadSafetyMode.PUBLICATION) {
        ViewModelProvider(this)[NutritionAnalysisViewModel::class.java]
    }

    private val adapter: AnalysisEditTextRecyclerViewAdapter by lazy(LazyThreadSafetyMode.PUBLICATION) {
        AnalysisEditTextRecyclerViewAdapter(
            deleteItemClickListener = { viewModel.deleteItemClickEvent() },
            addButtonVisibility = { addButtonClickability(it) }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNutritionAnalysisBinding.inflate(inflater, container, false)

        viewModel.listOfItems.observe(viewLifecycleOwner) { array ->
            adapter.differ.submitList(array)
        }
        binding.recyclerView.adapter = adapter

        binding.fabAdd.setOnClickListener {
            viewModel.addItemClickEvent()
        }

        return binding.root
    }

    private fun addButtonClickability(clickability: Boolean) {
        if (clickability) {
            binding.fabAdd.apply {
                backgroundTintList =
                    ColorStateList.valueOf(
                        ContextCompat.getColor(
                            requireActivity(),
                            R.color.yellow_dark_dark_custom
                        )
                    )
                isClickable = true
            }
        } else {
            binding.fabAdd.apply {
                backgroundTintList =
                    ColorStateList.valueOf(
                        ContextCompat.getColor(
                            requireActivity(),
                            R.color.gray
                        )
                    )
                isClickable = false
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}