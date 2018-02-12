package br.com.ascal.forgetful.presentation.dashboard

import android.app.AlertDialog
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import br.com.ascal.forgetful.ForgetfulApplication
import br.com.ascal.forgetful.R
import br.com.ascal.forgetful.data.entity.Item
import br.com.ascal.forgetful.presentation.util.BaseActivity
import br.com.ascal.forgetful.presentation.util.Navigator
import br.com.ascal.forgetful.presentation.util.SimpleDividerItemDecoration
import br.com.ascal.forgetful.presentation.util.visible
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity(), MainContract.View {

    private lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = MainPresenter()
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(SimpleDividerItemDecoration(this))
    }

    override fun onStart() {
        super.onStart()
        presenter.attachView(this, (application as ForgetfulApplication).getDatabase())
    }

    override fun onStop() {
        super.onStop()
        presenter.detachView()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.dashboard, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_add -> {
            presenter.onAddClicked()
            true
        }
        else -> super.onOptionsItemSelected(item)

    }

    override fun showOptionsDialog() {
        AlertDialog.Builder(this)
                .setTitle(R.string.activity_main_options_dialog_title)
                .setItems(R.array.options_dialog, { _, w ->
                    if (w == 0) {
                        presenter.onEditClicked()
                    } else presenter.onExcludeClicked()
                }).show()
    }

    override fun showExcludeConfirmation(itemTitle: String?) {
        val msg = resources.getString(R.string.activity_main_exclude_confirmation, itemTitle)
        AlertDialog.Builder(this)
                .setMessage(msg)
                .setPositiveButton(R.string.global_yes, { _, _ ->
                    presenter.onExcludeConfirmationClicked()
                })
                .setNegativeButton(R.string.global_no, { _, _ -> })
                .show()
    }

    override fun showItems(items: List<Item>) {
        emptyContainer.visible(false)
        recyclerView.visible(true)
        recyclerView.adapter = MainAdapter(this, items) {
            presenter.onItemClicked(it)
        }
    }

    override fun goToNewItem() {
        Navigator.goToNewItem(this)
    }

    override fun goToEditItem(itemId: Long) {
        Navigator.goToNewItem(this, itemId)
    }
}