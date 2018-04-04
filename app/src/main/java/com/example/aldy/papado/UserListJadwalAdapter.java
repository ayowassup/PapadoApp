package com.example.aldy.papado;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by DBSS014 on 4/4/2018.
 */

public class UserListJadwalAdapter extends RecyclerView.Adapter<UserListJadwalAdapter.ViewHolder> {

    private List<UserListJadwal> listJadwals;
    private Context context;

    public UserListJadwalAdapter(List<UserListJadwal> listJadwals, Context context) {
        this.listJadwals = listJadwals;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView jam1;
        TextView jam2;
        LinearLayout linearLayout;

        public ViewHolder(View v) {
            super(v);

            jam1 = v.findViewById(R.id.user_list_jamjadwal1);
            jam2 = v.findViewById(R.id.user_list_jamjadwal2);
            linearLayout = v.findViewById(R.id.user_clicklistener_list_jadwal);

        }
    }

    @Override
    public UserListJadwalAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list_jadwal, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final UserListJadwal listJadwal = listJadwals.get(position);

        holder.jam1.setText(listJadwal.getJam1());
        holder.jam2.setText(listJadwal.getJam2());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //kalau per item diklik ambil data peritem lewat listJadwal.getjam1() atau getjam2()
                Toast.makeText(context, "you clicked "+listJadwal.getJam1()+" - "+listJadwal.getJam2(),Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listJadwals.size();
    }

}