package com.example.aldy.papado;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by DBSS014 on 4/5/2018.
 */

public class UserListLapanganAdapter extends RecyclerView.Adapter<UserListLapanganAdapter.ViewHolder> {

    private List<UserListLapangan> listLapangans;
    private Context context;

    ////// untuk inflater
    private UserListLapanganAdapter.OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(UserListLapanganAdapter.OnItemClickListener listener){
        mListener = listener;
    }
    //////

    public UserListLapanganAdapter(List<UserListLapangan> listLapangans, Context context) {
        this.listLapangans = listLapangans;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView namalap;
        TextView ukuranlap;
        TextView hargalap;

        public ViewHolder(View v, final UserListLapanganAdapter.OnItemClickListener listener) {
            super(v);

            namalap = v.findViewById(R.id.user_list_namalap);
            ukuranlap = v.findViewById(R.id.user_list_ukuranlap);
            hargalap = v.findViewById(R.id.user_list_hargalap);

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
    public UserListLapanganAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list_lapangan, parent, false);
        return new UserListLapanganAdapter.ViewHolder(v, mListener);
    }

    @Override
    public void onBindViewHolder(UserListLapanganAdapter.ViewHolder holder, int position) {
        final UserListLapangan listItem = listLapangans.get(position);

        holder.namalap.setText(listItem.getNamalap());
        holder.hargalap.setText(listItem.getHargalap());
        holder.ukuranlap.setText(listItem.getUkuranlap());
    }

    @Override
    public int getItemCount() {
        return listLapangans.size();
    }


}
