package com.example.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class studytimeDBHelper extends SQLiteOpenHelper {

    private static studytimeDBHelper sInstance;
    //for make studytime string
    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "studytime.db";
    public static final String SQL_CREATE_ENTERS =
            String.format(
                    "CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT)",
                    studytimeContract.studytimeEntry.TABLE_NAME,
                    studytimeContract.studytimeEntry._ID,
                    studytimeContract.studytimeEntry.COLUMN_NAME_DATE,
                    studytimeContract.studytimeEntry.COLUMN_NAME_SUBJECT,
                    studytimeContract.studytimeEntry.COLUMN_NAME_TIME);
    //deleting table
    public static final String SQL_DELETE_ENTERS =
            "DROP TABLE IF EXISTS " + studytimeContract.studytimeEntry.TABLE_NAME;

    public static studytimeDBHelper getsInstance(Context context) {
        if (sInstance == null) {
            sInstance = new studytimeDBHelper(context);
        }
        return sInstance;
    }

    private studytimeDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    //for making studytime table
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTERS);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTERS);
        onCreate(db);
    }
}