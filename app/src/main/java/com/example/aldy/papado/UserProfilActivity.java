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
import android.widget.TextView;

public class UserProfilActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private Toolbar mToolbar;
    private TextView editprofile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profil);

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

//        ini kalau mau rubah nama di headernya
//        View hview = navigationView.getHeaderView(0);
//        TextView headercoba = hview.findViewById(R.id.user_nav_header_text1);
//        headercoba.setText("aldjflaksjdfklasjdflkalskdfj");

        /////
        editprofile = findViewById(R.id.user_profile_edit);
        editprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserProfilActivity.this,UserEditProfilActivity.class);
                startActivity(intent);
            }
        });
    }
    public void user_pindahactivity (MenuItem menuItem){
        switch (menuItem.getItemId()) {
            case R.id.user_nav_profil:
                mDrawerLayout.closeDrawers();
                break;
            case R.id.user_nav_pemesanan:
                Intent pemesanan = new Intent(UserProfilActivity.this, UserNotifActivity.class);
                startActivity(pemesanan);
                finish();
                break;
            case R.id.user_nav_riwayat:
                Intent riwayat = new Intent(UserProfilActivity.this, UserRiwayatActivity.class);
                startActivity(riwayat);
                finish();
                break;
            case R.id.user_nav_badminton:
                Intent badminton = new Intent(UserProfilActivity.this, UserBadmintonActivity.class);
                startActivity(badminton);
                finish();
                break;
            case R.id.user_nav_futsal:
                Intent futsal= new Intent(UserProfilActivity.this, UserFutsalActivity.class);
                startActivity(futsal);
                finish();
                break;
            case R.id.user_nav_pengaturan:
                Intent pengaturan= new Intent(UserProfilActivity.this, UserPengaturanActivity.class);
                startActivity(pengaturan);
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
        Intent intent = new Intent(UserProfilActivity.this,UserNotifActivity.class);
        startActivity(intent);
        finish();
//        super.onBackPressed();
    }
}
