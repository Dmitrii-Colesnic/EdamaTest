package com.example.edamatest.ui.nutrition_analysis.result_flow

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.edamatest.databinding.ActivityNutritionAnalysisResultBinding
import com.example.edamatest.ui.StateFlowStatus
import com.example.edamatest.ui.launchAndCollectWithLifecycle
import com.example.edamatest.ui.nutrition_analysis.adapter.NutritionAnalysisRecyclerViewAdapter
import com.example.edamatest.ui.nutrition_analysis.model.NutritionAnalysisModel
import com.example.edamatest.ui.showErrorAlertDialog
import org.koin.androidx.viewmodel.ext.android.viewModel

const val NUTRITION_ANALYSIS_LIST_KEY = "NUTRITION_ANALYSIS_LIST_KEY"

class NutritionAnalysisResultActivity : AppCompatActivity() {

    private val binding: ActivityNutritionAnalysisResultBinding by lazy(mode = LazyThreadSafetyMode.NONE) {
        ActivityNutritionAnalysisResultBinding.inflate(layoutInflater)
    }

    private val viewModel by viewModel<NutritionAnalysisResultViewModel>()

    private val recyclerViewAdapter = NutritionAnalysisRecyclerViewAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initToolbar()
        observeViewModelsFlow()

        intent.getStringArrayListExtra(NUTRITION_ANALYSIS_LIST_KEY).let { products ->
            if (products != null) {
                viewModel.getNutritionAnalysis(products)
            } else {
                showErrorAlertDialog("Update entered data and try again") { finish() }
            }
        }

        binding.recyclerView.adapter = recyclerViewAdapter
    }

    private fun initToolbar() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = ""
        binding.toolbarBack.setOnClickListener { finish() }
    }

    private fun observeViewModelsFlow() {

        viewModel.nutritionAnalysis.launchAndCollectWithLifecycle(lifecycleOwner = this) {
            when (it) {
                is StateFlowStatus.Active -> {
                    setNutritionFacts(it.model)
                }
                is StateFlowStatus.Empty -> {}
            }
        }

        viewModel.ingredients.launchAndCollectWithLifecycle(lifecycleOwner = this) {
            recyclerViewAdapter.differUtil.submitList(it)
        }

        viewModel.responseError.launchAndCollectWithLifecycle(lifecycleOwner = this) {
            showErrorAlertDialog("${it.responseMessage} \n\n Response code = ${it.responseCode}") { finish() }
        }
        viewModel.responseException.launchAndCollectWithLifecycle(lifecycleOwner = this) {
            showErrorAlertDialog("Request exception \n ${it.exceptionMessage}") { finish() }
        }
        viewModel.loadingEvent.launchAndCollectWithLifecycle(lifecycleOwner = this) {
            if (it) {
                binding.loadingView.visibility = View.VISIBLE
            } else {
                binding.loadingView.visibility = View.GONE
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setNutritionFacts(model: NutritionAnalysisModel) {
        binding.layoutNutritionFacts.visibility = View.VISIBLE

        binding.tvCaloriesValue.text = model.ENERC_KCAL.quantity.toString()

        //Fat
        binding.tvTotalFatWeight.text =
            "${model.totalNutrients.FAT.quantity} ${model.totalNutrients.FAT.unit}"
        binding.tvTotalFatPercent.text =
            "${model.totalDaily.FAT.quantity} ${model.totalDaily.FAT.unit}"
        binding.tvSaturatedFatWeight.text =
            "${model.totalNutrients.FASAT.quantity} ${model.totalNutrients.FASAT.unit}"
        binding.tvSaturatedFatPercent.text =
            "${model.totalDaily.FASAT.quantity} ${model.totalDaily.FASAT.unit}"
        binding.tvTransFatWeight.text =
            "${model.totalNutrients.FATRN.quantity} ${model.totalNutrients.FATRN.unit}"

        //cholesterol
        binding.tvCholesterolWeight.text =
            "${model.totalNutrients.CHOLE.quantity} ${model.totalNutrients.CHOLE.unit}"
        binding.tvCholesterolPercent.text =
            "${model.totalDaily.CHOLE.quantity} ${model.totalDaily.CHOLE.unit}"

        //sodium
        binding.tvSodiumWeight.text =
            "${model.totalNutrients.NA.quantity} ${model.totalNutrients.NA.unit}"
        binding.tvSodiumPercent.text = "${model.totalDaily.NA.quantity} ${model.totalDaily.NA.unit}"

        //Total Carbohydrate
        binding.tvCarbohydrateWeight.text =
            "${model.totalNutrients.CHOCDF.quantity} ${model.totalNutrients.CHOCDF.unit}"
        binding.tvCarbohydratePercent.text =
            "${model.totalDaily.CHOCDF.quantity} ${model.totalDaily.CHOCDF.unit}"

        //Dietary Fiber
        binding.tvDietaryFiberWeight.text =
            "${model.totalNutrients.FIBTG.quantity} ${model.totalNutrients.FIBTG.unit}"
        binding.tvDietaryFiberPercent.text =
            "${model.totalDaily.FIBTG.quantity} ${model.totalDaily.FIBTG.unit}"

        //Total Sugars
        binding.tvTotalSugarWeight.text =
            "${model.totalNutrients.SUGAR.quantity} ${model.totalNutrients.SUGAR.unit}"

        //Protein
        binding.tvProteinWeight.text =
            "${model.totalNutrients.PROCNT.quantity} ${model.totalNutrients.PROCNT.unit}"
        binding.tvProteinPercent.text =
            "${model.totalDaily.PROCNT.quantity} ${model.totalDaily.PROCNT.unit}"

        //Vitamin D
        binding.tvVitaminDWeight.text =
            "${model.totalNutrients.VITD.quantity} ${model.totalNutrients.VITD.unit}"
        binding.tvVitaminDPercent.text =
            "${model.totalDaily.VITD.quantity} ${model.totalDaily.VITD.unit}"
        //Calcium
        binding.tvCalciumWeight.text =
            "${model.totalNutrients.CA.quantity} ${model.totalNutrients.CA.unit}"
        binding.tvCalciumPercent.text =
            "${model.totalDaily.CA.quantity} ${model.totalDaily.CA.unit}"
        //Iron
        binding.tvIronWeight.text =
            "${model.totalNutrients.FE.quantity} ${model.totalNutrients.FE.unit}"
        binding.tvIronPercent.text = "${model.totalDaily.FE.quantity} ${model.totalDaily.FE.unit}"
        //Potassium
        binding.tvProteinWeight.text =
            "${model.totalNutrients.K.quantity} ${model.totalNutrients.K.unit}"
        binding.tvProteinPercent.text = "${model.totalDaily.K.quantity} ${model.totalDaily.K.unit}"

    }
}