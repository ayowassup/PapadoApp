package com.example.aldy.papado;

/**
 * Created by afridha on 05/04/18.
 */

public class PenyediaProfil {
    private String alamat, email, namaVenue, namaPemilik, jamBukaTutup, noTelp;

    public PenyediaProfil(String alamat, String email, String namaVenue, String namaPemilik, String jamBukaTutup, String noTelp) {
        this.alamat = alamat;
        this.namaVenue = namaVenue;
        this.namaPemilik = namaPemilik;
        this.jamBukaTutup = jamBukaTutup;
        this.noTelp = noTelp;
        this.email = email;
    }

    public PenyediaProfil() {
    }

    public String getNamaVenue() {
        return namaVenue;
    }

    public void setNamaVenue(String namaVenue) {
        this.namaVenue = namaVenue;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNamaPemilik() {
        return namaPemilik;
    }

    public void setNamaPemilik(String namaPemilik) {
        this.namaPemilik = namaPemilik;
    }

    public String getJamBukaTutup() {
        return jamBukaTutup;
    }

    public void setJamBukaTutup(String jamBukaTutup) {
        this.jamBukaTutup = jamBukaTutup;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
