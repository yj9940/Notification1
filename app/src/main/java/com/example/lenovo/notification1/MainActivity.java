package com.example.lenovo.notification1;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends Activity {

    SharedPreferences sp;
    Button button;
    TextView title;
    TextView note;
    TextView time;
    String strTime;
    String strTitle;
    String strNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        sp = getSharedPreferences("UserNote",MODE_PRIVATE);
    }

    public void init() {

        button = (Button) findViewById(R.id.bt);
        title = (TextView) findViewById(R.id.main_title);
        note = (TextView) findViewById(R.id.main_note);
        time = (TextView)findViewById(R.id.main_time);
    }

    protected void onStart() {
        super.onStart();
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

    }

    protected void onResume(){
        super.onResume();
        strTime = sp.getString("time","null");
        strTitle = sp.getString("title","null");
        strNote = sp.getString("text","null");
        title.setText(strTitle);
        note.setText(strNote);
        time.setText(strTime);
        System.out.println("onResume");

    }

}
