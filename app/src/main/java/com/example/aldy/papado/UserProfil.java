package com.example.aldy.papado;

/**
 * Created by afridha on 06/04/18.
 */

public class UserProfil {
    private String nama, email, telepon, kategori, password, address;

    public UserProfil() {
    }

    public UserProfil(String nama, String email, String telepon, String kategori, String password, String address) {
        this.nama = nama;
        this.email = email;
        this.telepon = telepon;
        this.kategori = kategori;
        this.password = password;
        this.address = address;
    }

    public String getNama() {
        return nama;
    }

    public String getEmail() {
        return email;
    }

    public String getTelepon() {
        return telepon;
    }

    public String getKategori() {
        return kategori;
    }

    public String getPassword() {
        return password;
    }

    public String getAddress() {
        return address;
    }
}
