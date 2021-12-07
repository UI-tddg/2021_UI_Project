package com.example.myapplication222;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FinishMemorize extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_memorize);

        Button golist_btn = (Button) findViewById(R.id.go_list_btn);
        golist_btn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), WordListView.class);
                startActivity(intent);
            }
        });

        Button gotest_btn = (Button) findViewById(R.id.go_test_btn);
        gotest_btn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TestSellect.class);
                startActivity(intent);
            }
        });
    }
}