package com.datagrandeur.neuropsych;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.datagrandeur.neuropsych.data.DatabaseHelper;
import com.datagrandeur.neuropsych.data.Pump;
import com.datagrandeur.neuropsych.data.Trial;
import com.example.neuropsych.R;

import java.time.LocalDateTime;
import java.util.Date;

public class ExperimentActivity extends AppCompatActivity {
    Constant constant=new Constant();
    private Button btnPump;
    private ProgressBar progressBar;
    private int pumpCount ;
    private int rewardCount=0;
    private double reward;
    private  int fillReward=0;
    private ProgressBar pbRewardMeter;
    private DatabaseHelper dbHelper;
    private Trial trial;
    private int trial_id;
    private Pump pump;
    private View vwBalloon;
    private View vwPoppedBalloon;
    private Button btnFillRewardMeter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        reward=0.0;
        trial_id=0;
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
        pbRewardMeter.setProgress(Singleton.getInstance().getReward());
        btnFillRewardMeter=findViewById(R.id.btnFillReward);
        vwBalloon=findViewById(R.id.balloon_view);
        vwPoppedBalloon=findViewById(R.id.popBalloon);

        final MediaPlayer mediaPlayer= MediaPlayer.create(this,R.raw.inflate);
        final MediaPlayer mediaPlayer2=MediaPlayer.create(this,R.raw.casino);

        btnPump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pumpCount++;
                pumpBalloon();
                rewardCount = 5;

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
                    trial.setReward(reward);
                    trial.setEndTimeOfTrial(DateUtils.getFormatDateTime(LocalDateTime.now()));

                    fillReward++;
                    mediaPlayer2.start();
                    int progress = pbRewardMeter.getProgress();

                    int barValue = (int) (reward + progress);
                    Singleton.getInstance().setReward(barValue);
                    pbRewardMeter.setProgress(barValue);

                    trial.setBalloonEndWidth(vwBalloon.getWidth());
                    trial.setBalloonEndHeight(vwBalloon.getHeight());
                    dbHelper.updateTrial(trial, dbHelper.getDb());

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(new Intent(ExperimentActivity.this, CongratulationActivity.class));
                            finish();
                        }
                    }, 100);
                    progressBar.setProgress(barValue);

                    if (fillReward == constant.balloonArray.length - 1) {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                startActivity(new Intent(ExperimentActivity.this, ThankYouActivity.class));
                                finish();
                            }
                        }, 100);
                    }
                }


                if(isEndExperiment()==true){
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(new Intent(ExperimentActivity.this,ThankYouActivity.class));
                            finish();
                        }
                    }, 100);

                }


            }
        });
    }
    public Boolean isPop(){
        if (pumpCount == constant.balloonArray[Singleton.getInstance().getTrialSequence()]){
            return true;
        }else {
            return false;
        }
    }
    public void pumpBalloon(){

        pump.setPumpSequence(pumpCount);
        reward=reward+1.0;

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
        trial.setReward(reward);
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

    public boolean isEndExperiment(){

        return pumpCount == (constant.balloonArray.length - 1);
    }
    public void popBalloon(){

        final MediaPlayer mediaPlayer1=MediaPlayer.create(this,R.raw.explosion);

        vwBalloon.setVisibility(View.GONE);
        vwPoppedBalloon.setVisibility(View.VISIBLE);
        mediaPlayer1.start();

        trial.setReward(0.0);
        trial.setEndTimeOfTrial(DateUtils.getFormatDateTime(LocalDateTime.now()));

        trial.setBalloonEndWidth(vwBalloon.getWidth());
        trial.setBalloonEndHeight(vwBalloon.getHeight());
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




