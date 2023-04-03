package com.example.edamatest.ui.recipe_search

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.example.edamatest.databinding.WriteRangeAlertBinding

fun Context.writeRangeAlert(valueMin: Int, valueMax: Int, returnValue: (Int, Int) -> Unit) {
    val builder = AlertDialog.Builder(this)
    val binding = WriteRangeAlertBinding.inflate(LayoutInflater.from(this))
    builder.setView(binding.root)

    if (valueMin != 0) {
        binding.editTextValueMin.setText(valueMin.toString())
    }
    if (valueMax != 0) {
        binding.editTextValueMax.setText(valueMax.toString())
    }

    val alertDialog: AlertDialog = builder.create()
    alertDialog.apply {
        setCancelable(true)
        show()
    }

    binding.btmApply.setOnClickListener {
        val etValueMin = try {
            binding.editTextValueMin.text.toString().toInt()
        } catch (e: NumberFormatException) {
            0
        }

        val etValueMax = try {
            binding.editTextValueMax.text.toString().toInt()
        } catch (e: NumberFormatException) {
            0
        }

        //handle error
        if ((etValueMin >= etValueMax  &&  etValueMax != 0)
            ||  etValueMin > 100
            ||  etValueMax > 100
        ) {
            binding.textViewError.visibility = View.VISIBLE
        } else {
            returnValue.invoke(etValueMin, etValueMax)
            alertDialog.cancel()
        }
    }
}

