package com.example.aldy.papado;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.listeners.IPickResult;


import java.util.HashMap;

public class PenyediaEditprofilActivity extends AppCompatActivity {
    private Button save;
    private EditText namaVenue, namaPemilik, alamatPenyedia, telpPenyedia, jamBuka, jamTutup;
    private String nama, pemilik, alamat, telepon, buka, tutup;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penyedia_editprofil);
        namaVenue = findViewById(R.id.penyedia_edit_venue);
        namaPemilik = findViewById(R.id.penyedia_edit_nama);
        alamatPenyedia = findViewById(R.id.penyedia_edit_alamat);
        telpPenyedia = findViewById(R.id.penyedia_edit_notelp);
        jamBuka = findViewById(R.id.penyedia_edit_jambuka);
        jamTutup = findViewById(R.id.penyedia_edit_jamtutup);
        save = findViewById(R.id.penyedia_save_editprofile);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference();

        user = mAuth.getCurrentUser();



        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent intent = new Intent(PenyediaEditprofilActivity.this, PenyediaDaftarLapanganActivity.class);
                nama = namaVenue.getText().toString();
                pemilik = namaPemilik.getText().toString();
                alamat = alamatPenyedia.getText().toString();
                telepon = telpPenyedia.getText().toString();
                buka = jamBuka.getText().toString();
                tutup = jamTutup.getText().toString();
                String uid = user.getUid();

                HashMap<String, Object> editProfile = new HashMap<String, Object>();
                editProfile.put("/penyedia/"+uid+"/namaVenue", nama);
                editProfile.put("/penyedia/"+uid+"/namaPemilik", pemilik);
                editProfile.put("/penyedia/"+uid+"/alamat", alamat);
                editProfile.put("/penyedia/"+uid+"/noTelp",telepon);
                editProfile.put("/penyedia/"+uid+"/jamBukaTutup", (buka+" - "+tutup));

                mRef.updateChildren(editProfile).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(PenyediaEditprofilActivity.this, "Berhasil mengupdate profil", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                        finish();
                    }
                });
            }
        });
    }
}
