package com.example.guru2_todolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    //주석 다시추가-민지
    //주석 추가-혜주
    //주석 추가-예린
    
    //주석 추가-규리

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    //메뉴바를 클릭했을 때의 동작
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId){
            R.id.action_achieve -> { //성취율 메뉴 동작
                var intent = Intent(this, Achievement::class.java)
                startActivity(intent)
                return true
            }

            //R.id.past_todo -> {} //이전 할일 조회하기
        }
        return super.onOptionsItemSelected(item)
    }
}