package com.example.myapplication222;

import android.content.Context;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION =1;
    public String TABLE_NAME = "tb_voca";

    public DBHelper(Context context){
        super(context,"vocadb", null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String vocadb="create table "+ TABLE_NAME+
                "(_id integer primary key autoincrement, "+  //id값 저절로 생성
                "eng, " +  //영단어
                "kor, " +  //뜻
                "speak, "+ //발음기호
                "day integer, " +  //day
                "star interger, " +  //중요단어
                "nope integer)";  //틀린단어 체크
        db.execSQL(vocadb);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        if(newVersion==DATABASE_VERSION){
            db.execSQL("drop table voca");
            onCreate(db);
        }
    }
}
