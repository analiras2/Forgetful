package br.com.ascal.forgetful.dashboard

interface MainContract {

    interface View {
        fun showOptionsDialog()
        fun showExcludeConfirmation(itemTitle: String)
    }

    interface Presenter {
        fun attachView(view: View)
        fun detachView()
        fun onItemClicked(item: String)
        fun onEditClicked()
        fun onExcludeClicked()
        fun onExcludeConfirmationClicked()
        fun onAddClicked()
    }
}