package com.example.aldy.papado;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by DBSS014 on 4/4/2018.
 */

public class UserListJadwal {
    private String jam1;
    private String jam2;

    public UserListJadwal(String jam1, String jam2) {
        this.jam1 = jam1;
        this.jam2 = jam2;
    }

    public String getJam1() {return jam1; }

    public String getJam2() {
        return jam2;
    }
}
