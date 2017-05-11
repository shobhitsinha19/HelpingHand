package com.example.mahe.helpinghand;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class Call_SMS_TTS_Activity extends AppCompatActivity {

    Button call, sms, tts;
    String phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call__sms__tts_);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent i = getIntent();
        phone = i.getExtras().get("phone").toString().trim();

        call = (Button) findViewById(R.id.callButton);
        sms = (Button) findViewById(R.id.smsButton);
        tts = (Button) findViewById(R.id.ttsButton);

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = "tel:" + phone;
                Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse(number));
                startActivity(callIntent);
            }
        });
        sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent smsAct = new Intent(Call_SMS_TTS_Activity.this,SMSActivity.class);
                smsAct.putExtra("phone",phone);
                startActivity(smsAct);
            }
        });

        tts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Call_SMS_TTS_Activity.this,TTSActivity.class);
                startActivity(i);

            }
        });


    }

}
