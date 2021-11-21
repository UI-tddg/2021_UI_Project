package com.example.myapplication222;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class DBHelper extends SQLiteOpenHelper {

    private static String DB_PATH="";
    private static String DB_NAME="voca.db";
    private Context mContext;

    public static final int DATABASE_VERSION=1;

    public DBHelper(Context context){
        super(context, DB_NAME, null, DATABASE_VERSION);

        DB_PATH="/data/data/" + context.getPackageName() +"/databases/";
        this.mContext=context;
        dataBaseCheck();  //DB가 있는지 체크
    }

    //DB 있는지 체크
    private void dataBaseCheck(){
        File dbFile = new File(DB_PATH+DB_NAME);

        if(!dbFile.exists()){  //DB가 없으면 복사
            dbCopy();
        }
    }

    //db 복사하기
    // assets의 /db/voca.db 파일을 프로그램의 내부 DB공간으로 복사하기
    private void dbCopy(){
        AssetManager manager=mContext.getAssets();
        File folder=new File(DB_PATH);
        File file = new File(DB_PATH+DB_NAME);

        FileOutputStream fos =null;
        BufferedOutputStream bos=null;
        try {
           InputStream is =manager.open("db/"+DB_NAME);
            BufferedInputStream bis = new BufferedInputStream(is);

            if(!folder.exists()){
                folder.mkdir();
            }
            if(file.exists()){
                file.delete();
                file.createNewFile();
            }

            fos=new FileOutputStream(file);
            bos=new BufferedOutputStream(fos);
            int read=-1;
            byte[] buffer=new byte[1024];
            while ((read=bis.read(buffer, 0, 1024))!=-1){
                bos.write(buffer, 0, read);
            }

            bos.flush();
            bos.close();
            fos.close();
            bis.close();
            is.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db){
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        if(newVersion==DATABASE_VERSION){
            db.execSQL("drop table voca.db");
            onCreate(db);
        }
    }
}
