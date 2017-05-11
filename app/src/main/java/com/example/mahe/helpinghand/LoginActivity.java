package com.example.mahe.helpinghand;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;

public class LoginActivity extends AppCompatActivity {
    DatabaseReference root = FirebaseDatabase.getInstance().getReference();
    String name1, name2, name3, name4, fimg1, fimg2, fimg3, fimg4, phone1, phone2, phone3, phone4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button button = (Button) findViewById(R.id.login);
        final EditText pass, user;
        user = (EditText) findViewById(R.id.user);
        pass = (EditText) findViewById(R.id.pass);
        Button button1 = (Button) findViewById(R.id.register);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          DatabaseReference root2 = root.child(user.getText().toString());
                                          //.equalTo("password", pass.getText().toString());
                                          root2.addValueEventListener(new ValueEventListener() {
                                                                          @Override
                                                                          public void onDataChange(DataSnapshot dataSnapshot) {
                                                                              Iterator i = dataSnapshot.getChildren().iterator();
                                                                              Iterator j = i;
                                                                              while (i.hasNext()) {
                                                                                  fimg1 = (String) (((DataSnapshot) i.next()).getValue());
                                                                                  fimg2 = (String) (((DataSnapshot) i.next()).getValue());
                                                                                  fimg3 = (String) (((DataSnapshot) i.next()).getValue());
                                                                                  fimg4 = (String) (((DataSnapshot) i.next()).getValue());
                                                                                  name1 = (String) (((DataSnapshot) i.next()).getValue());
                                                                                  name2 = (String) (((DataSnapshot) i.next()).getValue());
                                                                                  name3 = (String) (((DataSnapshot) i.next()).getValue());
                                                                                  name4 = (String) (((DataSnapshot) i.next()).getValue());
                                                                                  phone1 = (String) (((DataSnapshot) i.next()).getValue());
                                                                                  phone2 = (String) (((DataSnapshot) i.next()).getValue());
                                                                                  phone3 = (String) (((DataSnapshot) i.next()).getValue());
                                                                                  phone4 = (String) (((DataSnapshot) i.next()).getValue());
                                                                                  String p = (String) (((DataSnapshot) i.next()).getValue());

                                                                                  if (p.equals(pass.getText().toString())) {
                                                                                      SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
                                                                                      SharedPreferences.Editor editor = sharedPreferences.edit();
                                                                                      editor.putString("fname1", name1);
                                                                                      editor.putString("fname2", name2);
                                                                                      editor.putString("fname3", name3);
                                                                                      editor.putString("fname4", name4);
                                                                                      editor.putString("fphone1", phone1);
                                                                                      editor.putString("fphone2", phone2);
                                                                                      editor.putString("fphone3", phone3);
                                                                                      editor.putString("fphone4", phone4);
                                                                                      editor.putString("fimg1", fimg1);
                                                                                      editor.putString("fimg2", fimg2);
                                                                                      editor.putString("fimg3", fimg3);
                                                                                      editor.putString("fimg4", fimg4);
                                                                                      editor.putBoolean("logIn", true);
                                                                                      editor.apply();
                                                                                      Intent intent = new Intent(LoginActivity.this, HomepageActivity.class);
                                                                                      startActivity(intent);
                                                                                  } else {

                                                                                      Snackbar.make(getCurrentFocus(), "Incorrect Credentials", Snackbar.LENGTH_SHORT).show();
                                                                                  }
                                                                              }
                                                                              //set.add(((DataSnapshot)i.next()).getKey());
                                                                          }


                                                                          @Override
                                                                          public void onCancelled(DatabaseError databaseError) {

                                                                          }
                                                                      }

                                          );

                                      }

                                  }

        );
    }

}
