package br.com.ascal.forgetful.presentation.newitem

import android.os.Bundle
import br.com.ascal.forgetful.data.dao.ItemDao
import br.com.ascal.forgetful.data.entity.Item
import br.com.ascal.forgetful.presentation.newitem.NewItemContract.Companion.EXTRA_ITEM_ID
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class NewItemPresenter : NewItemContract.Presenter {

    private var view: NewItemContract.View? = null
    private lateinit var dao: ItemDao
    private var disposables = CompositeDisposable()
    private var item: Item? = null

    override fun attachView(view: NewItemContract.View, dao: ItemDao, bundle: Bundle) {
        this.view = view
        this.dao = dao
        val itemId = bundle.getLong(EXTRA_ITEM_ID)
        if (itemId != -1L) {
            val disposable = dao.findById(itemId)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        this.item = it
                        view.updateFields(it)
                    }, { onThrowable(it) })
            disposables.add(disposable)
        }
    }

    override fun detachView() {
        view = null
        disposables.clear()
    }

    override fun onSaveClicked(title: String, keyword: String) {
        if (title.isEmpty() && keyword.isEmpty()) view?.showEmptyError()
        else if (title.isEmpty()) view?.showTitleEmptyError()
        else if (keyword.isEmpty()) view?.showKeywordEmptyError()
        else {
            if (item == null) item = Item().data(title, keyword)
            else item!!.data(title, keyword)

            saveItem(item!!)
        }
    }

    private fun saveItem(item: Item) {
        val disposable = Completable.fromCallable { dao.insertAll(item) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ view?.showSaveSuccess() }, { onThrowable(it) })
        disposables.add(disposable)
    }

    private fun onThrowable(e: Throwable?) {
        view?.onUnknownError(e.toString())
    }
}