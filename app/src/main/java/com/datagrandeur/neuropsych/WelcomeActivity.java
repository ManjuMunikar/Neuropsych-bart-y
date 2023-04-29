package com.datagrandeur.neuropsych;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.neuropsych.R;


public class WelcomeActivity extends AppCompatActivity {
    private TextView txtViewWelcome;
    private int counter;
    private TextView txtViewGameInfo;
    private TextView txtViewPumpInfo;
    private TextView txtBox;
    private TextView txtViewNext;
    private TextView txtInfo1;
    private TextView txtInfo2;
    private TextView txtInfo3;
    private TextView txtInfo4;
    private TextView txtInfo5;
    private TextView txtInfo6;
    private TextView txtInfo7;
    private TextView txtInfo8;
    private Button btnPump;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        txtViewWelcome=findViewById(R.id.txt_Welcome);
        txtBox=findViewById(R.id.text_instruction);
        //textViewWelcome.findViewById(R.id.text_instruction);
        txtViewGameInfo=findViewById(R.id.Game_info1);
        txtViewPumpInfo=findViewById(R.id.pump_info);
        btnPump=findViewById(R.id.pump);
        txtViewNext=findViewById(R.id.next);
        txtInfo1=findViewById(R.id.txt_info1);
        txtInfo2=findViewById(R.id.txt_info2);
        txtInfo3=findViewById(R.id.txt_info3);
        txtInfo4=findViewById(R.id.txt_info4);
        txtInfo5=findViewById(R.id.txt_info5);
        txtInfo6=findViewById(R.id.txt_info6);
        txtInfo7=findViewById(R.id.txt_info7);
        txtInfo8=findViewById(R.id.txt_info8);
        txtViewNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter++;
                if(counter==1){
                    txtViewWelcome.setVisibility(View.GONE);
                    txtViewGameInfo.setVisibility(View.VISIBLE);
                }
                else if(counter==2){
                    txtViewGameInfo.setVisibility(View.GONE);
                    txtViewPumpInfo.setVisibility(View.VISIBLE);
                    btnPump.setVisibility(View.VISIBLE);

                }else if(counter==3){
                    txtViewPumpInfo.setVisibility(View.GONE);
                    txtInfo1.setVisibility(View.VISIBLE);
                }else if(counter==4){
                    txtInfo1.setVisibility(View.GONE);
                    txtInfo2.setVisibility(View.VISIBLE);

                }else if(counter==5){
                    txtInfo2.setVisibility(View.GONE);
                    txtInfo3.setVisibility(View.VISIBLE);

                }
                else if(counter==6){
                    txtInfo3.setVisibility(View.GONE);
                    txtInfo4.setVisibility(View.VISIBLE);
                } else if (counter==7) {
                    txtInfo4.setVisibility(View.GONE);
                    txtInfo5.setVisibility(View.VISIBLE);

                } else if (counter==8) {
                    txtInfo5.setVisibility(View.GONE);
                    txtInfo6.setVisibility(View.VISIBLE);


                } else if (counter==9) {
                    txtInfo6.setVisibility(View.GONE);
                    txtInfo7.setVisibility(View.VISIBLE);


                } else if (counter==10) {
                    txtInfo7.setVisibility(View.GONE);
                    txtInfo8.setVisibility(View.VISIBLE);

                } else if (counter==11) {
                    txtBox.setVisibility(View.GONE);
                    txtViewNext.setVisibility(View.GONE);


                }


            }
        });

    }
}