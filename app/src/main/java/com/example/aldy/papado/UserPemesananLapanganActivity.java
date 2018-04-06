package com.example.aldy.papado;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UserPemesananLapanganActivity extends AppCompatActivity {
    LinearLayout pilihlap;

    private RecyclerView recyclerView;
    private UserListLapanganAdapter adapter;
    private List<UserListLapangan> listlapangan;
    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mRef;
    private String uidVenue, namaLapangan, ukuranLapangan, hargaLapangan, namaVenue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_pemesanan_lapangan);

        //Auth & User
        mAuth = FirebaseAuth.getInstance();
        String uid = mAuth.getCurrentUser().getUid();

        //Database
        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference();

        recyclerView = findViewById(R.id.user_recycler_list_lapangan);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayout = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayout);
        linearLayout.setStackFromEnd(true);

        listlapangan = new ArrayList<>();

        uidVenue = getIntent().getStringExtra("uid");
        namaVenue = getIntent().getStringExtra("namavenue");

        Toast.makeText(this, "uidVenue"+uidVenue, Toast.LENGTH_SHORT).show();

        mRef.child("lapangan").child(uidVenue).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    namaLapangan = postSnapshot.child("namaLapangan").getValue(String.class);
                    ukuranLapangan = postSnapshot.child("ukuranLapangan").getValue(String.class);
                    hargaLapangan = postSnapshot.child("hargaLapangan").getValue(String.class);
                    listlapangan.add(new UserListLapangan(namaLapangan, ukuranLapangan, hargaLapangan));
                }
                adapter = new UserListLapanganAdapter(listlapangan, getApplicationContext());
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                adapter.setOnItemClickListener(new UserListLapanganAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        Intent intent = new Intent(UserPemesananLapanganActivity.this, UserPemesananJadwalActivity.class);
                        intent.putExtra("uid", uidVenue);
                        intent.putExtra("namalap", namaLapangan);
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
}
