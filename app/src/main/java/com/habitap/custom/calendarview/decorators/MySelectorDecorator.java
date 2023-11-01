package com.habitap.custom.calendarview.decorators;

import android.graphics.drawable.Drawable;

import androidx.appcompat.app.AppCompatActivity;

import com.habitap.custom.calendarview.CalendarDay;
import com.habitap.custom.calendarview.DayViewDecorator;
import com.habitap.custom.calendarview.DayViewFacade;
import com.habitap.custom.calendarview.R;

/**
 * Use a custom selector
 */
public class MySelectorDecorator implements DayViewDecorator {

  private final Drawable drawable;

  public MySelectorDecorator(AppCompatActivity context) {
    drawable = context.getResources().getDrawable(R.drawable.my_selector);
  }

  @Override
  public boolean shouldDecorate(CalendarDay day) {
    return true;
  }

  @Override
  public void decorate(DayViewFacade view) {
    view.setSelectionDrawable(drawable);
  }
}
