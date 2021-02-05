package com.example.guru2_todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import io.realm.Realm
import io.realm.kotlin.createObject
import io.realm.kotlin.where
import kotlinx.android.synthetic.main.activity_edit.*
import java.util.*

class EditActivity : AppCompatActivity() {
    //realm 인스턴스 얻기
    val realm= Realm.getDefaultInstance()
    val calendar:Calendar= Calendar.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
    val id= intent.getLongExtra("id",-1L)
        if(id==-1L){
            insertMode()
        }else{
            updateMode(id)
        }
        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            calendar.set(Calendar.YEAR,year)
            calendar.set(Calendar.MONTH,month)
            calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth)
        }
    }
    private fun insertMode(){
        deleteFab.visibility= View.GONE
        addFab.setOnClickListener {
            insertTodo()
        }
    }

    private fun updateMode(id:Long){
        val todo=realm.where<Todo>().equalTo("id",id).findFirst()!!
        todoEditText.setText(todo.date.toString())
        calendarView.date=todo.date
        addFab.setOnClickListener {
            updateTodo(id)
        }
        deleteFab.setOnClickListener {
            deleteTodo(id)
        }
    }
    private fun insertTodo(){
        realm.beginTransaction()
        val newItem=realm.createObject<Todo>(nextId())
        newItem.title=todoEditText.text.toString()
        newItem.date=calendar.timeInMillis
        realm.commitTransaction()

    }
    private fun nextId():Int{
        val maxId=realm.where<Todo>().max("id")
        return if(maxId != null) maxId.toInt()+1 else 0
    }
    private fun updateTodo(id:Long){
        realm.beginTransaction()
        val updateItem=realm.where<Todo>().equalTo("id",id).findFirst()!!
        //수정
        updateItem.title=todoEditText.text.toString()
        updateItem.date=calendar.timeInMillis
        realm.commitTransaction()
    }
    private fun deleteTodo(id:Long){
        realm.beginTransaction()
        val deleteItem=realm.where<Todo>().equalTo("id",id).findFirst()!!
        deleteItem.deleteFromRealm()
        realm.commitTransaction()
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }
}


