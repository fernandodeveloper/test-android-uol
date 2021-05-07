package br.com.fernandodeveloper.events.extensions

import android.graphics.Color
import android.graphics.drawable.ColorDrawable

fun androidx.appcompat.app.AlertDialog.setBackgroundTransparency() {
    this.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
}
