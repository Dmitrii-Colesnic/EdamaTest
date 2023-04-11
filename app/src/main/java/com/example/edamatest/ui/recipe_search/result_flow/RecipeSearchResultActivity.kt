package com.example.edamatest.ui.recipe_search.result_flow

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.edamatest.databinding.ActivityResipeSearchResultBinding
import com.example.edamatest.ui.launchAndCollectWithLifecycle
import com.example.edamatest.ui.showErrorAlertDialog

class RecipeSearchResultActivity : AppCompatActivity() {

    private val binding: ActivityResipeSearchResultBinding by lazy {
        ActivityResipeSearchResultBinding.inflate(layoutInflater)
    }

    private val viewModel by viewModels<RecipeSearchResultViewModel>()

    private val adapter = RecipeSearchResultRecyclerViewAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val request = intent.getParcelableExtra<RequestModel>("REQUEST_DATA")
//        if (request != null) {
//            viewModel.getRecipeList(request)
//        } else {
//            finish()
//            showErrorAlertDialog("Parcelable data null")
//        }
//
//        binding.recyclerView.adapter = adapter
//
//        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.recipeSearchResult.launchAndCollectWithLifecycle(lifecycleOwner = this) {
            adapter.differ.submitList(it)
        }
        viewModel.responseError.launchAndCollectWithLifecycle(lifecycleOwner = this) {
            showErrorAlertDialog(it)
            finish()
        }
        viewModel.responseException.launchAndCollectWithLifecycle(lifecycleOwner = this) {
            showErrorAlertDialog(it)
            finish()
        }
        viewModel.loadingEvent.launchAndCollectWithLifecycle(lifecycleOwner = this) {
            if (it) {
                binding.recyclerView.visibility = View.GONE
                binding.progressBarLoadingData.visibility = View.VISIBLE
            } else {
                binding.recyclerView.visibility = View.VISIBLE
                binding.progressBarLoadingData.visibility = View.GONE
            }
        }
    }
}