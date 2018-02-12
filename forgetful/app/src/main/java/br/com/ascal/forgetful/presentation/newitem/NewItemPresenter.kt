package br.com.ascal.forgetful.presentation.newitem

import android.os.Bundle
import br.com.ascal.forgetful.data.AppDatabase
import br.com.ascal.forgetful.data.entity.Item
import br.com.ascal.forgetful.presentation.newitem.NewItemContract.Companion.EXTRA_ITEM_ID


class NewItemPresenter : NewItemContract.Presenter {


    private var view: NewItemContract.View? = null
    private lateinit var database: AppDatabase

    override fun attachView(view: NewItemContract.View, database: AppDatabase, bundle: Bundle) {
        this.view = view
        this.database = database
        val itemId = bundle.getLong(EXTRA_ITEM_ID)
        if (itemId != -1L) {
            //TODO
        }
    }

    override fun detachView() {
        view = null
    }

    override fun onSaveClicked(title: String, keyword: String) {
        if (title.isEmpty() && keyword.isEmpty()) view?.showEmptyError()
        else if (title.isEmpty()) view?.showTitleEmptyError()
        else if (keyword.isEmpty()) view?.showKeywordEmptyError()
        else saveItem(title, keyword)
    }

    private fun saveItem(title: String, keyword: String) {
        val item = Item()
        item.title = title
        item.keyword = keyword
        //TODO

    }
}