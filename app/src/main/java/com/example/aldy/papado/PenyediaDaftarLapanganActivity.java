package com.example.aldy.papado;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class PenyediaDaftarLapanganActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private Toolbar mToolbar;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<PenyediaListLapangan> listitem;
    private Button tambahlap;
    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mRef;
    private String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penyedia_daftarlap);

        mToolbar = findViewById(R.id.penyedia_nav_action);
        setSupportActionBar(mToolbar);

        mDrawerLayout = findViewById(R.id.penyedia_drawer_layout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);

        mDrawerLayout.setDrawerListener(mToggle);
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Auth dan UID User
        mAuth = FirebaseAuth.getInstance();
        uid = mAuth.getCurrentUser().getUid();

        //Firebase Database Referencenya
        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference();

        NavigationView navigationView = findViewById(R.id.penyedia_nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        penyedia_pindahactivity(menuItem);
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });
        View view = navigationView.getHeaderView(0);
        final TextView header = view.findViewById(R.id.penyedia_nav_header_text1);
        mRef.child("users").child(uid).child("nama").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String username = dataSnapshot.getValue().toString();
                header.setText(username);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        //RecyclerView
        recyclerView = findViewById(R.id.penyedia_recycler_list_lapangan);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayout = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayout);
        linearLayout.setStackFromEnd(true);

        listitem = new ArrayList<>();
        mRef.child("lapangan").child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    String namaLapangan = postSnapshot.child("namaLapangan").getValue(String.class);
                    String ukuranLapangan = postSnapshot.child("ukuranLapangan").getValue(String.class);
                    String hargaLapangan = postSnapshot.child("hargaLapangan").getValue(String.class);
                    listitem.add(new PenyediaListLapangan(namaLapangan, ukuranLapangan, hargaLapangan));
                }
                adapter = new PenyediaListLapanganAdapter(listitem, getApplicationContext());
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        tambahlap = findViewById(R.id.penyedia_tambahlap);
        tambahlap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PenyediaDaftarLapanganActivity.this, PenyediaTambahlapActivity.class);
                startActivity(intent);
            }
        });
    }

    public void penyedia_pindahactivity (MenuItem menuItem){
        switch (menuItem.getItemId()) {
            case R.id.penyedia_nav_daftarlapangan:
                Intent halamansaya = new Intent(PenyediaDaftarLapanganActivity.this, PenyediaProfilActivity.class);
                startActivity(halamansaya);
                finish();
                break;
            case R.id.penyedia_nav_pemesanan:
                Intent pemesanan = new Intent(PenyediaDaftarLapanganActivity.this, PenyediaPemesananActivity.class);
                startActivity(pemesanan);
                finish();
                break;
            case R.id.penyedia_nav_pengaturan:
                Intent pengaturan = new Intent(PenyediaDaftarLapanganActivity.this, PenyediaPengaturanActivity.class);
                startActivity(pengaturan);
                finish();
                break;
            case R.id.penyedia_nav_jadwal:
                Intent jadwal= new Intent(PenyediaDaftarLapanganActivity.this, PenyediaJadwalActivity.class);
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
    //ini method yang melarang tombol kembali
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
//        super.onBackPressed();
    }
}
