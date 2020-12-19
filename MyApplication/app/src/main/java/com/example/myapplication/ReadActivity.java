package com.example.myapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ReadActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText titleText;
    private EditText contentText;
    private TextView id;
    Button add_btn,delete_btn;
    private long BoardId = -1;
    private long deleteId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
    //set info for using read
        titleText = findViewById(R.id.title_modify);
        contentText = findViewById(R.id.content_modify);
        add_btn=findViewById(R.id.modify);

        Intent intent = getIntent();

        if (intent != null) {//if error
            BoardId  = intent.getLongExtra("id", -1);
            String title = intent.getStringExtra("title");
            String content = intent.getStringExtra("content");
            titleText.setText(title);
            contentText.setText(content);
        }
        add_btn.setOnClickListener(this);
    }
    //for writing
    @Override
    public void onClick(View v) {
        if(v==add_btn){//modified
            String title = titleText.getText().toString();
            String contents = contentText.getText().toString();
            ContentValues contentValues = new ContentValues();
            contentValues.put(BoardContract.BoardEntry.COLUMN_NAME_TITLE, title);
            contentValues.put(BoardContract.BoardEntry.COLUMN_NAME_CONTENT, contents);
            //add into db
            SQLiteDatabase db = BoardDBHelper.getsInstance(this).getWritableDatabase();
        //error founding
            if (BoardId  == -1) {
                long IDSucess = db.insert(BoardContract.BoardEntry.TABLE_NAME, null, contentValues);
                if (IDSucess == -1) {
                    Toast.makeText(this, "저장 오류", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "저장 성공", Toast.LENGTH_SHORT).show();
                    setResult(RESULT_OK);
                }
            } else {

                int count = db.update(BoardContract.BoardEntry.TABLE_NAME, contentValues, BoardContract.BoardEntry._ID + "=" +BoardId , null);
                if (count == 0) {
                    Toast.makeText(this, "수정 오류", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "수정 성공", Toast.LENGTH_SHORT).show();
                    setResult(RESULT_OK);
                }
            }
            finish();
        }
        }

}
