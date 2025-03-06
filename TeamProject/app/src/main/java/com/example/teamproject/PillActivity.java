package com.example.teamproject;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.Calendar;

public class PillActivity extends AppCompatActivity {
    private static final String TAG = "PillActivity";
    private static final int REQUEST_CODE_SCHEDULE_EXACT_ALARM = 1;

    private TimePicker timePicker;
    private TextView timer1;
    private Button setTime;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pill);

        Button pillback = findViewById(R.id.pillbackbutton);
        pillback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        timer1 = findViewById(R.id.timer1);
        timePicker = findViewById(R.id.timepicker);
        setTime = findViewById(R.id.buttonSetTimer);
        setTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    setAlarm();
                }
            });
    }

    @SuppressLint("ScheduleExactAlarm")
    private void setAlarm() {
            int hour, min;
            hour = timePicker.getHour();
            min = timePicker.getMinute();

            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, hour);
            calendar.set(Calendar.MINUTE, min);
            calendar.set(Calendar.SECOND, 0);

            if (calendar.getTimeInMillis() <= System.currentTimeMillis()) {
                calendar.add(Calendar.DAY_OF_YEAR, 1);
            }

            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
            Intent intent = new Intent(this, AlarmReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

            alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
            Toast.makeText(this, "알람이 설정되었습니다.", Toast.LENGTH_SHORT).show();

            timer1.setText("알람시간: " + String.format("%02d", hour) + " : " + String.format("%02d", min));
    }
}