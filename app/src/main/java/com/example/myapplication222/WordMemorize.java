package com.example.myapplication222;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class WordMemorize extends AppCompatActivity implements View.OnClickListener{

    private Button touchButton;
    private TextView showWord;
    private ImageButton colorStar;
    private ImageButton starButton;
    private ImageButton nextBtn;
    private ImageButton prevBtn;
    private TextView speaker;
    private TextView korText;
    private int current=0;  //현재 출력되고 있는 배열의 번호

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memorize);

        touchButton = (Button) findViewById(R.id.touchButton);
        touchButton.setOnClickListener(this);

        showWord = (TextView) findViewById(R.id.showWord);
        showWord.setOnClickListener(this);

        starButton = (ImageButton) findViewById(R.id.starButton);
        starButton.setOnClickListener(this);

        colorStar = (ImageButton) findViewById(R.id.colorStar);
        colorStar.setOnClickListener(this);

        nextBtn = (ImageButton)findViewById(R.id.mean_next_btn);
        nextBtn.setOnClickListener(this);

        prevBtn =(ImageButton)findViewById(R.id.mean_prev_btn);
        prevBtn.setOnClickListener(this);

        speaker=(TextView)findViewById(R.id.speaker);
        korText = (TextView)findViewById(R.id.mean_kor_text);

        //초기값 세팅
        if(Integer.parseInt(WordListView.arrayDay[current][5])==1 ){    //중요단어라면 칠해진 별인 채로 출력
            colorStar.setVisibility(View.VISIBLE);
        }
        korText.setText(WordListView.arrayDay[current][1]);
        showWord.setText(WordListView.arrayDay[current][2]);
        speaker.setText(WordListView.arrayDay[current][3]);
        current++;
    }

    @Override
    public void onClick(View view) {
        if (view == touchButton) {
            touchButton.setVisibility(View.INVISIBLE);
        }
        if (view == showWord) {
            touchButton.setVisibility(View.VISIBLE);
        }
        if (view == starButton) {
            colorStar.setVisibility(View.VISIBLE);
            update(WordListView.arrayDay[(current-1)][1], 1);  //중요단어 체크
        }
        if (view == colorStar) {
            colorStar.setVisibility(View.INVISIBLE);
            update(WordListView.arrayDay[(current-1)][1], 0);  //중요단어 해제

        }
        if(view == nextBtn){
            if(current==20) {
                Intent intent = new Intent(getApplicationContext(), FinishMemorize.class);
                startActivity(intent);
            }else {
                setText();
            }
        }
        if (view==prevBtn){
            current--;
            if(current==0){
                Intent intent = new Intent(getApplicationContext(), MemorizeSellect.class);
                startActivity(intent);
            }else{
                current--;
                setText();
            }
        }
    }
    private void setText(){
        //별색 칠해져 출력하느냐 아니냐
        if(Integer.parseInt(WordListView.arrayDay[current][5])==1){    //중요단어라면 칠해진 별인 채로 출력
            colorStar.setVisibility(View.VISIBLE);
        }else{
            colorStar.setVisibility(View.INVISIBLE);
        }
        touchButton.setVisibility(View.VISIBLE);
        korText.setText(WordListView.arrayDay[current][1]);
        showWord.setText(WordListView.arrayDay[current][2]);
        speaker.setText(WordListView.arrayDay[current][3]);
        current++;
    }
    //별 눌렀을때! db랑 배열 값 바꾸기
    private void update(String eng, int star) {
        //배열 값 변경
        WordListView.arrayDay[(current-1)][5]=Integer.toString(star);

        //db 값 변경
        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        // 입력한 항목과 일치하는 행의 가격 정보 수정
        db.execSQL("UPDATE tb_voca SET star=" + star + " WHERE eng='" + eng + "';");
        db.close();
    }
}
