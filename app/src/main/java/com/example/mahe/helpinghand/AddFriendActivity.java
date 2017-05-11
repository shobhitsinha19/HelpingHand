package com.example.mahe.helpinghand;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class AddFriendActivity extends AppCompatActivity {

    Button b;
    ImageView image1, image2, image3, image4;
    TextView name1, name2, name3, name4;
    private static final int REQUEST_CODE = 1, REQUEST_CODE1 = 2;
    int counter;
    int userCount;
    Button button;
    ArrayList<String> list = new ArrayList<>();
    String phone1, phone2, phone3, phone4;
    DatabaseReference root = FirebaseDatabase.getInstance().getReference().getRoot();
    String fimg1, fimg2, fimg3, fimg4;
    String pass, user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(AddFriendActivity.this);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        button = (Button) findViewById(R.id.done);
        user = getIntent().getStringExtra("user");
        pass = getIntent().getStringExtra("password");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("fname1", name1.getText().toString());
                editor.putString("fname2", name2.getText().toString());
                editor.putString("fname3", name3.getText().toString());
                editor.putString("fname4", name4.getText().toString());
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
                Map<String, Object> m = new HashMap<String, Object>();
                m.put(user, "");
                root.updateChildren(m);
                DatabaseReference root2 = root.child(user);

                Map<String, Object> map = new HashMap<String, Object>();
                map.put("password", pass);
                map.put("fname1", name1.getText().toString());
                map.put("fname2", name2.getText().toString());
                map.put("fname3", name3.getText().toString());
                map.put("fname4", name4.getText().toString());
                map.put("fphone1", phone1);
                map.put("fphone2", phone2);
                map.put("fphone3", phone3);
                map.put("fphone4", phone4);
                map.put("fimg1", fimg1);
                map.put("fimg2", fimg2);
                map.put("fimg3", fimg3);
                map.put("fimg4", fimg4);
                //Map<String, Object> map2 = new HashMap<String, Object>();
                //map2.put(user, map);
                root2.updateChildren(map);
                Intent intent = new Intent(AddFriendActivity.this, HomepageActivity.class);
                startActivity(intent);

            }
        });
        /*
        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Set<String> set = new HashSet<String>();
                Iterator i = dataSnapshot.getChildren().iterator();
                while(i.hasNext()){

                    Log.d("Tag", ((DataSnapshot)i.next()).getKey());
                    //set.add(((DataSnapshot)i.next()).getKey());
                }
                //list.clear();
                //list.addAll(set);
                //Log.d("Tag", list.toString());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        }); */
        counter = userCount = 0;
        image1 = (ImageView) findViewById(R.id.imageView1);
        image2 = (ImageView) findViewById(R.id.imageView2);
        image3 = (ImageView) findViewById(R.id.imageView3);
        image4 = (ImageView) findViewById(R.id.imageView4);
        name1 = (TextView) findViewById(R.id.contactName1);
        name2 = (TextView) findViewById(R.id.contactName2);
        name3 = (TextView) findViewById(R.id.contactName3);
        name4 = (TextView) findViewById(R.id.contactName4);
        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ++counter;
                if (counter % 2 != 0) {

                    Uri uri = Uri.parse("content://contacts");
                    Intent intent = new Intent(Intent.ACTION_PICK, uri);
                    intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
                    startActivityForResult(intent, REQUEST_CODE);
                } else {
                    Intent intent1 = new Intent(Intent.ACTION_GET_CONTENT);
                    intent1.setType("image/*");
                    startActivityForResult(intent1, REQUEST_CODE1);
                }
            }

        });
        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ++counter;
                if (counter % 2 != 0) {

                    Uri uri = Uri.parse("content://contacts");
                    Intent intent = new Intent(Intent.ACTION_PICK, uri);
                    intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
                    startActivityForResult(intent, REQUEST_CODE);
                } else {
                    Intent intent1 = new Intent(Intent.ACTION_GET_CONTENT);
                    intent1.setType("image/*");
                    startActivityForResult(intent1, REQUEST_CODE1);
                }
            }

        });
        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ++counter;
                if (counter % 2 != 0) {

                    Uri uri = Uri.parse("content://contacts");
                    Intent intent = new Intent(Intent.ACTION_PICK, uri);
                    intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
                    startActivityForResult(intent, REQUEST_CODE);
                } else {
                    Intent intent1 = new Intent(Intent.ACTION_GET_CONTENT);
                    intent1.setType("image/*");
                    startActivityForResult(intent1, REQUEST_CODE1);
                }
            }

        });
        image4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ++counter;
                if (counter % 2 != 0) {

                    Uri uri = Uri.parse("content://contacts");
                    Intent intent = new Intent(Intent.ACTION_PICK, uri);
                    intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
                    startActivityForResult(intent, REQUEST_CODE);
                } else {
                    Intent intent1 = new Intent(Intent.ACTION_GET_CONTENT);
                    intent1.setType("image/*");
                    startActivityForResult(intent1, REQUEST_CODE1);
                }
            }

        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent intent) {
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                userCount++;
                Uri uri = intent.getData();
                String[] projection = {ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME};

                Cursor cursor = getContentResolver().query(uri, projection,
                        null, null, null);
                cursor.moveToFirst();

                int numberColumnIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                String number = cursor.getString(numberColumnIndex);

                int nameColumnIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
                String name = cursor.getString(nameColumnIndex);

                Toast.makeText(getApplicationContext(), "Tap again to add a picture " + name, Toast.LENGTH_SHORT).show();
                if (userCount == 1) {
                    name1.setText(name);
                    phone1 = number;
                }
                if (userCount == 2) {
                    name2.setText(name);
                    phone2 = number;
                }
                if (userCount == 3) {
                    name3.setText(name);
                    phone3 = number;
                }
                if (userCount == 4) {
                    name4.setText(name);
                    phone4 = number;
                }
            }
        }
        if (requestCode == REQUEST_CODE1 && resultCode == RESULT_OK && intent != null && intent.getData() != null) {

            Uri uri = intent.getData();


            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                // Log.d(TAG, String.valueOf(bitmap));
                if (userCount == 1) {
                    image1.setImageBitmap(bitmap);
                    fimg1 = Base64.encodeToString(byteArray, Base64.DEFAULT);

                }
                if (userCount == 2) {
                    image2.setImageBitmap(bitmap);
                    fimg2 = Base64.encodeToString(byteArray, Base64.DEFAULT);

                }
                if (userCount == 3) {
                    image3.setImageBitmap(bitmap);
                    fimg3 = Base64.encodeToString(byteArray, Base64.DEFAULT);
                }
                if (userCount == 4) {
                    image4.setImageBitmap(bitmap);
                    fimg4 = Base64.encodeToString(byteArray, Base64.DEFAULT);
                }
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }

        ;

    }
