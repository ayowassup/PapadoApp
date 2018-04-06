package com.example.aldy.papado;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class PenyediaPemesananActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private Toolbar mToolbar;

    private FirebaseDatabase mDatabase;
    private DatabaseReference mRef;
    private FirebaseAuth mAuth;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<PenyediaListPemesanan> listitem;
    private String uid;
    private TextView header;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penyedia_pemesanan);

        mToolbar = findViewById(R.id.penyedia_nav_action);
        setSupportActionBar(mToolbar);

        mDrawerLayout = findViewById(R.id.penyedia_drawer_layout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);

        mDrawerLayout.setDrawerListener(mToggle);
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView navigationView = findViewById(R.id.penyedia_nav_view);

        //Auth & UID
        mAuth = FirebaseAuth.getInstance();
        uid = mAuth.getCurrentUser().getUid();

        //Database
        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference();

        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {

                        penyedia_pindahactivity(menuItem);

                        // close drawer when item is tapped
                        mDrawerLayout.closeDrawers();

                        return true;
                    }
                });

        //Navigation Drawer
        view = navigationView.getHeaderView(0);
        header = view.findViewById(R.id.penyedia_nav_header_text1);

        //RecyclerView
        recyclerView = findViewById(R.id.penyedia_recycler_list_pemesananan);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listitem = new ArrayList<>();

        for (int i = 0; i<20; i++){
            PenyediaListPemesanan listitems = new PenyediaListPemesanan("aldy"+i,"12 juni", "aldy yang tanpan", "01:00 - 02:00", "082257693850");
            listitem.add(listitems);
        }

        adapter = new PenyediaListPemesananAdapter(listitem,this);

        recyclerView.setAdapter(adapter);
    }

    public void penyedia_pindahactivity(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.penyedia_nav_halamansaya:
                Intent halamansaya = new Intent(PenyediaPemesananActivity.this, PenyediaProfilActivity.class);
                startActivity(halamansaya);
                finish();
                break;
            case R.id.penyedia_nav_pemesanan:
                Intent pemesanan = new Intent(PenyediaPemesananActivity.this, PenyediaPemesananActivity.class);
                startActivity(pemesanan);
                finish();
                break;
            case R.id.penyedia_nav_pengaturan:
                Intent pengaturan = new Intent(PenyediaPemesananActivity.this, PenyediaPengaturanActivity.class);
                startActivity(pengaturan);
                finish();
                break;
            case R.id.penyedia_nav_jenislapangan:
                Intent jenislapangan = new Intent(PenyediaPemesananActivity.this, PenyediaDaftarLapanganActivity.class);
                startActivity(jenislapangan);
                finish();
                break;
            case R.id.penyedia_nav_jadwal:
                Intent jadwal= new Intent(PenyediaPemesananActivity.this, PenyediaJadwalActivity.class);
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
        Intent jenislapangan = new Intent(PenyediaPemesananActivity.this, PenyediaDaftarLapanganActivity.class);
        startActivity(jenislapangan);
        finish();
    }
}