package com.example.myapplication222;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;
import java.util.Timer;

public class MeaningTest extends AppCompatActivity implements View.OnClickListener {

    private ImageButton star_btn;
    private ImageButton colorstar_btn;
    private TextView meantest_eng;
    private Button sellect1;
    private Button sellect2;
    private Button sellect3;
    private Button sellect4;
    private int answer_num;
    private int current=0;
    private ImageView correct_img;
    private ImageView wrong_img;
    private int wrong=0;
    private int total=20;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meaning_test);

        meantest_eng=(TextView)findViewById(R.id.meantest_eng);

        star_btn=(ImageButton)findViewById(R.id.mean_star_btn);
        star_btn.setOnClickListener(this);

        colorstar_btn=(ImageButton)findViewById(R.id.mean_colorstar_btn);
        colorstar_btn.setOnClickListener(this);

        sellect1=(Button) findViewById(R.id.meantest_kor_btn1);
        sellect1.setOnClickListener(this);

        sellect2=(Button) findViewById(R.id.meantest_kor_btn2);
        sellect2.setOnClickListener(this);

        sellect3=(Button) findViewById(R.id.meantest_kor_btn3);
        sellect3.setOnClickListener(this);

        sellect4=(Button) findViewById(R.id.meantest_kor_btn4);
        sellect4.setOnClickListener(this);

        correct_img=findViewById(R.id.mean_test_correct);
        wrong_img = findViewById(R.id.mean_test_wrong);

        MySoundPlayer.initSounds(getApplicationContext());


        //????????????
        if(Integer.parseInt(WordListView.arrayDay[current][5])==1 ){    //?????????????????? ????????? ?????? ?????? ??????
            colorstar_btn.setVisibility(View.VISIBLE);
        }
        randomSetting();
    }

    @Override
    public void onClick(View view) {
        if(view ==star_btn){
            colorstar_btn.setVisibility(View.VISIBLE);
            update(5,1);
        }
        if(view==colorstar_btn){
            colorstar_btn.setVisibility(View.INVISIBLE);
            update(5,0);
        }
        if(view==sellect1){
            if(answer_num==1){
                correct();
            }else {
                wrong();
            }
        }
        if(view==sellect2){
            if(answer_num==2){
                correct();
            }else {
                wrong();
            }
        }
        if(view==sellect3){
            if(answer_num==3){
                correct();
            }else{
                wrong();
            }
        }if(view==sellect4){
            if(answer_num==4){
                correct();
            }else{
                wrong();
            }
        }
    }

    private void randomSetting(){
        //????????? ??????
        Random random = new Random();
        random.setSeed(System.currentTimeMillis());
        answer_num=(int)(Math.random()*4+1);


        int[] num = {-1,-1,-1};
        //?????? ??? ??????
        for(int i=0; i<3;){
            int tmp=(int)(Math.random()*20); //??????
            if(tmp!=current){
                for(int k=0; k<3; k++){
                    if(num[k]==tmp)
                        continue;
                }
                num[i]=tmp;
                i++;
            }
        }

        meantest_eng.setText(WordListView.arrayDay[current][1]);
        //text??? ??????
        if(answer_num==1){
            sellect1.setText(WordListView.arrayDay[current][2]);
            sellect2.setText(WordListView.arrayDay[num[0]][2]);
            sellect3.setText(WordListView.arrayDay[num[1]][2]);
            sellect4.setText(WordListView.arrayDay[num[2]][2]);
        }else if(answer_num==2){
            sellect2.setText(WordListView.arrayDay[current][2]);
            sellect1.setText(WordListView.arrayDay[num[0]][2]);
            sellect3.setText(WordListView.arrayDay[num[1]][2]);
            sellect4.setText(WordListView.arrayDay[num[2]][2]);
        }else if(answer_num==3){
            sellect3.setText(WordListView.arrayDay[current][2]);
            sellect2.setText(WordListView.arrayDay[num[0]][2]);
            sellect1.setText(WordListView.arrayDay[num[1]][2]);
            sellect4.setText(WordListView.arrayDay[num[2]][2]);
        }else if(answer_num==4){
            sellect4.setText(WordListView.arrayDay[current][2]);
            sellect2.setText(WordListView.arrayDay[num[0]][2]);
            sellect3.setText(WordListView.arrayDay[num[1]][2]);
            sellect1.setText(WordListView.arrayDay[num[2]][2]);
        }
    }

    private void starSetting(){
        if(Integer.parseInt(WordListView.arrayDay[current][5])==1){
            colorstar_btn.setVisibility(View.VISIBLE);
        }else{
            colorstar_btn.setVisibility(View.INVISIBLE);
        }
    }

    //????????????
    private void correct(){
        MySoundPlayer.play(MySoundPlayer.SUCCESS);
        Animation correct = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.alpha);
        correct_img.startAnimation(correct);
        current++;
        wrong++;
        if(current==20){
            Intent intent = new Intent(getApplicationContext(), FinishTest.class);
            intent.putExtra("wrong",wrong);
            intent.putExtra("total",total);
            startActivity(intent);
            finish();
        }else {
            meantest_eng.setText(WordListView.arrayDay[current][1]);
            randomSetting(); //????????? ????????? ?????????
            starSetting();  //??? ????????? ?????? or not
        }
    }

    private void wrong(){
        MySoundPlayer.play(MySoundPlayer.FAIL);
        Animation correct = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.alpha);
        wrong_img.startAnimation(correct);
        update(6,1); //?????? ??? db ??? nope ?????? ??????????????? ????????????
        current++;
        if(current==20){
            Intent intent = new Intent(getApplicationContext(), FinishTest.class);
            intent.putExtra("wrong", wrong);
            intent.putExtra("total",total);
            startActivity(intent);
            finish();
        }else{
            meantest_eng.setText(WordListView.arrayDay[current][1]);
            randomSetting();  //????????? ????????? ?????????
            starSetting();   //??? ????????? ?????? or not
        }
    }

    //index-> ????????? ???, num->
    private void update(int index, int num){
        //?????? ??? ??????
        WordListView.arrayDay[current][index]=Integer.toString(num);

        //db ??? ??????
        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        // ????????? ????????? ???????????? ?????? ?????? ?????? ??????
        //???????????? ????????????
        if(index==5){
            db.execSQL("UPDATE tb_voca SET star=" + num + " WHERE eng='" + WordListView.arrayDay[current][1] + "';");
        }
        //???????????? ????????????
        if(index==6){
            db.execSQL("UPDATE tb_voca SET nope=" + num + " WHERE eng='" + WordListView.arrayDay[current][1] + "';");
        }
        db.close();
    }
}