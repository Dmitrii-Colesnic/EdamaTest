package com.example.edamatest.ui.nutrition_analysis.result_flow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.edamatest.R
import com.example.edamatest.databinding.ActivityNutritionAnalysisResultBinding
import com.example.edamatest.ui.recipe_search.result_flow.REQUEST_DATA
import com.example.edamatest.ui.recipe_search.result_flow.RequestModel
import com.example.edamatest.ui.showErrorAlertDialog
import com.example.edamatest.ui.showSuccessAlertDialog

const val NUTRITION_ANALYSIS_LIST_KEY = "NUTRITION_ANALYSIS_LIST_KEY"

class NutritionAnalysisResultActivity : AppCompatActivity() {


    private val binding: ActivityNutritionAnalysisResultBinding by lazy {
        ActivityNutritionAnalysisResultBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initToolbar()

        val request: ArrayList<String>? =
            intent.getStringArrayListExtra(NUTRITION_ANALYSIS_LIST_KEY)
        if (request != null) {
//            var requestString = ""
//            for (item in request) {
//                requestString += "\n$item"
//            }
//            showSuccessAlertDialog("Parcelable data $requestString") { finish() }
        } else {
            showErrorAlertDialog("Parcelable data null") { finish() }
        }

    }

    private fun initToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = ""
        binding.toolbarBack.setOnClickListener { finish() }
    }
}