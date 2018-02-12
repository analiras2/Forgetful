package br.com.ascal.forgetful.presentation.util

import android.content.Context
import android.content.Intent
import br.com.ascal.forgetful.presentation.dashboard.MainActivity
import br.com.ascal.forgetful.presentation.login.LoginActivity
import br.com.ascal.forgetful.presentation.newitem.NewItemActivity
import br.com.ascal.forgetful.presentation.newitem.NewItemContract.Companion.EXTRA_ITEM_ID

class Navigator {

    companion object {

        fun goToLogin(context: Context) = context.startActivity(Intent(context, LoginActivity::class.java))

        fun goToDashboard(context: Context) = context.startActivity(Intent(context, MainActivity::class.java))

        fun goToNewItem(context: Context, itemId: Long = -1L) = context.startActivity(Intent(context, NewItemActivity::class.java)
                .putExtra(EXTRA_ITEM_ID, itemId))
    }
}