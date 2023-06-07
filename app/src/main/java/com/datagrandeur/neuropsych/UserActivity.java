package com.datagrandeur.neuropsych;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.datagrandeur.neuropsych.data.DatabaseHelper;
import com.datagrandeur.neuropsych.data.User;
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


                        Singleton.getInstance().setTrialSequence(0);

                        Intent intent = new Intent(UserActivity.this, WelcomeActivity.class);
                        startActivity(intent);

                        User user = new User();
                        user.setUserId(Singleton.getInstance().getUserId());
                        // user.setFullName(Singleton.getInstance().getFullName());
                        dbHelper.insertUser(user, dbHelper.getDb());
                    }



                    Singleton.getInstance().setReward(0);

                }

        });

        ImageButton imgBtnSetting= findViewById(R.id.imgBtnSetting);
        imgBtnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(UserActivity.this, CustomDialogActivity.class);
                startActivity(intent);
            }
        });
    }

//    public void btn_showDialog(View view) {
//        final AlertDialog.Builder alert = new  AlertDialog.Builder(UserActivity.this);
//        //Inflate the custom_dialog view
//        View mView = getLayoutInflater().inflate(R.layout.dialog,null);
//
//        final EditText edtPassword =(EditText) mView.findViewById(R.id.edtPassword);
//        Button btnOk= (Button)mView.findViewById(R.id.btnOk);
//        Button btnCancel = (Button)mView.findViewById(R.id.btnCancel);
//
//        alert.setView(mView);
//        //Create Dialog
//        final AlertDialog alertDialog = alert.create();
//        alertDialog.setCanceledOnTouchOutside(false);
//
//        btnCancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                alertDialog.dismiss();
//
//            }
//        });
//
//
//        btnOk.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(TextUtils.isEmpty(edtPassword.getText().toString().trim())){
//                    edtPassword.setError("Required!");
//                } else if (!TextUtils.equals(edtPassword.getText().toString(), "12345")) {
//                    edtPassword.setError("Incorrect");
//
//                }else{
//                    Intent intent= new Intent(UserActivity.this, SettingActivity.class);
//                    startActivity(intent);
//                }
//
//            }
//        });
//
//
//    }
}