package com.example.myapplication222;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RetestTestSellect extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sellect);

        TextView textView = (TextView)findViewById(R.id.sellect_name);
        textView.setText("능률보카 단어 재시험");

        //뜻맞추기 버튼 클릭시 액티비티 전환
        Button mean_btn = (Button) findViewById(R.id.sellect_left_btn);
        mean_btn.setText("뜻 맞추기");
        mean_btn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RetestMeaningTest.class);
                startActivity(intent);
            }
        });

        //스펠링맞추기 버튼 클릭시 액티비티 전환
        Button spell_btn = (Button) findViewById(R.id.sellect_right_btn);
        spell_btn.setText("스펠링 맞추기");
        spell_btn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RetestSpellTest.class);
                startActivity(intent);
            }
        });
    }
}
