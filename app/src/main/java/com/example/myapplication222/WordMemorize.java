package com.example.myapplication222;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class WordMemorize extends AppCompatActivity implements View.OnClickListener{

    Button touchButton1;
    TextView showMeaning1;
    ImageButton colorStar1;
    ImageButton starButton1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_memorize);

        touchButton1 = (Button) findViewById(R.id.M_touchButton);
        showMeaning1 = (TextView) findViewById(R.id.M_showMeaning);
        starButton1 = (ImageButton) findViewById(R.id.M_starButton);
        colorStar1 = (ImageButton) findViewById(R.id.M_colorStar);
        touchButton1.setOnClickListener(this);
        showMeaning1.setOnClickListener(this);
        starButton1.setOnClickListener(this);
        colorStar1.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == touchButton1) {
            touchButton1.setVisibility(View.INVISIBLE);
        }
        if (view == showMeaning1) {
            touchButton1.setVisibility(View.VISIBLE);
        }
        if (view == starButton1) {
            colorStar1.setVisibility(View.VISIBLE);
        }
        if (view == colorStar1) {
            colorStar1.setVisibility(View.INVISIBLE);
        }
    }
}
