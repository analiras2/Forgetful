package br.com.ascal.forgetful.presentation.util

import android.view.View

fun View.visible(visible: Boolean) {
    if (visible) this.visibility = View.VISIBLE
    else this.visibility = View.GONE
}