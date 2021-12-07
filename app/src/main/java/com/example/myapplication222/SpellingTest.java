package com.example.myapplication222;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class SpellingTest extends AppCompatActivity implements View.OnClickListener {

    private TextView spelling_test_kor;
    private TextView spelling_test_hint;
    private EditText spelling_test_eng;
    private Button spelling_test_submit_btn;
    private Button spelling_test_hint_btn;
    private Button spelling_test_pass_btn;
    private ImageButton nextBtn;
    private ImageView spelling_test_correct;
    private ImageView spelling_test_wrong;
    private int current=0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spelling_test);

        nextBtn = findViewById(R.id.spelling_text_next_btn);
        nextBtn.setOnClickListener(this);

        spelling_test_submit_btn = findViewById(R.id.spelling_test_submit_btn);
        spelling_test_submit_btn.setOnClickListener(this);

        spelling_test_hint_btn = findViewById(R.id.spelling_test_hint_btn);
        spelling_test_hint_btn.setOnClickListener(this);

        spelling_test_pass_btn = findViewById(R.id.spelling_test_pass_btn);
        spelling_test_pass_btn.setOnClickListener(this);

        spelling_test_hint = findViewById(R.id.spelling_test_hint);
        spelling_test_hint.setText(ListView.arrayDay[current][1]);

        spelling_test_kor = findViewById(R.id.spelling_test_kor);
        spelling_test_kor.setText(ListView.arrayDay[current][2]);

        spelling_test_eng = findViewById(R.id.spelling_test_eng);

        spelling_test_correct = findViewById(R.id.spelling_test_correct);
        spelling_test_wrong = findViewById(R.id.spelling_test_wrong);

        MySoundPlayer.initSounds(getApplicationContext());
    }

    @Override
    public void onClick(View view) {
        if (view == spelling_test_kor) {
            setText();
        }
        if (view == spelling_test_submit_btn) {
            if (spelling_test_eng.getText().toString().equals(ListView.arrayDay[current][1])) {
                MySoundPlayer.play(MySoundPlayer.SUCCESS);
                Animation correct = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.alpha);
                spelling_test_correct.startAnimation(correct);
                current++;
                setText();
            }
            else {
                MySoundPlayer.play(MySoundPlayer.FAIL);
                Animation fail = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.alpha);
                spelling_test_wrong.startAnimation(fail);
            }
        }
        if (view == nextBtn) {
            MySoundPlayer.play(MySoundPlayer.FAIL);
            Animation fail = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.alpha);
            spelling_test_wrong.startAnimation(fail);
            current++;
            setText();
        }
        if (view == spelling_test_hint_btn) {
            Animation hint = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.alpha2);
            spelling_test_hint.startAnimation(hint);
            spelling_test_pass_btn.setVisibility(View.VISIBLE);
        }
        if (view == spelling_test_pass_btn) {
            MySoundPlayer.play(MySoundPlayer.FAIL);
            Animation fail = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.alpha);
            spelling_test_wrong.startAnimation(fail);
            current++;
            setText();
            spelling_test_pass_btn.setVisibility(View.INVISIBLE);
        }
    }

    private void setText() {
        spelling_test_kor.setText(ListView.arrayDay[current][2]);
        spelling_test_hint.setText(ListView.arrayDay[current][1]);
    }
}