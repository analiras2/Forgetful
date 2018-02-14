package br.com.ascal.forgetful.presentation.newitem

import android.os.Bundle
import android.view.MenuItem
import br.com.ascal.forgetful.ForgetfulApplication
import br.com.ascal.forgetful.R
import br.com.ascal.forgetful.data.entity.Item
import br.com.ascal.forgetful.presentation.util.BaseActivity
import br.com.ascal.forgetful.presentation.util.showToast
import kotlinx.android.synthetic.main.activity_new_item.*

class NewItemActivity : BaseActivity(), NewItemContract.View {

    private lateinit var presenter: NewItemPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_item)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        presenter = NewItemPresenter()
        saveButton.setOnClickListener {
            presenter.onSaveClicked(titleEditText.text.toString(), keywordEditText.text.toString())
        }
    }

    override fun onStart() {
        super.onStart()
        presenter.attachView(this, (application as ForgetfulApplication).getDatabase().itemDao(), intent.extras)
    }

    override fun onStop() {
        super.onStop()
        presenter.detachView()
    }

    override fun onOptionsItemSelected(menuItem: MenuItem): Boolean {
        onBackPressed()
        return true
    }

    override fun updateFields(item: Item) {
        titleEditText.setText(item.title)
        keywordEditText.setText(item.keyword)
    }

    override fun showEmptyError() {
        showTitleEmptyError()
        showKeywordEmptyError()
    }

    override fun showTitleEmptyError() {
        titleEditText.error = resources.getString(R.string.activity_login_obligatory_field)
    }

    override fun showKeywordEmptyError() {
        keywordEditText.error = resources.getString(R.string.activity_login_obligatory_field)
    }

    override fun showSaveSuccess() {
        showToast(R.string.activity_new_save_success)
        finish()
    }

    override fun onUnknownError(error: String) {
        showToast(error)
    }

}