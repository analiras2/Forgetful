package br.com.ascal.forgetful.dashboard

interface MainContract {

    interface View

    interface Presenter {
        fun attachView(view: View)
        fun detachView()
    }
}