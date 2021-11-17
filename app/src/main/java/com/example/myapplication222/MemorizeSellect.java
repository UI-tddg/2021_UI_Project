
package com.example.myapplication222;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MemorizeSellect extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memorize_sellect);

        //meaning(뜻외우기) 버튼 클릭시 액티비티 전환
        Button ms_meaning_btn = (Button) findViewById(R.id.ms_meaning_btn);
        ms_meaning_btn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MeanMemorize.class);
                startActivity(intent);
            }
        });

        //단어외우기 버튼 클릭시 액티비티 전환
        Button ms_word_btn = (Button) findViewById(R.id.ms_word_btn);
        ms_word_btn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), WordMemorize.class);
                startActivity(intent);
            }
        });
    }
}

