package com.example.aldy.papado;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class UserTanggalActivity extends AppCompatActivity {
    private TextView waktu;
    private Button bdate, btime;
    Calendar c = Calendar.getInstance();
    DateFormat dateFormat = DateFormat.getDateTimeInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_tanggal);

        waktu = findViewById(R.id.waktu);
        bdate = findViewById(R.id.datepicker);
        btime = findViewById(R.id.timepicker);

        bdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateDate();
            }
        });

        btime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateTime();
            }
        });
        updateTextLabel();
    }

    public void updateDate(){
        new DatePickerDialog(this, d, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();
    }

    public void updateTime(){
        new TimePickerDialog(this, t, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), true).show();
    }

    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            c.set(Calendar.YEAR,i);
            c.set(Calendar.MONTH,i1);
            c.set(Calendar.DAY_OF_MONTH,i2);
            updateTextLabel();
        }
    };

    TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int i, int i1) {
            c.set(Calendar.HOUR, i);
            c.set(Calendar.MINUTE, i1);
            updateTextLabel();
        }
    };

    public void updateTextLabel(){
        //dia ngambil waktu sama tanggal lewat sini
        waktu.setText(dateFormat.format(c.getTime()));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
