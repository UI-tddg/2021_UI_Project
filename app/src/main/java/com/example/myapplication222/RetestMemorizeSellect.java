package com.example.myapplication222;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RetestMemorizeSellect extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sellect);

        TextView textView = (TextView)findViewById(R.id.sellect_name);
        textView.setText("능률보카 틀린단어 단어 암기");

        //meaning(뜻외우기) 버튼 클릭시 액티비티 전환
        Button ms_meaning_btn = (Button) findViewById(R.id.sellect_left_btn);
        ms_meaning_btn.setText("뜻 외우기");
        ms_meaning_btn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RetestMeanMeomorize.class);
                startActivity(intent);
            }
        });

        //단어외우기 버튼 클릭시 액티비티 전환
        Button ms_word_btn = (Button) findViewById(R.id.sellect_right_btn);
        ms_word_btn.setText("단어 외우기");
        ms_word_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RetestWordMemorize.class);
                startActivity(intent);
            }
        });
    }
}
