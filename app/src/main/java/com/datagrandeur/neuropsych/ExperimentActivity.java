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

import com.datagrandeur.neuropsych.data.DatabaseHelper;
import com.datagrandeur.neuropsych.data.Pump;
import com.datagrandeur.neuropsych.data.Trial;
import com.example.neuropsych.R;

import java.time.LocalDateTime;

public class ExperimentActivity extends AppCompatActivity {
    private Button btnPump;
    private ProgressBar progressBar;
    private int pumpCount ;
    private double pointValue = 0.50f;
    private ProgressBar pbRewardMeter;
    private DatabaseHelper dbHelper;
    private Trial trial;
    private Pump pump;
    private View vwBalloon;
    private View vwPoppedBalloon;
    private Button btnFillRewardMeter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper = new DatabaseHelper(getApplicationContext());
        trial = new Trial();
        pump=new Pump();
        trial.setUserId(Singleton.getInstance().getUserId());
        pump.setUserId(Singleton.getInstance().getUserId());
        trial.setTrialSequence(Singleton.getInstance().getTrialSequence()+1);
        pump.setTrialSequence(trial.getTrialSequence());
        pump.setLastPumpTime(DateUtils.getCurrentDateTime());
        trial.setStartTimeOfTrial(DateUtils.getFormatDateTime(LocalDateTime.now()));
        trial.setId((int)dbHelper.insertTrial(trial,dbHelper.getDb()));
        pump.setTrialId(trial.getId());
        setContentView(R.layout.activity_experiment);

        progressBar = findViewById(R.id.progressBar);
        int progress=0;
        progressBar.setProgress(progress);
        btnPump =findViewById(R.id.pump);
        pbRewardMeter =findViewById(R.id.progressBar);
        pbRewardMeter.setProgress((int)Singleton.getInstance().getReward());
        btnFillRewardMeter=findViewById(R.id.btnCollectPoints);
        vwBalloon=findViewById(R.id.balloon_view);
        vwPoppedBalloon=findViewById(R.id.popBalloon);

        final MediaPlayer mediaPlayer= MediaPlayer.create(this,R.raw.inflate);
        final MediaPlayer mediaPlayer2=MediaPlayer.create(this,R.raw.casino);

        btnPump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pumpCount++;
                pumpBalloon();

                mediaPlayer.start();
                if (isPop()) {
                    popBalloon();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(new Intent(ExperimentActivity.this, PointLostActivity.class));
                            finish();
                        }
                    }, 100);
                }
            }
        });

        btnFillRewardMeter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pumpCount>0) {

                    trial.setEndTimeOfTrial(DateUtils.getFormatDateTime(LocalDateTime.now()));

                    mediaPlayer2.start();
                    //int progress = pbRewardMeter.getProgress();

                    double barValue = (pumpCount*pointValue + Singleton.getInstance().getReward());

                    if(barValue>100){
                        barValue=100;
                    }

                    Singleton.getInstance().setCurrentTrialReward(pumpCount*pointValue);
                    Singleton.getInstance().setReward(barValue);
                    pbRewardMeter.setProgress((int)barValue);

                    trial.setTotalReward(barValue);
                    trial.setTrialReward(pumpCount*pointValue);
                    trial.setPopped(false);
                    trial.setPumpCount(pumpCount);
                    trial.setBalloonEndWidth(vwBalloon.getWidth());
                    trial.setBalloonEndHeight(vwBalloon.getHeight());
                    trial.setExplosionPoint(Singleton.getInstance().getExplosionPoints()[Singleton.getInstance().getTrialSequence()]);
                    dbHelper.updateTrial(trial, dbHelper.getDb());

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(new Intent(ExperimentActivity.this, CongratulationActivity.class));
                            finish();
                        }
                    }, 100);
                    progressBar.setProgress((int)barValue);

//                    if (isEndExperiment()) {
//                        new Handler().postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                startActivity(new Intent(ExperimentActivity.this, RewardActivity.class));
//                                finish();
//                            }
//                        }, 100);
//                    }
                }

