package br.com.ascal.forgetful.presentation.dashboard

import br.com.ascal.forgetful.data.AppDatabase
import br.com.ascal.forgetful.data.entity.Item
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainPresenter : MainContract.Presenter {

    private var view: MainContract.View? = null
    private lateinit var item: Item
    private lateinit var database: AppDatabase

    override fun attachView(view: MainContract.View, database: AppDatabase) {
        this.view = view
        this.database = database
        getItems()
    }

    private fun getItems() {
        database.itemDao().getAll()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    if (!it.isEmpty()) view?.showItems(it)
                }
    }

    override fun detachView() {
        view = null
    }

    override fun onItemClicked(item: Item) {
        this.item = item
        view?.showOptionsDialog()
    }

    override fun onEditClicked() {
        view?.goToEditItem(item.uid)
    }

    override fun onExcludeClicked() {
        view?.showExcludeConfirmation(item.title)
    }

    override fun onExcludeConfirmationClicked() {
        //TODO)
    }

    override fun onAddClicked() {
        view?.goToNewItem()
    }
}