package com.example.edamatest.ui.recipe_search.result_flow

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.edamatest.R
import com.example.edamatest.databinding.ActivityResipeSearchResultBinding
import com.example.edamatest.openActivity

class RecipeSearchResultActivity : AppCompatActivity() {

    private val binding: ActivityResipeSearchResultBinding by lazy {
        ActivityResipeSearchResultBinding.inflate(layoutInflater)
    }

    val viewModel by viewModels<RecipeSearchResultViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val request = intent.getParcelableExtra<RequestModel>("request")

    }

}