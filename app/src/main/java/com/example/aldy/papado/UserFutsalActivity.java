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
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UserFutsalActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private Toolbar mToolbar;

    private RecyclerView recyclerView;
    private UserListVenueAdapter adapter;
    private List<UserListVenue> listVenues;
    private FirebaseAuth mAuth;
    private DatabaseReference mRef;
    private FirebaseDatabase mDatabase;
    private String alamatVenue, namaVenue, uidVenue;
    private View view;
    private EditText header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_futsal);

        //Auth & User
        mAuth = FirebaseAuth.getInstance();
        String uid = mAuth.getCurrentUser().getUid();

        //Database
        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference();

        mToolbar = findViewById(R.id.user_nav_action);
        setSupportActionBar(mToolbar);

        mDrawerLayout = findViewById(R.id.user_drawer_layout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);

        mDrawerLayout.setDrawerListener(mToggle);
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView navigationView = findViewById(R.id.user_nav_view);
        navigationView.getMenu().findItem(R.id.user_nav_futsal).setEnabled(false);
        navigationView.getMenu().findItem(R.id.user_nav_futsal).setChecked(true);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);
                user_pindahactivity(item);
                mDrawerLayout.closeDrawers();

                return true;
            }
        });

        view = navigationView.getHeaderView(0);
        header = findViewById(R.id.user_nav_header_text1);
        mRef.child("users").child(uid).child("nama").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String nama = dataSnapshot.getValue().toString();
                header.setText(nama);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //RecyclerView
        recyclerView = findViewById(R.id.user_recycler_list_venue_futsal);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayout = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayout);
        linearLayout.setStackFromEnd(true);

        listVenues = new ArrayList<>();
        mRef.child("venue").child("Futsal").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    alamatVenue = postSnapshot.child("alamat").getValue(String.class);
                    namaVenue = postSnapshot.child("namaTempat").getValue(String.class);
                    uidVenue = postSnapshot.child("uid").getValue(String.class);
                    listVenues.add(new UserListVenue(namaVenue, alamatVenue));
                }
                adapter = new UserListVenueAdapter(listVenues, getApplicationContext());
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                adapter.setOnItemClickListener(new UserListVenueAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Intent intent = new Intent(UserFutsalActivity.this, UserPemesananLapanganActivity.class);
                        intent.putExtra("uid",uidVenue);
                        intent.putExtra("namavenue", namaVenue);
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
    public void user_pindahactivity (MenuItem menuItem){
        switch (menuItem.getItemId()) {
            case R.id.user_nav_profil:
                Intent favorit = new Intent(UserFutsalActivity.this, UserProfilActivity.class);
                startActivity(favorit);
                finish();
                break;
            case R.id.user_nav_pemesanan:
                Intent pemesanan = new Intent(UserFutsalActivity.this, UserNotifActivity.class);
                startActivity(pemesanan);
                finish();
                break;
            case R.id.user_nav_badminton:
                Intent badminton = new Intent(UserFutsalActivity.this, UserBadmintonActivity.class);
                startActivity(badminton);
                finish();
                break;
            case R.id.user_nav_futsal:
                mDrawerLayout.closeDrawers();
                break;
            case R.id.user_nav_pengaturan:
                Intent pengaturan= new Intent(UserFutsalActivity.this, UserPengaturanActivity.class);
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
        Intent intent = new Intent(UserFutsalActivity.this,UserNotifActivity.class);
        startActivity(intent);
        finish();
    }
}
