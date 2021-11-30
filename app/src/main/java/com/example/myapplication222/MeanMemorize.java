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

    Button touchButton;
    TextView showWord;
    ImageButton colorStar;
    ImageButton starButton;
    ImageButton nextBtn;
    TextView korText;
    private int current=0;  //현재 출력되고 있는 배열의 번호

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mean_memorize);

        touchButton = (Button) findViewById(R.id.touchButton);
        showWord = (TextView) findViewById(R.id.showWord);
        starButton = (ImageButton) findViewById(R.id.starButton);
        colorStar = (ImageButton) findViewById(R.id.colorStar);
        nextBtn = (ImageButton)findViewById(R.id.mean_next_btn);
        korText = (TextView)findViewById(R.id.mean_kor_text);
        touchButton.setOnClickListener(this);
        showWord.setOnClickListener(this);
        starButton.setOnClickListener(this);
        colorStar.setOnClickListener(this);
        nextBtn.setOnClickListener(this);

        //초기값 세팅
        korText.setText(ListView.arrayDay[current][2]);
        showWord.setText(ListView.arrayDay[current][1]);
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
            }
            korText.setText(ListView.arrayDay[current][2]);
            showWord.setText(ListView.arrayDay[current][1]);
            current++;
        }
    }
}