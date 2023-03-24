package com.example.edamatest.ui.nutrition_analysis

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import com.example.edamatest.databinding.EditTextLayoutBinding


//private fun setListeners() {
//    binding.fabAdd.setOnClickListener {
//        createNewTextView()
//    }
//}
//
//private fun createNewTextView() {
//    handlePreviousTextView()
//    createTextView()
//}
//
//private fun handlePreviousTextView() {
//    val itemsCount = binding.scrollViewLayout.childCount
//
//    if (itemsCount > 1) {
//        val lastChild = binding.scrollViewLayout.getChildAt(itemsCount - 1)
//
//        val frameLayoutButton = lastChild.findViewById<FrameLayout>(R.id.frame_layout_button)
//        makeLayoutClickable(frameLayoutButton as FrameLayout, false)
//
//        val editText = lastChild.findViewById<EditText>(R.id.textInputEditText)
//        editText.addTextChangedListener {
//            val newItemsCount = binding.scrollViewLayout.childCount
//            val newLastChild = binding.scrollViewLayout.getChildAt(newItemsCount - 1)
//            val newEditText = newLastChild.findViewById<EditText>(R.id.textInputEditText)
//            if (newEditText.text.toString().isNotEmpty()) {
//                makeFabAddClickable(true)
//            } else {
//                makeFabAddClickable(false)
//            }
//        }
//    }
//}
//
//private fun createTextView() {
//    val view = EditTextLayoutBinding.inflate(LayoutInflater.from(activity))
//
//    makeFabAddClickable(false)
//
//    view.textInputEditText.addTextChangedListener {
//        if (it.toString().isNotEmpty()) {
//            makeFabAddClickable(true)
//        } else {
//            makeFabAddClickable(false)
//        }
//    }
//
//    view.frameLayoutButton.setOnClickListener {
//        removeView()
//    }
//
//    binding.scrollViewLayout.addView(view.root)
//}
//
//private fun makeFabAddClickable(boolean: Boolean) {
//    if (boolean) {
//        binding.fabAdd.apply {
//            backgroundTintList =
//                ColorStateList.valueOf(
//                    ContextCompat.getColor(
//                        requireActivity(),
//                        R.color.yellow_dark_dark_custom
//                    )
//                )
//            isClickable = true
//        }
//    } else {
//        binding.fabAdd.apply {
//            backgroundTintList =
//                ColorStateList.valueOf(
//                    ContextCompat.getColor(
//                        requireActivity(),
//                        R.color.gray
//                    )
//                )
//            isClickable = false
//        }
//    }
//}
//
//private fun makeLayoutClickable(frameLayout: FrameLayout, boolean: Boolean) {
//    if (boolean) {
//        frameLayout.apply {
//            background =
//                ContextCompat.getDrawable(requireActivity(), R.drawable.bg_red_circle_ripple)
//            isFocusable = true
//            isClickable = true
//            elevation = 5f
//            setOnClickListener {
//                removeView()
//            }
//        }
//    } else {
//        frameLayout.apply {
//            background = ContextCompat.getDrawable(requireActivity(), R.drawable.bg_gray_circle)
//            isFocusable = false
//            isClickable = false
//            elevation = 0f
//        }
//    }
//}
//
//private fun removeView() {
//    val itemsCount = binding.scrollViewLayout.childCount
//    val lastChild = binding.scrollViewLayout.getChildAt(itemsCount - 1)
////        lastChild.findViewById<EditText>(R.id.textInputEditText).requestFocus()
//    binding.scrollViewLayout.removeViewAt(itemsCount - 1)
//
//    val frameLayoutBtn = lastChild.findViewById<FrameLayout>(R.id.frame_layout_button)
//    makeLayoutClickable(frameLayoutBtn, true)
//}
