package com.example.aldy.papado;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import android.text.TextUtils;
import android.widget.EditText;
import java.util.Map;
import java.util.HashMap;

//Tentang register
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class DaftarActivity extends AppCompatActivity {

    static final String jenis_user[] = {"Penyewa", "P. Badminton", "P. Futsal", "P. Renang"};

    private EditText inputUsername, inputPassword, rePassword, inputEmail, inputAddress, inputPhone, namaTempat;
    private FirebaseAuth auth;
    private DatabaseReference mDatabase;
    private Button daftar;
    public String email, username, pass, address, phone, tempat, kategori;
    Spinner spinner;
    CheckBox checkBox;
    Boolean user = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);

        //Otentikasi
        auth = FirebaseAuth.getInstance();

        //Instance db
        mDatabase = FirebaseDatabase.getInstance().getReference();

        //Variabel
        daftar = findViewById(R.id.daftar_button_daftar);
        inputUsername = findViewById(R.id.daftar_username_text);
        inputEmail = findViewById(R.id.daftar_email_text);
        inputPassword = findViewById(R.id.daftar_password_text);
        inputAddress = findViewById(R.id.daftar_alamat);
        inputPhone = findViewById(R.id.daftar_telepon_text);
        namaTempat = findViewById(R.id.daftar_namatempat_text);
        spinner = findViewById(R.id.spinner_jenisuser);
        rePassword = findViewById(R.id.daftar_repassword_text);
        checkBox = findViewById(R.id.daftar_checkbox);

        ArrayAdapter<String> adapter = new ArrayAdapter(DaftarActivity.this, R.layout.spinner_item, jenis_user);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // Kalau ada yang dipilih. Bisa dipanggil pake
                //adapterView.getItemAtPosition(i)
                LinearLayout a = findViewById(R.id.daftar_namatempat_text_hidden);
                if (adapterView.getItemAtPosition(i) != "Penyewa") {
                    a.setVisibility(View.VISIBLE);
                    user = false;
                } else {
                    a.setVisibility(View.GONE);
                    user = true;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //another interface callback
            }
        });

        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent intent = new Intent(DaftarActivity.this, PenyediaMainActivity.class);

                email = inputEmail.getText().toString();
                username = inputUsername.getText().toString();
                pass = inputPassword.getText().toString();
                phone = inputPhone.getText().toString();
                address = inputAddress.getText().toString();
                tempat = namaTempat.getText().toString();
                kategori = spinner.getSelectedItem().toString();

                if (inputPassword.getText().toString().matches(rePassword.getText().toString())) {
                    if (inputUsername.getText().toString().matches("")
                            || inputEmail.getText().toString().matches("")
                            || inputPhone.getText().toString().matches("")
                            || inputAddress.getText().toString().matches("")
                            || inputPassword.getText().toString().matches("")
                            || rePassword.getText().toString().matches("")
                            || !checkBox.isChecked()) {
                        Toast.makeText(DaftarActivity.this, "Data tidak lengkap", Toast.LENGTH_LONG).show();
                    } else {
                        if (user) {
                            auth.createUserWithEmailAndPassword(email, pass)
                                    .addOnCompleteListener(DaftarActivity.this, new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            if (!task.isSuccessful()) {
                                                Toast.makeText(DaftarActivity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                                                return;
                                            }
                                            auth = FirebaseAuth.getInstance();
                                            FirebaseUser user = auth.getCurrentUser();
                                            String uid = user.getUid();
                                            String key = mDatabase.push().getKey();
                                            //Toast.makeText(DaftarActivity.this, "UID"+uid, Toast.LENGTH_SHORT).show();
                                            Map<String, Object> akun = new HashMap<String, Object>();
                                            akun.put("/users/" + uid + "/username", username);
                                            akun.put("/users/" + uid + "/password", pass);
                                            akun.put("/users/" + uid + "/address", address);
                                            akun.put("/users/" + uid + "/kategori", kategori);
                                            akun.put("/users/" + uid + "/telepon", phone);
//
//                                            if (!kategori.equalsIgnoreCase("Penyewa")) {
//                                                akun.put("/penyedia/" + key + "/userId", uid);
//                                                akun.put("/penyedia/" + key + "/kategori", kategori);
//                                                akun.put("/lapangan/" + uid + "/namaTempat", tempat);
//                                            }

                                            mDatabase.updateChildren(akun).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    Toast.makeText(DaftarActivity.this, "Berhasil mendaftar akun", Toast.LENGTH_SHORT).show();
                                                    startActivity(intent);
                                                    finish();
                                                }
                                            });
                                        }
                                    });
                        } else {
                            if (namaTempat.getText().toString().matches("")) {
                                Toast.makeText(DaftarActivity.this, "Data tidak lengkap", Toast.LENGTH_LONG).show();
                            } else {
                                auth.createUserWithEmailAndPassword(email, pass)
                                        .addOnCompleteListener(DaftarActivity.this, new OnCompleteListener<AuthResult>() {
                                            @Override
                                            public void onComplete(@NonNull Task<AuthResult> task) {
                                                if (!task.isSuccessful()) {
                                                    Toast.makeText(DaftarActivity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                                                    return;
                                                }
                                                auth = FirebaseAuth.getInstance();
                                                FirebaseUser user = auth.getCurrentUser();
                                                String uid = user.getUid();
                                                String key = mDatabase.push().getKey();
                                                //Toast.makeText(DaftarActivity.this, "UID"+uid, Toast.LENGTH_SHORT).show();
                                                Map<String, Object> akun = new HashMap<String, Object>();
                                                akun.put("/users/" + uid + "/username", username);
                                                akun.put("/users/" + uid + "/password", pass);
                                                akun.put("/users/" + uid + "/address", address);
                                                akun.put("/users/" + uid + "/kategori", kategori);
                                                akun.put("/users/" + uid + "/telepon", phone);
                                                akun.put("/penyedia/" + uid + "/alamat", address);
                                                akun.put("/penyedia/" + uid+ "/kategori", kategori);
                                                akun.put("/penyedia/"+ uid +"/noTelp", phone);
                                                akun.put("/lapangan/" + uid + "/namaTempat", tempat);

                                                mDatabase.updateChildren(akun).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        Toast.makeText(DaftarActivity.this, "Berhasil mendaftar akun", Toast.LENGTH_SHORT).show();
                                                        startActivity(intent);
                                                        finish();
                                                    }
                                                });
                                            }
                                        });
                            }
                        }
                    }
                } else {
                    Toast.makeText(DaftarActivity.this, "Password tidak cocok", Toast.LENGTH_LONG).show();
                }
                //kode kalau tombol daftar diklik
            }
        });
    }
}