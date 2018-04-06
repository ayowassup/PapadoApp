package com.example.aldy.papado;

<<<<<<< HEAD
/**
 * Created by afridha on 06/04/18.
 */

=======
>>>>>>> 1fa3ecf94a3840591ce2b161837967ae49c886ab
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

<<<<<<< HEAD
    public class PenyediaListPemesananAdapter extends RecyclerView.Adapter<PenyediaListPemesananAdapter.ViewHolder> {
        private List<PenyediaListPemesanan> listPemesanans;
        private Context context;

        public PenyediaListPemesananAdapter(List<PenyediaListPemesanan> listPemesanans, Context context) {
            this.listPemesanans = listPemesanans;
            this.context = context;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            Button no, yes;
            TextView namalap, tanggalpesan, namapesan, waktupesan, notelppesan;
            ImageView fotoprofil;

            public ViewHolder(View v) {
                super(v);
                no = v.findViewById(R.id.penyedia_list_pemesanan_no);
                yes = v.findViewById(R.id.penyedia_list_pemesanan_yes);
                namalap = v.findViewById(R.id.penyedia_list_pemesanan_namalap);
                tanggalpesan = v.findViewById(R.id.penyedia_list_pemesanan_tanggal);
                namapesan = v.findViewById(R.id.penyedia_list_pemesanan_nama);
                waktupesan = v.findViewById(R.id.penyedia_list_pemesanan_waktu);
                notelppesan = v.findViewById(R.id.penyedia_list_pemesanan_notelp);
                fotoprofil = v.findViewById(R.id.penyedia_list_pemesanan_fotoprofil);
            }
        }

        @Override
        public PenyediaListPemesananAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.penyedia_list_pemesanan, parent, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(PenyediaListPemesananAdapter.ViewHolder holder, int position) {
            final PenyediaListPemesanan listPemesanan = listPemesanans.get(position);

//        holder.fotoprofil.setBackground(listPemesanan.getFotoprofil().getBackground()); //ambil foto profil
            holder.notelppesan.setText(listPemesanan.getNoTelpPesan()); //ambil no telp pemesan
            holder.waktupesan.setText(listPemesanan.getWaktuPesan()); //ambil waktu yang dipesan
            holder.namapesan.setText(listPemesanan.getNamaPesan()); //ambil nama pemesan
            holder.tanggalpesan.setText(listPemesanan.getTanggalPesan()); //ambil tanggal pemesanan
            holder.namalap.setText(listPemesanan.getNamaLap()); //ambil nama lapangan yang dipesan
            holder.no.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //kalau tolak
                    Toast.makeText(context, "Pesanan ditolak", Toast.LENGTH_SHORT).show();
                }
            });
            holder.yes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //kalau terima
                    Toast.makeText(context, "Pesanan diterima", Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public int getItemCount() {
            return listPemesanans.size();
        }
    }
=======
/**
 * Created by DBSS014 on 4/4/2018.
 */

public class PenyediaListPemesananAdapter extends RecyclerView.Adapter<PenyediaListPemesananAdapter.ViewHolder> {
    private List<PenyediaListPemesanan> listPemesanans;
    private Context context;

    public PenyediaListPemesananAdapter(List<PenyediaListPemesanan> listPemesanans, Context context) {
        this.listPemesanans = listPemesanans;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        Button no, yes;
        TextView namalap, tanggalpesan, namapesan, waktupesan, notelppesan;
        ImageView fotoprofil;

        public ViewHolder(View v) {
            super(v);
            no = v.findViewById(R.id.penyedia_list_pemesanan_no);
            yes = v.findViewById(R.id.penyedia_list_pemesanan_yes);
            namalap = v.findViewById(R.id.penyedia_list_pemesanan_namalap);
            tanggalpesan = v.findViewById(R.id.penyedia_list_pemesanan_tanggal);
            namapesan = v.findViewById(R.id.penyedia_list_pemesanan_nama);
            waktupesan = v.findViewById(R.id.penyedia_list_pemesanan_waktu);
            notelppesan = v.findViewById(R.id.penyedia_list_pemesanan_notelp);
            fotoprofil = v.findViewById(R.id.penyedia_list_pemesanan_fotoprofil);
        }
    }

    @Override
    public PenyediaListPemesananAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.penyedia_list_pemesanan, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PenyediaListPemesananAdapter.ViewHolder holder, int position) {
        final PenyediaListPemesanan listPemesanan = listPemesanans.get(position);

//        holder.fotoprofil.setBackground(listPemesanan.getFotoprofil().getBackground()); //ambil foto profil
        holder.notelppesan.setText(listPemesanan.getNotelppesan()); //ambil no telp pemesan
        holder.waktupesan.setText(listPemesanan.getWaktupesan()); //ambil waktu yang dipesan
        holder.namapesan.setText(listPemesanan.getNamapesan()); //ambil nama pemesan
        holder.tanggalpesan.setText(listPemesanan.getTanggalpesan()); //ambil tanggal pemesanan
        holder.namalap.setText(listPemesanan.getNamalap()); //ambil nama lapangan yang dipesan
        holder.no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //kalau tolak
                Toast.makeText(context, "Pesanan ditolak", Toast.LENGTH_SHORT).show();
            }
        });
        holder.yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //kalau terima
                Toast.makeText(context, "Pesanan diterima", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listPemesanans.size();
    }
}
>>>>>>> 1fa3ecf94a3840591ce2b161837967ae49c886ab
