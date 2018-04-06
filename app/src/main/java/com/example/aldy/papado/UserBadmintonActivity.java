package com.example.aldy.papado;

import android.content.Intent;
import android.support.annotation.NonNull;
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
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

public class UserBadmintonActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private Toolbar mToolbar;

    private RecyclerView recyclerView;
    private UserListVenueAdapter adapter;
    private List<UserListVenue> listVenues = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_badminton);

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

        /////
        recyclerView = findViewById(R.id.user_recycler_list_venue_badminton);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        for (int i = 0;i<20;i++){
            UserListVenue listVenue = new UserListVenue("venue badminton"+i, "ub");
            listVenues.add(listVenue);
        }

        adapter = new UserListVenueAdapter(listVenues, this);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new UserListVenueAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(UserBadmintonActivity.this, UserPemesananLapanganActivity.class);
                startActivity(intent);
            }
        });

    }
    public void user_pindahactivity (MenuItem menuItem){
        switch (menuItem.getItemId()) {
            case R.id.user_nav_profil:
                Intent favorit = new Intent(UserBadmintonActivity.this, UserProfilActivity.class);
                startActivity(favorit);
                finish();
                break;
            case R.id.user_nav_pemesanan:
                Intent pemesanan = new Intent(UserBadmintonActivity.this, UserNotifActivity.class);
                startActivity(pemesanan);
                finish();
                break;
            case R.id.user_nav_riwayat:
                Intent riwayat = new Intent(UserBadmintonActivity.this, UserRiwayatActivity.class);
                startActivity(riwayat);
                finish();
                break;
            case R.id.user_nav_badminton:
                mDrawerLayout.closeDrawers();
                break;
            case R.id.user_nav_futsal:
                Intent futsal= new Intent(UserBadmintonActivity.this, UserFutsalActivity.class);
                startActivity(futsal);
                finish();
                break;
            case R.id.user_nav_pengaturan:
                Intent pengaturan= new Intent(UserBadmintonActivity.this, UserPengaturanActivity.class);
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
        Intent intent = new Intent(UserBadmintonActivity.this,UserNotifActivity.class);
        startActivity(intent);
        finish();
//        super.onBackPressed();
    }
}
