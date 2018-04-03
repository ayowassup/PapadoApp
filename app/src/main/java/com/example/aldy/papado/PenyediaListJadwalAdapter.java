package com.example.aldy.papado;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by DBSS014 on 4/3/2018.
 */

public class PenyediaListJadwalAdapter extends RecyclerView.Adapter<PenyediaListJadwalAdapter.ViewHolder> {

    private List<PenyediaListJadwal> listJadwals;
    private Context context;

    public PenyediaListJadwalAdapter(List<PenyediaListJadwal> listJadwals, Context context) {
        this.listJadwals = listJadwals;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView jam1;
        TextView jam2;

        public ViewHolder(View v) {
            super(v);

            jam1 = v.findViewById(R.id.penyedia_list_jamjadwal1);
            jam2 = v.findViewById(R.id.penyedia_list_jamjadwal2);
        }
    }

    @Override
    public PenyediaListJadwalAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.penyedia_list_jadwal, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PenyediaListJadwal listJadwal = listJadwals.get(position);

        holder.jam1.setText(listJadwal.getJam1());
        holder.jam2.setText(listJadwal.getJam2());

    }

    @Override
    public int getItemCount() {
        return listJadwals.size();
    }
}
