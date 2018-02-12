package br.com.ascal.forgetful.presentation.util

import android.app.Activity
import android.view.WindowManager

object KeyboardController {

    fun show(activity: Activity) {
        activity.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)
    }

    fun hide(activity: Activity) {
        activity.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
    }
}