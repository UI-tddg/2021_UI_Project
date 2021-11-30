package com.example.myapplication222;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

public class WordListView extends AppCompatActivity {

    ArrayList<SampleData> wordDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wordlist_main);

        //단어 암기 버튼 클릭 시 액티비티 전환
       Button mem_btn88 = (Button) findViewById(R.id.mem_btn88);
        mem_btn88.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MemorizeSellect.class);
                startActivity(intent);
            }
        });


        this.InitializeWordData();
        ListView listView = (ListView)findViewById(R.id.listView);
        final MyAdapter myAdapter = new MyAdapter(this,wordDataList);

        listView.setAdapter(myAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id){
                Toast.makeText(getApplicationContext(),
                        myAdapter.getItem(position).getWord(),
                        Toast.LENGTH_LONG).show();
            }
        });

    }

    public void InitializeWordData()
    {
        wordDataList = new ArrayList<SampleData>();

        int i = 1;

        while(i <= 20){
            wordDataList.add(new SampleData(R.drawable.star, "breath : 입김"));
            i++;
        }
    }
}