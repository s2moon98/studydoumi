package com.example.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BoardDBHelper extends SQLiteOpenHelper {

    private static BoardDBHelper sInstance;
    //for make board string
    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "Board.db";
    public static final String SQL_CREATE_ENTERS =
            String.format(
                    "CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT)",
                    BoardContract.BoardEntry.TABLE_NAME,
                    BoardContract.BoardEntry._ID,
                    BoardContract.BoardEntry.COLUMN_NAME_TITLE,
                    BoardContract.BoardEntry.COLUMN_NAME_CONTENT);
//deleting table
    public static final String SQL_DELETE_ENTERS =
            "DROP TABLE IF EXISTS " + BoardContract.BoardEntry.TABLE_NAME;

    public static BoardDBHelper getsInstance(Context context) {
        if (sInstance == null) {
            sInstance = new BoardDBHelper(context);
        }
        return sInstance;
    }

    private BoardDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    //for making board table
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