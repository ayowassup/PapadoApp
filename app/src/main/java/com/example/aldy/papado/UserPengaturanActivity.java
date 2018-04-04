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

public class UserPengaturanActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private Toolbar mToolbar;
    private LinearLayout logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_pengaturan);

        mToolbar = findViewById(R.id.user_nav_action);
        setSupportActionBar(mToolbar);

        mDrawerLayout = findViewById(R.id.user_drawer_layout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);

        mDrawerLayout.setDrawerListener(mToggle);
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView navigationView = findViewById(R.id.user_nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);
                user_pindahactivity(item);
                mDrawerLayout.closeDrawers();

                return true;
            }
        });

        logout = findViewById(R.id.user_logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (UserPengaturanActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    public void user_pindahactivity (MenuItem menuItem){
        switch (menuItem.getItemId()) {
            case R.id.user_nav_profil:
                Intent favorit = new Intent(UserPengaturanActivity.this, UserProfilActivity.class);
                startActivity(favorit);
                finish();
                break;
            case R.id.user_nav_pemesanan:
                Intent pemesanan = new Intent(UserPengaturanActivity.this, UserPemesananActivity.class);
                startActivity(pemesanan);
                finish();
                break;
            case R.id.user_nav_riwayat:
                Intent riwayat = new Intent(UserPengaturanActivity.this, UserRiwayatActivity.class);
                startActivity(riwayat);
                finish();
                break;
            case R.id.user_nav_badminton:
                Intent badminton = new Intent(UserPengaturanActivity.this, UserBadmintonActivity.class);
                startActivity(badminton);
                finish();
                break;
            case R.id.user_nav_futsal:
                Intent futsal= new Intent(UserPengaturanActivity.this, UserFutsalActivity.class);
                startActivity(futsal);
                finish();
                break;
            case R.id.user_nav_pengaturan:
                mDrawerLayout.closeDrawers();
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
        Intent intent = new Intent(UserPengaturanActivity.this,UserPemesananActivity.class);
        startActivity(intent);
        finish();
//        super.onBackPressed();
    }
}
