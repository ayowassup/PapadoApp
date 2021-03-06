package com.example.aldy.papado;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.HashMap;
import java.util.Map;

//Firebase & Database
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PenyediaTambahlapActivity extends AppCompatActivity {
    private Button simpan;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private DatabaseReference mDatabase;
    private EditText inputNama, inputUkuran, inputHarga;
    private String namaLap, ukuran, harga;
    Intent mainLap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_penyedia_tambahlap);

        //FirebaseAuth
        mAuth = FirebaseAuth.getInstance();

        //Database
        mDatabase = FirebaseDatabase.getInstance().getReference();

        inputNama = findViewById(R.id.penyedia_lapbaru_nama);
        inputUkuran = findViewById(R.id.penyedia_lapbaru_ukuran);
        inputHarga = findViewById(R.id.penyedia_lapbaru_harga);
        simpan = findViewById(R.id.penyedia_button_simpanlap);

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                namaLap = inputNama.getText().toString();
                ukuran = inputUkuran.getText().toString();
                harga = inputHarga.getText().toString();
                mainLap = new Intent(PenyediaTambahlapActivity.this,PenyediaDaftarLapanganActivity.class);

                mUser = mAuth.getCurrentUser();
                String uid = mUser.getUid();
                String key = mDatabase.push().getKey();

                Map <String, Object> tambahLap = new HashMap<String, Object>();
                tambahLap.put("/lapangan/" + uid +"/"+key+ "/namaLapangan", namaLap);
                tambahLap.put("/lapangan/" + uid +"/"+key+ "/ukuranLapangan", ukuran);
                tambahLap.put("/lapangan/" +uid +"/"+key+ "/hargaLapangan", harga);

                mDatabase.updateChildren(tambahLap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(PenyediaTambahlapActivity.this, "Berhasil menambah lapangan baru", Toast.LENGTH_SHORT).show();
                        startActivity(mainLap);
                        finish();
                    }
                });
            }
        });
    }

}
