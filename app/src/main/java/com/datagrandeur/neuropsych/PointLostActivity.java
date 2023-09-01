package com.datagrandeur.neuropsych;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.example.neuropsych.R;

public class PointLostActivity extends AppCompatActivity {

    private double pointValue = 0.5f;
    Constant constant=new Constant();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_point_lost);

        String lostPoints = getString(R.string.LostPoints);
        String finalLostPoint = String.format(lostPoints, ""+ (Singleton.getInstance().getCurrentTrialReward()));
        TextView tvLostPoint = findViewById(R.id.tvLostPoint);
        tvLostPoint.setText(finalLostPoint);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(Singleton.getInstance().getTrialSequence()==constant.explosionPoints.length-1){
                    Intent intent=new Intent(PointLostActivity.this,RewardActivity.class);
                    startActivity(intent);
                }else{
                    int trialId=Singleton.getInstance().getTrialSequence();
                    trialId++;
                    Singleton.getInstance().setTrialSequence(trialId);

                    Intent intent=new Intent(PointLostActivity.this,ExperimentActivity.class);
                    startActivity(intent);


                }
            }
        }, 2000);

        Singleton.getInstance().setCurrentTrialReward(0);

    }
}