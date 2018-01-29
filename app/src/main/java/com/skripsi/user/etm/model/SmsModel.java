package com.skripsi.user.etm.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by USER on 1/22/2018.
 */

public class SmsModel {
    @SerializedName("id_jadwal")
    public int id;
    @SerializedName("etm_tanggal_sms")
    public String tanggal;
    @SerializedName("etm_deskripsi_sms")
    public String konten;
    @SerializedName("etm_status_jadwal")
    public String status;

    public SmsModel(int id, String tanggal, String konten, String status) {
        this.id = id;
        this.tanggal = tanggal;
        this.konten = konten;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getKonten() {
        return konten;
    }

    public void setKonten(String konten) {
        this.konten = konten;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
