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
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserProfilActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private Toolbar mToolbar;
    private TextView editprofile, namaUser, emailUser, noTelpUser, header;
    private String nama, email, telepon, uid;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private DatabaseReference mDatabase;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profil);

        namaUser = findViewById(R.id.user_profile_nama);
        emailUser = findViewById(R.id.user_profile_email);
        noTelpUser = findViewById(R.id.user_profile_notelp);

        //Auth  UID
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        uid = mUser.getUid();

        //Database
        mDatabase = FirebaseDatabase.getInstance().getReference();

        mToolbar = findViewById(R.id.user_nav_action);
        setSupportActionBar(mToolbar);

        mDrawerLayout = findViewById(R.id.user_drawer_layout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);

        mDrawerLayout.setDrawerListener(mToggle);
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView navigationView = findViewById(R.id.user_nav_view);
        navigationView.getMenu().findItem(R.id.user_nav_profil).setEnabled(false);
        navigationView.getMenu().findItem(R.id.user_nav_profil).setChecked(true);
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
        mDatabase.child("users").child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                    telepon = dataSnapshot.child("telepon").getValue(String.class);
                    email = dataSnapshot.child("email").getValue(String.class);
                    nama = dataSnapshot.child("nama").getValue(String.class);
                    namaUser.setText(nama);
                    noTelpUser.setText(telepon);
                    emailUser.setText(email);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //Edit Profil
        editprofile = findViewById(R.id.user_profile_edit);
        editprofile.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        editprofile.setTextColor(getResources().getColor(R.color.buttondown));
                        break;
                    case MotionEvent.ACTION_UP:
                        editprofile.setTextColor(getResources().getColor(android.R.color.primary_text_light));
                        Intent intent = new Intent (UserProfilActivity.this, UserEditProfilActivity.class);
                        startActivity(intent);
                        break;
                }
                return true;
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
