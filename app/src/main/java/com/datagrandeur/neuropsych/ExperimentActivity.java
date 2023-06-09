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
import com.datagrandeur.neuropsych.data.Trial;
import com.example.neuropsych.R;

public class ExperimentActivity extends AppCompatActivity {
    Constant constant=new Constant();
    private Button btnPump;
    private ProgressBar progressBar;
    private int pumpCount =0;
    private int rewardCount=0;
    private double reward;

    private  int fillReward=0;
    private ProgressBar pbRewardMeter;

    private DatabaseHelper dbHelper;
    private Trial trial;
    private View vwBalloon;
    private View vwPoppedBalloon;
    private Button btnFillRewardMeter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        reward=0.0;
        dbHelper = new DatabaseHelper(getApplicationContext());
        trial = new Trial();
        trial.setUserId(Singleton.getInstance().getUserId());


        trial.setTrialSequence(Singleton.getInstance().getTrialSequence()+1);


        trial.setStartTimeOfTrial(DateUtils.getCurrentDateTime());

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
                rewardCount = 5;
                mediaPlayer.start();


                if (pumpCount == constant.balloonArray[Singleton.getInstance().getTrialSequence()]) {
                    popBalloon();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(new Intent(ExperimentActivity.this, PointLostActivity.class));
                            finish();
                        }
                    }, 100);

                } else {
                    pumpBalloon();
                }


            }
        });
        btnFillRewardMeter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                trial.setReward(reward);

                trial.setEndTimeOfTrial(DateUtils.getCurrentDateTime());

                fillReward++;
                mediaPlayer2.start();


                    int progress = pbRewardMeter.getProgress();

                  int bar = Singleton.getInstance().getReward();
                    bar = 5 + progress;
                    Singleton.getInstance().setReward(bar);
                    pbRewardMeter.setProgress(bar);

                    trial.setBalloonEndWidth(vwBalloon.getWidth());
                    trial.setBalloonEndHeight(vwBalloon.getHeight());
                    dbHelper.insertTrial(trial, dbHelper.getDb());





                new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(new Intent(ExperimentActivity.this, CongratulationActivity.class));
                            finish();
                        }
                    }, 100);
                progressBar.setProgress(bar);

                if(fillReward==constant.balloonArray.length-1){
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(new Intent(ExperimentActivity.this, ThankYouActivity.class));
                            finish();
                        }
                    }, 100);
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
    public void pumpBalloon(){
        reward=reward+0.5;
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
    public boolean isEndExperiment(){
        return pumpCount == (constant.balloonArray.length - 1);
    }
    public void popBalloon(){

        final MediaPlayer mediaPlayer1=MediaPlayer.create(this,R.raw.explosion);

        vwBalloon.setVisibility(View.GONE);
        vwPoppedBalloon.setVisibility(View.VISIBLE);
        mediaPlayer1.start();
        trial.setReward(0.0);


        trial.setEndTimeOfTrial(DateUtils.getCurrentDateTime());

        trial.setBalloonEndWidth(vwBalloon.getWidth());
        trial.setBalloonEndHeight(vwBalloon.getHeight());
        long i = dbHelper.insertTrial(trial, dbHelper.getDb());
        System.out.println(i);




        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(ExperimentActivity.this,PointLostActivity.class));
                finish();
            }
        }, 100);

    }

    }
