package com.example.myapplication222;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;


public class RetestSellect extends AppCompatActivity implements View.OnClickListener {

    public static String[][] retestarr;
    Button mem_btn;
    Button retest_btn;
    static int wordnum;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sellect);

        wordnum=0;

        for(int i=0; i<20; i++){
            if(Integer.parseInt(WordListView.arrayDay[i][6])==1){
                wordnum++;
            }
        }


        retestarr= new String[wordnum][7];
        int m=0;
        for(int i=0; i<20; i++){
            if(Integer.parseInt(WordListView.arrayDay[i][6])==1){
                for(int k=0;k<7;k++){
                    retestarr[m][k]=WordListView.arrayDay[i][k];
                }
                m++;
            }
        }

        TextView textView = (TextView)findViewById(R.id.sellect_name);
        textView.setText("능률보카 재시험");

        mem_btn = (Button) findViewById(R.id.sellect_left_btn);
        mem_btn.setText("틀린단어 암기");

        retest_btn = (Button) findViewById(R.id.sellect_right_btn);
        retest_btn.setText("틀린단어 재시험");
        Log.d("d", wordnum+"");

        mem_btn.setOnClickListener(this);
        retest_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Log.d("d", wordnum+"");

        if (view == mem_btn) {
                if(wordnum==0){
                    Toast.makeText(this,"틀린단어가 없습니다.",Toast.LENGTH_LONG).show();
                }else {
                    Intent intent = new Intent(getApplicationContext(), RetestMemorizeSellect.class);
                    startActivity(intent);
                }
            }
            if (view == retest_btn) {
                if(wordnum==0){
                    Toast.makeText(this,"틀린단어가 없습니다.",Toast.LENGTH_LONG).show();
                }else {
                    Intent intent = new Intent(getApplicationContext(), RetestTestSellect.class);
                    startActivity(intent);
                }
            }
    }
}
