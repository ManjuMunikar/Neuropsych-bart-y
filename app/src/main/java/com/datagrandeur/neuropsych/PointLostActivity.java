package com.datagrandeur.neuropsych;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.neuropsych.R;

public class PointLostActivity extends AppCompatActivity {
    Constant constant=new Constant();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_point_lost);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(Singleton.getInstance().getTrialSequence()==constant.balloonArray.length-1){
                    Intent intent=new Intent(PointLostActivity.this,ThankYouActivity.class);
                    startActivity(intent);
                }else{
                    int trialId=Singleton.getInstance().getTrialSequence();
                    trialId++;
                    Singleton.getInstance().setTrialSequence(trialId);

                    Intent intent=new Intent(PointLostActivity.this,ExperimentActivity.class);
                    startActivity(intent);


                }
            }
        }, 3000);

    }
}