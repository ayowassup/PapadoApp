package com.example.aldy.papado;

/**
 * Created by afridha on 04/04/18.
 */

public class Jadwal {
    String jamAwal;
    String jamAkhir;

    public Jadwal(String jamAwal, String jamAkhir) {
        this.jamAwal = jamAwal;
        this.jamAkhir = jamAkhir;
    }

    public String getJamAwal() {
        return jamAwal;
    }

    public void setJamAwal(String jamAwal) {
        this.jamAwal = jamAwal;
    }

    public String getJamAkhir() {
        return jamAkhir;
    }

    public void setJamAkhir(String jamAkhir) {
        this.jamAkhir = jamAkhir;
    }
}

