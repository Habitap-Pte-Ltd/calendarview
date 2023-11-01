package com.habitap.custom.calendarview.decorators;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;

import com.habitap.custom.calendarview.CalendarDay;
import com.habitap.custom.calendarview.DayViewDecorator;
import com.habitap.custom.calendarview.DayViewFacade;

import org.threeten.bp.DayOfWeek;

/**
 * Highlight Saturdays and Sundays with a background
 */
public class HighlightWeekendsDecorator implements DayViewDecorator {

  private final Drawable highlightDrawable;
  private static final int color = Color.parseColor("#228BC34A");

  public HighlightWeekendsDecorator() {
    highlightDrawable = new ColorDrawable(color);
  }

  @Override public boolean shouldDecorate(final CalendarDay day) {
    final DayOfWeek weekDay = day.getDate().getDayOfWeek();
    return weekDay == DayOfWeek.SATURDAY || weekDay == DayOfWeek.SUNDAY;
  }

  @Override public void decorate(final DayViewFacade view) {
    view.setBackgroundDrawable(highlightDrawable);
  }
}
