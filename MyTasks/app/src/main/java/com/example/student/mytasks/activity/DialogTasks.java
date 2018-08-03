package com.example.student.mytasks.activity;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.student.mytasks.R;
import com.example.student.mytasks.models.ModelTasks;
import com.example.student.mytasks.provaider.UserProvaider;

import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;


@SuppressLint("ValidFragment")
public class DialogTasks extends DialogFragment {


    private TextView textTime;
    private TextView textDate;
    private Calendar calendar = Calendar.getInstance();
    private int[] times = new int[2];
    private int dates[] = new int[3];
    private CircleImageView circleImageView;
    private int positionImage;
    private int images[] = {R.drawable.dialog1, R.drawable.dialog2, R.drawable.dialog3,};


    private final Context context;

    public DialogTasks(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Black_NoTitleBar_Fullscreen);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = LayoutInflater.from(context).inflate(R.layout.dialogtasks, container, false);
        findViews(view);
        changeImages(view);
        showDatePicker(view);
        showTimePicker(view);
        addTask(view);
        return view;
    }


    private void findViews(final View view) {
        textTime = view.findViewById(R.id.add_time);
        textDate = view.findViewById(R.id.add_date);
        circleImageView = view.findViewById(R.id.add_tasks_image);
    }

    private void addTask(final View view) {
        final ImageButton addButton = view.findViewById(R.id.tasks_add);
        final EditText editText = view.findViewById(R.id.input_mision);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserProvaider.getListTasks().add(new ModelTasks(images[positionImage], editText.getText().toString(), times, dates));
                dismiss();
            }
        });

    }


    private void changeImages(final View view) {
        Spinner spinner = view.findViewById(R.id.spinner_item);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        circleImageView.setImageResource(images[0]);
                        positionImage = 0;
                        break;
                    case 1:
                        circleImageView.setImageResource(images[1]);
                        positionImage = 1;
                        break;
                    case 2:
                        circleImageView.setImageResource(images[2]);
                        positionImage = 2;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void showDatePicker(final View view) {
        ImageButton dateButton = view.findViewById(R.id.date_piker);
        dateButton.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                final DatePickerDialog datePickerDialog =
                        new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                                textDate.setText(String.valueOf(i) + "." + String.valueOf(i1) + "." + String.valueOf(i2));
                                dates[0] = i;
                                dates[1] = i1;
                                dates[2] = i2;

                            }
                        }, calendar.get(Calendar.DAY_OF_MONTH)
                                , calendar.get(Calendar.MONTH)
                                , calendar.get(Calendar.YEAR));

                calendar.set(Calendar.DAY_OF_MONTH, Calendar.MONTH, Calendar.YEAR);

                datePickerDialog.show();
            }
        });

    }

    private void showTimePicker(final View view) {
        ImageButton timeButton = view.findViewById(R.id.time_piker);
        timeButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                final TimePickerDialog pikerDialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        textTime.setText(String.valueOf(i) + " : " + String.valueOf(i1));
                        times[0] = i;
                        times[1] = i1;
                    }
                },
                        calendar.get(Calendar.HOUR_OF_DAY),
                        calendar.get(Calendar.MINUTE), true);
                calendar.set(calendar.HOUR_OF_DAY, calendar.MINUTE);
                pikerDialog.show();
            }
        });

    }


}
