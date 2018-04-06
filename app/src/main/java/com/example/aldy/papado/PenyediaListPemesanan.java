package com.example.aldy.papado;

/**
 * Created by afridha on 06/04/18.
 */

public class PenyediaListPemesanan {
    private String namaLap, tanggalPesan, namaPesan, waktuPesan, noTelpPesan;

    public PenyediaListPemesanan(String namaLap, String tanggalPesan, String namaPesan, String waktuPesan, String noTelpPesan) {
        this.namaLap = namaLap;
        this.tanggalPesan = tanggalPesan;
        this.namaPesan = namaPesan;
        this.waktuPesan = waktuPesan;
        this.noTelpPesan = noTelpPesan;
    }

    public PenyediaListPemesanan() {
    }

    public String getNamaLap() {
        return namaLap;
    }

    public void setNamaLap(String namaLap) {
        this.namaLap = namaLap;
    }

    public String getTanggalPesan() {
        return tanggalPesan;
    }

    public void setTanggalPesan(String tanggalPesan) {
        this.tanggalPesan = tanggalPesan;
    }

    public String getNamaPesan() {
        return namaPesan;
    }

    public void setNamaPesan(String namaPesan) {
        this.namaPesan = namaPesan;
    }

    public String getWaktuPesan() {
        return waktuPesan;
    }

    public void setWaktuPesan(String waktuPesan) {
        this.waktuPesan = waktuPesan;
    }

    public String getNoTelpPesan() {
        return noTelpPesan;
    }

    public void setNoTelpPesan(String noTelpPesan) {
        this.noTelpPesan = noTelpPesan;
    }
}
