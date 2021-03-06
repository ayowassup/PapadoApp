package com.example.aldy.papado;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

//Berkaitan dengan login
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class LoginActivity extends AppCompatActivity {
    private EditText inputUsername, inputPassword;
    private Button masuk;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener authListener;
    private DatabaseReference mDatabase;
    private EditText username, pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Instance mAuth
        mAuth = FirebaseAuth.getInstance();



        inputUsername = findViewById(R.id.login_username_text);
        inputPassword = findViewById(R.id.login_password_text);
        masuk = findViewById(R.id.login_button_masuk);
        mDatabase = FirebaseDatabase.getInstance().getReference();


        masuk = findViewById(R.id.login_button_masuk);
        masuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = inputUsername.getText().toString();
                final String password = inputPassword.getText().toString();
                        if (email.matches("") || password.matches("")) {
                            //ini kalau name kosong atau pass kosong
                            Toast.makeText(LoginActivity.this, "Masukkan data dengan benar", Toast.LENGTH_LONG).show();
                        } else {
                            //Otentikasi
                            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(
                                    LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            // Status otentikasi, apabila berhasil dan gagal
                                            if (!task.isSuccessful()) {
                                                if (password.length() < 6) {
                                                    inputPassword.setError("Password kurang dari 6 karakter");
                                                } else {
                                                    Toast.makeText(LoginActivity.this, "Login gagal!", Toast.LENGTH_LONG).show();
                                                }
                                            } else {
                                                String uid = mAuth.getCurrentUser().getUid();
                                                mDatabase.child("users").child(uid).child("kategori").addListenerForSingleValueEvent(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                                        String jenis = dataSnapshot.getValue().toString();
                                                        if (jenis.equalsIgnoreCase("Penyewa")) {
                                                            Intent penyewa = new Intent(LoginActivity.this, UserNotifActivity.class);
                                                            penyewa.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                                            startActivity(penyewa);
                                                        } else {
                                                            Intent penyedia = new Intent(LoginActivity.this, PenyediaDaftarLapanganActivity.class);
                                                            penyedia.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                                            startActivity(penyedia);
                                                        }
                                                    }
                                                    @Override
                                                    public void onCancelled(DatabaseError databaseError) {

                                                    }
                                                });
                                            }
                                        }
                                    });
                        }

            }
        });
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