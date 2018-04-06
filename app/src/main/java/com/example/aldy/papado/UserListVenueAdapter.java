package com.example.aldy.papado;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by DBSS014 on 4/5/2018.
 */

public class UserListVenueAdapter extends RecyclerView.Adapter<UserListVenueAdapter.ViewHolder> {
    private List<UserListVenue> listVenues;
    private Context context;

    public UserListVenueAdapter(List<UserListVenue> listVenues, Context context) {
        this.listVenues = listVenues;
        this.context = context;
    }

    ////// untuk inflater
    private UserListVenueAdapter.OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(UserListVenueAdapter.OnItemClickListener listener) {
        mListener = listener;
    }
    //////

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView namavenue, alamatvenue;

        public ViewHolder(View v, final UserListVenueAdapter.OnItemClickListener listener) {
            super(v);

            namavenue = v.findViewById(R.id.user_list_namavenue);
            alamatvenue = v.findViewById(R.id.user_list_alamatvenue);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(position);
                    }
                }
            });
        }
    }

    @Override
    public UserListVenueAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list_venue, parent, false);
        return new UserListVenueAdapter.ViewHolder(v, mListener);
    }

    @Override
    public void onBindViewHolder(UserListVenueAdapter.ViewHolder holder, int position) {
        final UserListVenue listVenue = listVenues.get(position);

        holder.namavenue.setText(listVenue.getNamavenue());
        holder.alamatvenue.setText(listVenue.getAlamatvenue());
    }

    @Override
    public int getItemCount() {
        return listVenues.size();
    }
}
