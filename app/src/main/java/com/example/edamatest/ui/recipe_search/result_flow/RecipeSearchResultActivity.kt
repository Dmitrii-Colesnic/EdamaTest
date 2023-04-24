package com.example.edamatest.ui.recipe_search.result_flow

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.edamatest.databinding.ActivityResipeSearchResultBinding
import com.example.edamatest.databinding.RecipeSearchResultDetailsBinding
import com.example.edamatest.ui.launchAndCollectWithLifecycle
import com.example.edamatest.ui.recipe_search.loadPictures
import com.example.edamatest.ui.showErrorAlertDialog
import org.koin.androidx.viewmodel.ext.android.viewModel

const val REQUEST_DATA = "REQUEST_DATA"
class RecipeSearchResultActivity : AppCompatActivity() {

    private val binding: ActivityResipeSearchResultBinding by lazy {
        ActivityResipeSearchResultBinding.inflate(layoutInflater)
    }

    private val viewModel by viewModel<RecipeSearchResultViewModel>()

    private val adapter =
        RecipeSearchResultRecyclerViewAdapter(onItemClickListener = this::onItemClick)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initToolbar()

        val request = intent.getParcelableExtra<RequestModel>(REQUEST_DATA)
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
                    binding.progressBarPagination.visibility = View.GONE
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
        viewModel.loadingMoreItemsEvent.launchAndCollectWithLifecycle(lifecycleOwner = this) {
            if (it) {
                binding.progressBarPagination.visibility = View.VISIBLE
                showLoadView = true
            } else {
                binding.progressBarPagination.visibility = View.GONE
                showLoadView = false
            }
        }
    }

    private fun onItemClick(item: RecipeSearchResultItem) {
        itemDialog(item = item)
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

private fun Context.itemDialog(item: RecipeSearchResultItem) {
    val builder = AlertDialog.Builder(this)
    val binding = RecipeSearchResultDetailsBinding.inflate(LayoutInflater.from(this))
    builder.setView(binding.root)

    binding.tvTitle.text = item.label
    binding.image.loadPictures(item.image)
    binding.ingredients.text = item.ingredients.mapToString()
    binding.btnGoToRecipe.setOnClickListener {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(item.url)))
    }

    val alertDialog = builder.create()
    alertDialog.setCancelable(true)
    alertDialog.show()
}

private fun List<String>.mapToString(): String {
    var result = ""
    for (item in this) {
        result += "$item\n"
    }
    return result
}
