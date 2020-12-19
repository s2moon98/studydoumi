package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
//choicer for board or product
public class ChoiceActivity extends AppCompatActivity {

    Button todolist_button, studytime_button, studytimechk_button, community_button;
    TextView emailTv, pwdTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);
        todolist_button= (Button) findViewById(R.id.todolist_button);
        todolist_button.setOnClickListener(listener);
        studytime_button= (Button) findViewById(R.id.studytime_button);
        studytime_button.setOnClickListener(listener);
        community_button= (Button) findViewById(R.id.community_button);
        community_button.setOnClickListener(listener);
        emailTv = (TextView) findViewById(R.id.emailTv);
        pwdTv = (TextView) findViewById(R.id.pwdTv);

    }
    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            switch (v.getId())
            {
                case R.id.todolist_button://see the product
                    Toast.makeText(ChoiceActivity.this, "To do list", Toast.LENGTH_SHORT).show();
                    Intent intent = getIntent();
                    startActivity(new Intent(ChoiceActivity.this, TestCustomListViewActivity.class));
                    //finish();
                    break;
                case R.id.studytime_button://see the product
                    Toast.makeText(ChoiceActivity.this, "study time", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ChoiceActivity.this, studytime.class));
                    //finish();
                    break;
                case R.id.community_button://see the board
                    Toast.makeText(ChoiceActivity.this, "board", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ChoiceActivity.this, BoardActivity.class));
                    //finish();
                    break;
            }
        }
    };
}