package com.example.aldy.papado;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;

public class DaftarActivity extends AppCompatActivity {

    static final String jenis_user[] = {"Penyewa", "P. Badminton", "P. Futsal", "P. Renang"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);

        Spinner spinner = findViewById(R.id.spinner_jenisuser);
        ArrayAdapter<String> adapter = new ArrayAdapter(DaftarActivity.this, android.R.layout.simple_spinner_item, jenis_user);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // Kalau ada yang dipilih. Bisa dipanggil pake
                // adapterView.getItemAtPosition(i)
                LinearLayout linearLayout = findViewById(R.id.daftar_namatempat_text_hidden);
                if (i!=0){
                    linearLayout.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //another interface callback
            }
        });
    }
}
