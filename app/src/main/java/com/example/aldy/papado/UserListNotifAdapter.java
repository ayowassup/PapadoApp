package com.example.aldy.papado;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by DBSS014 on 4/4/2018.
 */

public class UserListNotifAdapter extends RecyclerView.Adapter<UserListNotifAdapter.ViewHolder> {
    private List<UserListNotif> listVenues;
    private Context context;

    public UserListNotifAdapter(List<UserListNotif> listVenues, Context context) {
        this.listVenues = listVenues;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView namalap, tanggalpesan, jampesan, notelp, namapenyedia, keterangan;
        public ViewHolder(View v) {
            super(v);

            namalap = v.findViewById(R.id.user_pesan_namalap);
            tanggalpesan = v.findViewById(R.id.user_pesan_tanggal);
            jampesan = v.findViewById(R.id.user_pesan_jam);
            notelp = v.findViewById(R.id.user_pesan_notelp);
            namapenyedia = v.findViewById(R.id.user_pesan_namapenyedia);
            keterangan = v.findViewById(R.id.user_pesan_keterangan);
        }
    }

    @Override
    public UserListNotifAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list_notif, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(UserListNotifAdapter.ViewHolder holder, int position) {
        final UserListNotif listVenue = listVenues.get(position);

        holder.namalap.setText(listVenue.getNamalap());
        holder.tanggalpesan.setText(listVenue.getTanggalpesan());
        holder.jampesan.setText(listVenue.getJampesan());
        holder.notelp.setText(listVenue.getNotelp());
        holder.namapenyedia.setText(listVenue.getNamapenyedia());
        holder.keterangan.setText(listVenue.getKeterangan());
    }

    @Override
    public int getItemCount() {
        return listVenues.size();
    }
}
