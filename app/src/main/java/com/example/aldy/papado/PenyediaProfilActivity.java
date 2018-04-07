package com.example.aldy.papado;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PenyediaProfilActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private Toolbar mToolbar;
    private TextView header, emailPenyedia, editprofile, alamatPenyedia, namaVenue, namaPemilik,jamBukaTutup,telpPenyedia;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private FirebaseUser mUser;
    private View view;
    private String uid, email, alamat, telepon, jam, pemilik, penyedia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penyedia_profil);

        namaVenue = findViewById(R.id.penyedia_profile_nama);
        namaPemilik = findViewById(R.id.penyedia_profile_ownername);
        telpPenyedia = findViewById(R.id.penyedia_profile_notelp);
        alamatPenyedia = findViewById(R.id.penyedia_profile_alamat);
        jamBukaTutup = findViewById(R.id.penyedia_profile_jam);
        emailPenyedia = findViewById(R.id.penyedia_profile_email);


        //Auth & UID
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        uid = mUser.getUid();

        //Database
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mToolbar = findViewById(R.id.penyedia_nav_action);
        setSupportActionBar(mToolbar);

        mDrawerLayout = findViewById(R.id.penyedia_drawer_layout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);

        mDrawerLayout.setDrawerListener(mToggle);
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView navigationView = findViewById(R.id.penyedia_nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        penyedia_pindahactivity(menuItem);
                        // close drawer when item is tapped
                        mDrawerLayout.closeDrawers();// Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here
                        return true;
                    }
                });

        view = navigationView.getHeaderView(0);
        header = view.findViewById(R.id.penyedia_nav_header_text1);
        mDatabase.child("users").child(uid).child("nama").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String nama = dataSnapshot.getValue().toString();
                header.setText(nama);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        mDatabase.child("penyedia").child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                    telepon = dataSnapshot.child("noTelp").getValue(String.class);
                    alamat = dataSnapshot.child("alamat").getValue(String.class);
                    pemilik = dataSnapshot.child("namaPemilik").getValue(String.class);
                    jam = dataSnapshot.child("jamBukaTutup").getValue(String.class);
                    penyedia = dataSnapshot.child("namaVenue").getValue(String.class);
                    email = dataSnapshot.child("email").getValue(String.class);
                    telpPenyedia.setText(telepon);
                    alamatPenyedia.setText(alamat);
                    jamBukaTutup.setText(jam);
                    namaPemilik.setText(pemilik);
                    namaVenue.setText(penyedia);
                    emailPenyedia.setText(email);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        editprofile = findViewById(R.id.penyedia_profile_edit);
        editprofile.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        editprofile.setTextColor(getResources().getColor(R.color.buttondown));
                        break;
                    case MotionEvent.ACTION_UP:
                        editprofile.setTextColor(getResources().getColor(android.R.color.primary_text_light));
                        Intent intent = new Intent (PenyediaProfilActivity.this, PenyediaEditprofilActivity.class);
                        startActivity(intent);
                        break;
                }
                return true;
            }
        });
    }

    public void penyedia_pindahactivity(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.penyedia_nav_profil:
                Intent halamansaya = new Intent(PenyediaProfilActivity.this, PenyediaProfilActivity.class);
                startActivity(halamansaya);
                finish();
                break;
            case R.id.penyedia_nav_pengaturan:
                Intent pengaturan = new Intent(PenyediaProfilActivity.this, PenyediaPengaturanActivity.class);
                startActivity(pengaturan);
                finish();
                break;
            case R.id.penyedia_nav_daftarlapangan:
                Intent jenislapangan = new Intent(PenyediaProfilActivity.this, PenyediaDaftarLapanganActivity.class);
                startActivity(jenislapangan);
                finish();
                break;
            case R.id.penyedia_nav_jadwal:
                Intent jadwal= new Intent(PenyediaProfilActivity.this, PenyediaJadwalActivity.class);
                startActivity(jadwal);
                finish();
                break;

        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        Intent jenislapangan = new Intent(PenyediaProfilActivity.this, PenyediaDaftarLapanganActivity.class);
        startActivity(jenislapangan);
        finish();
//        super.onBackPressed();
    }
}
