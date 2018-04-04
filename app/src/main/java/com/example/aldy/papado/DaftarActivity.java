package com.example.aldy.papado;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

public class DaftarActivity extends AppCompatActivity {

    static final String jenis_user[] = {"Penyewa", "P. Badminton", "P. Futsal"};
    EditText username, email, telepon, alamat, pass, repass;
    EditText namatempat;
    CheckBox checkBox;
    Boolean user = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);

        Spinner spinner = findViewById(R.id.spinner_jenisuser);
        ArrayAdapter<String> adapter = new ArrayAdapter(DaftarActivity.this, R.layout.spinner_item, jenis_user);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // Kalau ada yang dipilih. Bisa dipanggil pake
                //adapterView.getItemAtPosition(i)
                LinearLayout a = findViewById(R.id.daftar_namatempat_text_hidden);
                LinearLayout b = findViewById(R.id.daftar_alamat_text_hidden);
                if (adapterView.getItemAtPosition(i) != "Penyewa") {
                    a.setVisibility(View.VISIBLE);
                    b.setVisibility(View.VISIBLE);

                    user = false;
                } else {
                    a.setVisibility(View.GONE);
                    b.setVisibility(View.GONE);

                    user = true;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //another interface callback
            }
        });

        Button daftar = findViewById(R.id.daftar_button_daftar);
        username = findViewById(R.id.daftar_username_text);
        email = findViewById(R.id.daftar_email_text);
        telepon = findViewById(R.id.daftar_telepon_text);
        alamat = findViewById(R.id.daftar_alamat);
        pass = findViewById(R.id.daftar_password_text);
        repass = findViewById(R.id.daftar_repassword_text);
        checkBox = findViewById(R.id.daftar_checkbox);
        //kalau bukan penyewa
        namatempat = findViewById(R.id.daftar_namatempat_text);
        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (pass.getText().toString().matches(repass.getText().toString())) {
                    if (username.getText().toString().matches("")
                            || email.getText().toString().matches("")
                            || telepon.getText().toString().matches("")
                            || pass.getText().toString().matches("")
                            || repass.getText().toString().matches("")
                            || !checkBox.isChecked()) {
                        Toast.makeText(DaftarActivity.this, "Data tidak lengkap", Toast.LENGTH_LONG).show();
                    } else {
                        if (user){
                            Intent intent = new Intent(DaftarActivity.this, PenyediaDaftarLapanganActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        else {
                            if (namatempat.getText().toString().matches("")
                                    || alamat.getText().toString().matches("")){
                                Toast.makeText(DaftarActivity.this, "Data tidak lengkap", Toast.LENGTH_LONG).show();
                            }
                            else {
                                Intent intent = new Intent(DaftarActivity.this, PenyediaDaftarLapanganActivity.class);
                                startActivity(intent);
                                finish();
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
