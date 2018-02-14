package br.com.ascal.forgetful.presentation.newitem

import android.os.Bundle
import br.com.ascal.forgetful.data.dao.ItemDao
import br.com.ascal.forgetful.data.entity.Item


interface NewItemContract {

    companion object {
        val EXTRA_ITEM_ID = "extra_item"
    }

    interface View {
        fun updateFields(item: Item)
        fun showEmptyError()
        fun showTitleEmptyError()
        fun showKeywordEmptyError()
        fun showSaveSuccess()
        fun onUnknownError(error: String)
    }

    interface Presenter {
        fun attachView(view: View, dao: ItemDao, bundle: Bundle)
        fun detachView()
        fun onSaveClicked(title: String, keyword: String)
    }
}