package com.datagrandeur.neuropsych;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.datagrandeur.neuropsych.data.Trial;
import com.example.neuropsych.R;


public class WelcomeActivity extends AppCompatActivity {
    private TextView[] tvInstructions;
    private float scaleFactor = 1.0f;
    private TextView tvInstructionBox;

    private int[] balloonArray = {3,5,39,96,88,21,121,10,64,32,64,101,26,34,47,121,64,95,75,13,64,112,30,88,9,64,91,17,115,50};
    private int pumpCount =0;
    private long startTime;
    private long endTime;

    private Trial trial;
    private int instructionIndex;
    private TextView tvNext;
    private ProgressBar pbRewardMeter;
    private  Button btnFillRewardMeter;
    private Button btnClickToContinue; //experiment start

    private TextView tvBack;
    private ImageView vwBalloon;
    private ImageView vwPoppedBalloon;
    private  Button btnInflate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_welcome);
        tvInstructions =new TextView[] {findViewById(R.id.tvWelcome),findViewById(R.id.Game_info1),findViewById(R.id.pump_info),findViewById(R.id.txt_info1),findViewById(R.id.txt_info2),findViewById(R.id.txt_info3),findViewById(R.id.txt_info4),findViewById(R.id.txt_info5),findViewById(R.id.txt_info6),findViewById(R.id.txt_info7),findViewById(R.id.txt_info8)};
        instructionIndex =0;

        tvNext =findViewById(R.id.tvNext); //Next
        tvInstructionBox=findViewById(R.id.text_instruction); //textView
        tvBack =findViewById(R.id.txtGoBack); //Back

        btnInflate=findViewById(R.id.btnPump);

        vwBalloon =findViewById(R.id.balloon_view); //balloon imageview

        vwPoppedBalloon=findViewById(R.id.popBalloon); //balloon pop imageview

        btnClickToContinue =findViewById(R.id.clickToContinue);


        final MediaPlayer mediaPlayer= MediaPlayer.create(this,R.raw.inflate);
        tvNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                instructionIndex =(instructionIndex +1)% tvInstructions.length; //next button increase tv
                updateTextView();
            }
        });
        btnClickToContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(WelcomeActivity.this,ExperimentActivity.class);
                startActivity(intent);

            }
        });


        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                instructionIndex =(instructionIndex -1+ tvInstructions.length)% tvInstructions.length;
                updateTextView();

            }
        });
        btnInflate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pumpCount++;
                inflateBalloon();
                mediaPlayer.start();
            }
        });
        btnFillRewardMeter =findViewById(R.id.btnFillReward);

        pbRewardMeter =findViewById(R.id.progressBar);
        btnFillRewardMeter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int progress = pbRewardMeter.getProgress();
                if (progress < 100) {
                    progress += 10; // Increase the progress by 10
                    pbRewardMeter.setProgress(progress);
                }


//                trial=new Trial(TrialId++,String.valueOf(startTime),String.valueOf(endTime));

            }
        });

    }
    private void inflateBalloon(){

        ViewGroup.LayoutParams params= vwBalloon.getLayoutParams();
        ViewGroup.LayoutParams params1=vwPoppedBalloon.getLayoutParams();

        params.width+=5;
        params.height+=10;
        vwBalloon.setLayoutParams(params);
        vwBalloon.requestLayout();

        params1.width+=5;
        params1.height+=10;
        vwPoppedBalloon.setLayoutParams(params1);
        vwPoppedBalloon.requestLayout();
    }



    private void updateTextView(){
        for(int i = 0; i< tvInstructions.length; i++){
            if(i== instructionIndex){ //instructionIndex=0
                tvInstructions[i].setVisibility(View.VISIBLE);
            }else{
                tvInstructions[i].setVisibility(View.GONE);
            }if(tvInstructions[i]==findViewById(R.id.txt_info8)){
                btnClickToContinue.setVisibility(View.VISIBLE);

            }


        }

    }
}