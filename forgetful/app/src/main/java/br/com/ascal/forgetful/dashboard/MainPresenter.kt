package br.com.ascal.forgetful.dashboard

class MainPresenter : MainContract.Presenter {

    private var view: MainContract.View? = null
    private lateinit var item: String

    override fun attachView(view: MainContract.View) {
        this.view = view
    }

    override fun detachView() {
        view = null
    }

    override fun onItemClicked(item: String) {
        this.item = item
        view?.showOptionsDialog()
    }

    override fun onEditClicked() {
        //TODO
    }

    override fun onExcludeClicked() {
        view?.showExcludeConfirmation(item)
    }

    override fun onExcludeConfirmationClicked() {
        //TODO
    }

    override fun onAddClicked() {
        // TODO
    }
}