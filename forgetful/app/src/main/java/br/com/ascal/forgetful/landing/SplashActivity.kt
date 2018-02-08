package br.com.ascal.forgetful.landing

import android.os.Bundle
import br.com.ascal.forgetful.base.BaseActivity
import br.com.ascal.forgetful.util.Navigator

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Navigator.goToLogin(this)
        finish()
    }
}
