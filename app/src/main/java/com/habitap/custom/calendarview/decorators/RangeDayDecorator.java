package com.habitap.custom.calendarview.decorators;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.habitap.custom.calendarview.CalendarDay;
import com.habitap.custom.calendarview.DayViewDecorator;
import com.habitap.custom.calendarview.DayViewFacade;
import com.habitap.custom.calendarview.MaterialCalendarView;
import com.habitap.custom.calendarview.R;

import java.util.HashSet;

/**
 * Decorate 2 days.
 */
public class RangeDayDecorator implements DayViewDecorator {

  private final HashSet<CalendarDay> list = new HashSet<>();
  private final Drawable drawable;

  public RangeDayDecorator(final Context context) {
    drawable = context.getResources().getDrawable(R.drawable.my_selector);
  }

  @Override
  public boolean shouldDecorate(CalendarDay day) {
    return list.contains(day);
  }

  @Override
  public void decorate(DayViewFacade view) {
    view.setSelectionDrawable(drawable);
  }

  /**
   * We're changing the dates, so make sure to call {@linkplain MaterialCalendarView#invalidateDecorators()}
   */
  public void addFirstAndLast(final CalendarDay first, final CalendarDay last) {
    list.clear();
    list.add(first);
    list.add(last);
  }
}
