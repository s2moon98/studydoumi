package com.example.myapplication;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
//for join app
public class JoinActivity extends AppCompatActivity {

    MembershipOpenHelper openHelper;
    SQLiteDatabase db;
    EditText emailEt, pwdEt;
    Button joinBtn;

    @Override
    protected void onCreate( Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        openHelper = new MembershipOpenHelper(this);
        db = openHelper.getWritableDatabase();
        emailEt = (EditText) findViewById(R.id.emailEt2);
        pwdEt = (EditText) findViewById(R.id.pwdEt2);
        joinBtn = (Button) findViewById(R.id.joinBtn2);
        joinBtn.setOnClickListener(listener);
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.joinBtn2://if join case
                    String email = emailEt.getText().toString();
                    String pwd = pwdEt.getText().toString();
                    String sql = "select * from membership where email = '"+email+"'";
                    Cursor cursor = db.rawQuery(sql, null);
                    if(cursor.getCount() == 1) {
                    //already exist mail
                        Toast.makeText(JoinActivity.this, "already exist email", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(JoinActivity.this, MainActivity.class));
                        finish();
                    } else {
                        //successfully join

                        String sql2 ="insert into membership(email, pwd ) values('"+email+"','"+pwd+"')";
                        System.out.println(sql2);
                        db.execSQL(sql2);
                        Toast.makeText(JoinActivity.this, "Congratulation! join our site", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(JoinActivity.this, LoginActivity.class));
                    }
                    cursor.close();
                    break;
            }
        }
    };
}