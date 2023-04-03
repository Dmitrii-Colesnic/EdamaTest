package com.example.edamatest.ui.recipe_search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.edamatest.databinding.FragmentRecipeSearchBinding
import com.example.edamatest.ui.launchAndCollectWithLifecycle
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import kotlinx.coroutines.launch

class RecipeSearchFragment : Fragment() {

    private var _binding: FragmentRecipeSearchBinding? = null
    private val binding get() = _binding!!

    private val viewModel: RecipeSearchViewModel by lazy {
        ViewModelProvider(this)[RecipeSearchViewModel::class.java]
    }

    private val nutrientsRecyclerViewAdapter: NutrientsRecyclerViewAdapter by lazy {
        NutrientsRecyclerViewAdapter(
            onItemClick = this@RecipeSearchFragment::onItemClick,
            onItemClearClick = this@RecipeSearchFragment::onItemClearClick
        )
    }

    private val _layoutManager: FlexboxLayoutManager by lazy {
        FlexboxLayoutManager(context).apply {
            flexDirection = FlexDirection.ROW
            justifyContent = JustifyContent.CENTER
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecipeSearchBinding.inflate(inflater, container, false)

        binding.recyclerViewMacronutrients.apply {
            layoutManager = _layoutManager
            adapter = nutrientsRecyclerViewAdapter
        }

        viewModel.macronutrientsList.launchAndCollectWithLifecycle(viewLifecycleOwner) {
            nutrientsRecyclerViewAdapter.differ.submitList(it)
        }

        return binding.root
    }

    private fun onItemClick(itemPosition: Int, rangeValue: String) {
        requireActivity().writeRangeAlert(serverRangeValue = rangeValue) {
            lifecycleScope.launch {
                viewModel.setMacronutrientsItemRange(itemPosition = itemPosition, value = it)
            }
        }
    }

    private fun onItemClearClick(itemPosition: Int) {
        viewModel.clearMacronutrientsItemRange(itemPosition = itemPosition)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}