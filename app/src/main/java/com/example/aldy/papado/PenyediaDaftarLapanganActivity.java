package com.example.aldy.papado;

import android.content.Intent;
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

        NavigationView navigationView = findViewById(R.id.penyedia_nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
//                         set item as selected to persist highlight
                        menuItem.setChecked(true);

                        penyedia_pindahactivity(menuItem);

                        // close drawer when item is tapped
                        mDrawerLayout.closeDrawers();

                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here

                        return true;
                    }
                });

        recyclerView = findViewById(R.id.penyedia_recycler_list_lapangan);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //DUMMY DATA (BELUM NGAMBIL DARI SERVER)
        listitem = new ArrayList<>();

        for (int i = 0; i<20; i++){
            PenyediaListLapangan listitems = new PenyediaListLapangan("namalap"+(i+1), "ukuranlap", "1000");
            listitem.add(listitems);
        }

        adapter = new PenyediaListLapanganAdapter(listitem,this);

        recyclerView.setAdapter(adapter);

        ///////
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
            case R.id.penyedia_nav_halamansaya:
                Intent halamansaya = new Intent(PenyediaDaftarLapanganActivity.this, HalamanSayaActivity.class);
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
            case R.id.penyedia_nav_jenislapangan:
                Intent jenislapangan = new Intent(PenyediaDaftarLapanganActivity.this, PenyediaDaftarLapanganActivity.class);
                startActivity(jenislapangan);
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
