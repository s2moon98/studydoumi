package com.example.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

public class MembershipOpenHelper extends SQLiteOpenHelper {

    Context context;

    public MembershipOpenHelper(Context context) {
        super(context, "member.db", null, 1);
        this.context = context;
    }

    @Override//creating table membership and product
    public void onCreate(SQLiteDatabase db) {
        try {
            String sql1 = "create table membership (" +
                    "pwd varchar(72) not null," +
                    "email varchar(40) not null primary key"+
                    ");";

            db.execSQL(sql1);
            Toast.makeText(context, "[membership]table created", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}