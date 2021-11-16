package com.example.myapplication222;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ImageButton;

public class WordMemorize extends AppCompatActivity implements View.OnClickListener{

    Button touchButton;
    TextView showMeaning;
    ImageButton colorStar;
    ImageButton starButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_memorize);

        touchButton = (Button) findViewById(R.id.touchButton);
        showMeaning = (TextView) findViewById(R.id.showMeaning);
        starButton = (ImageButton) findViewById(R.id.starButton);
        colorStar = (ImageButton) findViewById(R.id.colorStar);
        touchButton.setOnClickListener(this);
        showMeaning.setOnClickListener(this);
        starButton.setOnClickListener(this);
        colorStar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == touchButton) {
            touchButton.setVisibility(View.INVISIBLE);
        }
        if (view == showMeaning) {
            touchButton.setVisibility(View.VISIBLE);
        }
        if (view == starButton) {
            colorStar.setVisibility(View.VISIBLE);
        }
        if (view == colorStar) {
            colorStar.setVisibility(View.INVISIBLE);
        }
    }
}