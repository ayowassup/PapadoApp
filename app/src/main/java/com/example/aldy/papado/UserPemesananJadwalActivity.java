package com.example.aldy.papado;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class UserPemesananJadwalActivity extends AppCompatActivity {
    private TextView waktu;
    private Button bdate, btime;
    private String uidLapangan;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mRef;
    private FirebaseUser mUser;
    private FirebaseAuth mAuth;
    Calendar c = Calendar.getInstance();
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    LinearLayout jadwal;
    private RecyclerView recyclerView;
    private UserListJadwalAdapter adapter;
    private List<UserListJadwal> listJadwal;
    private String uid,namaVenue, namaLapangan, jam1, jam2, tanggalPesan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_pemesanan_jadwal);

        //Auth
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        uid = mUser.getUid();

        //Database
        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference();

        //UID
        uidLapangan = getIntent().getStringExtra("uid");
        namaVenue = getIntent().getStringExtra("namavenue");
        namaLapangan = getIntent().getStringExtra("namalap");

        waktu = findViewById(R.id.waktu);
        bdate = findViewById(R.id.user_pemesanan_datepicker);

        bdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateDate();
            }
        });
        updateTextLabel();

        recyclerView = findViewById(R.id.user_recycler_list_jadwal);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 3);
        recyclerView.setLayoutManager(layoutManager);

        tanggalPesan = waktu.getText().toString();


        listJadwal = new ArrayList<>();
        mRef.child("jadwal").child(uidLapangan).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    String jamAwal = postSnapshot.child("jamAwal").getValue(String.class);
                    String jamAkhir = postSnapshot.child("jamAkhir").getValue(String.class);
                    listJadwal.add(new UserListJadwal(jamAwal, jamAkhir));
                }
                adapter = new UserListJadwalAdapter(listJadwal, getApplicationContext());
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                adapter.setOnItemClickListener(new UserListJadwalAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        jam1 = listJadwal.get(position).getJam1();
                        jam2 = listJadwal.get(position).getJam2();
                        buatinflater();
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

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
                mRef.child("users").child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String telpuser = dataSnapshot.child("telepon").getValue(String.class);
                        String namaPemesan = dataSnapshot.child("nama").getValue(String.class);
                        HashMap <String, Object> pemesanan = new HashMap<String, Object>();
                        String key = mRef.push().getKey();
                        pemesanan.put("pemesanan/"+uid+"/"+key+"/namaVenue", namaVenue);
                        pemesanan.put("pemesanan/"+uid+"/"+key+"/tanggalPesan", tanggalPesan);
                        pemesanan.put("pemesanan/"+uid+"/"+key+"/jamPesan", (jam1+" - "+jam2));
                        pemesanan.put("pemesanan/"+uid+"/"+key+"/namaLapangan", namaLapangan);
                        pemesanan.put("pemesanan/"+uid+"/"+key+"/statusPesan", "Belum diproses");
                        pemesanan.put("pemesanan/"+uid+"/"+key+"/namaPemesan", namaPemesan);
                        pemesanan.put("pemesanan/"+uid+"/"+key+"/telpPemesan", telpuser);

                        pemesanan.put("orderPenyedia/"+uidLapangan+"/"+key+"/uidPemesan", uid);
                        pemesanan.put("orderPenyedia/"+uidLapangan+"/"+key+"/orderId", key);
                        pemesanan.put("orderPenyedia/"+uidLapangan+"/"+key+"/namaPemesan", namaPemesan);
                        pemesanan.put("orderPenyedia/"+uidLapangan+"/"+key+"/telpPemesan", telpuser);
                        pemesanan.put("orderPenyedia/"+uidLapangan+"/"+key+"/namaLapangan", namaLapangan);
                        pemesanan.put("orderPenyedia/"+uidLapangan+"/"+key+"/jamPesan", (jam1+" - "+jam2));
                        pemesanan.put("orderPenyedia/"+uidLapangan+"/"+key+"/tanggalPesan", tanggalPesan);



                        mRef.updateChildren(pemesanan).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(UserPemesananJadwalActivity.this, "Berhasil booking lapangan", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(UserPemesananJadwalActivity.this, UserNotifActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });

                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
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
