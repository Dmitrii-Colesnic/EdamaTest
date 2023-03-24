package com.example.edamatest.ui.nutrition_analysis

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.edamatest.R
import com.example.edamatest.databinding.EditTextLayoutBinding
import com.example.edamatest.databinding.FragmentNutritionAnalysisBinding

class NutritionAnalysisFragment : Fragment() {

    private var _binding: FragmentNutritionAnalysisBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel = ViewModelProvider(this)[NutritionAnalysisViewModel::class.java]

        _binding = FragmentNutritionAnalysisBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}