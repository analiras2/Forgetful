package br.com.ascal.forgetful.login

import android.os.Bundle
import br.com.ascal.forgetful.R
import br.com.ascal.forgetful.base.BaseActivity
import br.com.ascal.forgetful.util.Navigator
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity(), LoginContract.View {

    private lateinit var presenter: LoginContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        presenter = LoginPresenter()
        enterButton.setOnClickListener { presenter.onEnterClicked(pinCodeEditText.text.toString()) }
    }

    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.detachView()
    }

    override fun goToDashboard() {
        Navigator.goToDashboard(this)
        finish()
    }

    override fun showEmptyError() {
        showError(resources.getString(R.string.activity_login_obligatory_field))
    }

    override fun showInvalidPinError() {
        showError(resources.getString(R.string.activity_login_invalid_pin_code))
    }

    private fun showError(msg: String) {
        pinCodeEditText.error = msg
    }
}