package com.datagrandeur.neuropsych;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.neuropsych.R;

public class CustomDialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_dialog);

        EditText edtPasscode = findViewById(R.id.edtPasscode);
        Button btnOk = findViewById(R.id.btnOk);
        Button btnCancel = findViewById(R.id.btnCancel);


        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(edtPasscode.getText().toString().trim())){
                    edtPasscode.setError("Required!!");

                }else if(!TextUtils.equals(edtPasscode.getText().toString(), "12345")){
                    edtPasscode.setError("Incorrect");
                } else{
                        Intent intent= new Intent(CustomDialogActivity.this, SettingActivity.class);
                        startActivity(intent);
                    }
                }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(CustomDialogActivity.this, UserActivity.class);
                startActivity(intent);

            }
        });



    }
}