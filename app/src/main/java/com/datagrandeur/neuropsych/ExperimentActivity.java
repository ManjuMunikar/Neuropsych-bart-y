package com.datagrandeur.neuropsych;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.datagrandeur.neuropsych.data.Trial;
import com.example.neuropsych.R;

public class ExperimentActivity extends AppCompatActivity {

private Button btn_pump;
    private int[] balloonArray = {3,5,39,96,88,21,121,10,64,32,64,101,26,34,47,121,64,95,75,13,64,112,30,88,9,64,91,17,115,50};
    private int pumpCount =0;
    private ProgressBar pbRewardMeter;
    private long startTime;
    private long endTime;

    private Trial trial;
    private View vwBalloon;
    private View vwPoppedBalloon;
    private Button btnFillRewardMeter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experiment);
        btn_pump=findViewById(R.id.pump);
        pbRewardMeter =findViewById(R.id.progressBar);
        btnFillRewardMeter=findViewById(R.id.btnFillReward);
        vwBalloon=findViewById(R.id.balloon_view);
        vwPoppedBalloon=findViewById(R.id.popBalloon);

        final MediaPlayer mediaPlayer= MediaPlayer.create(this,R.raw.inflate);

        btn_pump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                pumpBalloon();
                pumpCount++;

                mediaPlayer.start();
                startTime=System.currentTimeMillis();
                endTime=System.currentTimeMillis();

                if(pumpCount ==balloonArray[Singleton.getInstance().getTrialSequence()]){

                    popBalloon();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(new Intent(ExperimentActivity.this,PointLostActivity.class));
                            finish();
                        }
                    }, 100);


                }
            }
        });
        btnFillRewardMeter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int progress = pbRewardMeter.getProgress();
                if (progress < 100) {
                    progress += 10; // Increase the progress by 10
                    pbRewardMeter.setProgress(progress);

                }
            }
        });



    }
    public void pumpBalloon(){
        ViewGroup.LayoutParams paramss= vwBalloon.getLayoutParams();
        ViewGroup.LayoutParams params1=vwPoppedBalloon.getLayoutParams();

        paramss.width+=5;
        paramss.height+=10;
        vwBalloon.setLayoutParams(paramss);
        vwBalloon.requestLayout();

        params1.width+=5;
        params1.height+=10;
        vwPoppedBalloon.setLayoutParams(params1);
        vwPoppedBalloon.requestLayout();
    }
    public void popBalloon(){
        vwBalloon.setVisibility(View.GONE);
        vwPoppedBalloon.setVisibility(View.VISIBLE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(ExperimentActivity.this,PointLostActivity.class));
                finish();
            }
        }, 100);
    }

    }
