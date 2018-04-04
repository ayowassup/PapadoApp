package com.example.aldy.papado;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private Button loginuser;
    private EditText username;
    private EditText pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final TextView lupapassword = findViewById(R.id.login_lupapassword_text);
        lupapassword.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        lupapassword.setTextColor(Color.parseColor("#F3D06A"));
                        break;
                    case MotionEvent.ACTION_UP:
                        lupapassword.setTextColor(getResources().getColor(R.color.colorAccent));
                        break;
                }
                return false;
            }
        });

        lupapassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //kode kalau text lupa password diklik

            }
        });

        username = findViewById(R.id.login_username_text);
        pass = findViewById(R.id.login_password_text);
        Button masuk = findViewById(R.id.login_button_masuk);
        masuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (username.getText().toString().matches("") || pass.getText().toString().matches("")) {
                    //ini kalau username kosong atau pass kosong
                    Toast.makeText(LoginActivity.this, "Masukkan data dengan benar", Toast.LENGTH_LONG).show();
                } else {
                    Intent intent = new Intent(LoginActivity.this, UserPemesananVenueActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        TextView daftar = findViewById(R.id.login_button_daftar);
        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, DaftarActivity.class);
                startActivity(intent);
                //kode kalau tombol daftar diklik

            }
        });

    }


}
