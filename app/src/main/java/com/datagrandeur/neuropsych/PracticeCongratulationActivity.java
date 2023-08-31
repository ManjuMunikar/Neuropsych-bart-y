package com.datagrandeur.neuropsych;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.neuropsych.R;

public class PracticeCongratulationActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_congratulation);

        String pointCollected = getString(R.string.PointsCollected);
        String pointLost = getString(R.string.LostPoints);

        if(SingletonPractice.getInstance().isPop()){
            float diff = SingletonPractice.getInstance().getPoints() - SingletonPractice.getInstance().getCurrentTrialReward();
            if(diff>0.0) {
                SingletonPractice.getInstance().setPoints(diff);
            }else{
                SingletonPractice.getInstance().setPoints(0);
            }
        }else{
            float sum = SingletonPractice.getInstance().getPoints() + SingletonPractice.getInstance().getCurrentTrialReward();
            if(sum>100.0f) {
                SingletonPractice.getInstance().setPoints(100.0f);
            }else{
                SingletonPractice.getInstance().setPoints(sum);
            }
        }
        String finalPointCollected = String.format(pointCollected, ""+SingletonPractice.getInstance().getCurrentTrialReward());
        String finalPointLost = String.format(pointLost, ""+SingletonPractice.getInstance().getCurrentTrialReward());
        TextView tvPointCollected = findViewById(R.id.tvPointCollected);
        TextView tvPointLost = findViewById(R.id.tvPointLost);

        if(SingletonPractice.getInstance().isPop()) {
            tvPointCollected.setVisibility(View.GONE);
            tvPointLost.setVisibility(View.VISIBLE);
            tvPointLost.setText(finalPointLost);
        } else {
            tvPointCollected.setVisibility(View.VISIBLE);
            tvPointLost.setVisibility(View.GONE);
            tvPointCollected.setText(finalPointCollected);
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(PracticeCongratulationActivity.this, PracticeActivity.class);
                startActivity(intent);
            }
        }, 2000);
    }
}