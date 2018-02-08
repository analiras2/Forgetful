package br.com.ascal.forgetful.login

import br.com.ascal.forgetful.login.LoginContract.Companion.PIN_CODE_DEFAULT

class LoginPresenter : LoginContract.Presenter {

    private var view: LoginContract.View? = null

    override fun attachView(view: LoginContract.View) {
        this.view = view
    }

    override fun detachView() {
        view = null
    }

    override fun onEnterClicked(pinCode: String) {
        validatePinCode(pinCode)
    }

    private fun validatePinCode(pinCode: String) {
        when {
            pinCode.isEmpty() -> view?.showEmptyError()
            pinCode != PIN_CODE_DEFAULT -> view?.showInvalidPinError()
            else -> view?.goToDashboard()
        }
    }
}