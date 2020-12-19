package com.example.myapplication;
import android.provider.BaseColumns;
public class BoardContract {

        private BoardContract() {}
//for saving information about board
        public static class BoardEntry implements BaseColumns {
            public static final String TABLE_NAME = "board";
            public static final String COLUMN_NAME_TITLE = "title";
            public static final String COLUMN_NAME_CONTENT = "content";

        }

}