//
//                if(isEndExperiment()==true){
//                    new Handler().postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            startActivity(new Intent(ExperimentActivity.this,RewardActivity.class));
//                            finish();
//                        }
//                    }, 100);
//
//                }


            }
        });
    }
    public Boolean isPop(){
        if (pumpCount == Singleton.getInstance().getExplosionPoints()[Singleton.getInstance().getTrialSequence()]){
            return true;
        }else {
            return false;
        }
    }
    public void pumpBalloon(){

        pump.setPumpSequence(pumpCount);

        int initialX = (int) vwBalloon.getX();
        int initialY = (int) vwBalloon.getY();
        
        ViewGroup.LayoutParams balloonParams= vwBalloon.getLayoutParams();
        ViewGroup.LayoutParams poppedBalloonParams=vwPoppedBalloon.getLayoutParams();


        balloonParams.width+=5;
        balloonParams.height+=3;
        
        // Set the balloon's position to the initial position
        vwBalloon.setX(initialX);
        vwBalloon.setY(initialY);

        vwBalloon.setLayoutParams(balloonParams);
        vwBalloon.requestLayout();

        poppedBalloonParams.width+=5;
        poppedBalloonParams.height+=3;
        vwPoppedBalloon.setLayoutParams(poppedBalloonParams);
        vwPoppedBalloon.requestLayout();
        // trial.setReward(reward);
        pump.setCurrentPumpTime(DateUtils.getCurrentDateTime());
        pump.setPumpBtwPumps(String.valueOf(DateUtils.getDifferenceInMillisecond(pump.getLastPumpTime(),pump.getCurrentPumpTime())));


        if(isPop()) {
            pump.setExploded(true);
            pump.setBalloonWidth(0);
            pump.setBalloonHeight(0);
            vwPoppedBalloon.setVisibility(View.VISIBLE);
            vwPoppedBalloon.setX(vwBalloon.getX());
            vwPoppedBalloon.setY(vwBalloon.getY());
            btnPump.setVisibility(View.INVISIBLE);
        }else{
            pump.setExploded(false);
            pump.setBalloonHeight(vwBalloon.getHeight());
            pump.setBalloonWidth(vwBalloon.getWidth());
            vwPoppedBalloon.setVisibility(View.INVISIBLE);
        }

        dbHelper.insertPump(pump, dbHelper.getDb());
        pump.setLastPumpTime(pump.getCurrentPumpTime());

    }

    public void popBalloon(){

        final MediaPlayer mediaPlayer1=MediaPlayer.create(this,R.raw.explosion);

        vwBalloon.setVisibility(View.GONE);
        vwPoppedBalloon.setVisibility(View.VISIBLE);
        mediaPlayer1.start();

        trial.setEndTimeOfTrial(DateUtils.getFormatDateTime(LocalDateTime.now()));

        trial.setBalloonEndWidth(vwBalloon.getWidth());
        trial.setBalloonEndHeight(vwBalloon.getHeight());


        //int progress = pbRewardMeter.getProgress();

        double barValue = (-1 * pumpCount * pointValue +  Singleton.getInstance().getReward());

        if(barValue<0)
            Singleton.getInstance().setReward(0);
        else
            Singleton.getInstance().setReward(barValue);

        Singleton.getInstance().setCurrentTrialReward(-1 * pumpCount * pointValue);
        trial.setTrialReward(-1 * pumpCount * pointValue);
        trial.setTotalReward(Singleton.getInstance().getReward());
        trial.setPopped(true);
        trial.setPumpCount(pumpCount);
        trial.setExplosionPoint(Singleton.getInstance().getExplosionPoints()[Singleton.getInstance().getTrialSequence()]);
        pbRewardMeter.setProgress((int)barValue);
        dbHelper.updateTrial(trial, dbHelper.getDb());

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(ExperimentActivity.this,PointLostActivity.class));
                finish();
            }
        }, 100);

    }

    }




