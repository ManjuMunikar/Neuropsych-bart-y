package com.datagrandeur.neuropsych;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.neuropsych.R;

public class ExperimentActivity extends AppCompatActivity {
    private int mBalloonSize=100;
    private ImageView mBalloonView;
    private Button inflate;
    private TextView mInflateCountText;
    private int[] mInflateSequence={1,2,3,4,5,3,2,1};
    private int mInflateCount=0;
    private int mCurrentInflateIndex=0;
    private int mBlastLimit=5;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experiment);
         mBalloonView= findViewById(R.id.balloon_view);
         inflate=findViewById(R.id.button6);
         mInflateCountText=findViewById(R.id.inflate_count_text);
        final MediaPlayer mediaPlayer= MediaPlayer.create(this,R.raw.inflate);
        inflate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
                inflateBalloon();
            }
        });

    }
    private void inflateBalloon() {


        mBalloonSize += 10;
        RelativeLayout.LayoutParams Relative_params = new RelativeLayout.LayoutParams(mBalloonSize, mBalloonSize);
        //Relative_params.setMargins(100,200,0,200);
        Relative_params.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        mBalloonView.setLayoutParams(Relative_params);

        mBalloonView.requestLayout();

        int nextInflation = mInflateSequence[mCurrentInflateIndex];
        mCurrentInflateIndex++;

        for (int i = 0; i < nextInflation; i++) {
            mInflateCount++;
            mInflateCountText.setText(String.valueOf(mInflateCount));


        }if (mInflateCount == mBlastLimit) {
            blastBalloon();
            return;
        }
        if (mCurrentInflateIndex >= mInflateSequence.length) {
            inflate.setEnabled(false);
        }
    }
        private void blastBalloon(){
            mInflateCount=0;
            mCurrentInflateIndex=0;
            mInflateCountText.setText("0");
            mBalloonView.setImageResource(R.drawable.poppedballoon);
            inflate.setEnabled(false);

        }
    }
