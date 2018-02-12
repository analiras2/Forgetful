package br.com.ascal.forgetful.presentation.newitem

import android.os.Bundle
import br.com.ascal.forgetful.data.AppDatabase
import br.com.ascal.forgetful.data.entity.Item


interface NewItemContract {

    companion object {
        val EXTRA_ITEM_ID = "extra_item"
    }

    interface View {
        fun populateFields(item: Item)
        fun showEmptyError()
        fun showTitleEmptyError()
        fun showKeywordEmptyError()
        fun showSaveSuccess()
    }

    interface Presenter {
        fun attachView(view: View, database: AppDatabase, bundle: Bundle)
        fun detachView()
        fun onSaveClicked(title: String, keyword: String)
    }
}