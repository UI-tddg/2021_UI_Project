package com.example.myapplication222;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FinishTest extends AppCompatActivity {

    private int total_correct;
    private TextView correct;
    private TextView wrong;
    private Button go_List;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finish_test);

        total_correct=getIntent().getExtras().getInt("total");

        correct=(TextView) findViewById(R.id.total_correct);
        wrong=(TextView) findViewById(R.id.total_wrong);

        go_List=(Button)findViewById(R.id.finish_test_btn);
        go_List.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        correct.setText("맞은 개수: "+total_correct+"/20");
        wrong.setText("틀린 개수: "+(20-total_correct)+"/20");
    }
}