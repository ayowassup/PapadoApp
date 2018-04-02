package com.example.aldy.papado;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class UserPemesananListActivity extends AppCompatActivity {
    LinearLayout pilihlap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_pemesanan_list);
        pilihlap = findViewById(R.id.user_pilihlap);
        pilihlap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (UserPemesananListActivity.this, UserTanggalActivity.class);
                startActivity(intent);
            }
        });
    }
}
