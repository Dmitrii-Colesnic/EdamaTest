package com.example.edamatest

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat

fun Context.showSuccessAlertDialog(message: String?) {
    val builder = AlertDialog.Builder(this)
    val customView = LayoutInflater.from(this).inflate(R.layout.alert_dialog, null)
    builder.setView(customView)

    customView.findViewById<ImageView>(R.id.image).setImageDrawable(ContextCompat.getDrawable(this, R.drawable.baseline_check_mark_outline_24))
    customView.findViewById<TextView>(R.id.title).text = message

    val alertDialog: AlertDialog = builder.create()
    alertDialog.setCancelable(false)
    alertDialog.show()
}

fun Context.showErrorAlertDialog(message: String?) {
    val builder = AlertDialog.Builder(this)
    val customView = LayoutInflater.from(this).inflate(R.layout.alert_dialog, null)
    builder.setView(customView)

    customView.findViewById<ImageView>(R.id.image).setImageDrawable(ContextCompat.getDrawable(this, R.drawable.baseline_backspace_24))
    customView.findViewById<TextView>(R.id.title).text = message

    val alertDialog: AlertDialog = builder.create()
    alertDialog.setCancelable(false)
    alertDialog.show()
}