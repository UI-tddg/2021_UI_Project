package com.example.myapplication222;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.media.SoundPool;
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
    private EditText spelling_test_eng;
    private Button spelling_test_submit_btn;
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
        if(view == spelling_test_submit_btn) {
            if (spelling_test_eng.getText().toString().equals(ListView.arrayDay[current][1])) {
                MySoundPlayer.play(MySoundPlayer.SUCCESS);
                Animation correct = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.alpha);
                spelling_test_correct.startAnimation(correct);
                setText();
            }
            else {
                MySoundPlayer.play(MySoundPlayer.FAIL);
                Animation fail = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.alpha);
                spelling_test_wrong.startAnimation(fail);
            }
        }
        if(view == nextBtn) {
            setText();
            spelling_test_correct.setVisibility(View.INVISIBLE);
            spelling_test_wrong.setVisibility(View.INVISIBLE);
        }
    }

    private void setText() {
        current++;
        spelling_test_kor.setText(ListView.arrayDay[current][2]);
        spelling_test_correct.setVisibility(View.INVISIBLE);
        spelling_test_wrong.setVisibility(View.INVISIBLE);
    }
}