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

    private Button btnPump;
    Constant constant=new Constant();
    private int pumpCount =0;
    private int countReward=0;
    private int rewardCount=0;

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

        btnPump =findViewById(R.id.pump);
        pbRewardMeter =findViewById(R.id.progressBar);
        btnFillRewardMeter=findViewById(R.id.btnFillReward);
        vwBalloon=findViewById(R.id.balloon_view);
        vwPoppedBalloon=findViewById(R.id.popBalloon);

        final MediaPlayer mediaPlayer= MediaPlayer.create(this,R.raw.inflate);
        final MediaPlayer mediaPlayer2= MediaPlayer.create(this,R.raw.casino);


        btnPump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                pumpBalloon();
                pumpCount++;
                rewardCount+=2;


                mediaPlayer.start();
                startTime=System.currentTimeMillis();
                endTime=System.currentTimeMillis();

                if(pumpCount ==constant.balloonArray[Singleton.getInstance().getTrialSequence()]){

                    popBalloon(); //pumpCount 3 vaye pop
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
                mediaPlayer2.start();
                int reward=Singleton.getInstance().getReward();
                reward+=rewardCount;
                Singleton.getInstance().setReward(reward);
                pbRewardMeter.setProgress(reward);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(ExperimentActivity.this, CongratulationActivity.class));
                        finish();
                    }
                }, 100);


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
    public void rewardMeter(){

    }
    public void popBalloon(){
        final MediaPlayer mediaPlayer1=MediaPlayer.create(this,R.raw.explosion);


        vwBalloon.setVisibility(View.GONE);
        vwPoppedBalloon.setVisibility(View.VISIBLE);
        mediaPlayer1.start();



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(ExperimentActivity.this,PointLostActivity.class));
                finish();
            }
        }, 100);
    }

    }
