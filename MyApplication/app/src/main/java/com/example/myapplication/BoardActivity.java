package com.example.myapplication;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.Nullable;

public class BoardActivity extends AppCompatActivity implements View.OnClickListener{

    public static final int REQUEST_CODE_INSERT = 1000;
    private MemoAdapter Adapter;
    //boardMain


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);
        Button btn= findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(BoardActivity.this, WriteActivity.class), REQUEST_CODE_INSERT);
            }

        });

        ListView listView = findViewById(R.id.board_list);

        Cursor cursor = getMemoCursor();
        Adapter = new MemoAdapter(this, cursor);
        listView.setAdapter(Adapter);

        //show the writing list
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(BoardActivity.this, ReadActivity.class);

                Cursor cursor = (Cursor) Adapter.getItem(position);

                String title = cursor.getString(cursor.getColumnIndexOrThrow(BoardContract.BoardEntry.COLUMN_NAME_TITLE));
                String content = cursor.getString(cursor.getColumnIndexOrThrow(BoardContract.BoardEntry.COLUMN_NAME_CONTENT));

                intent.putExtra("id", id);
                intent.putExtra("title", title);
                intent.putExtra("content", content);

                startActivityForResult(intent, REQUEST_CODE_INSERT);
            }
        });


       //for deleting
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                final long deleteId = id;

                AlertDialog.Builder builder = new AlertDialog.Builder(BoardActivity.this);
                builder.setTitle("Deleting");
                builder.setMessage("글을 삭제하시겠습니까?");
                builder.setPositiveButton("delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //call db
                        SQLiteDatabase db = BoardDBHelper.getsInstance(BoardActivity.this).getWritableDatabase();
                        int deletedCount = db.delete(BoardContract.BoardEntry.TABLE_NAME,
                                BoardContract.BoardEntry._ID + "=" + deleteId, null);

                        if (deletedCount == 0 ) {
                            Toast.makeText(BoardActivity.this, "삭제 에러", Toast.LENGTH_SHORT).show();
                        } else {
                            Adapter.swapCursor(getMemoCursor());
                            Toast.makeText(BoardActivity.this, "삭제 성공", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                builder.setNegativeButton("cancel", null);
                builder.show();
                return true;
            }
        });

    }

    public void onClick(View v) {

    }
    // searching DB
    private Cursor getMemoCursor() {
        BoardDBHelper dbHelper = BoardDBHelper.getsInstance(this);
        return dbHelper.getReadableDatabase()
                .query(BoardContract.BoardEntry.TABLE_NAME,
                        null, null, null, null, null, null);
    }

//get the result code
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_INSERT && resultCode == RESULT_OK) {
            Adapter.swapCursor(getMemoCursor());
        }
    }

    //adapter for board
    private static class MemoAdapter extends CursorAdapter {

        public MemoAdapter(Context context, Cursor c) {
            super(context, c);
        }
    //use list for writing
        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            return LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, parent, false);
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            TextView titleText = view.findViewById(android.R.id.text1);
            titleText.setText(cursor.getString(cursor.getColumnIndexOrThrow(BoardContract.BoardEntry.COLUMN_NAME_TITLE)));
        }
    }

}
