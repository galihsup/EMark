package com.skripsi.user.etm.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by USER on 2/28/2018.
 */

public class ListRelasi {
    @SerializedName("etm_asal_sekolah")
    public String nama;

    public ListRelasi(String nama) {
        this.nama = nama;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
}
