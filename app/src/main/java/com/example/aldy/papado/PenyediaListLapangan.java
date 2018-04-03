package com.example.aldy.papado;

/**
 * Created by DBSS014 on 4/3/2018.
 */

public class PenyediaListLapangan {
    private String namalap;
    private String ukuranlap;
    private String hargalap;

    public PenyediaListLapangan(String namalap, String ukuranlap, String hargalap) {
        this.namalap = namalap;
        this.ukuranlap = ukuranlap;
        this.hargalap = hargalap;
    }

    public String getNamalap() {
        return namalap;
    }

    public String getUkuranlap() {
        return ukuranlap;
    }

    public String getHargalap() {
        return hargalap;
    }
}
