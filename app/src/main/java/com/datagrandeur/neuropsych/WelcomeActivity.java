package com.datagrandeur.neuropsych;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.neuropsych.R;


public class WelcomeActivity extends AppCompatActivity {
    private TextView[] mTextViews;
    private int mCurrentIndex;
    private TextView txtViewNext;
    private ProgressBar progressBar;
    private  Button btnFillReward;

    private TextView txtGoBack;
    private View balloonView;
    private  Button btnInflate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        mTextViews=new TextView[] {findViewById(R.id.txt_Welcome),findViewById(R.id.Game_info1),findViewById(R.id.pump_info),findViewById(R.id.txt_info1),findViewById(R.id.txt_info2),findViewById(R.id.txt_info3),findViewById(R.id.txt_info4),findViewById(R.id.txt_info5),findViewById(R.id.txt_info6),findViewById(R.id.txt_info7),findViewById(R.id.txt_info8)};
        mCurrentIndex=0;

        txtViewNext=findViewById(R.id.next);

        txtGoBack=findViewById(R.id.txtGoBack);
        btnInflate=findViewById(R.id.btnPump);
        balloonView=findViewById(R.id.balloon_view);
        progressBar=findViewById(R.id.progressBar);
        btnFillReward=findViewById(R.id.btnFillReward);
        txtViewNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex=(mCurrentIndex+1)%mTextViews.length;
                updateTextView();



            }
        });

        txtGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex=(mCurrentIndex-1+mTextViews.length)%mTextViews.length;
                updateTextView();

            }
        });
        btnInflate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewGroup.LayoutParams params= balloonView.getLayoutParams();
                params.width+=20;
                params.height+=20;
                balloonView.setLayoutParams(params);
                balloonView.requestLayout();
            }
        });
        btnFillReward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int progress = progressBar.getProgress();
                if (progress < 100) {
                    progress += 10; // Increase the progress by 10
                    progressBar.setProgress(progress);
                }
            }
        });

    }
    private void updateTextView(){
        for(int i=0;i<mTextViews.length;i++){
            if(i==mCurrentIndex){
                mTextViews[i].setVisibility(View.VISIBLE);
            }else{
                mTextViews[i].setVisibility(View.GONE);
            }
        }
    }
}