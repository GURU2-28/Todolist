package com.example.guru2_todolist
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
//데이터베이스 모델클래스 생성
//클래스명은 테이블명과 동일

open class Todo (
    //기본키
    @PrimaryKey var id:Long=0
    ,var title: String=""   //제목
    ,var date:Long=0    //날짜
    ,var content:String=""
) :  RealmObject()