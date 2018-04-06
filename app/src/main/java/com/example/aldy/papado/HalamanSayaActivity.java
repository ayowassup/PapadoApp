package com.example.aldy.papado;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HalamanSayaActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private Toolbar mToolbar;
    private LinearLayout tambah_lap;
    private TextView header, editprofile, alamatPenyedia, namaVenue, namaPemilik,jamBukaTutup,telpPenyedia;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private FirebaseUser user;
    private View view;
    private String alamat, telepon, jam, pemilik, penyedia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_saya);

        namaVenue = findViewById(R.id.penyedia_profile_nama);
        namaPemilik = findViewById(R.id.penyedia_profile_ownername);
        telpPenyedia = findViewById(R.id.penyedia_profile_notelp);
        alamatPenyedia = findViewById(R.id.penyedia_profile_alamat);
        jamBukaTutup = findViewById(R.id.penyedia_profile_jam);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference("penyedia");

        user = mAuth.getCurrentUser();
        String uid = user.getUid();
//        Toast.makeText(this, "uid"+uid, Toast.LENGTH_SHORT).show();

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
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    PenyediaProfil profil = (PenyediaProfil) postSnapshot.getValue(PenyediaProfil.class);
                    telepon = profil.getNoTelp();
                    alamat = profil.getAlamat();
                    jam = profil.getJamBukaTutup();
                    pemilik = profil.getNamaPemilik();
                    penyedia = profil.getNamaVenue();
                    telpPenyedia.setText(telepon);
                    alamatPenyedia.setText(alamat);
                    jamBukaTutup.setText(jam);
                    namaPemilik.setText(pemilik);
                    namaVenue.setText(penyedia);
                    //Toast.makeText(HalamanSayaActivity.this, telepon+" "+alamat, Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




        editprofile = findViewById(R.id.penyedia_profile_edit);
        editprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (HalamanSayaActivity.this, PenyediaEditprofilActivity.class);
                startActivity(intent);

            }
        });
    }

    public void penyedia_pindahactivity(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.penyedia_nav_halamansaya:
                Intent halamansaya = new Intent(HalamanSayaActivity.this, HalamanSayaActivity.class);
                startActivity(halamansaya);
                finish();
                break;
            case R.id.penyedia_nav_pemesanan:
                Intent pemesanan = new Intent(HalamanSayaActivity.this, PemesananActivity.class);
                startActivity(pemesanan);
                finish();
                break;
            case R.id.penyedia_nav_pengaturan:
                Intent pengaturan = new Intent(HalamanSayaActivity.this, PengaturanActivity.class);
                startActivity(pengaturan);
                finish();
                break;
            case R.id.penyedia_nav_jenislapangan:
                Intent jenislapangan = new Intent(HalamanSayaActivity.this, PenyediaMainActivity.class);
                startActivity(jenislapangan);
                finish();
                break;
            case R.id.penyedia_nav_jadwal:
                Intent jadwal= new Intent(HalamanSayaActivity.this, PenyediaJadwalActivity.class);
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
        Intent jenislapangan = new Intent(HalamanSayaActivity.this, PenyediaMainActivity.class);
        startActivity(jenislapangan);
        finish();
//        super.onBackPressed();
    }
}
