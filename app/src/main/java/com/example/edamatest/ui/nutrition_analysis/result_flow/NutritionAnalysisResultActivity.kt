package com.example.edamatest.ui.nutrition_analysis.result_flow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.edamatest.databinding.ActivityNutritionAnalysisResultBinding
import com.example.edamatest.ui.showErrorAlertDialog
import org.koin.androidx.viewmodel.ext.android.viewModel

const val NUTRITION_ANALYSIS_LIST_KEY = "NUTRITION_ANALYSIS_LIST_KEY"

class NutritionAnalysisResultActivity : AppCompatActivity() {

    private val binding: ActivityNutritionAnalysisResultBinding by lazy(mode = LazyThreadSafetyMode.NONE) {
        ActivityNutritionAnalysisResultBinding.inflate(layoutInflater)
    }

    private val viewModel by viewModel<NutritionAnalysisResultViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initToolbar()

        intent.getStringArrayListExtra(NUTRITION_ANALYSIS_LIST_KEY).let { products ->
            if (products != null) {
                viewModel.getNutritionAnalysis(products)
            } else {
                showErrorAlertDialog("Update entered data and try again") { finish() }
            }
        }

    }

    private fun initToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = ""
        binding.toolbarBack.setOnClickListener { finish() }
    }
}