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
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UserNotifActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private Toolbar mToolbar;
    private FirebaseUser mUser;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private View view;
    private TextView header;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<UserListNotif> listVenues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_notif);

        mToolbar = findViewById(R.id.user_nav_action);
        setSupportActionBar(mToolbar);

        mDrawerLayout = findViewById(R.id.user_drawer_layout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);

        mDrawerLayout.setDrawerListener(mToggle);
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Auth Instance
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();


        //User terkini
        mUser = mAuth.getCurrentUser();
        String uid = mUser.getUid();

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


        view = navigationView.getHeaderView(0);
        header = view.findViewById(R.id.user_nav_header_text1);
        mDatabase.child("users").child(uid).child("username").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String username = dataSnapshot.getValue().toString();
                header.setText(username);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
        ////
        recyclerView = findViewById(R.id.user_recycler_list_notif);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //DUMMY DATA
        listVenues = new ArrayList<>();

        for (int i = 0; i<20; i++) {
            UserListNotif listVenue = new UserListNotif("nama lap aduh "+i,"23-23-2323", "11:00 - 12:00", "081034194234", "aldy","belum di acc");
            listVenues.add(listVenue);
        }

        adapter = new UserListNotifAdapter(listVenues, this);
        recyclerView.setAdapter(adapter);

        }

    public void user_pindahactivity (MenuItem menuItem){
        switch (menuItem.getItemId()) {
            case R.id.user_nav_profil:
                Intent favorit = new Intent(UserNotifActivity.this, UserProfilActivity.class);
                startActivity(favorit);
                finish();
                break;
            case R.id.user_nav_pemesanan:
                mDrawerLayout.closeDrawers();
                break;
            case R.id.user_nav_riwayat:
                Intent riwayat = new Intent(UserNotifActivity.this, UserRiwayatActivity.class);
                startActivity(riwayat);
                finish();
                break;
            case R.id.user_nav_badminton:
                Intent badminton = new Intent(UserNotifActivity.this, UserBadmintonActivity.class);
                startActivity(badminton);
                finish();
                break;
            case R.id.user_nav_futsal:
                Intent futsal= new Intent(UserNotifActivity.this, UserFutsalActivity.class);
                startActivity(futsal);
                finish();
                break;
            case R.id.user_nav_pengaturan:
                Intent pengaturan= new Intent(UserNotifActivity.this, UserPengaturanActivity.class);
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
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
//        super.onBackPressed();
    }
}
