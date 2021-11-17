package com.example.myapplication222;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ListView extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);


        //단어 암기 버튼 클릭시 액티비티 전환

        //단어 암기 버튼 클릭시 액티비티 전환 서유닝~~~~~
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

        //매칭게임 버튼 클릭시 액티비티 전환
        Button match_btn = (Button) findViewById(R.id.match_btn);
        match_btn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MatchingGame.class);
                startActivity(intent);
            }
        });
    }

}