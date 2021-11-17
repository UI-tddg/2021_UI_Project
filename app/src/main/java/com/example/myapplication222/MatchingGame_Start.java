package com.example.myapplication222;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MatchingGame_Start extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.matchinggame_start);

        //매칭게임 start 버튼 -> MatchingGame 페이지
        Button mg_start = (Button) findViewById(R.id.mg_start_btn);
        mg_start.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MatchingGame.class);
                startActivity(intent);
            }
        });
    }
}