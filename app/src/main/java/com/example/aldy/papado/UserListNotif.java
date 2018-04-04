package com.example.aldy.papado;

/**
 * Created by DBSS014 on 4/4/2018.
 */

public class UserListNotif {
    String namalap, tanggalpesan, jampesan, notelp, namapenyedia, keterangan;

    public UserListNotif(String namalap, String tanggalpesan, String jampesan, String notelp, String namapenyedia, String keterangan) {
        this.namalap = namalap;
        this.tanggalpesan = tanggalpesan;
        this.jampesan = jampesan;
        this.notelp = notelp;
        this.namapenyedia = namapenyedia;
        this.keterangan = keterangan;
    }

    public String getNamalap() {
        return namalap;
    }

    public String getTanggalpesan() {
        return tanggalpesan;
    }

    public String getJampesan() {
        return jampesan;
    }

    public String getNotelp() {
        return notelp;
    }

    public String getNamapenyedia() {
        return namapenyedia;
    }

    public String getKeterangan() {
        return keterangan;
    }
}
