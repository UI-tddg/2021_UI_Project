package com.example.myapplication222;

import android.content.Context;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {


    public DBHelper(Context context){
        super(context,"/sdcard/Download/voca.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db){

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        if(newVersion==1){
            db.execSQL("drop table voca.db");
            onCreate(db);
        }
    }
}
