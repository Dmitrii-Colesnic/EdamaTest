package com.example.edamatest.ui.recipe_search

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.addTextChangedListener
import com.example.edamatest.databinding.WriteRangeAlertBinding

fun Context.writeRangeAlert(serverRangeValue: String = "", returnValue: (String) -> Unit) {
    val builder = AlertDialog.Builder(this)
    val binding = WriteRangeAlertBinding.inflate(LayoutInflater.from(this))
    builder.setView(binding.root)

    var valueMin = ""
    var valueMax = ""

    if (serverRangeValue.isNotEmpty()) {
        val dashIndex = serverRangeValue.indexOf("-")
        if (serverRangeValue.contains("+")) {
            // serverRangeValue = MIN+
            valueMin =
                serverRangeValue.substring(
                    startIndex = 0,
                    endIndex = serverRangeValue.length - 1
                )
        } else if (serverRangeValue.contains("-")) {
            // serverRangeValue = MIN-MAX
            val dashIndex = serverRangeValue.indexOf("-")
            valueMin =
                serverRangeValue.substring(
                    startIndex = 0,
                    endIndex = dashIndex
                )
            valueMax =
                serverRangeValue.substring(
                    startIndex = dashIndex + 1,
                    endIndex = serverRangeValue.length
                )
        } else {
            // serverRangeValue = MAX
            valueMax = serverRangeValue
        }
    }

    binding.editTextValueMin.setText(valueMin)
    binding.editTextValueMin.addTextChangedListener {
        valueMin = it.toString()
    }

    binding.editTextValueMax.setText(valueMax)
    binding.editTextValueMax.addTextChangedListener {
        valueMax = it.toString()
    }

    val alertDialog: AlertDialog = builder.create()
    alertDialog.apply {
        setCancelable(true)
        show()
    }

    //todo: have to handle by "0"
    binding.btmApply.setOnClickListener {
        if (valueMin.isNotEmpty() && valueMax.isNotEmpty()) {
            if (valueMin.toInt() >= valueMax.toInt()) {
                binding.textViewError.visibility = View.VISIBLE
            } else {
                returnValue.invoke("$valueMin-$valueMax")
                alertDialog.cancel()
            }
        } else if (valueMin.isNotEmpty()) {
            returnValue.invoke("$valueMin+")
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

