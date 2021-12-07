package com.example.myapplication222;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

public class WordListView extends AppCompatActivity {

    ArrayList<SampleData> wordDataList;
    public static String[][] arrayDay;
    public String EnglishWord;
    private int day = 1;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id==R.id.share_btn){
            Toast.makeText(this, "공유 버튼", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if (id==R.id.day_word_list){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        else if (id==R.id.today_word_list){
            Intent intent = new Intent(this, WordListView.class);
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



        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.wordlist_main);
            getSupportActionBar().setSubtitle("수능 실전편");
            day = getIntent().getExtras().getInt("day");

        //단어 암기 버튼 클릭 시 액티비티 전환
        Button mem_btn88 = (Button) findViewById(R.id.mem_btn88);
        mem_btn88.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MemorizeSellect.class);
                startActivity(intent);
            }
        });

        Button mem_btn = (Button) findViewById(R.id.mem_btn);
        mem_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MemorizeSellect.class);
                startActivity(intent);
            }
        });

        //시험 버튼 클릭시 액티비티 전환
        Button test_btn = (Button) findViewById(R.id.test_btn1);
        test_btn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TestSellect.class);
                startActivity(intent);
            }
        });

        //재시험 버튼 클릭시 액티비티 전환
        Button retest_btn = (Button) findViewById(R.id.retest_btn1);
        retest_btn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RetestSellect.class);
                startActivity(intent);
            }
        });

        //매칭게임 버튼 클릭시 매칭 게임 설명 화면 액티비티 전환
        Button match_btn = (Button) findViewById(R.id.match_btn1);
        match_btn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MatchingGame_Start.class);
                startActivity(intent);
            }
        });

        selectDB();

        this.InitializeWordData();
        ListView listView = (ListView)findViewById(R.id.listView);
        final MyAdapter myAdapter = new MyAdapter(this,wordDataList);

        listView.setAdapter(myAdapter);
    }

    private void selectDB(){
        arrayDay = new String[20][7];
        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from tb_voca", null);
        int k=0;
        try {
            while(cursor.moveToNext()){
                if((cursor.getInt(4))== day) {//day가 1인 데이터만 배열에 저장
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

    public void InitializeWordData()
    {
        wordDataList = new ArrayList<SampleData>();
        for (int current = 0; current < 20; current++) {
            EnglishWord = WordListView.arrayDay[current][1];
            wordDataList.add(new SampleData(R.drawable.dot, EnglishWord));
        }
    }
}