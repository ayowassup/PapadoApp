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
import android.view.View;
import android.widget.LinearLayout;

<<<<<<< HEAD:app/src/main/java/com/example/aldy/papado/PengaturanActivity.java
import android.widget.Toast;

//Untuk akun
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class PengaturanActivity extends AppCompatActivity {
=======
public class PenyediaPengaturanActivity extends AppCompatActivity {
>>>>>>> 1fa3ecf94a3840591ce2b161837967ae49c886ab:app/src/main/java/com/example/aldy/papado/PenyediaPengaturanActivity.java
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private Toolbar mToolbar;
    private FirebaseUser user;
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authListener;
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
        auth = FirebaseAuth.getInstance();

        //User terkini
        user = auth.getCurrentUser();

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

        logout = findViewById(R.id.penyedia_logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
<<<<<<< HEAD:app/src/main/java/com/example/aldy/papado/PengaturanActivity.java
                auth.signOut();
                if (auth.getCurrentUser() == null) {
                    Intent loginActivity = new Intent(PengaturanActivity.this, LoginActivity.class);
                    startActivity(loginActivity);
                    finish();
                }
            }
        });

        delacc = findViewById(R.id.penyedia_delacc);
        delacc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user.delete()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(PengaturanActivity.this, "Akun berhasil dihapus", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                Intent intent = new Intent(PengaturanActivity.this, LoginActivity.class);
=======
                Intent intent = new Intent(PenyediaPengaturanActivity.this, LoginActivity.class);
>>>>>>> 1fa3ecf94a3840591ce2b161837967ae49c886ab:app/src/main/java/com/example/aldy/papado/PenyediaPengaturanActivity.java
                startActivity(intent);
                finish();

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