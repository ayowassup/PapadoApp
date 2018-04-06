package com.example.aldy.papado;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class UserPemesananJadwalActivity extends AppCompatActivity {
    private TextView waktu;
    private Button bdate, btime;
    Calendar c = Calendar.getInstance();
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    LinearLayout jadwal;

    /////
    private RecyclerView recyclerView;
    private UserListJadwalAdapter adapter; //dari RecyclerView.Adapter diganti UserListJadwalAdapter buat memperjelas

    private List<UserListJadwal> listJadwals = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_pemesanan_jadwal);

        waktu = findViewById(R.id.waktu);
        bdate = findViewById(R.id.user_pemesanan_datepicker);

        bdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateDate();
            }
        });
        updateTextLabel();
        /////
        recyclerView = findViewById(R.id.user_recycler_list_jadwal);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));

        //DUMMY DATA
        for (int i = 0; i<20; i++){
            UserListJadwal listitems = new UserListJadwal(i+":00", (i+1)+":00");
            listJadwals.add(listitems);
        }

        adapter = new UserListJadwalAdapter(listJadwals, this);
        recyclerView.setAdapter(adapter);

        ////////
        adapter.setOnItemClickListener(new UserListJadwalAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                buatinflater();
            }
        });
        ////////
    }
    public void buatinflater(){
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(UserPemesananJadwalActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.user_jadwal_dialog, null);
        Button yes = mView.findViewById(R.id.user_jadwal_pesan_yes);
        Button no = mView.findViewById(R.id.user_jadwal_pesan_no);

        mBuilder.setView(mView);
        final AlertDialog dialog = mBuilder.create();
        dialog.show();

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //kalau tombol yes diklik
//                        dialog.dismiss(); // ini method untuk menghilangkan alertdialog
                Toast.makeText(UserPemesananJadwalActivity.this, "yes", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(UserPemesananJadwalActivity.this, UserNotifActivity.class);
                startActivity(intent);
                finish();
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //kalau tombol no diklik
                dialog.dismiss();// ini method untuk menghilangkan alertdialog
            }
        });
    }
    public void updateDate() {
        new DatePickerDialog(this, d, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();
    }
    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            c.set(Calendar.YEAR, i);
            c.set(Calendar.MONTH, i1);
            c.set(Calendar.DAY_OF_MONTH, i2);
            updateTextLabel();
        }
    };

    public void updateTextLabel() {
        //dia ngambil waktu sama tanggal lewat sini
        waktu.setText(dateFormat.format(c.getTime()));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
