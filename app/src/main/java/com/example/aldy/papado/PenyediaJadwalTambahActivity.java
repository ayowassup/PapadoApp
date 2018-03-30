package com.example.aldy.papado;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PenyediaJadwalTambahActivity extends AppCompatActivity {
    private Button save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penyedia_jadwal_tambah);
        save = findViewById(R.id.penyedia_jadwal_tambah_save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //kalau save diklik
                Intent intent = new Intent(PenyediaJadwalTambahActivity.this,PenyediaJadwalActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
