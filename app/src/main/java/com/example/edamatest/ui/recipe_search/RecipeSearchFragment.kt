package com.example.edamatest.ui.recipe_search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.edamatest.databinding.FragmentRecipeSearchBinding
import com.example.edamatest.openActivity
import com.example.edamatest.ui.launchAndCollectWithLifecycle
import com.example.edamatest.ui.recipe_search.adapter.DietsRecyclerViewAdapter
import com.example.edamatest.ui.recipe_search.adapter.NutrientsModel
import com.example.edamatest.ui.recipe_search.adapter.NutrientsRecyclerViewAdapter
import com.example.edamatest.ui.recipe_search.result_flow.RecipeSearchResultActivity
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import org.koin.androidx.viewmodel.ext.android.viewModel

class RecipeSearchFragment : Fragment() {

    private var _binding: FragmentRecipeSearchBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModel<RecipeSearchViewModel>()

    private val macronutrientsRecyclerViewAdapter: NutrientsRecyclerViewAdapter by lazy {
        NutrientsRecyclerViewAdapter(
            onItemClick = this@RecipeSearchFragment::onItemClickMacro,
            onItemClearClick = this@RecipeSearchFragment::onItemClearClickMacro
        )
    }

    private val micronutrientsRecyclerViewAdapter: NutrientsRecyclerViewAdapter by lazy {
        NutrientsRecyclerViewAdapter(
            onItemClick = this@RecipeSearchFragment::onItemClickMicro,
            onItemClearClick = this@RecipeSearchFragment::onItemClearClickMicro
        )
    }

    private val healthRecyclerViewAdapter: DietsRecyclerViewAdapter by lazy {
        DietsRecyclerViewAdapter(
            onItemClick = this@RecipeSearchFragment::onItemClickHealth
        )
    }

    private val dietRecyclerViewAdapter: DietsRecyclerViewAdapter by lazy {
        DietsRecyclerViewAdapter(
            onItemClick = this@RecipeSearchFragment::onItemClickDiet
        )
    }

    private val _layoutManagerMacro: FlexboxLayoutManager by lazy {
        FlexboxLayoutManager(context).apply {
            flexDirection = FlexDirection.ROW
            justifyContent = JustifyContent.FLEX_START
        }
    }

    private val _layoutManagerMicro: FlexboxLayoutManager by lazy {
        FlexboxLayoutManager(context).apply {
            flexDirection = FlexDirection.ROW
            justifyContent = JustifyContent.FLEX_START
        }
    }

    private val _layoutManagerHealth: FlexboxLayoutManager by lazy {
        FlexboxLayoutManager(context).apply {
            flexDirection = FlexDirection.ROW
            justifyContent = JustifyContent.FLEX_START
        }
    }

    private val _layoutManagerDiet: FlexboxLayoutManager by lazy {
        FlexboxLayoutManager(context).apply {
            flexDirection = FlexDirection.ROW
            justifyContent = JustifyContent.FLEX_START
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecipeSearchBinding.inflate(inflater, container, false)

        binding.recyclerViewHealth.apply {
            layoutManager = _layoutManagerHealth
            adapter = healthRecyclerViewAdapter
        }
        viewModel.healthList.launchAndCollectWithLifecycle(viewLifecycleOwner) {
            healthRecyclerViewAdapter.differ.submitList(it)
        }

        binding.recyclerViewDiets.apply {
            layoutManager = _layoutManagerDiet
            adapter = dietRecyclerViewAdapter
        }
        viewModel.dietList.launchAndCollectWithLifecycle(viewLifecycleOwner) {
            dietRecyclerViewAdapter.differ.submitList(it)
        }

        binding.recyclerViewMacronutrients.apply {
            layoutManager = _layoutManagerMacro
            adapter = macronutrientsRecyclerViewAdapter
        }
        viewModel.macronutrientsList.launchAndCollectWithLifecycle(viewLifecycleOwner) {
            macronutrientsRecyclerViewAdapter.differ.submitList(it)
        }

        binding.recyclerViewMicronutrients.apply {
            layoutManager = _layoutManagerMicro
            adapter = micronutrientsRecyclerViewAdapter
        }
        viewModel.micronutrientsList.launchAndCollectWithLifecycle(viewLifecycleOwner) {
            micronutrientsRecyclerViewAdapter.differ.submitList(it)
        }

        binding.btnApply.setOnClickListener {
            submitDataToViewModel()

            if (editTextKeywordIsNotEmpty()) {
                viewModel.getCollectedData().let {
                    requireActivity().openActivity(RecipeSearchResultActivity::class.java) {
                        putParcelable("REQUEST_DATA", it)
                    }
                }
            }
        }


        return binding.root
    }

    private fun submitDataToViewModel() {
        viewModel.setCalories(
            min = try {
                binding.editTextCalorieRangeMin.text.toString().toInt()
            } catch (e: NumberFormatException) {
                0
            },
            max = try {
                binding.editTextCalorieRangeMax.text.toString().toInt()
            } catch (e: NumberFormatException) {
                0
            }
        )

        viewModel.setKeyWord(string = binding.editTextKeyword.toString())
    }

    private fun editTextKeywordIsNotEmpty(): Boolean {
        if (binding.editTextKeyword.text.toString().isEmpty()) {
            binding.nestedScrollView.scrollTo(0, 0)
            binding.tvKeywordError.visibility = View.VISIBLE
            return false
        }
        return true
    }

    private fun onItemClickHealth(itemPosition: Int, bool: Boolean) {
        viewModel.changeHealthListItemStatus(itemPosition = itemPosition, currentStatus = bool)
    }

    private fun onItemClickDiet(itemPosition: Int, bool: Boolean) {
        viewModel.changeDietListItemStatus(itemPosition = itemPosition, currentStatus = bool)
    }

    private fun onItemClickMacro(itemPosition: Int, item: NutrientsModel) {
        requireActivity().writeRangeAlert(
            valueMin = item.valueMin,
            valueMax = item.valueMax
        ) { min, max ->
            viewModel.setMacronutrientsItemRange(itemPosition = itemPosition, min = min, max = max)
        }
    }

    private fun onItemClearClickMacro(itemPosition: Int) {
        viewModel.clearMacronutrientsItemRange(itemPosition = itemPosition)
    }

    private fun onItemClickMicro(itemPosition: Int, item: NutrientsModel) {
        requireActivity().writeRangeAlert(
            valueMin = item.valueMin,
            valueMax = item.valueMax
        ) { min, max ->
            viewModel.setMicronutrientsItemRange(itemPosition = itemPosition, min = min, max = max)
        }
    }

    private fun onItemClearClickMicro(itemPosition: Int) {
        viewModel.clearMicronutrientsItemRange(itemPosition = itemPosition)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}