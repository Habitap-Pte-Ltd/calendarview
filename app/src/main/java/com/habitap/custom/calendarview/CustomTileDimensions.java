package com.habitap.custom.calendarview;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.NumberPicker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by maragues on 17/06/16.
 */
public class CustomTileDimensions extends AppCompatActivity {
    MaterialCalendarView widget;

    private int currentTileWidth;
    private int currentTileHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_tile);

        widget = findViewById(R.id.calendarView);

        currentTileWidth = MaterialCalendarView.DEFAULT_TILE_SIZE_DP;
        currentTileHeight = MaterialCalendarView.DEFAULT_TILE_SIZE_DP;

        widget.addDecorator(new TodayDecorator());

        findViewById(R.id.custom_tile_match_parent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onMatchParentClick();
            }
        });

        findViewById(R.id.custom_tile_width_match_parent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onWidthMatchParentClick();
            }
        });

        findViewById(R.id.custom_tile_height_match_parent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onHeightMatchParentClick();
            }
        });

        findViewById(R.id.custom_tile_width_size).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onWidthClick();
            }
        });

        findViewById(R.id.custom_tile_height_size).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onHeightClick();
            }
        });
    }

    public void onMatchParentClick() {
        widget.setTileSize(LinearLayout.LayoutParams.MATCH_PARENT);
    }

    public void onWidthMatchParentClick() {
        widget.setTileWidth(LinearLayout.LayoutParams.MATCH_PARENT);
    }

    public void onHeightMatchParentClick() {
        widget.setTileHeight(LinearLayout.LayoutParams.MATCH_PARENT);
    }

    public void onWidthClick() {
        final NumberPicker view = new NumberPicker(this);
        view.setMinValue(24);
        view.setMaxValue(64);
        view.setWrapSelectorWheel(false);
        view.setValue(currentTileWidth);
        new AlertDialog.Builder(this)
                .setView(view)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(@NonNull DialogInterface dialog, int which) {
                        currentTileWidth = view.getValue();
                        widget.setTileWidthDp(currentTileWidth);
                    }
                })
                .show();
    }

    public void onHeightClick() {
        final NumberPicker view = new NumberPicker(this);
        view.setMinValue(24);
        view.setMaxValue(64);
        view.setWrapSelectorWheel(false);
        view.setValue(currentTileHeight);
        new AlertDialog.Builder(this)
                .setView(view)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(@NonNull DialogInterface dialog, int which) {
                        currentTileHeight = view.getValue();
                        widget.setTileHeightDp(currentTileHeight);
                    }
                })
                .show();
    }

    private class TodayDecorator implements DayViewDecorator {

        private final CalendarDay today;
        private final Drawable backgroundDrawable;

        public TodayDecorator() {
            today = CalendarDay.today();
            backgroundDrawable = getResources().getDrawable(R.drawable.today_circle_background);
        }

        @Override
        public boolean shouldDecorate(CalendarDay day) {
            return today.equals(day);
        }

        @Override
        public void decorate(DayViewFacade view) {
            view.setBackgroundDrawable(backgroundDrawable);
        }
    }
}
