package com.example.myapplication222;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setSubtitle("수능 실전편");

        //day1 버튼 클릭시 액티비티 전환
        Button day1_btn = (Button) findViewById(R.id.day1);
        day1_btn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ListView.class);
                startActivity(intent);
            }
        });

        //day2 버튼 클릭시 액티비티 전환
        Button day2_btn = (Button) findViewById(R.id.day2);
        day2_btn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ListView.class);
                startActivity(intent);
            }
        });

        //day3 버튼 클릭시 액티비티 전환
        Button day3_btn = (Button) findViewById(R.id.day3);
        day3_btn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ListView.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id==R.id.action_one){
            Toast.makeText(this, "Item One Clicked", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if (id==R.id.action_two){
            Toast.makeText(this, "Day 단어 리스트", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if (id==R.id.action_three){
            Toast.makeText(this, "오늘의 단어 리스트", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if (id==R.id.action_four){
            Toast.makeText(this, "틀린 단어 리스트", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if (id==R.id.action_five){
            Toast.makeText(this, "중요 단어 리스트", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

