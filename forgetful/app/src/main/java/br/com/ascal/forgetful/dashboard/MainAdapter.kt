package br.com.ascal.forgetful.dashboard

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.ascal.forgetful.R
import kotlinx.android.synthetic.main.main_item.view.*

class MainAdapter(private val context: Context, private val itemList: List<String>, private val listener: (String) -> Unit) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bind(itemList[position], listener)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.main_item, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: String, listener: (String) -> Unit) = with(itemView) {
            titleTextView.text = item
            passwordTextView.text = "password"
            setOnClickListener { listener(item) }
        }
    }

}