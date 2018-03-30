package com.example.aldy.papado;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class PenyediaJadwalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penyedia_jadwal);
    }
    @Override
    public void onBackPressed() {
        Intent jenislapangan = new Intent(PenyediaJadwalActivity.this, PenyediaMainActivity.class);
        startActivity(jenislapangan);
        finish();
//        super.onBackPressed();
    }
}
