package com.datagrandeur.neuropsych;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.neuropsych.R;

public class PracticeCongratulationActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_congratulation);

        String pointCollected = getString(R.string.PointsCollected);
        String finalPointCollected = String.format(pointCollected, ""+Singleton.getInstance().getCurrentTrialReward());
        TextView tvPointCollected = findViewById(R.id.tvPointCollected);
        tvPointCollected.setText(finalPointCollected);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(PracticeCongratulationActivity.this, PracticeActivity.class);
                startActivity(intent);
            }
        }, 2000);
    }
}