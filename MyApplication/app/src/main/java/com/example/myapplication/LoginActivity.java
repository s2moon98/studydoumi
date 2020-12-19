package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class LoginActivity extends AppCompatActivity
{

    MembershipOpenHelper openHelper;
    EditText emailEt, pwdEt;
    SQLiteDatabase db;
    Button loginBtn, joinBtn;
    @Override
    protected void onCreate( Bundle savedInstanceState)
    {
        //setting variables
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        openHelper = new MembershipOpenHelper(this);
        db = openHelper.getWritableDatabase();
        emailEt = (EditText) findViewById(R.id.emailEt);
        pwdEt = (EditText) findViewById(R.id.pwdEt);
        loginBtn = (Button) findViewById(R.id.loginBtn);
        joinBtn = (Button) findViewById(R.id.joinBtn);
        loginBtn.setOnClickListener(listener);
        joinBtn.setOnClickListener(listener);

    }
    private void insertData()//set the product
    {
        try
        {
            String item="nintendo switch gray";
            int a=360000;
            int b=100;
            String sql1 ="insert into product(bno,item,price,stock) values('"+ 1 +"','"+ item +"','"+a+"','"+b+"')";
            item="nintendo switch lite";
            String sql2 ="insert into product(bno,item,price,stock) values('"+ 2 +"','"+ item +"','"+a+"','"+b+"')";
            item="nintendo switch neon";
            String sql3 ="insert into product(bno,item,price,stock) values('"+ 3 +"','"+ item +"','"+a+"','"+b+"')";
            a=1000;
            item="battery";
            String sql4 ="insert into product(bno,item,price,stock) values('"+ 4 +"','"+ item +"','"+a+"','"+b+"')";
            item="super mario";
            String sql5 ="insert into product(bno,item,price,stock) values('"+ 5 +"','"+ item +"','"+a+"','"+b+"')";
            item="amibo card";
            String sql6 ="insert into product(bno,item,price,stock) values('"+ 6 +"','"+ item +"','"+a+"','"+b+"')";
            db.execSQL(sql1);
            db.execSQL(sql2);
            db.execSQL(sql3);
            db.execSQL(sql4);
            db.execSQL(sql5);
            db.execSQL(sql6);
            //setting product for table
        }
        catch(Exception e)
        {

        }
    }
//listener function for join and login button
    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            switch (v.getId())
            {
                case R.id.joinBtn:    //join site
                    startActivity(new Intent(LoginActivity.this, JoinActivity.class));
                    finish();
                    break;
                case R.id.loginBtn://login button on
                    String email = emailEt.getText().toString();
                    String pwd = pwdEt.getText().toString();

                    //check and login
                    String sql = "select * from membership where email = '" + email + "' and pwd = '" + pwd + "'";
                    Cursor cursor = db.rawQuery(sql, null);
                    while (cursor.moveToNext()) {
                        String no = cursor.getString(0);
                        String rest_id = cursor.getString(1);
                        Log.d("select ", "no : " + no + "\nrest_id : " + rest_id);
                    }
        //check id
                    if (cursor.getCount() == 1) {//if correct login
                        insertData();//get the item
                        Toast.makeText(LoginActivity.this, email + " hello", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, ChoiceActivity.class);
                        intent.putExtra("email", email);
                        intent.putExtra("pwd", pwd);
                        startActivity( new Intent(LoginActivity.this,ChoiceActivity.class));

                    } else {
//not correct id
                        Toast.makeText(LoginActivity.this, "email or password is not correct", Toast.LENGTH_SHORT).show();
                    }
                    cursor.close();
                    break;
            }
        }
    };
}