package com.example.myapplication222;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


//TODO : 제출 버튼 누르고 맞으면 답안 텍스트 지우기
//TODO : 위에 추천어 안 나오는 키보드로

public class SpellingTest extends AppCompatActivity implements View.OnClickListener {

    private TextView spelling_test_kor;
    private TextView spelling_test_hint;
    private EditText spelling_test_eng;
    private Button spelling_test_submit_btn;
    private Button spelling_test_hint_btn;
    private Button spelling_test_pass_btn;
    private ImageView spelling_test_correct;
    private ImageView spelling_test_wrong;
    private ImageButton star_btn;
    private ImageButton colorStar_btn;

    private int wrong;
    private int total=20;
    private int current=0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spelling_test);

        spelling_test_submit_btn = findViewById(R.id.spelling_test_submit_btn);
        spelling_test_submit_btn.setOnClickListener(this);

        spelling_test_hint_btn = findViewById(R.id.spelling_test_hint_btn);
        spelling_test_hint_btn.setOnClickListener(this);

        spelling_test_pass_btn = findViewById(R.id.spelling_test_pass_btn);
        spelling_test_pass_btn.setOnClickListener(this);

        spelling_test_hint = findViewById(R.id.spelling_test_hint);
        spelling_test_hint.setText(WordListView.arrayDay[current][1]);

        spelling_test_kor = findViewById(R.id.spelling_test_kor);
        spelling_test_kor.setText(WordListView.arrayDay[current][2]);

        spelling_test_eng = findViewById(R.id.spelling_test_eng);

        spelling_test_correct = findViewById(R.id.spelling_test_correct);
        spelling_test_wrong = findViewById(R.id.spelling_test_wrong);

        star_btn = (ImageButton) findViewById(R.id.star_btn);
        star_btn.setOnClickListener(this);

        colorStar_btn = (ImageButton) findViewById(R.id.colorStar_btn);
        colorStar_btn.setOnClickListener(this);

        if(Integer.parseInt(WordListView.arrayDay[current][5])==1 ){    //중요단어라면 칠해진 별인 채로 출력
            colorStar_btn.setVisibility(View.VISIBLE);
        }
        MySoundPlayer.initSounds(getApplicationContext());
    }

    @Override
    public void onClick(View view) {
        if (view == star_btn) {
            colorStar_btn.setVisibility(View.VISIBLE);
            updateStar(WordListView.arrayDay[(current)][1], 1);  //중요단어 체크
        }
        if (view == colorStar_btn) {
            colorStar_btn.setVisibility(View.INVISIBLE);
            updateStar(WordListView.arrayDay[(current)][1], 0);  //중요단어 해제
        }
        if (view == spelling_test_kor) {
            setText();
        }
        if (view == spelling_test_submit_btn) {
            if (spelling_test_eng.getText().toString().equals(WordListView.arrayDay[current][1])) {
                MySoundPlayer.play(MySoundPlayer.SUCCESS);
                Animation correct = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.alpha);
                spelling_test_correct.startAnimation(correct);
                current++;
                wrong++;
                if(current==20){
                    Intent intent = new Intent(getApplicationContext(), FinishTest.class);
                    intent.putExtra("wrong",wrong);
                    intent.putExtra("total",total);
                    startActivity(intent);
                    finish();
                }
                setText();
            }
            else {
                MySoundPlayer.play(MySoundPlayer.FAIL);
                Animation fail = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.alpha);
                spelling_test_wrong.startAnimation(fail);
                updateStar(WordListView.arrayDay[(current)][1], 1);
            }
        }
        if (view == spelling_test_hint_btn) {
            Animation hint = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.alpha2);
            spelling_test_hint.startAnimation(hint);
            spelling_test_pass_btn.setVisibility(View.VISIBLE);
            spelling_test_hint_btn.setVisibility(View.INVISIBLE);
        }
        if (view == spelling_test_pass_btn) {
            MySoundPlayer.play(MySoundPlayer.FAIL);
            Animation fail = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.alpha);
            spelling_test_wrong.startAnimation(fail);
            current++;  //순서
            updateStar(WordListView.arrayDay[(current)][1], 1);

            if(current==20){
                Intent intent = new Intent(getApplicationContext(), FinishTest.class);
                intent.putExtra("wrong",wrong);
                intent.putExtra("total",total);
                startActivity(intent);
                finish();
            }

            setText();
            spelling_test_pass_btn.setVisibility(View.INVISIBLE);
            spelling_test_hint_btn.setVisibility(View.VISIBLE);
        }

    }

    private void setText() {
        spelling_test_kor.setText(WordListView.arrayDay[current][2]);
        spelling_test_hint.setText(WordListView.arrayDay[current][1]);
        spelling_test_eng.setText(null);

        if (Integer.parseInt(WordListView.arrayDay[current][5])==1){    //중요단어라면 칠해진 별인 채로 출력
            colorStar_btn.setVisibility(View.VISIBLE);
        }else{
            colorStar_btn.setVisibility(View.INVISIBLE);
        }
    }
    private void updateStar(String eng, int star) {
        //배열 값 변경
        WordListView.arrayDay[(current-1)][5]=Integer.toString(star);

        //db 값 변경
        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        // 입력한 항목과 일치하는 행의 가격 정보 수정
        db.execSQL("UPDATE tb_voca SET star=" + star + " WHERE eng='" + eng + "';");
        db.close();
    }

    private void updateNope(String eng, int nope) {
        //배열 값 변경
        WordListView.arrayDay[(current)][6]=Integer.toString(nope);

        //db 값 변경
        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        // 입력한 항목과 일치하는 행의 가격 정보 수정
        db.execSQL("UPDATE tb_voca SET nope=" + nope + " WHERE eng='" + eng + "';");
        db.close();
    }
}