package com.example.myapplication222;

import android.content.Intent;
import android.database.Cursor;
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
        setDB();   //DB에 초기 데이터 저장하기 와 !!!!에러 해결!!!!!!!
        readDB();

        //day1 버튼 클릭시 액티비티 전환
        Button day1_btn = (Button) findViewById(R.id.day1);
        day1_btn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {


                Intent intent = new Intent(getApplicationContext(), WordListView.class);
                intent.putExtra("day",1); //인텐트에 데이터 추가

                startActivity(intent);
            }
        });

        //day2 버튼 클릭시 액티비티 전환
        Button day2_btn = (Button) findViewById(R.id.day2);
        day2_btn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), WordListView.class);
                intent.putExtra("day",2);

                startActivity(intent);
            }
        });

        //day3 버튼 클릭시 액티비티 전환
        Button day3_btn = (Button) findViewById(R.id.day3);
        day3_btn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), WordListView.class);
                intent.putExtra("day",3);

                startActivity(intent);
            }
        });

        Button day4_btn = (Button) findViewById(R.id.day4);
        day4_btn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), WordListView.class);
                intent.putExtra("day",4);

                startActivity(intent);
            }
        });

        Button day5_btn = (Button) findViewById(R.id.day5);
        day5_btn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), WordListView.class);
                intent.putExtra("day",5);

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
        if (id==R.id.share_btn){
            Intent msg = new Intent(Intent.ACTION_SEND);
            msg.addCategory(Intent.CATEGORY_DEFAULT);
            msg.putExtra(Intent.EXTRA_TEXT, "능률보카 수능실전편 단어암기 어플 공유");
            msg.setType("text/plain");
            startActivity(Intent.createChooser(msg, "앱을 선택해주세요"));
        }
        else if (id==R.id.day_word_list){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        else if (id==R.id.star_word_list){
            Intent intent = new Intent(this, StarWordListView.class);
            startActivity(intent);
        }
        else if (id==R.id.nope_word_list){
            Intent intent = new Intent(this, NopeWordListView.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public void readDB()
    {
        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from tb_voca", null);
        while(cursor.moveToNext()){
            Log.d(cursor.getString(1), cursor.getString(3));
        }
    }

    //파일읽는 함수
    private void fileRead() {
        InputStream inputStream = getResources().openRawResource(R.raw.vocadb); //res/raw/vocadb 텍스트 파일 가져오기
        arrayList = new ArrayList();
        try{
           BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
           String data;
           while((data=bufferedReader.readLine())!=null){
               String[] split = data.split("-"); //-를 기준으로 나눔
               arrayList.add(split[0]);  //arrayList에 저장 eng
               arrayList.add(split[1]);  //kor
               arrayList.add(split[2]);  //speak
               arrayList.add(split[3]);  //day
           }
        }catch (IOException e){
            e.printStackTrace();
        }
   }

    private void setDB(){
        DBHelper helper= new DBHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();

//        Cursor cursor = db.rawQuery("select * from tb_voca",null);
//        cursor.moveToFirst();
//        if(cursor.getString(1)){
//            cursor.close();
//            return;
//        }
        for(int i=0; i<100; i++) {  //단어 60개라 60개 썼다.
            db.execSQL("insert into tb_voca (eng, kor, speak, day, star, nope) values('"
                    + arrayList.get(i*4) + "', '"  //eng
                    + arrayList.get(i*4+1) + "', '"  //kor
                    + arrayList.get(i*4+2) + "', '" //speak
                    + Integer.parseInt(arrayList.get(i*4+3)) + "', '" //day
                    + 0 + "', '" + 0 + "')"); //star랑 nope
        }
        db.close();
    }
}

