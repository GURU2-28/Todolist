package com.example.guru2_todolist

import android.app.Application
import io.realm.Realm
//Realm 데이터베이스 연동준비
class MyApplication:Application() {
    override fun onCreate(){
        super.onCreate()
        //Realm 초기화
        Realm.init(this)
    }
}