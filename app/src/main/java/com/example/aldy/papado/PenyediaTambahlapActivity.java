package com.example.aldy.papado;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PenyediaTambahlapActivity extends AppCompatActivity {
    private Button simpan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penyedia_tambahlap);

        simpan = findViewById(R.id.penyedia_button_simpanlap);
        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //kalau simpan dipencet
                Intent intent = new Intent(PenyediaTambahlapActivity.this, PenyediaDaftarLapanganActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

}
