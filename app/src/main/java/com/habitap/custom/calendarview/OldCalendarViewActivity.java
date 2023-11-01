package com.habitap.custom.calendarview;

import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Shows off the most basic usage
 */
public class OldCalendarViewActivity extends AppCompatActivity
        implements CalendarView.OnDateChangeListener {

    private static final DateFormat FORMATTER = SimpleDateFormat.getDateInstance();

    CalendarView widget;

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_old_calendarview);
        widget = findViewById(R.id.calendarView);
        textView = findViewById(R.id.textView);

        widget.setOnDateChangeListener(this);
    }

    @Override
    public void onSelectedDayChange(
            CalendarView view, int year, int month,
            int dayOfMonth) {
        textView.setText(FORMATTER.format(view.getDate()));
    }
}
