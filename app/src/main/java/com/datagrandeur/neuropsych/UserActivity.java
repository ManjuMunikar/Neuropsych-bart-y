package com.datagrandeur.neuropsych;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.datagrandeur.neuropsych.data.DatabaseHelper;
import com.datagrandeur.neuropsych.data.User;
import com.example.neuropsych.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        Button nextButton = findViewById(R.id.Register);
        final EditText txtUserId = findViewById(R.id.txtUserId);
        final EditText txtFullName = findViewById(R.id.txtFullName);

        SingletonPractice singletonPractice = SingletonPractice.getInstance();
        singletonPractice.setInstructionIndex(0);
        singletonPractice.setPoints(0);

        // final TextView txtLoginScreenMessage = findViewById(R.id.txtLoginScreenMessage);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext());


                if (TextUtils.isEmpty(txtUserId.getText().toString().trim())) {
                    txtUserId.setError("Required!");
                } else if (TextUtils.isEmpty(txtFullName.getText().toString().trim())) {
                    txtFullName.setError("Required!");
                } else {
                        Singleton.getInstance().setUserId(txtUserId.getText().toString().trim());
                        Singleton.getInstance().setFullName(txtFullName.getText().toString().trim());


                        Singleton.getInstance().setTrialSequence(0);
                        Singleton.getInstance().setExplosionPoints(getExplosionPoints());

                        Intent intent = new Intent(UserActivity.this, PracticeActivity.class);
                        startActivity(intent);

                        User user = new User();
                        user.setUserId(Singleton.getInstance().getUserId());
                        user.setFullName(Singleton.getInstance().getFullName());
                        dbHelper.insertUser(user, dbHelper.getDb());
                    }


                    Singleton.getInstance().setReward(0.0);

                }

        });

        ImageButton imgBtnSetting= findViewById(R.id.imgBtnSetting);
        imgBtnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(UserActivity.this, AdminActivity.class);
                startActivity(intent);
            }
        });
    }

    private int[] getExplosionPoints() {

        int[] result = new int[Constant.first15ExplosionPoints.length + Constant.last15ExplosionPoints.length];
        int[] firstArray = shuffleArray(Constant.first15ExplosionPoints);
        int[] lastArray = shuffleArray(Constant.last15ExplosionPoints);

        System.arraycopy(firstArray, 0, result, 0, firstArray.length);
        System.arraycopy(lastArray, 0, result, firstArray.length, lastArray.length);

        return result;


    }

    public static int[] shuffleArray(int[] array) {
        List<Integer> list = new ArrayList<>();

        for (int value : array) {
            list.add(value);
        }

        Collections.shuffle(list);

        for (int i = 0; i < array.length; i++) {
            array[i] = list.get(i);
        }

        return array;
    }

}