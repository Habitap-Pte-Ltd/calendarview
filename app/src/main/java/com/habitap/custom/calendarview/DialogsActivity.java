package com.habitap.custom.calendarview;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;

import org.threeten.bp.format.DateTimeFormatter;

/**
 * Shows off the most basic usage
 */
public class DialogsActivity extends AppCompatActivity {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("EEE, d MMM yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialogs);

        findViewById(R.id.button_normal_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onNormalDialogClick();
            }
        });

        findViewById(R.id.button_simple_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSimpleCalendarDialogClick();
            }
        });

    }

    void onNormalDialogClick() {
        new SimpleDialogFragment().show(getSupportFragmentManager(), "test-normal");
    }

    void onSimpleCalendarDialogClick() {
        new SimpleCalendarDialogFragment().show(getSupportFragmentManager(), "test-simple-calendar");
    }

    public static class SimpleDialogFragment extends AppCompatDialogFragment {

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            return new AlertDialog.Builder(getActivity())
                    .setTitle(R.string.title_activity_dialogs)
                    .setMessage("Test Dialog")
                    .setPositiveButton(android.R.string.ok, null)
                    .create();
        }
    }

    public static class SimpleCalendarDialogFragment extends AppCompatDialogFragment
            implements OnDateSelectedListener {

        private TextView textView;

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            LayoutInflater inflater = getActivity().getLayoutInflater();

            //inflate custom layout and get views
            //pass null as parent view because will be in dialog layout
            View view = inflater.inflate(R.layout.dialog_basic, null);

            textView = view.findViewById(R.id.textView);

            MaterialCalendarView widget = view.findViewById(R.id.calendarView);

            widget.setOnDateChangedListener(this);

            return new AlertDialog.Builder(getActivity())
                    .setTitle(R.string.title_activity_dialogs)
                    .setView(view)
                    .setPositiveButton(android.R.string.ok, null)
                    .create();
        }

        @Override
        public void onDateSelected(
                @NonNull MaterialCalendarView widget,
                @NonNull CalendarDay date,
                boolean selected) {
            textView.setText(FORMATTER.format(date.getDate()));
        }
    }
}
