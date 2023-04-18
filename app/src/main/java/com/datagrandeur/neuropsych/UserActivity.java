package com.datagrandeur.neuropsych;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.neuropsych.R;

public class UserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        Button nextButton = findViewById(R.id.btnShowInstruction);
        final EditText txtSubject = findViewById(R.id.txtSubject);
        final EditText txtFullName = findViewById(R.id.txtFullName);
        final TextView txtLoginScreenMessage = findViewById(R.id.txtLoginScreenMessage);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(txtSubject.getText().toString().trim())) {
                    txtSubject.setError("Required!");
                } else if (TextUtils.isEmpty(txtFullName.getText().toString().trim())) {
                    txtFullName.setError("Required!");
                } else {

                    Singleton.getInstance().setUserId(txtSubject.getText().toString().trim());
                    Singleton.getInstance().setFullname(txtFullName.getText().toString().trim());

                  //  Intent intent = new Intent(getApplicationContext(), IntroActivity.class);
                   // startActivity(intent);

                }
            }
        });
    }
}