package com.example.aldy.papado;

/**
 * Created by DBSS014 on 4/3/2018.
 */

public class PenyediaListJadwal {
    private String jam1;
    private String menit1;
    private String jam2;
    private String menit2;

    public PenyediaListJadwal(String jam1, String menit1, String jam2, String menit2) {
        this.jam1 = jam1;
        this.menit1 = menit1;
        this.jam2 = jam2;
        this.menit2 = menit2;
    }

    public String getJam1() {
        return jam1;
    }

    public String getMenit1() {
        return menit1;
    }

    public String getJam2() {
        return jam2;
    }

    public String getMenit2() {
        return menit2;
    }
}
