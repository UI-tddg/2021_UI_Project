package com.example.myapplication222;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


public class ListView extends AppCompatActivity {

    public static String[][] arrayDay;
    public static int day;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);

        //단어 암기 버튼 클릭시 액티비티 전환
        Button mem_btn = (Button) findViewById(R.id.mem_btn);
        mem_btn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MemorizeSellect.class);
                startActivity(intent);
            }
        });

        //시험 버튼 클릭시 액티비티 전환
        Button test_btn = (Button) findViewById(R.id.test_btn);
        test_btn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TestSellect.class);
                startActivity(intent);
            }
        });

        //재시험 버튼 클릭시 액티비티 전환
        Button retest_btn = (Button) findViewById(R.id.retest_btn);
        retest_btn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TestSellect.class);
                startActivity(intent);
            }
        });

        //매칭게임 버튼 클릭시 매칭 게임 설명 화면 액티비티 전환
        Button match_btn = (Button) findViewById(R.id.match_btn);
        match_btn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MatchingGame_Start.class);
                startActivity(intent);
            }
        });

        selectDB();
    }

    private void selectDB(){
        arrayDay = new String[20][7];
        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from tb_voca", null);
        int k=0;
        try {
            while(cursor.moveToNext()){
                  if((cursor.getInt(4))==1) {//day가 1인 데이터만 배열에 저장
                      arrayDay[k][0]=Integer.toString(cursor.getInt(0)); //id
                      arrayDay[k][1]=cursor.getString(1); //eng
                      arrayDay[k][2]=cursor.getString(2); //kor
                      arrayDay[k][3]=cursor.getString(3); //speak
                      arrayDay[k][4]=Integer.toString(cursor.getInt(4)); //day
                      arrayDay[k][5]=Integer.toString(cursor.getInt(5)); //star
                      arrayDay[k][6]=Integer.toString(cursor.getInt(6)); //nope
                      Log.d("cursor", arrayDay[k][3]);
                      Log.d("k", Integer.toString(k));
                      k++;
                  }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}