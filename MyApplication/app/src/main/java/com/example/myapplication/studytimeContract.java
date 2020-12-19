package com.example.myapplication;

import android.provider.BaseColumns;

import java.util.Date;

public class studytimeContract {
    private studytimeContract() {}

    public static class studytimeEntry implements BaseColumns {
        public static final String TABLE_NAME="studytime";
        public static final String COLUMN_NAME_DATE="date";
        public static final String COLUMN_NAME_SUBJECT="subject";
        public static final String COLUMN_NAME_TIME="time";

    }

}

