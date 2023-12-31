package com.datagrandeur.neuropsych;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.example.neuropsych.R;

public class CongratulationActivity extends AppCompatActivity {
    TextView tvPointCollected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Constant constant=new Constant();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congratulation);

        String pointCollected = getString(R.string.PointsCollected);
        String finalPointCollected = String.format(pointCollected, ""+Singleton.getInstance().getCurrentTrialReward());
        TextView tvPointCollected = findViewById(R.id.tvPointCollected);
        tvPointCollected.setText(finalPointCollected);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(Singleton.getInstance().getTrialSequence()==Singleton.getInstance().getExplosionPoints().length-1){
                    Intent intent=new Intent(CongratulationActivity.this,RewardActivity.class);
                    startActivity(intent);
                }else{
                    int trialId=Singleton.getInstance().getTrialSequence();
                    trialId++;
                    Singleton.getInstance().setTrialSequence(trialId);

                    Intent intent=new Intent(CongratulationActivity.this,ExperimentActivity.class);
                    startActivity(intent);


                }
            }
        }, 2000);

        Singleton.getInstance().setCurrentTrialReward(0);


    }
}