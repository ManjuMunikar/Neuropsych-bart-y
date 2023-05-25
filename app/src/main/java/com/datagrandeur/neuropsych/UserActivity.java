package com.datagrandeur.neuropsych;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.datagrandeur.neuropsych.data.DatabaseHelper;
import com.example.neuropsych.R;

public class UserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        Button nextButton = findViewById(R.id.Register);
        final EditText txtUserId = findViewById(R.id.txtUserId);
        final EditText txtFullName = findViewById(R.id.txtFullName);
        final TextView txtLoginScreenMessage = findViewById(R.id.txtLoginScreenMessage);

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
                    Singleton.getInstance().setTrialSequence(1);

                    Intent intent = new Intent(UserActivity.this, WelcomeActivity.class);
                    startActivity(intent);

                }
            }
        });
    }
}