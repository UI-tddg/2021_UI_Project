package com.example.myapplication222;

        import androidx.annotation.Nullable;
        import androidx.appcompat.app.AppCompatActivity;
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mean_memorize);

        touchButton = (Button) findViewById(R.id.touchButton);
        showWord = (TextView) findViewById(R.id.showWord);
        starButton = (ImageButton) findViewById(R.id.starButton);
        colorStar = (ImageButton) findViewById(R.id.colorStar);
        touchButton.setOnClickListener(this);
        showWord.setOnClickListener(this);
        starButton.setOnClickListener(this);
        colorStar.setOnClickListener(this);
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
    }
}