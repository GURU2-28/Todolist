package com.example.guru2_todolist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import io.realm.OrderedRealmCollection
import io.realm.RealmBaseAdapter
import io.realm.RealmResults

class TodoListAdapter(realmResults: OrderedRealmCollection<Todo>): RealmBaseAdapter<Todo>(realmResults) {

    override fun getView(position: Int, converView: View?, parent: ViewGroup?): View {
        val vh: ViewHolder
        val view: View

        if(converView == null){
            view = LayoutInflater.from(parent?.context).inflate(R.layout.activity_edit,parent,false)
            vh = ViewHolder(view)
            view.tag = vh;
        } else {
            view = converView
            vh = view.tag as ViewHolder
        }

        if(adapterData != null) {
            val item = adapterData!![position]
            vh.textTextView.text = item.title
            vh.memoTextView.text = item.title
        }
        return view
    }

    override fun getItemId(position: Int): Long {
        if(adapterData != null) {
            return adapterData!![position].id
        }
        return super.getItemId(position)
    }

    class ViewHolder(view: View){

        val textTextView: TextView = view.findViewById(R.id.edt_Todo)
        val memoTextView: TextView = view.findViewById(R.id.memo)
    }
}