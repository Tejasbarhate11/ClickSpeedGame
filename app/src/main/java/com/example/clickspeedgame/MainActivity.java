package com.example.clickspeedgame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ConstraintLayout field;
    private TextView time,score;
    private Button play;

    private CountDownTimer countDownTimer;
    private long timemilliseconds=10000;

    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        field = findViewById(R.id.field);
        time = findViewById(R.id.time);
        score = findViewById(R.id.score);
        play = findViewById(R.id.play);

        score.setText("0");
        time.setText("10 sec");

        field.setEnabled(false);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                play();
            }
        });

        field.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;
                score.setText(""+count);
            }
        });

    }
    private void play(){
        play.setVisibility(View.GONE);
        field.setEnabled(true);
        count=0;
        startTime();
    }

    private void startTime(){
        countDownTimer = new CountDownTimer(timemilliseconds,1000) {
            @Override
            public void onTick(long l) {
                timemilliseconds = l;
                update();
            }

            @Override
            public void onFinish() {
                Intent i=new Intent(MainActivity.this,ResultActivity.class);
                i.putExtra("score",count);
                startActivity(i);
                finish();
            }
        }.start();
    }

    private void update(){
        int seconds=(int) timemilliseconds/1000;
        time.setText(""+seconds+" sec");
    }
}
