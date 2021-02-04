package com.example.guru2_todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import io.realm.Realm
import io.realm.Sort
import io.realm.kotlin.createObject
import io.realm.kotlin.where
import kotlinx.android.synthetic.main.activity_edit.*
import java.util.*

class EditActivity : AppCompatActivity() {

    val realm = Realm.getDefaultInstance()
    val calendar: Calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        val id = intent.getLongExtra("id", -1L)
        if(id == -1L){
            insertMode()
        }else {
            updateMode(id)
        }

        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_YEAR, dayOfMonth)
        }

        val realmResult = realm.where<Todo>().findAll().sort("date",Sort.DESCENDING)
        //todo_list
        val adapter = TodoListAdapter(realmResult)
        listView.adapter = adapter

        realmResult.addChangeListener { _ -> adapter.notifyDataSetChanged() }
    }

    private fun insertMode(){
        deleteFab.visibility = View.GONE

        doneFab.setOnClickListener{
            insertTodo()
        }
    }

    private fun updateMode(id: Long){
        val todo = realm.where<Todo>().equalTo("id",id).findFirst()!!
        todoEditText.setText(todo.title)
        calendarView.date = todo.date

        doneFab.setOnClickListener {
            updateTodo(id)
        }

        deleteFab.setOnClickListener {
            deleteTodo(id)
        }
    }

    private fun insertTodo(){
        realm.beginTransaction()

        val newItem = realm.createObject<Todo>(nextId())

        newItem.title = todoEditText.text.toString()
        newItem.date = calendar.timeInMillis

        realm.commitTransaction()

        //Toast("내용이 변경되었습니다")
    }

    private fun nextId(): Int {

        val maxId = realm.where<Todo>().max("id")
        return if(maxId != null) maxId.toInt()+1 else 0
    }

    private fun updateTodo(id: Long) {
        realm.beginTransaction()
        val updateItem = realm.where<Todo>().equalTo("id", id).findFirst()!!

        updateItem.title = todoEditText.text.toString()
        updateItem.date = calendar.timeInMillis

        realm.commitTransaction()
        //Toast("내용이 변경되었습니다")
    }

    private fun deleteTodo(id: Long) {
        realm.beginTransaction()
        val deletItem = realm.where<Todo>().equalTo("id", id).findFirst()!!

        deletItem.deleteFromRealm()

        realm.commitTransaction()
        //Toast("내용이 변경되었습니다")
    }

    override fun onDestroy() {
        super.onDestroy()

        realm.close()
    }
}