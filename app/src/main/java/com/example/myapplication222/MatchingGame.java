package com.example.myapplication222;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.CountDownTimer;
import android.widget.TextView;
import android.os.Bundle;

public class MatchingGame extends AppCompatActivity {

    TextView timer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.matching_game);

        timer = findViewById(R.id.timer);

        CountDownTimer countDownTimer = new CountDownTimer(100000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int num = (int) (millisUntilFinished / 1000 % 60);
                int num2 = (int) (millisUntilFinished / 1000 / 60);
                String num3 = String.format("%02d", num);

                timer.setText("0" + Integer.toString(num2) + " : " + num3);
            }
            @Override
            public void onFinish() {
                timer.setText("00 : 00");
            }
        }.start();
    }
}