package com.example.aldy.papado;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PenyediaEditprofilActivity extends AppCompatActivity {
    private Button save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penyedia_editprofil);
        save = findViewById(R.id.penyedia_save_edit);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(PenyediaEditprofilActivity.this, HalamanSayaActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
