package com.datagrandeur.neuropsych;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.neuropsych.R;

public class PracticeCongratulationActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_congratulation);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(PracticeCongratulationActivity.this, PracticeActivity.class);
                startActivity(intent);
            }
        }, 2000);


    }
}