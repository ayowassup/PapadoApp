package com.example.aldy.papado;

import android.app.DatePickerDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class UserTanggalActivity extends AppCompatActivity {
    private EditText tanggal;
    private int hari, bulan, tahun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_tanggal);

        tanggal = findViewById(R.id.user_tanggal_picker);
        tanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                hari = c.get(Calendar.DAY_OF_MONTH);
                bulan = c.get(Calendar.MONTH);
                tahun = c.get(Calendar.YEAR);
                Context a;
                DatePickerDialog datePickerDialog = new DatePickerDialog(a, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        tanggal.setText(i+"/"+(i1+1)+"/"+i2);
                    }
                }, hari, bulan, tahun);
                datePickerDialog.show();
//
            }
        });
    }

        @Override
        public void onBackPressed () {
            super.onBackPressed();
            finish();
        }
    }
