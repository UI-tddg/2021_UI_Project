package com.example.myapplication222;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.os.Bundle;
import java.util.Random;

public class MatchingGame extends AppCompatActivity implements View.OnClickListener {

    TextView timer;
    private Button first_left, second_left, third_left, fourth_left, fifth_left;
    private Button first_right, second_right, third_right, fourth_right, fifth_right;
    private int a00 = 0, a01 = 0, a02 = 0, a03 = 0, a04 = 0;
    private int a10 = 0, a11 = 0, a12 = 0, a13 = 0, a14 = 0;
    private int a20 = 0, a21 = 0, a22 = 0, a23 = 0, a24 = 0;
    private int a30 = 0, a31 = 0, a32 = 0, a33 = 0, a34 = 0;
    private int a40 = 0, a41 = 0, a42 = 0, a43 = 0, a44 = 0;
    private int current = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.matching_game);

        first_left = findViewById(R.id.first_left);
        first_left.setOnClickListener(this);
        second_left = findViewById(R.id.second_left);
        second_left.setOnClickListener(this);
        third_left = findViewById(R.id.third_left);
        third_left.setOnClickListener(this);
        fourth_left = findViewById(R.id.fourth_left);
        fourth_left.setOnClickListener(this);
        fifth_left = findViewById(R.id.fifth_left);
        fifth_left.setOnClickListener(this);


        first_right = findViewById(R.id.first_right);
        first_right.setOnClickListener(this);
        second_right = findViewById(R.id.second_right);
        second_right.setOnClickListener(this);
        third_right = findViewById(R.id.third_right);
        third_right.setOnClickListener(this);
        fourth_right = findViewById(R.id.fourth_right);
        fourth_right.setOnClickListener(this);
        fifth_right = findViewById(R.id.fifth_right);
        fifth_right.setOnClickListener(this);

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

        firstSetting();
    }

    private void firstSetting() {
        int num[] = new int[20];
        Random random = new Random();

        for(int i = 0; i < 20; i++) {
            num[i] = random.nextInt(20);
            for(int j = 0; j < i; j++) {
                if(num[i] == num[j])
                    i--;
            }
        }

        //초기 영단어 random 세팅
        first_left.setText(WordListView.arrayDay[num[0]][1]);
        second_left.setText(WordListView.arrayDay[num[1]][1]);
        third_left.setText(WordListView.arrayDay[num[2]][1]);
        fourth_left.setText(WordListView.arrayDay[num[3]][1]);
        fifth_left.setText(WordListView.arrayDay[num[4]][1]);

        int[] num1 = {num[0], num[1], num[2], num[3], num[4]};
        int[] num2 = {num[5], num[6], num[7], num[8], num[9]};
        int[] num3 = {num[10], num[11], num[12], num[13], num[14]};
        int[] num4 = {num[15], num[16], num[17], num[18], num[19]};

        int rand[] = new int[5];
        for(int i = 0; i < 5; i++) {
            rand[i] = random.nextInt(5);
            for(int j = 0; j < i; j++) {
                if(rand[i] == rand[j])
                    i--;
            }
        }

        num1[0] = num[rand[0]];
        num1[1] = num[rand[1]];
        num1[2] = num[rand[2]];
        num1[3] = num[rand[3]];
        num1[4] = num[rand[4]];

        first_right.setText(WordListView.arrayDay[num1[0]][2]);
        second_right.setText(WordListView.arrayDay[num1[1]][2]);
        third_right.setText(WordListView.arrayDay[num1[2]][2]);
        fourth_right.setText(WordListView.arrayDay[num1[3]][2]);
        fifth_right.setText(WordListView.arrayDay[num1[4]][2]);

        if (num[0] == num1[0]) a00++;
        if (num[0] == num1[1]) a01++;
        if (num[0] == num1[2]) a02++;
        if (num[0] == num1[3]) a03++;
        if (num[0] == num1[4]) a04++;

        if (num[1] == num1[0]) a10++;
        if (num[1] == num1[1]) a11++;
        if (num[1] == num1[2]) a12++;
        if (num[1] == num1[3]) a13++;
        if (num[1] == num1[4]) a14++;

        if (num[2] == num1[0]) a20++;
        if (num[2] == num1[1]) a21++;
        if (num[2] == num1[2]) a22++;
        if (num[2] == num1[3]) a23++;
        if (num[2] == num1[4]) a24++;

        if (num[3] == num1[0]) a30++;
        if (num[3] == num1[1]) a31++;
        if (num[3] == num1[2]) a32++;
        if (num[3] == num1[3]) a33++;
        if (num[3] == num1[4]) a34++;

        if (num[4] == num1[0]) a40++;
        if (num[4] == num1[1]) a41++;
        if (num[4] == num1[2]) a42++;
        if (num[4] == num1[3]) a43++;
        if (num[4] == num1[4]) a44++;
    }


    @Override
    public void onClick(View view) {
        if (view == first_left) {
            if (first_right.callOnClick()) {
                if(a00 == 1)
                    first_right.setVisibility(View.INVISIBLE);
            }

        }

        if (current == 5) {
            a00 = 0;
            a01 = 0;
            a02 = 0;
            a03 = 0;
            a04 = 0;

            a10 = 0;
            a11 = 0;
            a12 = 0;
            a13 = 0;
            a14 = 0;

            a20 = 0;
            a21 = 0;
            a22 = 0;
            a23 = 0;
            a24 = 0;

            a30 = 0;
            a31 = 0;
            a32 = 0;
            a33 = 0;
            a34 = 0;

            a40 = 0;
            a41 = 0;
            a42 = 0;
            a43 = 0;
            a44 = 0;

            current = 0;
        }
    }
}