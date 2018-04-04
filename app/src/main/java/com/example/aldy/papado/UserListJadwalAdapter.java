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

    ////// untuk inflater
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }
    //////

    public UserListJadwalAdapter(List<UserListJadwal> listJadwals, Context context) {
        this.listJadwals = listJadwals;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView jam1;
        TextView jam2;

        public ViewHolder(View v, final OnItemClickListener listener) {
            super(v);

            jam1 = v.findViewById(R.id.user_list_jamjadwal1);
            jam2 = v.findViewById(R.id.user_list_jamjadwal2);

            /////
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (listener != null && position!=RecyclerView.NO_POSITION){
                        listener.onItemClick(position);
                    }
                }
            });
            /////
        }
    }

    @Override
    public UserListJadwalAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list_jadwal, parent, false);
        return new ViewHolder(v, mListener);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final UserListJadwal listJadwal = listJadwals.get(position);

        holder.jam1.setText(listJadwal.getJam1());
        holder.jam2.setText(listJadwal.getJam2());
    }

    @Override
    public int getItemCount() {
        return listJadwals.size();
    }

}