package com.example.edamatest.ui.nutrition_analysis

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.edamatest.R
import com.example.edamatest.databinding.FragmentNutritionAnalysisBinding
import com.example.edamatest.openActivity
import com.example.edamatest.ui.launchAndCollectWithLifecycle
import com.example.edamatest.ui.nutrition_analysis.result_flow.NUTRITION_ANALYSIS_LIST_KEY
import com.example.edamatest.ui.nutrition_analysis.result_flow.NutritionAnalysisResultActivity
import com.example.edamatest.ui.showErrorAlertDialog
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
        binding.textInputEditText.text.toString().let {
            if (it.isNotEmpty()) {
                recipe.add(it)
            }
        }
        for (item in viewModel.listOfItems.value) {
            item.input.let {
                if (it.isNotEmpty()) {
                    recipe.add(it)
                }
            }
        }
        requireActivity().openActivity(NutritionAnalysisResultActivity::class.java) {
            putStringArrayList(NUTRITION_ANALYSIS_LIST_KEY, recipe)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

private fun Context.showExampleAlert() {
    val builder = AlertDialog.Builder(this)
    val customView = LayoutInflater.from(this).inflate(R.layout.nutrition_analysis_example_dialog, null)
    builder.setView(customView)

    val alertDialog: AlertDialog = builder.create()
    alertDialog.setCancelable(true)
    alertDialog.show()
}