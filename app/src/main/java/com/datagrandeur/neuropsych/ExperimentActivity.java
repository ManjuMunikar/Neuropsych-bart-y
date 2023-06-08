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

import java.text.SimpleDateFormat;
import java.util.Date;


public class ExperimentActivity extends AppCompatActivity {
    Constant constant=new Constant();
    private Button btnPump;
    int bar;
    private ProgressBar progressBar;
    private int pumpCount =0;
    private int rewardCount=0;
    private double reward;
    private String startTime;
    private  int fillReward=0;
    private ProgressBar pbRewardMeter;
    private String endTime;
    DatabaseHelper dbHelper;
    Trial trial;
    private View vwBalloon;
    private View vwPoppedBalloon;
    private Button btnFillRewardMeter;
    ExperimentActivity experimentActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        experimentActivity=new ExperimentActivity();
        reward=0.0;

        dbHelper = new DatabaseHelper(getApplicationContext());
        trial = new Trial();
        trial.setUserId(Singleton.getInstance().getUserId());

        //set trial sequence to the trial database
        trial.setTrialSequence(Singleton.getInstance().getTrialSequence()+1);

        //set start time of the trial to the database
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        startTime= sdf.format(new Date());
        trial.setStartTimeOfTrial(startTime);

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
                rewardCount=5;
                mediaPlayer.start();


                if(pumpCount ==constant.balloonArray[Singleton.getInstance().getTrialSequence()]){
                    popBalloon();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(new Intent(ExperimentActivity.this,PointLostActivity.class));
                            finish();
                        }
                    }, 100);

                }else{
                    pumpBalloon();
                }

                    if(pumpCount==constant.balloonArray.length-1){
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
        btnFillRewardMeter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //add end time of the trial
                trial.setReward(reward);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                endTime= sdf.format(new Date());
                trial.setEndTimeOfTrial(endTime);

                fillReward++;
                mediaPlayer2.start();

                    int progress = pbRewardMeter.getProgress();

                    bar = Singleton.getInstance().getReward();
                    bar = 5 + progress;
                    Singleton.getInstance().setReward(bar);
                    pbRewardMeter.setProgress(bar);

                    //add height and width to the database
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
    public void popBalloon(){

        final MediaPlayer mediaPlayer1=MediaPlayer.create(this,R.raw.explosion);

        vwBalloon.setVisibility(View.GONE);
        vwPoppedBalloon.setVisibility(View.VISIBLE);
        mediaPlayer1.start();
        trial.setReward(0.0);

        //add end time of the trial
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        endTime= sdf.format(new Date());
        trial.setEndTimeOfTrial(endTime);

        //add height and width to the database
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
