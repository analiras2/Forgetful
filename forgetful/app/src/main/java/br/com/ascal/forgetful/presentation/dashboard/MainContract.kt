package br.com.ascal.forgetful.presentation.dashboard

import br.com.ascal.forgetful.data.dao.ItemDao
import br.com.ascal.forgetful.data.entity.Item

interface MainContract {

    interface View {
        fun showOptionsDialog()
        fun showExcludeConfirmation(itemTitle: String?)
        fun showItems(items: List<Item>)
        fun goToNewItem()
        fun goToEditItem(itemId: Long)
        fun onUnknownError(error: String)
    }

    interface Presenter {
        fun attachView(view: View, dao: ItemDao)
        fun detachView()
        fun onItemClicked(item: Item)
        fun onEditClicked()
        fun onExcludeClicked()
        fun onExcludeConfirmationClicked()
        fun onAddClicked()
    }
}