package com.example.myapplication222;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setSubtitle("수능 실전편");

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

