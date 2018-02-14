package br.com.ascal.forgetful.presentation.util

import android.content.Context
import android.view.View
import android.widget.Toast

fun View.visible(visible: Boolean) {
    if (visible) this.visibility = View.VISIBLE
    else this.visibility = View.GONE
}

fun Context.showToast(msgRes: Int) {
    Toast.makeText(this, msgRes, Toast.LENGTH_SHORT).show()
}

fun Context.showToast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}