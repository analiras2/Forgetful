package br.com.ascal.forgetful.dashboard

import android.app.AlertDialog
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import br.com.ascal.forgetful.R
import br.com.ascal.forgetful.base.BaseActivity
import br.com.ascal.forgetful.util.SimpleDividerItemDecoration
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity(), MainContract.View {

    private lateinit var presenter: MainContract.Presenter
    private lateinit var mainAdapter: MainAdapter

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
        presenter.attachView(this)
        val tempList = listOf("item 1", "item 2", "item 3")
        mainAdapter = MainAdapter(this, tempList) {
            presenter.onItemClicked(it)
        }
        recyclerView.adapter = mainAdapter
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
            Toast.makeText(this, "Add novo item", Toast.LENGTH_SHORT).show()
            true
        }
        else -> super.onOptionsItemSelected(item)

    }

    override fun showOptionsDialog() {
        AlertDialog.Builder(this)
                .setTitle(R.string.activity_main_options_dialog_title)
                .setItems(R.array.options_dialog, { _, w ->
                    if (w == 0) {
//                        presenter.onEditClicked()
                        Toast.makeText(this, "Editar", Toast.LENGTH_SHORT).show()
                    } else presenter.onExcludeClicked()
                }).show()
    }

    override fun showExcludeConfirmation(itemTitle: String) {
        val msg = resources.getString(R.string.activity_main_exclude_confirmation, itemTitle)
        AlertDialog.Builder(this)
                .setMessage(msg)
                .setPositiveButton(R.string.global_yes, { _, _ ->
                    //                    presenter.onExcludeConfirmationClicked()
                    Toast.makeText(this, "Excluir", Toast.LENGTH_SHORT).show()

                })
                .setNegativeButton(R.string.global_no, { _, _ -> })
                .show()
    }
}