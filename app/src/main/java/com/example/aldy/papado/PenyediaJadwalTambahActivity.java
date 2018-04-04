package com.example.aldy.papado;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Map;
import java.util.HashMap;

//Firebase
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PenyediaJadwalTambahActivity extends AppCompatActivity {
    private Button save;
    private EditText inputJamAwal, inputJamAkhir;
    private String jamAwal, jamAkhir;
    Intent penyediatambah;
    private DatabaseReference mDatabase;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penyedia_jadwal_tambah);

        //auth instance
        auth = FirebaseAuth.getInstance();

        //database
        mDatabase = FirebaseDatabase.getInstance().getReference();

        save = findViewById(R.id.penyedia_jadwal_tambah_save);
        inputJamAwal = findViewById(R.id.penyedia_jadwal_tambah_jam1);
        inputJamAkhir = findViewById(R.id.penyedia_jadwal_tambah_jam2);
        penyediatambah = new Intent(PenyediaJadwalTambahActivity.this,PenyediaJadwalActivity.class);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Variabel
                jamAwal = inputJamAwal.getText().toString();
                jamAkhir = inputJamAkhir.getText().toString();

                //Auth
                auth = FirebaseAuth.getInstance();
                FirebaseUser user = auth.getCurrentUser();
                String uid = user.getUid();
                String key = mDatabase.push().getKey();

                Map <String, Object> tambahJadwal = new HashMap<String, Object>();
                tambahJadwal.put("/jadwal/" + uid + "/jamAwal", jamAwal);
                tambahJadwal.put("/jadwal/" + uid + "/jamAkhir", jamAkhir);

                mDatabase.updateChildren(tambahJadwal).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(PenyediaJadwalTambahActivity.this, "Berhasil menambahkan jadwal.", Toast.LENGTH_SHORT).show();
                        startActivity(penyediatambah);
                        finish();
                    }
                });
            }
        });
    }
}
