package com.example.myapplication222;

        import androidx.annotation.Nullable;
        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.TextView;
        import android.widget.ImageButton;

public class MeanMemorize extends AppCompatActivity implements View.OnClickListener{

    private Button touchButton;
    private TextView showWord;
    private ImageButton colorStar;
    private ImageButton starButton;
    private ImageButton nextBtn;
    private ImageButton prevBtn;
    private TextView korText;
    private TextView showSpeaker;
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

        showSpeaker=(TextView)findViewById(R.id.showWord_speaker);
        korText = (TextView)findViewById(R.id.mean_kor_text);

        //초기값 세팅
        korText.setText(ListView.arrayDay[current][2]);
        showWord.setText(ListView.arrayDay[current][1]);
        showSpeaker.setText(ListView.arrayDay[current][3]);
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
        }
        if (view == colorStar) {
            colorStar.setVisibility(View.INVISIBLE);
        }
        if(view == nextBtn){
            if(current==20) {
                Intent intent = new Intent(getApplicationContext(), FinishMemorize.class);
                startActivity(intent);
            }else {
                touchButton.setVisibility(View.VISIBLE);
                korText.setText(ListView.arrayDay[current][2]);
                showWord.setText(ListView.arrayDay[current][1]);
                showSpeaker.setText(ListView.arrayDay[current][3]);
                current++;
            }
        }
        if (view==prevBtn){
            current--;
            if(current==0){
                Intent intent = new Intent(getApplicationContext(), MemorizeSellect.class);
                startActivity(intent);
            }else{
                current--;
                touchButton.setVisibility(View.VISIBLE);
                korText.setText(ListView.arrayDay[current][2]);
                showWord.setText(ListView.arrayDay[current][1]);
                showSpeaker.setText(ListView.arrayDay[current][3]);
                current++;
            }
        }
    }
}