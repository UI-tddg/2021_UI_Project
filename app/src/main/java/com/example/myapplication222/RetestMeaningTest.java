package com.example.myapplication222;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class RetestMeaningTest extends AppCompatActivity implements View.OnClickListener {

    private ImageButton star_btn;
    private ImageButton colorstar_btn;
    private TextView meantest_eng;
    private Button sellect1;
    private Button sellect2;
    private Button sellect3;
    private Button sellect4;
    private int answer_num;
    private ImageView correct_img;
    private ImageView wrong_img;

    private int wrong;
    private int total;
    private int current=0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meaning_test);

        total = RetestSellect.retestarr.length;

        meantest_eng=findViewById(R.id.meantest_eng);

        star_btn=findViewById(R.id.mean_star_btn);
        star_btn.setOnClickListener(this);

        colorstar_btn=findViewById(R.id.mean_colorstar_btn);
        colorstar_btn.setOnClickListener(this);

        sellect1=findViewById(R.id.meantest_kor_btn1);
        sellect1.setOnClickListener(this);

        sellect2=findViewById(R.id.meantest_kor_btn2);
        sellect2.setOnClickListener(this);

        sellect3=findViewById(R.id.meantest_kor_btn3);
        sellect3.setOnClickListener(this);

        sellect4=findViewById(R.id.meantest_kor_btn4);
        sellect4.setOnClickListener(this);

        correct_img=findViewById(R.id.mean_test_correct);
        wrong_img = findViewById(R.id.mean_test_wrong);

        MySoundPlayer.initSounds(getApplicationContext());


        //????????????
        if(Integer.parseInt(RetestSellect.retestarr[current][5])==1 ){    //?????????????????? ????????? ?????? ?????? ??????
            colorstar_btn.setVisibility(View.VISIBLE);
        }
        randomSetting();
    }

    @Override
    public void onClick(View view) {
        if(view ==star_btn) {
            colorstar_btn.setVisibility(View.VISIBLE);
            updateStar(RetestSellect.retestarr[(current)][1], 1);  //???????????? ??????
        }
        if(view==colorstar_btn){
            colorstar_btn.setVisibility(View.INVISIBLE);
            updateStar(RetestSellect.retestarr[(current)][1], 0);  //???????????? ??????
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
            int tmp=(int)(Math.random()*total); //??????
            if(tmp!=current){
                for(int k=0; k<3; k++){
                    if(num[k]==tmp)
                        continue;
                }
                num[i]=tmp;
                i++;
            }
        }

        meantest_eng.setText(RetestSellect.retestarr[current][1]);
        //text??? ??????
        if(answer_num==1){
            sellect1.setText(RetestSellect.retestarr[current][2]);
            sellect2.setText(RetestSellect.retestarr[num[0]][2]);
            sellect3.setText(RetestSellect.retestarr[num[1]][2]);
            sellect4.setText(RetestSellect.retestarr[num[2]][2]);
        }else if(answer_num==2){
            sellect2.setText(RetestSellect.retestarr[current][2]);
            sellect1.setText(RetestSellect.retestarr[num[0]][2]);
            sellect3.setText(RetestSellect.retestarr[num[1]][2]);
            sellect4.setText(RetestSellect.retestarr[num[2]][2]);
        }else if(answer_num==3){
            sellect3.setText(RetestSellect.retestarr[current][2]);
            sellect2.setText(RetestSellect.retestarr[num[0]][2]);
            sellect1.setText(RetestSellect.retestarr[num[1]][2]);
            sellect4.setText(RetestSellect.retestarr[num[2]][2]);
        }else if(answer_num==4){
            sellect4.setText(RetestSellect.retestarr[current][2]);
            sellect2.setText(RetestSellect.retestarr[num[0]][2]);
            sellect3.setText(RetestSellect.retestarr[num[1]][2]);
            sellect1.setText(RetestSellect.retestarr[num[2]][2]);
        }
    }

    private void starSetting(){
        if(Integer.parseInt(RetestSellect.retestarr[current][5])==1){
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
        updateNope(RetestSellect.retestarr[(current)][1], 0);
        current++;
        wrong++;
        if(current==RetestSellect.retestarr.length){
            Intent intent = new Intent(getApplicationContext(), FinishTest.class);
            intent.putExtra("wrong",wrong);
            intent.putExtra("total",total);
            startActivity(intent);
            finish();
        }else {
            meantest_eng.setText(RetestSellect.retestarr[current][1]);
            randomSetting(); //????????? ????????? ?????????
            starSetting();  //??? ????????? ?????? or not
        }
    }

    private void wrong(){
        MySoundPlayer.play(MySoundPlayer.FAIL);
        Animation correct = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.alpha);
        wrong_img.startAnimation(correct);
        updateNope(RetestSellect.retestarr[(current)][1], 1);
        current++;
//        wrong++;
        if(current==RetestSellect.retestarr.length){
            Intent intent = new Intent(getApplicationContext(), FinishTest.class);
            intent.putExtra("wrong", wrong);
            intent.putExtra("total",total);
            startActivity(intent);
            finish();
        }else{
            meantest_eng.setText(RetestSellect.retestarr[current][1]);
            randomSetting();  //????????? ????????? ?????????
            starSetting();   //??? ????????? ?????? or not
        }
    }


    private void updateNope(String eng, int nope) {
        //?????? ??? ??????
        RetestSellect.retestarr[(current)][6]=Integer.toString(nope);

        //db ??? ??????
        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        // ????????? ????????? ???????????? ?????? ?????? ?????? ??????
        db.execSQL("UPDATE tb_voca SET nope=" + nope + " WHERE eng='" + eng + "';");
        db.close();
    }

    private void updateStar(String eng, int star) {
        //?????? ??? ??????
        RetestSellect.retestarr[(current)][5]=Integer.toString(star);

        //db ??? ??????
        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        // ????????? ????????? ???????????? ?????? ?????? ?????? ??????
        db.execSQL("UPDATE tb_voca SET star=" + star + " WHERE eng='" + eng + "';");
        db.close();
    }
}