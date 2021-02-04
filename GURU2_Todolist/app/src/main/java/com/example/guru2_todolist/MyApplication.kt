package com.example.guru2_todolist

import android.app.Application
import io.realm.Realm

class MyApplication:Application() {
    override fun onCreate(){
        super.onCreate()
        //Realm 초기화
        Realm.init(this)
    }
}