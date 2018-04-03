package com.example.aldy.papado;

import android.app.LauncherActivity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by DBSS014 on 4/3/2018.
 */

public class PenyediaListLapanganAdapter extends RecyclerView.Adapter<PenyediaListLapanganAdapter.ViewHolder>{

    private List<PenyediaListLapangan> listitem;
    private Context context;

    public PenyediaListLapanganAdapter(List<PenyediaListLapangan> listitem, Context context) {
        this.listitem = listitem;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView namalap;
        TextView ukuranlap;
        TextView hargalap;

        public ViewHolder(View v){
            super(v);

            namalap = v.findViewById(R.id.penyedia_list_namalap);
            ukuranlap = v.findViewById(R.id.penyedia_list_ukuranlap);
            hargalap = v.findViewById(R.id.penyedia_list_hargalap);

        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.penyedia_list_lapangan, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PenyediaListLapangan listItem = listitem.get(position);

        holder.namalap.setText(listItem.getNamalap());
        holder.hargalap.setText(listItem.getHargalap());
        holder.ukuranlap.setText(listItem.getUkuranlap());


    }

    @Override
    public int getItemCount() {
        return listitem.size();
    }


}
