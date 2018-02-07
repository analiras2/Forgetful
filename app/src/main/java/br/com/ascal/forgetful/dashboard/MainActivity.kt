package br.com.ascal.forgetful.dashboard

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
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
        mainAdapter = MainAdapter(this, tempList)
        recyclerView.adapter = mainAdapter
    }

    override fun onStop() {
        super.onStop()
        presenter.detachView()
    }
}