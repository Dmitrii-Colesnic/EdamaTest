package com.example.edamatest.ui.recipe_search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.edamatest.databinding.FragmentRecipeSearchBinding
import com.example.edamatest.ui.launchAndCollectWithLifecycle
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class RecipeSearchFragment : Fragment() {

    private var _binding: FragmentRecipeSearchBinding? = null
    private val binding get() = _binding!!

    private val viewModel: RecipeSearchViewModel by lazy {
        ViewModelProvider(this)[RecipeSearchViewModel::class.java]
    }

    val nutrientsRecyclerViewAdapter: NutrientsRecyclerViewAdapter by lazy {
        NutrientsRecyclerViewAdapter(
            list = viewModel.macronutrientsList.value!!,
            onItemClick = this@RecipeSearchFragment::onItemClick
        )
    }

    private val _layoutManager: FlexboxLayoutManager by lazy {
        FlexboxLayoutManager(context).apply {
            flexDirection = FlexDirection.ROW
            justifyContent = JustifyContent.FLEX_START
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

        lifecycleScope.launch(Dispatchers.Main) {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.macronutrientsList.collect() {
//                    nutrientsRecyclerViewAdapter.differ.submitList(it)
                    nutrientsRecyclerViewAdapter.setList(viewModel.macronutrientsList.value)
                }
            }
        }

//        viewModel.macronutrientsList.launchAndCollectWithLifecycle(viewLifecycleOwner) {
////            nutrientsRecyclerViewAdapter.differ.submitList(it)
//            nutrientsRecyclerViewAdapter.setList(viewModel.macronutrientsList.value)
//        }
//        viewModel.macronutrientsList.observe(viewLifecycleOwner) {
////            nutrientsRecyclerViewAdapter.differ.submitList(it)
//            nutrientsRecyclerViewAdapter.setList(viewModel.macronutrientsList.value!!)
//        }

        return binding.root
    }

    private fun onItemClick(item: NutrientsModel) {
        requireActivity().writeRangeAlert {
            lifecycleScope.launch {
                viewModel.changeMacronutrientsListElement(item = item, value = it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}