package com.example.myapplication222;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private  ArrayList<String> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setSubtitle("수능 실전편");

        //DB저장
        fileRead();  //파일 읽기
        //setDB();   //DB에 초기 데이터 저장하기 근데 이부분 자꾸 에러나요 아직 못고침

        //day1 버튼 클릭시 액티비티 전환
        Button day1_btn = (Button) findViewById(R.id.day1);
        day1_btn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ListView.class);
                startActivity(intent);
            }
        });

        //day2 버튼 클릭시 액티비티 전환
        Button day2_btn = (Button) findViewById(R.id.day2);
        day2_btn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ListView.class);
                startActivity(intent);
            }
        });

        //day3 버튼 클릭시 액티비티 전환
        Button day3_btn = (Button) findViewById(R.id.day3);
        day3_btn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ListView.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id==R.id.action_one){
            Toast.makeText(this, "Item One Clicked", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if (id==R.id.action_two){
            Toast.makeText(this, "Day 단어 리스트", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if (id==R.id.action_three){
            Toast.makeText(this, "오늘의 단어 리스트", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if (id==R.id.action_four){
            Toast.makeText(this, "틀린 단어 리스트", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if (id==R.id.action_five){
            Toast.makeText(this, "중요 단어 리스트", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //파일읽는 함수
    private void fileRead(){
        InputStream inputStream = getResources().openRawResource(R.raw.vocadb); //res/raw/vocadb 텍스트 파일 가져오기
        arrayList = new ArrayList();
        try{
           BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"EUC_KR"));
           String data;
           while((data=bufferedReader.readLine())!=null){
               String[] split = data.split("-"); //-를 기준으로 나눔
               arrayList.add(split[0]);  //arrayList에 저장 eng
               arrayList.add(split[1]);  //kor 근데 한글이 깨져서 저장이 된다. 왜 한글이 깨지니!!!!!!!!!
               arrayList.add(split[2]);  //day
           }
        }catch (IOException e){
            e.printStackTrace();
        }

        //디버그
        for(int i=0; i< arrayList.size();i++)
            Log.d("arr",arrayList.get(i));
    }

    private void setDB(){  //이거 추가하고 에러 나요 no such column이라는데
        DBHelper helper= new DBHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        for(int i=0; i<60; i++) {
            db.execSQL("insert into tb_voca (eng, kor, day, star, nope) values("
                    + arrayList.get(i*3) + ","  //eng
                    + arrayList.get(i*3+1) + ","  //kor 한글이 깨져서 에러가 뜨나?
                    + Integer.parseInt(arrayList.get(i*3+2)) + "," //day
                    + false + "," + false + ")"); //star랑 nope
        }
        db.close();
    }
}

