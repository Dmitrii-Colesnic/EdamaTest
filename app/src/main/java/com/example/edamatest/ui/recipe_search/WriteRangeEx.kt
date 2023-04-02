package com.example.edamatest.ui.recipe_search

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.addTextChangedListener
import com.example.edamatest.databinding.WriteRangeAlertBinding

fun Context.writeRangeAlert(returnValue: (String) -> Unit) {
    val builder = AlertDialog.Builder(this)
    val binding = WriteRangeAlertBinding.inflate(LayoutInflater.from(this))
    builder.setView(binding.root)

    var valueMin = ""
    binding.editTextValueMin.addTextChangedListener {
        valueMin = it.toString()
    }

    var valueMax = ""
    binding.editTextValueMax.addTextChangedListener {
        valueMax = it.toString()
    }

    val alertDialog: AlertDialog = builder.create()
    alertDialog.apply {
        setCancelable(true)
        show()
    }

    binding.btmApply.setOnClickListener {
        if (valueMin.isNotEmpty() && valueMax.isNotEmpty()) {
            if(valueMin.toFloat() >= valueMax.toFloat()) {
                binding.textViewError.visibility = View.VISIBLE
            } else {
                returnValue.invoke("$valueMin - $valueMax")
                alertDialog.cancel()
            }
        } else if (valueMin.isNotEmpty()) {
            returnValue.invoke(valueMin)
            alertDialog.cancel()
        } else if (valueMax.isNotEmpty()) {
            returnValue.invoke(valueMax)
            alertDialog.cancel()
        } else {
            returnValue.invoke("")
            alertDialog.cancel()
        }
    }
}

