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
    private int current=0;
    private ImageView correct_img;
    private ImageView wrong_img;
    private int wrong;
    private int total=0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meaning_test);

        total = RetestSellect.retestarr.length;

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


        //초기세팅
        if(Integer.parseInt(RetestSellect.retestarr[current][5])==1 ){    //중요단어라면 칠해진 별인 채로 출력
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
        //정답값 세팅
        Random random = new Random();
        random.setSeed(System.currentTimeMillis());
        answer_num=(int)(Math.random()*4+1);


        int[] num = {-1,-1,-1};
        //랜덤 수 뽑기
        for(int i=0; i<3;){
            int tmp=(int)(Math.random()*20); //랜덤
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
        //text에 세팅
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

    //맞았을때
    private void correct(){
        MySoundPlayer.play(MySoundPlayer.SUCCESS);
        Animation correct = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.alpha);
        correct_img.startAnimation(correct);
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
            randomSetting(); //객관식 텍스트 바꾸기
            starSetting();  //별 칠해져 출력 or not
        }
    }

    private void wrong(){
        MySoundPlayer.play(MySoundPlayer.FAIL);
        Animation correct = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.alpha);
        wrong_img.startAnimation(correct);
        update(6,1); //배열 및 db 값 nope 부분 틀린단어로 업데이트
        current++;
        wrong++;
        if(current==RetestSellect.retestarr.length){
            Intent intent = new Intent(getApplicationContext(), FinishTest.class);
            intent.putExtra("wrong", wrong);
            intent.putExtra("total",total);
            startActivity(intent);
            finish();
        }else{
            meantest_eng.setText(RetestSellect.retestarr[current][1]);
            randomSetting();  //객관식 테스트 바꾸기
            starSetting();   //별 칠해져 출력 or not
        }
    }

    //index-> 변경할 열, num->
    private void update(int index, int num){
        //배열 값 변경
        RetestSellect.retestarr[current][index]=Integer.toString(num);

        //db 값 변경
        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        // 입력한 항목과 일치하는 행의 가격 정보 수정
        //중요단어 체크일때
        if(index==5){
            db.execSQL("UPDATE tb_voca SET star=" + num + " WHERE eng='" + RetestSellect.retestarr[current][1] + "';");
        }
        //틀린단어 체크일때
        if(index==6){
            db.execSQL("UPDATE tb_voca SET nope=" + num + " WHERE eng='" + RetestSellect.retestarr[current][1] + "';");
        }
        db.close();
    }
}