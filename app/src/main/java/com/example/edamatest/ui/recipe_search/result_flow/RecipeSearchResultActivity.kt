package com.example.edamatest.ui.recipe_search.result_flow

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.edamatest.databinding.ActivityResipeSearchResultBinding
import com.example.edamatest.ui.launchAndCollectWithLifecycle
import com.example.edamatest.ui.showErrorAlertDialog
import org.koin.androidx.viewmodel.ext.android.viewModel

class RecipeSearchResultActivity : AppCompatActivity() {

    private val binding: ActivityResipeSearchResultBinding by lazy {
        ActivityResipeSearchResultBinding.inflate(layoutInflater)
    }

    private val viewModel by viewModel<RecipeSearchResultViewModel>()

    private val adapter = RecipeSearchResultRecyclerViewAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initToolbar()

        val request = intent.getParcelableExtra<RequestModel>("REQUEST_DATA")
        if (request != null) {
            viewModel.getRecipeList(request)
        } else {
            showErrorAlertDialog("Parcelable data null") { finish() }
        }

        binding.recyclerView.adapter = adapter
        addRecyclerViewScrollListener(binding.recyclerView)

        observeViewModel()
    }

    private fun initToolbar() {
        setSupportActionBar(binding.toolbar)
        binding.toolbarBack.setOnClickListener { finish() }
        supportActionBar?.title = ""
    }

    private fun observeViewModel() {
        viewModel.recipeSearchResult.launchAndCollectWithLifecycle(lifecycleOwner = this) {
            when {
                it.isNotEmpty() -> {
                    adapter.differ.submitList(it)
                    showLoadView = false
                }
                else -> {
                    showErrorAlertDialog(
                        message = "OOPS:0\n " +
                                "We can't find anything based on provided information\n" +
                                "Change filters and try again"
                    ) { finish() }
                }
            }
        }
        viewModel.responseError.launchAndCollectWithLifecycle(lifecycleOwner = this) {
            showErrorAlertDialog(message = "Response Error \n$it") { finish() }
        }
        viewModel.responseException.launchAndCollectWithLifecycle(lifecycleOwner = this) {
            showErrorAlertDialog(message = "Response Exception \n$it") { finish() }
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
        viewModel.isLastPage.launchAndCollectWithLifecycle(lifecycleOwner = this) {
            if (it) {
                binding.progressBarPagination.visibility = View.GONE
                showLoadView = false
            }
        }
    }

    private var showLoadView = false
    private val visibleThreshold = 2
    private var lastVisibleItem = 0
    private var totalItemCount = 0
    private fun addRecyclerViewScrollListener(recyclerView: RecyclerView) {
        val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager?
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                totalItemCount = linearLayoutManager!!.itemCount
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition()
                if (!showLoadView && totalItemCount <= lastVisibleItem + visibleThreshold) {
                    viewModel.getRecipeListNext()
                    binding.progressBarPagination.visibility = View.VISIBLE
                    showLoadView = true
                }
            }
        })
    }
}