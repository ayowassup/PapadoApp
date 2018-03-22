package com.example.aldy.papado;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView lupapassword = findViewById(R.id.login_lupapassword_text);
        lupapassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //kode kalau text lupa password diklik
            }
        });
        Button masuk = findViewById(R.id.login_button_masuk);
        masuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //kode kalau tombol masuk diklik
            }
        });
        Button daftar = findViewById(R.id.login_button_daftar);
        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //kode kalau tombol daftar diklik
            }
        });
    }


}
