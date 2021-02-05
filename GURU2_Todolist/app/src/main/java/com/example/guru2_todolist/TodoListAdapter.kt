package com.example.guru2_todolist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.realm.OrderedRealmCollection
import io.realm.RealmBaseAdapter

class TodoListAdapter(realmResult:OrderedRealmCollection<Todo>): RealmBaseAdapter<Todo>(realmResult) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val vh:ViewHolder
        val view:View
        if(convertView==null){
            view= LayoutInflater.from(parent?.context)
                .inflate(R.layout.item_todo,parent,false)
            vh=ViewHolder(view)
            view.tag=vh;
        }else{
            view=convertView
            vh=view.tag as ViewHolder
        }
        if(adapterData != null){
            val item=adapterData!![position]


        }
    return view
}

    override fun getItemId(position: Int): Long {
        if(adapterData!=null){
            return adapterData!![position].id
        }
        return super.getItemId(position)
    }


}

class ViewHolder(view : View){

}