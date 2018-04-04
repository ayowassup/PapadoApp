package com.example.aldy.papado;

import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by DBSS014 on 4/4/2018.
 */

public class PenyediaListPemesanan {
    private Button no, yes;
    private String namalap;
    private String tanggalpesan;
    private String namapesan;
    private String waktupesan;
    private String notelppesan;
    private ImageView fotoprofil;

    public PenyediaListPemesanan(String namalap, String tanggalpesan, String namapesan, String waktupesan, String notelppesan) {
        this.namalap = namalap;
        this.tanggalpesan = tanggalpesan;
        this.namapesan = namapesan;
        this.waktupesan = waktupesan;
        this.notelppesan = notelppesan;
    }

    public Button getNo() {
        return no;
    }

    public Button getYes() {
        return yes;
    }

    public String getNamalap() {
        return namalap;
    }

    public String getTanggalpesan() {
        return tanggalpesan;
    }

    public String getNamapesan() {
        return namapesan;
    }

    public String getWaktupesan() {
        return waktupesan;
    }

    public String getNotelppesan() {
        return notelppesan;
    }

    public ImageView getFotoprofil() {
        return fotoprofil;
    }
}
