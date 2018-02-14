package br.com.ascal.forgetful.presentation.dashboard

import br.com.ascal.forgetful.data.dao.ItemDao
import br.com.ascal.forgetful.data.entity.Item
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainPresenter : MainContract.Presenter {

    private var view: MainContract.View? = null
    private lateinit var item: Item
    private lateinit var dao: ItemDao
    private var disposables = CompositeDisposable()

    override fun attachView(view: MainContract.View, dao: ItemDao) {
        this.view = view
        this.dao = dao
        getItems()
    }

    private fun getItems() {
        val disposable = dao.getAll()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ if (!it.isEmpty()) view?.showItems(it) }, { onThrowable(it) })

        disposables.add(disposable)
    }

    override fun detachView() {
        view = null
        disposables.clear()
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
        val disposable = Completable.fromCallable { dao.delete(item) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError { onThrowable(it) }
                .subscribe()
        disposables.add(disposable)
    }

    override fun onAddClicked() {
        view?.goToNewItem()
    }

    private fun onThrowable(e: Throwable?) {
        view?.onUnknownError(e.toString())
    }
}
