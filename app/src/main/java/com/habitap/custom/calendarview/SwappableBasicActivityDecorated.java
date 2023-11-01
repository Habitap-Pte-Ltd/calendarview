package com.habitap.custom.calendarview;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.habitap.custom.calendarview.decorators.HighlightWeekendsDecorator;
import com.habitap.custom.calendarview.decorators.MySelectorDecorator;
import com.habitap.custom.calendarview.decorators.OneDayDecorator;

import org.threeten.bp.LocalDate;
import org.threeten.bp.Month;

/**
 * Shows off the most basic usage
 */
public class SwappableBasicActivityDecorated extends AppCompatActivity
        implements OnDateSelectedListener {

    private final OneDayDecorator oneDayDecorator = new OneDayDecorator();

    MaterialCalendarView widget;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_modes);

        widget = findViewById(R.id.calendarView);

        widget.setOnDateChangedListener(this);
        widget.setShowOtherDates(MaterialCalendarView.SHOW_ALL);

        final LocalDate instance = LocalDate.now();
        widget.setSelectedDate(instance);

        final LocalDate min = LocalDate.of(instance.getYear(), Month.JANUARY, 1);
        final LocalDate max = LocalDate.of(instance.getYear(), Month.DECEMBER, 31);

        widget.state().edit().setMinimumDate(min).setMaximumDate(max).commit();

        widget.addDecorators(
                new MySelectorDecorator(this),
                new HighlightWeekendsDecorator(),
                oneDayDecorator
        );

        findViewById(R.id.button_weeks).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSetWeekMode();
            }
        });

        findViewById(R.id.button_months).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSetMonthMode();
            }
        });

    }

    @Override
    public void onDateSelected(
            @NonNull MaterialCalendarView widget,
            @NonNull CalendarDay date,
            boolean selected) {
        //If you change a decorate, you need to invalidate decorators
        oneDayDecorator.setDate(date.getDate());
        widget.invalidateDecorators();
    }

    public void onSetWeekMode() {
        widget.state().edit()
                .setCalendarDisplayMode(CalendarMode.WEEKS)
                .commit();
    }

    public void onSetMonthMode() {
        widget.state().edit()
                .setCalendarDisplayMode(CalendarMode.MONTHS)
                .commit();
    }
}
