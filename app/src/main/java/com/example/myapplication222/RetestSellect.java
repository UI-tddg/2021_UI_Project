package com.example.myapplication222;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RetestSellect extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sellect);

        TextView textView = (TextView)findViewById(R.id.sellect_name);
        textView.setText("능률보카 재시험");

        //틀린단어 암기 버튼 클릭시 액티비티 전환
        Button mem_btn = (Button) findViewById(R.id.sellect_left_btn);
        mem_btn.setText("틀린단어 암기");
        mem_btn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MemorizeSellect.class);
                startActivity(intent);
            }
        });

        //틀린단어 재시험 버튼 클릭시 액티비티 전환
        Button retest_btn = (Button) findViewById(R.id.sellect_right_btn);
        retest_btn.setText("틀린단어 재시험");
        retest_btn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TestSellect.class);
                startActivity(intent);
            }
        });
    }
}
