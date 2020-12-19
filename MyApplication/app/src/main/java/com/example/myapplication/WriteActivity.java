package com.example.myapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
//for writing page
public class WriteActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText titleText;
    private EditText contentText;
    Button add_btn;
    private long BoardId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);
        titleText = findViewById(R.id.title_edit);
        contentText = findViewById(R.id.content_edit);
        add_btn=findViewById(R.id.add);

        Intent intent = getIntent();
        if (intent != null) {
            BoardId  = intent.getLongExtra("id", -1);
            String title = intent.getStringExtra("title");
            String content = intent.getStringExtra("content");
            titleText.setText(title);
            contentText.setText(content);
        }
        add_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==add_btn) {
            String title = titleText.getText().toString();//get the data
            String content = contentText.getText().toString();
            ContentValues contentValues = new ContentValues();
            contentValues.put(BoardContract.BoardEntry.COLUMN_NAME_TITLE, title);
            contentValues.put(BoardContract.BoardEntry.COLUMN_NAME_CONTENT, content);
            SQLiteDatabase db = BoardDBHelper.getsInstance(this).getWritableDatabase();
            startActivity(new Intent(WriteActivity.this, BoardActivity.class));
            finish();
//check for saving
            if (BoardId == -1) {
                long successID = db.insert(BoardContract.BoardEntry.TABLE_NAME, null, contentValues);//check if it is success
                if (successID == -1) {
                    Toast.makeText(this, "글 저장 오류", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "글 저장 완료", Toast.LENGTH_SHORT).show();
                    setResult(RESULT_OK);
                }
            }
        }

        finish();
    }
}