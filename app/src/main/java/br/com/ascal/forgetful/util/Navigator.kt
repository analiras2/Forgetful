package br.com.ascal.forgetful.util

import android.content.Context
import android.content.Intent
import br.com.ascal.forgetful.dash.MainActivity
import br.com.ascal.forgetful.login.LoginActivity

class Navigator {

    companion object {
        fun goToLogin(context: Context) {
            context.startActivity(Intent(context, LoginActivity::class.java))
        }

        fun goToDashboard(context: Context) {
            context.startActivity(Intent(context, MainActivity::class.java))
        }
    }
}
