package com.example.edamatest.ui.nutrition_analysis

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.edamatest.databinding.FragmentNutritionAnalysisBinding
import com.example.edamatest.openActivity
import com.example.edamatest.ui.launchAndCollectWithLifecycle
import com.example.edamatest.ui.nutrition_analysis.result_flow.NUTRITION_ANALYSIS_LIST_KEY
import com.example.edamatest.ui.nutrition_analysis.result_flow.NutritionAnalysisResultActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class NutritionAnalysisFragment : Fragment() {

    private var _binding: FragmentNutritionAnalysisBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModel<NutritionAnalysisViewModel>()

    private val adapter: AnalysisEditTextRecyclerViewAdapter by lazy(LazyThreadSafetyMode.PUBLICATION) {
        AnalysisEditTextRecyclerViewAdapter(
            deleteItemClickListener = { viewModel.deleteItemClickEvent(it) },
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNutritionAnalysisBinding.inflate(inflater, container, false)

        viewModel.listOfItems.launchAndCollectWithLifecycle(lifecycleOwner = viewLifecycleOwner) { array ->
            adapter.differ.submitList(array)
        }

        binding.recyclerView.adapter = adapter

        binding.fabAdd.setOnClickListener {
            viewModel.addItemClickEvent()
        }

        binding.fabSearch.setOnClickListener { goToResultModel() }

        return binding.root
    }

    private fun goToResultModel() {
        val recipe = arrayListOf<String>()
        for (item in viewModel.listOfItems.value) {
            recipe.add(item.input)
        }
        requireActivity().openActivity(NutritionAnalysisResultActivity::class.java) {
            putStringArrayList(NUTRITION_ANALYSIS_LIST_KEY, recipe)
        }
    }

//    private fun addButtonClickability(clickability: Boolean) {
//        if (clickability) {
//            binding.fabAdd.apply {
//                backgroundTintList =
//                    ColorStateList.valueOf(
//                        ContextCompat.getColor(
//                            requireActivity(),
//                            R.color.yellow_dark_dark_custom
//                        )
//                    )
//                isClickable = true
//            }
//        } else {
//            binding.fabAdd.apply {
//                backgroundTintList =
//                    ColorStateList.valueOf(
//                        ContextCompat.getColor(
//                            requireActivity(),
//                            R.color.gray
//                        )
//                    )
//                isClickable = false
//            }
//        }
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}