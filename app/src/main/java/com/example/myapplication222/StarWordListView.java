package com.example.myapplication222;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class StarWordListView extends AppCompatActivity {

    ArrayList<SampleData> wordDataList;
    public static String[][] arrayDay;
    public String EngWord;
    public String KorWord;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
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
            finish();
        }
        else if (id==R.id.star_word_list){
            Intent intent = new Intent(this, StarWordListView.class);
            startActivity(intent);
            finish();
        }
        else if (id==R.id.nope_word_list){
            Intent intent = new Intent(this, NopeWordListView.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);
        getSupportActionBar().setSubtitle("수능 실전편_중요 단어");

        selectDB();

        this.InitializeWordData();
        ListView listView = (ListView)findViewById(R.id.listView);
        final MyAdapter myAdapter = new MyAdapter(this,wordDataList);

        listView.setAdapter(myAdapter);
    }
    private void selectDB(){
        arrayDay = new String[100][7];
        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from tb_voca", null);
        int k=0;
        try {
            while(cursor.moveToNext()){
                if(((cursor.getInt(4)) == 1 )||((cursor.getInt(4)) == 2 )||((cursor.getInt(4)) == 3)||((cursor.getInt(4)) == 4)||((cursor.getInt(4)) == 5)) {
                    arrayDay[k][0]=Integer.toString(cursor.getInt(0)); //id
                    arrayDay[k][1]=cursor.getString(1); //eng
                    arrayDay[k][2]=cursor.getString(2); //kor
                    arrayDay[k][3]=cursor.getString(3); //speak
                    arrayDay[k][4]=Integer.toString(cursor.getInt(4)); //day
                    arrayDay[k][5]=Integer.toString(cursor.getInt(5)); //star
                    arrayDay[k][6]=Integer.toString(cursor.getInt(6)); //nope
                    Log.d("cursor", arrayDay[k][5]);
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
        for (int current = 0; current < 90; current++) {
            if (Integer.parseInt(NopeWordListView.arrayDay[current][5]) == 1){
                EngWord = StarWordListView.arrayDay[current][1];
                KorWord = StarWordListView.arrayDay[current][2];
                wordDataList.add(new SampleData(R.drawable.dot, EngWord, KorWord));
            }
        }
    }
}