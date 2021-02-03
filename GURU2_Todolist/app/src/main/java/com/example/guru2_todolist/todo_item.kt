package com.example.guru2_todolist

import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class todo_item : AppCompatActivity() {

    lateinit var dbManager: DBManager
    lateinit var sqlitedb: SQLiteDatabase

    lateinit var btn_Ok: Button
    lateinit var delete_Button: Button
    lateinit var edt_Todo: CheckBox
    lateinit var memo: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.todo_item)

        btn_Ok = findViewById(R.id.btn_OK)
        delete_Button = findViewById(R.id.delete_Button)
        edt_Todo = findViewById(R.id.edt_Todo)
        memo = findViewById(R.id.memo)

        dbManager = DBManager(this,"todolistDB",null,1)

        btn_Ok.setOnClickListener {
            var str_todo: String = edt_Todo.text.toString()
            var str_memo: String = memo.text.toString()

            sqlitedb = dbManager.writableDatabase
            sqlitedb.execSQL("INSERT INTO todolist VALUES ('"+str_todo+"', '" + str_memo+"')")
            sqlitedb.close()
        }
    }
}