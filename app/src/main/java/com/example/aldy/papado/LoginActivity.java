package com.example.aldy.papado;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

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
                        lupapassword.setTextColor(Color.parseColor("#5A999A"));
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
        Button masuk = findViewById(R.id.login_button_masuk);
        masuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //kode kalau tombol masuk diklik
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
