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
        tvRewardPoint.setText("You won " + Singleton.getInstance().getReward() + " Points. ("  + getReward() +")  \n Congratulations!!!!");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent=new Intent(RewardActivity.this, ThankYouActivity.class);
                startActivity(intent);
            }
        },4000);
    }

    private String getReward() {
       if(Singleton.getInstance().getReward() >=0 && Singleton.getInstance().getReward() <=25){
           return "Small Prize";
       }

        if(Singleton.getInstance().getReward() >25 && Singleton.getInstance().getReward() <=50){
            return "Medium Prize";
        }

        if(Singleton.getInstance().getReward() >50 && Singleton.getInstance().getReward() <=75){
            return "Big Prize";
        }

        if(Singleton.getInstance().getReward() >75){
            return "Bonus Prize";
        }

        return "";
    }
}