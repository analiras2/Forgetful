package br.com.ascal.forgetful.presentation.landing

import android.os.Bundle
import br.com.ascal.forgetful.presentation.util.BaseActivity
import br.com.ascal.forgetful.presentation.util.Navigator

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Navigator.goToLogin(this)
        finish()
    }
}
