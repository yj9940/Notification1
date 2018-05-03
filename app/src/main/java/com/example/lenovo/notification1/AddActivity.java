package com.example.lenovo.notification1;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddActivity extends Activity{
    private Button conButton;
//    private TimePicker tp;
//    private DatePicker dp;
    private EditText etTitle, etNote;
    private String title, text;
    private SharedPreferences sp;

    private int mYear;
    private int mMonth;
    private int mDay;
    private int mHour;
    private int mMinute;

    private TextView tv_date;
    private TextView tv_time;

    private Calendar c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        init();

        Button dButton = (Button) findViewById(R.id.bt_date);
        dButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(AddActivity.this, new DatePickerDialog.OnDateSetListener(){
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        mYear=year;
                        mMonth=monthOfYear;
                        mDay=dayOfMonth;
                        updatedate();
                    }
                },c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        Button tButton = (Button) findViewById(R.id.bt_time);
        tButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(AddActivity.this,new TimePickerDialog.OnTimeSetListener()
                {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        mHour=hourOfDay;
                        mMinute=minute;
                        updatetime();
                    }
                },c.get(Calendar.HOUR_OF_DAY),c.get(Calendar.MINUTE),false).show();
            }
        });
    }

    protected void onStart() {
        super.onStart();
        conButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                saveTime();

            }
        });
    }

    @SuppressLint("NewApi")
    public void init() {
        conButton = (Button) findViewById(R.id.add_bt_confirm);
//        tp = (TimePicker) findViewById(R.id.timePicker1);
//        dp = (DatePicker) findViewById(R.id.datePicker1);
        etTitle = (EditText) findViewById(R.id.add_et_title);
        etNote = (EditText) findViewById(R.id.add_et_note);
//        tp.setIs24HourView(true);
//        dp.setCalendarViewShown(false);
        sp = getSharedPreferences("UserNote", MODE_PRIVATE);

        tv_date = (TextView) findViewById(R.id.tv_date);
        tv_time = (TextView) findViewById(R.id.tv_time);

        c=Calendar.getInstance();
    }

    public void saveTime() {
        text = etNote.getText().toString();
        title = etTitle.getText().toString();
//        int day = dp.getDayOfMonth();
//        int mon = dp.getMonth()+1;//getMonth 返回0
//        System.out.println("setMon is"+mon);
//        int year = dp.getYear();
//        int hour = tp.getCurrentHour();
//        int min = tp.getCurrentMinute();

//        SimpleDateFormat sdf = new SimpleDateFormat("HHmm");

        String timeShow = MyTime.getTimeShow(mYear, mMonth + 1, mDay, mHour, mMinute);

        String time = MyTime.getTime(mYear, mMonth + 1, mDay, mHour, mMinute);

        System.out.println(timeShow+"我的我的都是我的");
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("text", text);
        editor.putString("time", time);
        editor.putString("title",title);
        editor.commit();
        System.out.println(time+"：模式");
        Intent intent = new Intent(AddActivity.this, NotifyService.class);
        startService(intent);
        Log.i("ADD", "intent");
        finish();
    }


    private String format(int x)
    {
        String s=""+x;
        if(s.length()==1) s="0"+s;
        return s;
    }
    private void updatedate()
    {
        tv_date.setText(new StringBuilder().append(mYear).append("/")
                .append(format(mMonth + 1)).append("/")
                .append(format(mDay)).append(" "));
    }
    private void updatetime()
    {
        tv_time.setText(new StringBuilder().append(mHour).append(":")
                .append(format(mMinute)).append(" "));
    }

}