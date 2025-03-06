package com.example.teamproject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class EatActivity extends AppCompatActivity {

    private CalendarView calendarView;
    private LinearLayout linearLayoutCalories;
    private TextView textViewDate, textViewCalories;
    private EditText editTextCaloriesInput;
    private Button buttonSave, back;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private SharedPreferences sharedPreferences;
    private Calendar selectedDate; // selectedDate 초기화

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eat);

        calendarView = findViewById(R.id.calendarView);
        linearLayoutCalories = findViewById(R.id.linearLayoutCalories);
        textViewDate = findViewById(R.id.textViewDate);
        textViewCalories = findViewById(R.id.textViewCalories);
        editTextCaloriesInput = findViewById(R.id.editTextCaloriesInput);
        buttonSave = findViewById(R.id.buttonSave);
        back = findViewById(R.id.buttonback);

        sharedPreferences = getSharedPreferences("calories", MODE_PRIVATE);

        // 오늘 날짜로 초기화
        Calendar today = Calendar.getInstance();
        calendarView.setDate(today.getTimeInMillis());
        updateCalendar(today);

        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            selectedDate = Calendar.getInstance(); // 리스너 내에서 초기화
            selectedDate.set(year, month, dayOfMonth);
            updateCalendar(selectedDate);
        });

        buttonSave.setOnClickListener(v -> {
            // 선택된 날짜의 섭취 칼로리 저장
            String dateString; // 변수 선언 (선택적)
            if (dateFormat.format(selectedDate.getTime()).equals(textViewDate.getText().toString())) {
                dateString = textViewDate.getText().toString(); // 변수 사용 (선택적)
            } else {
                dateString = dateFormat.format(selectedDate.getTime());
            }
            int calories = Integer.parseInt(editTextCaloriesInput.getText().toString());
            sharedPreferences.edit().putInt(dateString, calories).apply();
            updateCalendar(selectedDate); // 칼로리 저장 후 다시 화면 갱신
        });
    }

    private void updateCalendar(Calendar selectedDate) {
        textViewDate.setText(dateFormat.format(selectedDate.getTime()));
        int calories = sharedPreferences.getInt(dateFormat.format(selectedDate.getTime()), 0);
        textViewCalories.setText(String.valueOf(calories));

        // 칼로리 입력/수정 모드 토글
        if (calories == 0) {
            linearLayoutCalories.setVisibility(View.GONE);
            editTextCaloriesInput.setVisibility(View.VISIBLE);
            buttonSave.setVisibility(View.VISIBLE);
        } else {
            linearLayoutCalories.setVisibility(View.VISIBLE);
            editTextCaloriesInput.setVisibility(View.GONE);
            buttonSave.setVisibility(View.GONE);
        }

        editTextCaloriesInput.setText(String.valueOf(calories));

        back.setOnClickListener(v -> finish());
    }
}