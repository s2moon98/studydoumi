package com.example.myapplication;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
//main page
public class MainActivity extends AppCompatActivity {

    TextView emailTv, pwdTv;
    MembershipOpenHelper openHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        String email = intent.getStringExtra("email");
        String pwd = intent.getStringExtra("pwd");
        emailTv = (TextView) findViewById(R.id.emailTv);
        pwdTv = (TextView) findViewById(R.id.pwdTv);


        //go to next
        Handler hand = new Handler();
        hand.postDelayed(new Runnable() {
            @Override
            public void run() {

                //Intent intent =new Intent(MainActivity.this,LoginActivity.class);
                Intent intent =new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);
    }

}
