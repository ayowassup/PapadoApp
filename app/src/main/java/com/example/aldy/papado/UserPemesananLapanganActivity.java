package com.example.aldy.papado;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class UserPemesananLapanganActivity extends AppCompatActivity {
    LinearLayout pilihlap;

    private RecyclerView recyclerView;
    private UserListLapanganAdapter adapter;
    private List<UserListLapangan> listLapangans = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_pemesanan_lapangan);

        recyclerView = findViewById(R.id.user_recycler_list_lapangan);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        for (int i = 0; i<20; i++){
            UserListLapangan listLapangan = new UserListLapangan("namalap"+(i+1), "ukuranlap", "1000");
            listLapangans.add(listLapangan);
        }

        adapter = new UserListLapanganAdapter(listLapangans, this);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new UserListLapanganAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(UserPemesananLapanganActivity.this, UserPemesananJadwalActivity.class);
                startActivity(intent);
            }
        });
    }
}
