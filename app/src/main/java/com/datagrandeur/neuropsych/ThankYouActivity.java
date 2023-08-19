package com.datagrandeur.neuropsych;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.neuropsych.R;

public class ThankYouActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thank_you);

        Singleton.getInstance().setPracticeSessionOver(false);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                finishAffinity();

            }
        },2000);
    }
}