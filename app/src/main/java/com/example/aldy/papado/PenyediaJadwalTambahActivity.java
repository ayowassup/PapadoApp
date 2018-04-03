package com.example.aldy.papado;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PenyediaJadwalTambahActivity extends AppCompatActivity {
    private Button save;
    EditText jam1;
    EditText jam2;
    Calendar c = Calendar.getInstance();
    SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");

    TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int i, int i1) {
            c.set(Calendar.HOUR, i);
            c.set(Calendar.MINUTE, i1);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penyedia_jadwal_tambah);
        save = findViewById(R.id.penyedia_jadwal_tambah_save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //kalau save diklik
                Intent intent = new Intent(PenyediaJadwalTambahActivity.this, PenyediaJadwalActivity.class);
                startActivity(intent);
                finish();
            }
        });

        jam1 = findViewById(R.id.penyedia_jadwal_tambah_jam1);
        jam2 = findViewById(R.id.penyedia_jadwal_tambah_jam2);

        jam1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jam1.setText(dateFormat.format(c.getTime()));
                updateTime();
            }
        });
        jam2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jam2.setText(dateFormat.format(c.getTime()));
                updateTime();
            }
        });

    }

    public void updateTime() {
        new TimePickerDialog(this, t, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), true).show();
    }
}
