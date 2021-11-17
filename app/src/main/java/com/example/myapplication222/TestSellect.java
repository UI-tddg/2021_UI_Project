package com.example.myapplication222;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TestSellect extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_sellect);

        //뜻맞추기 버튼 클릭시 액티비티 전환
        Button mean_btn = (Button) findViewById(R.id.mean_btn);
        mean_btn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MeaningTest.class);
                startActivity(intent);
            }
        });

        //스펠링맞추기 버튼 클릭시 액티비티 전환
        Button spell_btn = (Button) findViewById(R.id.spell_btn);
        spell_btn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SpellingTest.class);
                startActivity(intent);
            }
        });
    }
}