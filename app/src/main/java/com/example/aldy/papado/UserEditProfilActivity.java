package com.example.aldy.papado;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.UploadTask;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.listeners.IPickResult;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.HashMap;

public class UserEditProfilActivity extends AppCompatActivity implements IPickResult{
    private Button save, editfoto;
    private TextView nama,email,notelp;
    String namaUser, emailUser, noTelpUser;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private Intent userProfil;
    private String uid;
    Bitmap mGallery;
    Uri mUri, dlUri;
    String mPath, idString, name, caption, mKeys;
    Button mUpload;
    int id;

    private static final String ALLOWED_CHARACTERS = "0123456789qwertyuiopasdfghjklzxcvbnmABCDEFGHIJKLMNOPQRSTUVWXYZ";

    @Override
    public void onPickResult(PickResult r) {
        if (r.getError() == null) {
            mGallery = r.getBitmap();
            mUri = r.getUri();
            mPath = r.getPath();
            Toast.makeText(this, r.getUri() + "  " + r.getPath(), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, r.getError().getMessage(), Toast.LENGTH_LONG).show();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_edit_profil);

        nama = findViewById(R.id.user_edit_nama);
        email = findViewById(R.id.user_edit_alamat);
        notelp = findViewById(R.id.user_edit_notelp);

        //Auth & User
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        //Database
        mDatabase = FirebaseDatabase.getInstance().getReference();

        save = findViewById(R.id.user_save_editprofile);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userProfil = new Intent(UserEditProfilActivity.this, UserProfilActivity.class);
                namaUser = nama.getText().toString();
                emailUser = email.getText().toString();
                noTelpUser = notelp.getText().toString();
                uid = mUser.getUid();

                HashMap<String, Object> profilUser = new HashMap<String, Object>();
                profilUser.put("/users/"+uid+"/nama", namaUser);
                profilUser.put("/users/"+uid+"/email", emailUser);
                profilUser.put("/users/"+uid+"/telepon", noTelpUser);

                mDatabase.updateChildren(profilUser).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(UserEditProfilActivity.this, "Berhasil mengedit profil", Toast.LENGTH_SHORT).show();
                        startActivity(userProfil);
                        finish();
                    }
                });
            }
        });

        editfoto = findViewById(R.id.user_edit_foto);
        editfoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //kalau edit foto diklik
                /////
                PickImageDialog.build(new PickSetup()).show(UserEditProfilActivity.this);

//                mKeys = getRandomString(8);
//                new Timer().schedule(new TimerTask() {
//                    @Override
//                    public void run() {
//                        uploadGallery(mGallery, mKeys);
//                    }
//                }, 1000);

//                getId();
                /////
            }
        });
    }
}
