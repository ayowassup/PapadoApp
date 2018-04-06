package com.example.aldy.papado;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

//Untuk akun
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class PenyediaPengaturanActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private Toolbar mToolbar;
    private FirebaseUser mUser;
    private FirebaseAuth mAuth;
    private LinearLayout logout, delacc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penyedia_pengaturan);

        mToolbar = findViewById(R.id.penyedia_nav_action);
        setSupportActionBar(mToolbar);

        mDrawerLayout = findViewById(R.id.penyedia_drawer_layout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);

        mDrawerLayout.setDrawerListener(mToggle);
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Auth Instance
        mAuth = FirebaseAuth.getInstance();

        //User terkini
        mUser = mAuth.getCurrentUser();

        NavigationView navigationView = findViewById(R.id.penyedia_nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        penyedia_pindahactivity(menuItem);
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });

        logout = findViewById(R.id.penyedia_logout);
        logout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        logout.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                        break;
                    case MotionEvent.ACTION_UP:
                        logout.setBackgroundColor(0x00000000);
                        mAuth.signOut();
                        if (mAuth.getCurrentUser() == null) {
                            Intent loginActivity = new Intent(PenyediaPengaturanActivity.this, LoginActivity.class);
                            startActivity(loginActivity);
                            finish();
                        }
                        break;
                }
                return true;
            }
        });

        //Delete Account
        delacc = findViewById(R.id.penyedia_delacc);
        delacc.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        delacc.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                        break;
                    case MotionEvent.ACTION_UP:
                        delacc.setBackgroundColor(0x00000000);
                        mUser.delete()
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(PenyediaPengaturanActivity.this, "Akun berhasil dihapus", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                        Intent intent = new Intent(PenyediaPengaturanActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                }
                return true;
            }
        });
    }

    public void penyedia_pindahactivity(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.penyedia_nav_halamansaya:
                Intent halamansaya = new Intent(PenyediaPengaturanActivity.this, PenyediaProfilActivity.class);
                startActivity(halamansaya);
                finish();
                break;
            case R.id.penyedia_nav_pemesanan:
                Intent pemesanan = new Intent(PenyediaPengaturanActivity.this, PenyediaPemesananActivity.class);
                startActivity(pemesanan);
                finish();
                break;
            case R.id.penyedia_nav_pengaturan:
                Intent pengaturan = new Intent(PenyediaPengaturanActivity.this, PenyediaPengaturanActivity.class);
                startActivity(pengaturan);
                finish();
                break;
            case R.id.penyedia_nav_jenislapangan:
                Intent jenislapangan = new Intent(PenyediaPengaturanActivity.this, PenyediaDaftarLapanganActivity.class);
                startActivity(jenislapangan);
                finish();
                break;
            case R.id.penyedia_nav_jadwal:
                Intent jadwal= new Intent(PenyediaPengaturanActivity.this, PenyediaJadwalActivity.class);
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
        Intent jenislapangan = new Intent(PenyediaPengaturanActivity.this, PenyediaDaftarLapanganActivity.class);
        startActivity(jenislapangan);
        finish();
//        super.onBackPressed();
    }
}