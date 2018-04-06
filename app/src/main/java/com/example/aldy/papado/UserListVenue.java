package com.example.aldy.papado;

/**
 * Created by DBSS014 on 4/5/2018.
 */

public class UserListVenue {
    String namavenue, alamatvenue, uid;

    public UserListVenue(String namavenue, String alamatvenue) {
        this.namavenue = namavenue;
        this.alamatvenue = alamatvenue;
    }

    public String getNamavenue() {
        return namavenue;
    }

    public String getAlamatvenue() {
        return alamatvenue;
    }

    public String getUid() {
        return uid;
    }
}
