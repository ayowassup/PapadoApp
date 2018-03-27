package com.example.aldy.papado;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class HalamanSayaActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_saya);

        mToolbar = findViewById(R.id.penyedia_nav_action);
        setSupportActionBar(mToolbar);

        mDrawerLayout = findViewById(R.id.penyedia_drawer_layout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView navigationView = findViewById(R.id.penyedia_nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
//                         set item as selected to persist highlight
//                        menuItem.setChecked(true);

                        penyedia_pindahactivity(menuItem);

                        // close drawer when item is tapped
                        mDrawerLayout.closeDrawers();

                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here

                        return true;
                    }
                });
    }

    public void penyedia_pindahactivity(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.penyedia_nav_halamansaya:
                Intent halamansaya = new Intent(HalamanSayaActivity.this, HalamanSayaActivity.class);
                startActivity(halamansaya);
                break;
            case R.id.penyedia_nav_pemesanan:
                Intent pemesanan = new Intent(HalamanSayaActivity.this, PemesananActivity.class);
                startActivity(pemesanan);
                break;
            case R.id.penyedia_nav_pengaturan:
                Intent pengaturan = new Intent(HalamanSayaActivity.this, PengaturanActivity.class);
                startActivity(pengaturan);
                break;
            case R.id.penyedia_nav_jenislapangan:
                Intent jenislapangan = new Intent(HalamanSayaActivity.this, PenyediaMainActivity.class);
                startActivity(jenislapangan);
                break;
        }
    }
}
