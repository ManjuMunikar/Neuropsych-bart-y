package com.datagrandeur.neuropsych;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.neuropsych.R;

import org.w3c.dom.Text;

public class RewardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reward);

        TextView tvRewardPoint = findViewById(R.id.tvPoints);

        String congratulations = getString(R.string.congratulations);
        String finalStr = String.format(congratulations, ""+Singleton.getInstance().getReward(), getReward());

        tvRewardPoint.setText(finalStr);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent=new Intent(RewardActivity.this, ThankYouActivity.class);
                startActivity(intent);
            }
        },7000);
    }

    private String getReward() {
       if(Singleton.getInstance().getReward() >=0 && Singleton.getInstance().getReward() <=25){
           return getString(R.string.smallPrize).replace("_","");
       }

        if(Singleton.getInstance().getReward() >25 && Singleton.getInstance().getReward() <=50){
            return getString(R.string.middlePrize).replace("_","");
        }

        if(Singleton.getInstance().getReward() >50 && Singleton.getInstance().getReward() <=75){
            return getString(R.string.bigPrize).replace("_","");
        }

        if(Singleton.getInstance().getReward() >75){
            return getString(R.string.bonus).replace("_","");
        }

        return "";
    }
}