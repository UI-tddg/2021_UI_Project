package com.example.myapplication222;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;
import android.os.Bundle;

import org.w3c.dom.Text;

import java.util.Random;

public class MatchingGame extends AppCompatActivity implements View.OnClickListener {

    TextView timer;
    TextView success;
    TextView counting_score;
    TableLayout tableLayout;
    private Button first_left, second_left, third_left, fourth_left, fifth_left;
    private Button first_right, second_right, third_right, fourth_right, fifth_right;
    private static int[] eng;
    private static int[] kor;
    private int engCurrent = 0;
    private int korCurrent = 0;
    private int checkFirstL, checkSecondL, checkThirdL, checkFourthL, checkFifthL;
    private int checkFirstR, checkSecondR, checkThirdR, checkFourthR, checkFifthR;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.matching_game);

        MySoundPlayer.initSounds(getApplicationContext());

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
        success = findViewById(R.id.success);
        counting_score = findViewById(R.id.countingScore);
        counting_score.setText("0");
        tableLayout = findViewById(R.id.tableLayout);

        CountDownTimer countDownTimer = new CountDownTimer(100000, 1000) {
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
                tableLayout.setVisibility(View.INVISIBLE);
                success.setText("실패!");
                success.setVisibility(View.VISIBLE);
            }
        }.start();

        engRandom();
        korRandom();
        setBtn();
    }

    private void engRandom() {
        eng = new int[20];
        Random random = new Random();

        for (int i = 0; i < 20; i++) {
            eng[i] = random.nextInt(20);
            for (int j = 0; j < i; j++) {
                if (eng[i] == eng[j])
                    i--;
            }
        }
    }

    private void korRandom() {
        kor = new int[20];
        int kor1[] = {eng[0],eng[1],eng[2],eng[3],eng[4]};
        int kor2[] = {eng[5],eng[6],eng[7],eng[8],eng[9]};
        int kor3[] = {eng[10],eng[11],eng[12],eng[13],eng[14]};
        int kor4[] = {eng[15],eng[16],eng[17],eng[18],eng[19]};

        int[] r1 = fiveRandom();
        for (int i = 0; i < 5; i++) kor[i] = kor1[r1[i]];
        int[] r2 = fiveRandom();
        for (int i = 0; i < 5; i++) kor[i + 5] = kor2[r2[i]];
        int[] r3 = fiveRandom();
        for (int i = 0; i < 5; i++) kor[i + 10] = kor3[r3[i]];
        int[] r4 = fiveRandom();
        for (int i = 0; i < 5; i++) kor[i + 15] = kor4[r4[i]];

    }

    private int[] fiveRandom() {
        Random random = new Random();
        int[] rand = new int[5];
        for (int i = 0; i < 5; i++) {
            rand[i] = random.nextInt(5);
            for (int j = 0; j < i; j++) {
                if (rand[i] == rand[j])
                    i--;
            }
        }
        return rand;
    }

    private void setBtn() {
        setFirstL();
        setSecondL();
        setThirdL();
        setFourthL();
        setFifthL();

        setFirstR();
        setSecondR();
        setThirdR();
        setFourthR();
        setFifthR();
    }

    private void setFirstL() {
        first_left.setText(WordListView.arrayDay[eng[engCurrent++]][1]);
        checkFirstL = engCurrent;
    }
    private void setSecondL() {
        second_left.setText(WordListView.arrayDay[eng[engCurrent++]][1]);
        checkSecondL = engCurrent;
    }
    private void setThirdL() {
        third_left.setText(WordListView.arrayDay[eng[engCurrent++]][1]);
        checkThirdL = engCurrent;
    }
    private void setFourthL() {
        fourth_left.setText(WordListView.arrayDay[eng[engCurrent++]][1]);
        checkFourthL = engCurrent;
    }
    private void setFifthL() {
        fifth_left.setText(WordListView.arrayDay[eng[engCurrent++]][1]);
        checkFifthL = engCurrent;
    }

    private void setFirstR(){
        first_right.setText(WordListView.arrayDay[kor[korCurrent++]][2]);
        checkFirstR = korCurrent;
    }
    private void setSecondR(){
        second_right.setText(WordListView.arrayDay[kor[korCurrent++]][2]);
        checkSecondR = korCurrent;
    }
    private void setThirdR(){
        third_right.setText(WordListView.arrayDay[kor[korCurrent++]][2]);
        checkThirdR = korCurrent;
    }
    private void setFourthR(){
        fourth_right.setText(WordListView.arrayDay[kor[korCurrent++]][2]);
        checkFourthR = korCurrent;
    }
    private void setFifthR(){
        fifth_right.setText(WordListView.arrayDay[kor[korCurrent++]][2]);
        checkFifthR = korCurrent;
    }

    @Override
    public void onClick(View view) {
        if (view == first_left) first_left.setVisibility(View.INVISIBLE);
        if (view == second_left) second_left.setVisibility(View.INVISIBLE);
        if (view == third_left) third_left.setVisibility(View.INVISIBLE);
        if (view == fourth_left) fourth_left.setVisibility(View.INVISIBLE);
        if (view == fifth_left) fifth_left.setVisibility(View.INVISIBLE);

        if (view == first_right) first_right.setVisibility(View.INVISIBLE);
        if (view == second_right) second_right.setVisibility(View.INVISIBLE);
        if (view == third_right) third_right.setVisibility(View.INVISIBLE);
        if (view == fourth_right) fourth_right.setVisibility(View.INVISIBLE);
        if (view == fifth_right) fifth_right.setVisibility(View.INVISIBLE);


        if (first_left.getVisibility() == View.INVISIBLE && first_right.getVisibility() == View.INVISIBLE) {
            if (eng[checkFirstL - 1] == kor[checkFirstR - 1]) {
                MySoundPlayer.play(MySoundPlayer.SUCCESS);
                counting_score.setText(Integer.toString(Integer.parseInt(counting_score.getText().toString()) + 100));                if (engCurrent != 20) {
                    setFirstL();
                    setFirstR();
                    first_left.setVisibility(View.VISIBLE);
                    first_right.setVisibility(View.VISIBLE);
                } else {
                    if (eng[checkFirstL - 1] == kor[checkFirstR - 1]) {
                        first_left.setText("이 칸의 마지막입니다.");
                        first_right.setText("이 칸의 마지막입니다.");
                        first_left.setVisibility(View.VISIBLE);
                        first_right.setVisibility(View.VISIBLE);
                        first_left.setEnabled(false);
                        first_right.setEnabled(false);
                    }
                }
            } else {
                counting_score.setText(Integer.toString(Integer.parseInt(counting_score.getText().toString()) - 50));
                MySoundPlayer.play(MySoundPlayer.FAIL);
                first_left.setVisibility(View.VISIBLE);
                first_right.setVisibility(View.VISIBLE);
            }
        }
        if (first_left.getVisibility() == View.INVISIBLE && second_right.getVisibility() == View.INVISIBLE) {
            if (eng[checkFirstL - 1] == kor[checkSecondR - 1]) {
                counting_score.setText(Integer.toString(Integer.parseInt(counting_score.getText().toString()) + 100));                MySoundPlayer.play(MySoundPlayer.SUCCESS);
                if (engCurrent != 20) {
                    setFirstL();
                    setSecondR();
                    first_left.setVisibility(View.VISIBLE);
                    second_right.setVisibility(View.VISIBLE);
                } else {
                    if (eng[checkFirstL - 1] == kor[checkSecondR - 1]) {
                        first_left.setText("이 칸의 마지막입니다.");
                        second_right.setText("이 칸의 마지막입니다.");
                        first_left.setVisibility(View.VISIBLE);
                        second_right.setVisibility(View.VISIBLE);
                        first_left.setEnabled(false);
                        second_right.setEnabled(false);
                    }
                }
            } else {
                counting_score.setText(Integer.toString(Integer.parseInt(counting_score.getText().toString()) - 50));
                MySoundPlayer.play(MySoundPlayer.FAIL);
                first_left.setVisibility(View.VISIBLE);
                second_right.setVisibility(View.VISIBLE);
            }
        }
        if (first_left.getVisibility() == View.INVISIBLE && third_right.getVisibility() == View.INVISIBLE) {
            if (eng[checkFirstL - 1] == kor[checkThirdR - 1]) {
                counting_score.setText(Integer.toString(Integer.parseInt(counting_score.getText().toString()) + 100));                MySoundPlayer.play(MySoundPlayer.SUCCESS);
                if (engCurrent != 20) {
                    setFirstL();
                    setThirdR();
                    first_left.setVisibility(View.VISIBLE);
                    third_right.setVisibility(View.VISIBLE);
                } else {
                    if (eng[checkFirstL - 1] == kor[checkThirdR - 1]) {
                        first_left.setText("이 칸의 마지막입니다.");
                        third_right.setText("이 칸의 마지막입니다.");
                        first_left.setVisibility(View.VISIBLE);
                        third_right.setVisibility(View.VISIBLE);
                        first_left.setEnabled(false);
                        third_right.setEnabled(false);
                    }
                }
            } else {
                counting_score.setText(Integer.toString(Integer.parseInt(counting_score.getText().toString()) - 50));
                MySoundPlayer.play(MySoundPlayer.FAIL);
                first_left.setVisibility(View.VISIBLE);
                third_right.setVisibility(View.VISIBLE);
            }
        }
        if (first_left.getVisibility() == View.INVISIBLE && fourth_right.getVisibility() == View.INVISIBLE) {
            if (eng[checkFirstL - 1] == kor[checkFourthR - 1]) {
                counting_score.setText(Integer.toString(Integer.parseInt(counting_score.getText().toString()) + 100));                MySoundPlayer.play(MySoundPlayer.SUCCESS);
                if (engCurrent != 20) {
                    setFirstL();
                    setFourthR();
                    first_left.setVisibility(View.VISIBLE);
                    fourth_right.setVisibility(View.VISIBLE);
                } else {
                    if (eng[checkFirstL - 1] == kor[checkFourthR - 1]) {
                        first_left.setText("이 칸의 마지막입니다.");
                        fourth_right.setText("이 칸의 마지막입니다.");
                        first_left.setVisibility(View.VISIBLE);
                        fourth_right.setVisibility(View.VISIBLE);
                        first_left.setEnabled(false);
                        fourth_right.setEnabled(false);
                    }
                }
            } else {
                counting_score.setText(Integer.toString(Integer.parseInt(counting_score.getText().toString()) - 50));
                MySoundPlayer.play(MySoundPlayer.FAIL);
                first_left.setVisibility(View.VISIBLE);
                fourth_right.setVisibility(View.VISIBLE);
            }
        }
        if (first_left.getVisibility() == View.INVISIBLE && fifth_right.getVisibility() == View.INVISIBLE) {
            if (eng[checkFirstL - 1] == kor[checkFifthR - 1]) {
                counting_score.setText(Integer.toString(Integer.parseInt(counting_score.getText().toString()) + 100));
                MySoundPlayer.play(MySoundPlayer.SUCCESS);
                if (engCurrent != 20) {
                    setFirstL();
                    setFifthR();
                    first_left.setVisibility(View.VISIBLE);
                    fifth_right.setVisibility(View.VISIBLE);
                } else {
                    if (eng[checkFirstL - 1] == kor[checkFifthR - 1]) {
                        MySoundPlayer.play(MySoundPlayer.SUCCESS);
                        first_left.setText("이 칸의 마지막입니다.");
                        fifth_right.setText("이 칸의 마지막입니다.");
                        first_left.setVisibility(View.VISIBLE);
                        fifth_right.setVisibility(View.VISIBLE);
                        first_left.setEnabled(false);
                        fifth_right.setEnabled(false);
                    }
                }
            } else {
                counting_score.setText(Integer.toString(Integer.parseInt(counting_score.getText().toString()) - 50));
                MySoundPlayer.play(MySoundPlayer.FAIL);
                first_left.setVisibility(View.VISIBLE);
                fifth_right.setVisibility(View.VISIBLE);
            }
        }
        if (second_left.getVisibility() == View.INVISIBLE && first_right.getVisibility() == View.INVISIBLE) {
            if (eng[checkSecondL - 1] == kor[checkFirstR - 1]) {
                counting_score.setText(Integer.toString(Integer.parseInt(counting_score.getText().toString()) + 100));
                MySoundPlayer.play(MySoundPlayer.SUCCESS);
                if (engCurrent != 20) {
                    setSecondL();
                    setFirstR();
                    second_left.setVisibility(View.VISIBLE);
                    first_right.setVisibility(View.VISIBLE);
                } else {
                    if (eng[checkSecondL - 1] == kor[checkFirstR - 1]) {
                        MySoundPlayer.play(MySoundPlayer.SUCCESS);
                        second_left.setText("이 칸의 마지막입니다.");
                        first_right.setText("이 칸의 마지막입니다.");
                        second_left.setVisibility(View.VISIBLE);
                        first_right.setVisibility(View.VISIBLE);
                        second_left.setEnabled(false);
                        first_right.setEnabled(false);
                    }
                }
            } else {
                counting_score.setText(Integer.toString(Integer.parseInt(counting_score.getText().toString()) - 50));
                MySoundPlayer.play(MySoundPlayer.FAIL);
                second_left.setVisibility(View.VISIBLE);
                first_right.setVisibility(View.VISIBLE);
            }
        }
        if (second_left.getVisibility() == View.INVISIBLE && second_right.getVisibility() == View.INVISIBLE) {
            if (eng[checkSecondL - 1] == kor[checkSecondR - 1]) {
                counting_score.setText(Integer.toString(Integer.parseInt(counting_score.getText().toString()) + 100));
                MySoundPlayer.play(MySoundPlayer.SUCCESS);
                if (engCurrent != 20) {
                    setSecondL();
                    setSecondR();
                    second_left.setVisibility(View.VISIBLE);
                    second_right.setVisibility(View.VISIBLE);
                } else {
                    if (eng[checkSecondL - 1] == kor[checkSecondR - 1]) {
                        MySoundPlayer.play(MySoundPlayer.SUCCESS);
                        second_left.setText("이 칸의 마지막입니다.");
                        second_right.setText("이 칸의 마지막입니다.");
                        second_left.setVisibility(View.VISIBLE);
                        second_right.setVisibility(View.VISIBLE);
                        second_left.setEnabled(false);
                        second_right.setEnabled(false);
                    }
                }
            } else {
                counting_score.setText(Integer.toString(Integer.parseInt(counting_score.getText().toString()) - 50));
                MySoundPlayer.play(MySoundPlayer.FAIL);
                second_left.setVisibility(View.VISIBLE);
                second_right.setVisibility(View.VISIBLE);
            }
        }
        if (second_left.getVisibility() == View.INVISIBLE && third_right.getVisibility() == View.INVISIBLE) {
            if (eng[checkSecondL - 1] == kor[checkThirdR - 1]) {
                counting_score.setText(Integer.toString(Integer.parseInt(counting_score.getText().toString()) + 100));
                MySoundPlayer.play(MySoundPlayer.SUCCESS);
                if (engCurrent != 20) {
                    setSecondL();
                    setThirdR();
                    second_left.setVisibility(View.VISIBLE);
                    third_right.setVisibility(View.VISIBLE);
                } else {
                    if (eng[checkSecondL - 1] == kor[checkThirdR - 1]) {
                        MySoundPlayer.play(MySoundPlayer.SUCCESS);
                        second_left.setText("이 칸의 마지막입니다.");
                        third_right.setText("이 칸의 마지막입니다.");
                        second_left.setVisibility(View.VISIBLE);
                        third_right.setVisibility(View.VISIBLE);
                        second_left.setEnabled(false);
                        third_right.setEnabled(false);
                    }
                }
            } else {
                counting_score.setText(Integer.toString(Integer.parseInt(counting_score.getText().toString()) - 50));
                MySoundPlayer.play(MySoundPlayer.FAIL);
                second_left.setVisibility(View.VISIBLE);
                third_right.setVisibility(View.VISIBLE);
            }
        }
        if (second_left.getVisibility() == View.INVISIBLE && fourth_right.getVisibility() == View.INVISIBLE) {
            if (eng[checkSecondL - 1] == kor[checkFourthR - 1]) {
                counting_score.setText(Integer.toString(Integer.parseInt(counting_score.getText().toString()) + 100));
                MySoundPlayer.play(MySoundPlayer.SUCCESS);
                if (engCurrent != 20) {
                    setSecondL();
                    setFourthR();
                    second_left.setVisibility(View.VISIBLE);
                    fourth_right.setVisibility(View.VISIBLE);
                } else {
                    if (eng[checkSecondL - 1] == kor[checkFourthR - 1]) {
                        MySoundPlayer.play(MySoundPlayer.SUCCESS);
                        second_left.setText("이 칸의 마지막입니다.");
                        fourth_right.setText("이 칸의 마지막입니다.");
                        second_left.setVisibility(View.VISIBLE);
                        fourth_right.setVisibility(View.VISIBLE);
                        second_left.setEnabled(false);
                        fourth_right.setEnabled(false);
                    }
                }
            } else {
                counting_score.setText(Integer.toString(Integer.parseInt(counting_score.getText().toString()) - 50));
                MySoundPlayer.play(MySoundPlayer.FAIL);
                second_left.setVisibility(View.VISIBLE);
                fourth_right.setVisibility(View.VISIBLE);
            }
        }
        if (second_left.getVisibility() == View.INVISIBLE && fifth_right.getVisibility() == View.INVISIBLE) {
            if (eng[checkSecondL - 1] == kor[checkFifthR - 1]) {
                counting_score.setText(Integer.toString(Integer.parseInt(counting_score.getText().toString()) + 100));
                MySoundPlayer.play(MySoundPlayer.SUCCESS);
                if (engCurrent != 20) {
                    setSecondL();
                    setFifthR();
                    second_left.setVisibility(View.VISIBLE);
                    fifth_right.setVisibility(View.VISIBLE);
                } else {
                    if (eng[checkSecondL - 1] == kor[checkFifthR - 1]) {
                        MySoundPlayer.play(MySoundPlayer.SUCCESS);
                        second_left.setText("이 칸의 마지막입니다.");
                        fifth_right.setText("이 칸의 마지막입니다.");
                        second_left.setVisibility(View.VISIBLE);
                        fifth_right.setVisibility(View.VISIBLE);
                        second_left.setEnabled(false);
                        fifth_right.setEnabled(false);
                    }
                }
            } else {
                counting_score.setText(Integer.toString(Integer.parseInt(counting_score.getText().toString()) - 50));
                MySoundPlayer.play(MySoundPlayer.FAIL);
                second_left.setVisibility(View.VISIBLE);
                fifth_right.setVisibility(View.VISIBLE);
            }
        }
        if (third_left.getVisibility() == View.INVISIBLE && first_right.getVisibility() == View.INVISIBLE) {
            if (eng[checkThirdL - 1] == kor[checkFirstR - 1]) {
                counting_score.setText(Integer.toString(Integer.parseInt(counting_score.getText().toString()) + 100));
                MySoundPlayer.play(MySoundPlayer.SUCCESS);
                if (engCurrent != 20) {
                    setThirdL();
                    setFirstR();
                    third_left.setVisibility(View.VISIBLE);
                    first_right.setVisibility(View.VISIBLE);
                } else {
                    if (eng[checkThirdL - 1] == kor[checkFirstR - 1]) {
                        MySoundPlayer.play(MySoundPlayer.SUCCESS);
                        third_left.setText("이 칸의 마지막입니다.");
                        first_right.setText("이 칸의 마지막입니다.");
                        third_left.setVisibility(View.VISIBLE);
                        first_right.setVisibility(View.VISIBLE);
                        third_left.setEnabled(false);
                        first_right.setEnabled(false);
                    }
                }
            } else {
                counting_score.setText(Integer.toString(Integer.parseInt(counting_score.getText().toString()) - 50));
                MySoundPlayer.play(MySoundPlayer.FAIL);
                third_left.setVisibility(View.VISIBLE);
                first_right.setVisibility(View.VISIBLE);
            }
        }
        if (third_left.getVisibility() == View.INVISIBLE && second_right.getVisibility() == View.INVISIBLE) {
            if (eng[checkThirdL - 1] == kor[checkSecondR - 1]) {
                counting_score.setText(Integer.toString(Integer.parseInt(counting_score.getText().toString()) + 100));
                MySoundPlayer.play(MySoundPlayer.SUCCESS);
                if (engCurrent != 20) {
                    setThirdL();
                    setSecondR();
                    third_left.setVisibility(View.VISIBLE);
                    second_right.setVisibility(View.VISIBLE);
                } else {
                    if (eng[checkThirdL - 1] == kor[checkSecondR - 1]) {
                        MySoundPlayer.play(MySoundPlayer.SUCCESS);
                        third_left.setText("이 칸의 마지막입니다.");
                        second_right.setText("이 칸의 마지막입니다.");
                        third_left.setVisibility(View.VISIBLE);
                        second_right.setVisibility(View.VISIBLE);
                        third_left.setEnabled(false);
                        second_right.setEnabled(false);
                    }
                }
            } else {
                counting_score.setText(Integer.toString(Integer.parseInt(counting_score.getText().toString()) - 50));
                MySoundPlayer.play(MySoundPlayer.FAIL);
                third_left.setVisibility(View.VISIBLE);
                second_right.setVisibility(View.VISIBLE);
            }
        }
        if (third_left.getVisibility() == View.INVISIBLE && third_right.getVisibility() == View.INVISIBLE) {
            if (eng[checkThirdL - 1] == kor[checkThirdR - 1]) {
                counting_score.setText(Integer.toString(Integer.parseInt(counting_score.getText().toString()) + 100));
                MySoundPlayer.play(MySoundPlayer.SUCCESS);
                if (engCurrent != 20) {
                    setThirdL();
                    setThirdR();
                    third_left.setVisibility(View.VISIBLE);
                    third_right.setVisibility(View.VISIBLE);
                } else {
                    if (eng[checkThirdL - 1] == kor[checkThirdR - 1]) {
                        MySoundPlayer.play(MySoundPlayer.SUCCESS);
                        third_left.setText("이 칸의 마지막입니다.");
                        third_right.setText("이 칸의 마지막입니다.");
                        third_left.setVisibility(View.VISIBLE);
                        third_right.setVisibility(View.VISIBLE);
                        third_left.setEnabled(false);
                        third_right.setEnabled(false);
                    }
                }
            } else {
                counting_score.setText(Integer.toString(Integer.parseInt(counting_score.getText().toString()) - 50));
                MySoundPlayer.play(MySoundPlayer.FAIL);
                third_left.setVisibility(View.VISIBLE);
                third_right.setVisibility(View.VISIBLE);
            }
        }
        if (third_left.getVisibility() == View.INVISIBLE && fourth_right.getVisibility() == View.INVISIBLE) {
            if (eng[checkThirdL - 1] == kor[checkFourthR - 1]) {
                counting_score.setText(Integer.toString(Integer.parseInt(counting_score.getText().toString()) + 100));
                MySoundPlayer.play(MySoundPlayer.SUCCESS);
                if (engCurrent != 20) {
                    setThirdL();
                    setFourthR();
                    third_left.setVisibility(View.VISIBLE);
                    fourth_right.setVisibility(View.VISIBLE);
                } else {
                    if (eng[checkThirdL - 1] == kor[checkFourthR - 1]) {
                        MySoundPlayer.play(MySoundPlayer.SUCCESS);
                        third_left.setText("이 칸의 마지막입니다.");
                        fourth_right.setText("이 칸의 마지막입니다.");
                        third_left.setVisibility(View.VISIBLE);
                        fourth_right.setVisibility(View.VISIBLE);
                        third_left.setEnabled(false);
                        fourth_right.setEnabled(false);
                    }
                }
            } else {
                counting_score.setText(Integer.toString(Integer.parseInt(counting_score.getText().toString()) - 50));
                MySoundPlayer.play(MySoundPlayer.FAIL);
                third_left.setVisibility(View.VISIBLE);
                fourth_right.setVisibility(View.VISIBLE);
            }
        }
        if (third_left.getVisibility() == View.INVISIBLE && fifth_right.getVisibility() == View.INVISIBLE) {
            if (eng[checkThirdL - 1] == kor[checkFifthR - 1]) {
                counting_score.setText(Integer.toString(Integer.parseInt(counting_score.getText().toString()) + 100));
                MySoundPlayer.play(MySoundPlayer.SUCCESS);
                if (engCurrent != 20) {
                    setThirdL();
                    setFifthR();
                    third_left.setVisibility(View.VISIBLE);
                    fifth_right.setVisibility(View.VISIBLE);
                } else {
                    if (eng[checkThirdL - 1] == kor[checkFifthR - 1]) {
                        MySoundPlayer.play(MySoundPlayer.SUCCESS);
                        third_left.setText("이 칸의 마지막입니다.");
                        fifth_right.setText("이 칸의 마지막입니다.");
                        third_left.setVisibility(View.VISIBLE);
                        fifth_right.setVisibility(View.VISIBLE);
                        third_left.setEnabled(false);
                        fifth_right.setEnabled(false);
                    }
                }
            } else {
                counting_score.setText(Integer.toString(Integer.parseInt(counting_score.getText().toString()) - 50));
                MySoundPlayer.play(MySoundPlayer.FAIL);
                third_left.setVisibility(View.VISIBLE);
                fifth_right.setVisibility(View.VISIBLE);
            }
        }
        if (fourth_left.getVisibility() == View.INVISIBLE && first_right.getVisibility() == View.INVISIBLE) {
            if (eng[checkFourthL - 1] == kor[checkFirstR - 1]) {
                counting_score.setText(Integer.toString(Integer.parseInt(counting_score.getText().toString()) + 100));
                MySoundPlayer.play(MySoundPlayer.SUCCESS);
                if (engCurrent != 20) {
                    setFourthL();
                    setFirstR();
                    fourth_left.setVisibility(View.VISIBLE);
                    first_right.setVisibility(View.VISIBLE);
                } else {
                    if (eng[checkFourthL - 1] == kor[checkFirstR - 1]) {
                        MySoundPlayer.play(MySoundPlayer.SUCCESS);
                        fourth_left.setText("이 칸의 마지막입니다.");
                        first_right.setText("이 칸의 마지막입니다.");
                        fourth_left.setVisibility(View.VISIBLE);
                        first_right.setVisibility(View.VISIBLE);
                        fourth_left.setEnabled(false);
                        first_right.setEnabled(false);
                    }
                }
            } else {
                counting_score.setText(Integer.toString(Integer.parseInt(counting_score.getText().toString()) - 50));
                MySoundPlayer.play(MySoundPlayer.FAIL);
                fourth_left.setVisibility(View.VISIBLE);
                first_right.setVisibility(View.VISIBLE);
            }
        }
        if (fourth_left.getVisibility() == View.INVISIBLE && second_right.getVisibility() == View.INVISIBLE) {
            if (eng[checkFourthL - 1] == kor[checkSecondR - 1]) {
                counting_score.setText(Integer.toString(Integer.parseInt(counting_score.getText().toString()) + 100));
                MySoundPlayer.play(MySoundPlayer.SUCCESS);
                if (engCurrent != 20) {
                    setFourthL();
                    setSecondR();
                    fourth_left.setVisibility(View.VISIBLE);
                    second_right.setVisibility(View.VISIBLE);
                } else {
                    if (eng[checkFourthL - 1] == kor[checkSecondR - 1]) {
                        MySoundPlayer.play(MySoundPlayer.SUCCESS);
                        fourth_left.setText("이 칸의 마지막입니다.");
                        second_right.setText("이 칸의 마지막입니다.");
                        fourth_left.setVisibility(View.VISIBLE);
                        second_right.setVisibility(View.VISIBLE);
                        fourth_left.setEnabled(false);
                        second_right.setEnabled(false);
                    }
                }
            } else {
                counting_score.setText(Integer.toString(Integer.parseInt(counting_score.getText().toString()) - 50));
                MySoundPlayer.play(MySoundPlayer.FAIL);
                fourth_left.setVisibility(View.VISIBLE);
                second_right.setVisibility(View.VISIBLE);
            }
        }
        if (fourth_left.getVisibility() == View.INVISIBLE && third_right.getVisibility() == View.INVISIBLE) {
            if (eng[checkFourthL - 1] == kor[checkThirdR - 1]) {
                counting_score.setText(Integer.toString(Integer.parseInt(counting_score.getText().toString()) + 100));
                MySoundPlayer.play(MySoundPlayer.SUCCESS);
                if (engCurrent != 20) {
                    setFourthL();
                    setThirdR();
                    fourth_left.setVisibility(View.VISIBLE);
                    third_right.setVisibility(View.VISIBLE);
                } else {
                    if (eng[checkFourthL - 1] == kor[checkThirdR - 1]) {
                        MySoundPlayer.play(MySoundPlayer.SUCCESS);
                        fourth_left.setText("이 칸의 마지막입니다.");
                        third_right.setText("이 칸의 마지막입니다.");
                        fourth_left.setVisibility(View.VISIBLE);
                        third_right.setVisibility(View.VISIBLE);
                        fourth_left.setEnabled(false);
                        third_right.setEnabled(false);
                    }
                }
            } else {
                counting_score.setText(Integer.toString(Integer.parseInt(counting_score.getText().toString()) - 50));
                MySoundPlayer.play(MySoundPlayer.FAIL);
                fourth_left.setVisibility(View.VISIBLE);
                third_right.setVisibility(View.VISIBLE);
            }
        }
        if (fourth_left.getVisibility() == View.INVISIBLE && fourth_right.getVisibility() == View.INVISIBLE) {
            if (eng[checkFourthL - 1] == kor[checkFourthR - 1]) {
                counting_score.setText(Integer.toString(Integer.parseInt(counting_score.getText().toString()) + 100));
                MySoundPlayer.play(MySoundPlayer.SUCCESS);
                if (engCurrent != 20) {
                    setFourthL();
                    setFourthR();
                    fourth_left.setVisibility(View.VISIBLE);
                    fourth_right.setVisibility(View.VISIBLE);
                } else {
                    if (eng[checkFourthL - 1] == kor[checkFourthR - 1]) {
                        MySoundPlayer.play(MySoundPlayer.SUCCESS);
                        fourth_left.setText("이 칸의 마지막입니다.");
                        fourth_right.setText("이 칸의 마지막입니다.");
                        fourth_left.setVisibility(View.VISIBLE);
                        fourth_right.setVisibility(View.VISIBLE);
                        fourth_left.setEnabled(false);
                        fourth_right.setEnabled(false);
                    }
                }
            } else {
                counting_score.setText(Integer.toString(Integer.parseInt(counting_score.getText().toString()) - 50));
                MySoundPlayer.play(MySoundPlayer.FAIL);
                fourth_left.setVisibility(View.VISIBLE);
                fourth_right.setVisibility(View.VISIBLE);
            }
        }
        if (fourth_left.getVisibility() == View.INVISIBLE && fifth_right.getVisibility() == View.INVISIBLE) {
            if (eng[checkFourthL - 1] == kor[checkFifthR - 1]) {
                counting_score.setText(Integer.toString(Integer.parseInt(counting_score.getText().toString()) + 100));
                MySoundPlayer.play(MySoundPlayer.SUCCESS);
                if (engCurrent != 20) {
                    setFourthL();
                    setFifthR();
                    fourth_left.setVisibility(View.VISIBLE);
                    fifth_right.setVisibility(View.VISIBLE);
                } else {
                    if (eng[checkFourthL - 1] == kor[checkFifthR - 1]) {
                        MySoundPlayer.play(MySoundPlayer.SUCCESS);
                        fourth_left.setText("이 칸의 마지막입니다.");
                        fifth_right.setText("이 칸의 마지막입니다.");
                        fourth_left.setVisibility(View.VISIBLE);
                        fifth_right.setVisibility(View.VISIBLE);
                        fourth_left.setEnabled(false);
                        fifth_right.setEnabled(false);
                    }
                }
            } else {
                counting_score.setText(Integer.toString(Integer.parseInt(counting_score.getText().toString()) - 50));
                MySoundPlayer.play(MySoundPlayer.FAIL);
                fourth_left.setVisibility(View.VISIBLE);
                fifth_right.setVisibility(View.VISIBLE);
            }
        }
        if (fifth_left.getVisibility() == View.INVISIBLE && first_right.getVisibility() == View.INVISIBLE) {
            if (eng[checkFifthL - 1] == kor[checkFirstR - 1]) {
                counting_score.setText(Integer.toString(Integer.parseInt(counting_score.getText().toString()) + 100));
                MySoundPlayer.play(MySoundPlayer.SUCCESS);
                if (engCurrent != 20) {
                    setFifthL();
                    setFirstR();
                    fifth_left.setVisibility(View.VISIBLE);
                    first_right.setVisibility(View.VISIBLE);
                } else {
                    if (eng[checkFifthL - 1] == kor[checkFirstR - 1]) {
                        MySoundPlayer.play(MySoundPlayer.SUCCESS);
                        fifth_left.setText("이 칸의 마지막입니다.");
                        first_right.setText("이 칸의 마지막입니다.");
                        fifth_left.setVisibility(View.VISIBLE);
                        first_right.setVisibility(View.VISIBLE);
                        fifth_left.setEnabled(false);
                        first_right.setEnabled(false);
                    }
                }
            } else {
                counting_score.setText(Integer.toString(Integer.parseInt(counting_score.getText().toString()) - 50));
                MySoundPlayer.play(MySoundPlayer.FAIL);
                fifth_left.setVisibility(View.VISIBLE);
                first_right.setVisibility(View.VISIBLE);
            }
        }
        if (fifth_left.getVisibility() == View.INVISIBLE && second_right.getVisibility() == View.INVISIBLE) {
            if (eng[checkFifthL - 1] == kor[checkSecondR - 1]) {
                counting_score.setText(Integer.toString(Integer.parseInt(counting_score.getText().toString()) + 100));
                MySoundPlayer.play(MySoundPlayer.SUCCESS);
                if (engCurrent != 20) {
                    setFifthL();
                    setSecondR();
                    fifth_left.setVisibility(View.VISIBLE);
                    second_right.setVisibility(View.VISIBLE);
                } else {
                    if (eng[checkFifthL - 1] == kor[checkSecondR - 1]) {
                        MySoundPlayer.play(MySoundPlayer.SUCCESS);
                        fifth_left.setText("이 칸의 마지막입니다.");
                        second_right.setText("이 칸의 마지막입니다.");
                        fifth_left.setVisibility(View.VISIBLE);
                        second_right.setVisibility(View.VISIBLE);
                        fifth_left.setEnabled(false);
                        second_right.setEnabled(false);
                    }
                }
            } else {
                counting_score.setText(Integer.toString(Integer.parseInt(counting_score.getText().toString()) - 50));
                MySoundPlayer.play(MySoundPlayer.FAIL);
                fifth_left.setVisibility(View.VISIBLE);
                second_right.setVisibility(View.VISIBLE);
            }
        }
        if (fifth_left.getVisibility() == View.INVISIBLE && third_right.getVisibility() == View.INVISIBLE) {
            if (eng[checkFifthL - 1] == kor[checkThirdR - 1]) {
                counting_score.setText(Integer.toString(Integer.parseInt(counting_score.getText().toString()) + 100));
                MySoundPlayer.play(MySoundPlayer.SUCCESS);
                if (engCurrent != 20) {
                    setFifthL();
                    setThirdR();
                    fifth_left.setVisibility(View.VISIBLE);
                    third_right.setVisibility(View.VISIBLE);
                } else {
                    if (eng[checkFifthL - 1] == kor[checkThirdR - 1]) {
                        MySoundPlayer.play(MySoundPlayer.SUCCESS);
                        fifth_left.setText("이 칸의 마지막입니다.");
                        third_right.setText("이 칸의 마지막입니다.");
                        fifth_left.setVisibility(View.VISIBLE);
                        third_right.setVisibility(View.VISIBLE);
                        fifth_left.setEnabled(false);
                        third_right.setEnabled(false);
                    }
                }
            } else {
                counting_score.setText(Integer.toString(Integer.parseInt(counting_score.getText().toString()) - 50));
                MySoundPlayer.play(MySoundPlayer.FAIL);
                fifth_left.setVisibility(View.VISIBLE);
                third_right.setVisibility(View.VISIBLE);
            }
        }
        if (fifth_left.getVisibility() == View.INVISIBLE && fourth_right.getVisibility() == View.INVISIBLE) {
            if (eng[checkFifthL - 1] == kor[checkFourthR - 1]) {
                counting_score.setText(Integer.toString(Integer.parseInt(counting_score.getText().toString()) + 100));
                MySoundPlayer.play(MySoundPlayer.SUCCESS);
                if (engCurrent != 20) {
                    setFifthL();
                    setFourthR();
                    fifth_left.setVisibility(View.VISIBLE);
                    fourth_right.setVisibility(View.VISIBLE);
                } else {
                    if (eng[checkFifthL - 1] == kor[checkFourthR - 1]) {
                        MySoundPlayer.play(MySoundPlayer.SUCCESS);
                        fifth_left.setText("이 칸의 마지막입니다.");
                        fourth_right.setText("이 칸의 마지막입니다.");
                        fifth_left.setVisibility(View.VISIBLE);
                        fourth_right.setVisibility(View.VISIBLE);
                        fifth_left.setEnabled(false);
                        fourth_right.setEnabled(false);
                    }
                }
            } else {
                counting_score.setText(Integer.toString(Integer.parseInt(counting_score.getText().toString()) - 50));
                MySoundPlayer.play(MySoundPlayer.FAIL);
                fifth_left.setVisibility(View.VISIBLE);
                fourth_right.setVisibility(View.VISIBLE);
            }
        }
        if (fifth_left.getVisibility() == View.INVISIBLE && fifth_right.getVisibility() == View.INVISIBLE) {
            if (eng[checkFifthL - 1] == kor[checkFifthR - 1]) {
                counting_score.setText(Integer.toString(Integer.parseInt(counting_score.getText().toString()) + 100));
                MySoundPlayer.play(MySoundPlayer.SUCCESS);
                if (engCurrent != 20) {
                    setFifthL();
                    setFifthR();
                    fifth_left.setVisibility(View.VISIBLE);
                    fifth_right.setVisibility(View.VISIBLE);
                } else {
                    if (eng[checkFifthL - 1] == kor[checkFifthR - 1]) {
                        MySoundPlayer.play(MySoundPlayer.SUCCESS);
                        fifth_left.setText("이 칸의 마지막입니다.");
                        fifth_right.setText("이 칸의 마지막입니다.");
                        fifth_left.setVisibility(View.VISIBLE);
                        fifth_right.setVisibility(View.VISIBLE);
                        fifth_left.setEnabled(false);
                        fifth_right.setEnabled(false);
                    }
                }
            } else {
                counting_score.setText(Integer.toString(Integer.parseInt(counting_score.getText().toString()) - 50));
                MySoundPlayer.play(MySoundPlayer.FAIL);
                fifth_left.setVisibility(View.VISIBLE);
                fifth_right.setVisibility(View.VISIBLE);
            }
        }
        if (first_left.isEnabled() == false && second_left.isEnabled() == false && third_left.isEnabled() == false && fourth_left.isEnabled() == false && fifth_left.isEnabled() == false && first_right.isEnabled() == false && second_right.isEnabled() == false && third_right.isEnabled() == false && fourth_right.isEnabled() == false && fifth_right.isEnabled() == false) {
            tableLayout.setVisibility(View.INVISIBLE);
            success.setVisibility(View.VISIBLE);
        }
    }
}