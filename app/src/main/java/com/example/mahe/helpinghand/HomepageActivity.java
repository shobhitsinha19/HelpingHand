package com.example.mahe.helpinghand;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telecom.Call;
import android.telephony.SmsManager;
import android.util.Base64;
import android.view.View;
import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.FrameLayout;
import android.view.KeyEvent;
import android.util.Log;
import android.widget.Toast;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomepageActivity extends AppCompatActivity {

    CircleImageView contact1, contact2,contact3,contact4;

    String phone1, phone2, phone3, phone4;
    String fimg1, fimg2, fimg3, fimg4;
    String name1, name2, name3, name4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        contact1 = (CircleImageView) findViewById(R.id.contact1);
        contact2 = (CircleImageView) findViewById(R.id.contact2);
        contact3 = (CircleImageView) findViewById(R.id.contact3);
        contact4 = (CircleImageView) findViewById(R.id.contact4);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(HomepageActivity.this);
        fimg1 = sharedPreferences.getString("fimg1", "qwerty");
        fimg2 = sharedPreferences.getString("fimg2", "qwerty");
        fimg3 = sharedPreferences.getString("fimg3", "qwerty");
        fimg4 = sharedPreferences.getString("fimg4", "qwerty");
        byte[] f1 = Base64.decode(fimg1, Base64.DEFAULT);
        Bitmap d1 = BitmapFactory.decodeByteArray(f1, 0, f1.length);
        contact1.setImageBitmap(d1);
        byte[] f2 = Base64.decode(fimg2, Base64.DEFAULT);
        Bitmap d2 = BitmapFactory.decodeByteArray(f2, 0, f2.length);
        contact2.setImageBitmap(d2);
        byte[] f3 = Base64.decode(fimg3, Base64.DEFAULT);
        Bitmap d3 = BitmapFactory.decodeByteArray(f3, 0, f3.length);
        contact3.setImageBitmap(d3);
        byte[] f4 = Base64.decode(fimg4, Base64.DEFAULT);
        Bitmap d4 = BitmapFactory.decodeByteArray(f4, 0, f4.length);
        contact4.setImageBitmap(d4);
        name1 = sharedPreferences.getString("fname1", "");
        name2 = sharedPreferences.getString("fname2", "");
        name3 = sharedPreferences.getString("fname3", "");
        name4 = sharedPreferences.getString("fname4", "");
        phone1 = sharedPreferences.getString("fphone1", "");
        phone2 = sharedPreferences.getString("fphone2", "");
        phone3 = sharedPreferences.getString("fphone3", "");
        phone4 = sharedPreferences.getString("fphone4", "");

        contact1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(HomepageActivity.this, Call_SMS_TTS_Activity.class);
                i.putExtra("phone",phone1);
                startActivity(i);
            }
        });
        contact2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(HomepageActivity.this, Call_SMS_TTS_Activity.class);
                i.putExtra("phone",phone2);
                startActivity(i);
            }
        });
        contact3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(HomepageActivity.this, Call_SMS_TTS_Activity.class);
                i.putExtra("phone",phone3);
                startActivity(i);
            }
        });

        contact4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(HomepageActivity.this, Call_SMS_TTS_Activity.class);
                i.putExtra("phone",phone4);
                startActivity(i);
            }
        });

    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_VOLUME_DOWN)) {

            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phone1.trim(), null, "It's an emergency. Please help", null, null);


            String number = "tel:" + phone1.trim();
            Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse(number));
            startActivity(callIntent);

        }

        return true;
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
        startActivity(intent);
        finish();
        System.exit(0);
    }
}
