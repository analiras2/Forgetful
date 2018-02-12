package br.com.ascal.forgetful.presentation.dashboard

import br.com.ascal.forgetful.data.AppDatabase
import br.com.ascal.forgetful.data.entity.Item

interface MainContract {

    interface View {
        fun showOptionsDialog()
        fun showExcludeConfirmation(itemTitle: String?)
        fun showItems(items: List<Item>)
        fun goToNewItem()
        fun goToEditItem(itemId: Long)
    }

    interface Presenter {
        fun attachView(view: View, database: AppDatabase)
        fun detachView()
        fun onItemClicked(item: Item)
        fun onEditClicked()
        fun onExcludeClicked()
        fun onExcludeConfirmationClicked()
        fun onAddClicked()
    }
}