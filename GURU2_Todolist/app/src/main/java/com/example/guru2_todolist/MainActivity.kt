package com.example.guru2_todolist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_list.*
import android.app.Activity
import android.content.Intent
import android.graphics.Rect
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_header.view.*
import kotlinx.android.synthetic.main.item_main.view.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        add.setOnClickListener {
            setContentView(R.layout.item_todo)
        }
    }

    /* 신규 항목 추가(리사이클러뷰에)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 10 && resultCode == Activity.RESULT_OK) {
            // selectDB()
        }
    }*/

    // 뷰를 준비함
    class HeaderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val headerView = view.itemHeaderView
    }

    // 데이타 준비함
    class DataViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val completedIconView = view.completedIconView
        val itemTitleView = view.itemTitleView
        val itemContentView = view.itemContentView
    }

}