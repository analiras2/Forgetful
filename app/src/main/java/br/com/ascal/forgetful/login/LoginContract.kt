package br.com.ascal.forgetful.login

interface LoginContract {

    companion object {
        val PIN_CODE_DEFAULT = "1402"
    }

    interface View {
        fun goToDashboard()
        fun showEmptyError()
        fun showInvalidPinError()
    }

    interface Presenter {
        fun attachView(view: View)
        fun detachView()
        fun onEnterClicked(pinCode: String)
    }

}