package br.com.fernandodeveloper.events.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import br.com.fernandodeveloper.events.R

fun AppCompatActivity.createsGenericDialog(layout: Int): AlertDialog {
    val dialogView: View = LayoutInflater.from(this)
        .inflate(layout, window?.decorView as ViewGroup, false)
    val alert: AlertDialog = AlertDialog.Builder(this)
        .setView(dialogView)
        .show()
    alert.setCanceledOnTouchOutside(false)
    return alert
}

fun AppCompatActivity.createsErrorDialog(textId: Int): AlertDialog {
    val alert = createsGenericDialog(R.layout.dialog_error)
    alert.findViewById<TextView>(R.id.txt_error_message)?.text = getString(textId)
    alert.findViewById<ImageView>(R.id.img_btn_close)?.setOnClickListener { alert.dismiss() }
    alert.findViewById<Button>(R.id.btn_close)?.setOnClickListener { alert.dismiss() }
    return alert
}

fun AppCompatActivity.createsSuccessDialog(
    textId: Int
): AlertDialog {
    val alert = createsGenericDialog(R.layout.dialog_success)
    alert.findViewById<TextView>(R.id.txt_success_message)?.text = getString(textId)
    alert.findViewById<ImageView>(R.id.img_btn_close)?.setOnClickListener { finish() }
    alert.findViewById<Button>(R.id.btn_close)?.setOnClickListener { finish() }
    return alert
}

fun AppCompatActivity.createsLoadingDialog(): AlertDialog {
    val alert = createsGenericDialog(R.layout.dialog_loading)
    alert.setBackgroundTransparency()
    return alert
}
