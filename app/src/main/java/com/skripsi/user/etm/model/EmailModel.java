package com.skripsi.user.etm.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by USER on 2/20/2018.
 */

public class EmailModel {
    @SerializedName("id_emailmarketing")
    public int idt;
    @SerializedName("id_daftar_siswa")
    public int ids;
    @SerializedName("etm_nama_siswa")
    public String nama;
    @SerializedName("etm_email_siswa")
    public String email;
    @SerializedName("etm_asal_sekolah")
    public String asalSekolah;
    @SerializedName("etm_kelas_siswa")
    public String kelas;
    @SerializedName("etm_jurusan_sekolah")
    public String jurusan;
    @SerializedName("etm_jurusan_kuliah")
    public String jurusanKuliah;
    @SerializedName("etm_ptn_pilihan")
    public String ptn;
    @SerializedName("etm_pts_pilihan")
    public String pts;
    @SerializedName("etm_respon_1")
    public String respon1;
    @SerializedName("etm_respon_2")
    public String respon2;
    @SerializedName("etm_respon_3")
    public String respon3;

    public EmailModel(int idt, int ids, String nama, String email, String asalSekolah, String kelas, String jurusan, String jurusanKuliah, String ptn, String pts, String respon1, String respon2, String respon3) {
        this.idt = idt;
        this.ids = ids;
        this.nama = nama;
        this.email = email;
        this.asalSekolah = asalSekolah;
        this.kelas = kelas;
        this.jurusan = jurusan;
        this.jurusanKuliah = jurusanKuliah;
        this.ptn = ptn;
        this.pts = pts;
        this.respon1 = respon1;
        this.respon2 = respon2;
        this.respon3 = respon3;
    }

    public int getIdt() {
        return idt;
    }

    public void setIdt(int idt) {
        this.idt = idt;
    }

    public int getIds() {
        return ids;
    }

    public void setIds(int ids) {
        this.ids = ids;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAsalSekolah() {
        return asalSekolah;
    }

    public void setAsalSekolah(String asalSekolah) {
        this.asalSekolah = asalSekolah;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public String getJurusan() {
        return jurusan;
    }

    public void setJurusan(String jurusan) {
        this.jurusan = jurusan;
    }

    public String getJurusanKuliah() {
        return jurusanKuliah;
    }

    public void setJurusanKuliah(String jurusanKuliah) {
        this.jurusanKuliah = jurusanKuliah;
    }

    public String getPtn() {
        return ptn;
    }

    public void setPtn(String ptn) {
        this.ptn = ptn;
    }

    public String getPts() {
        return pts;
    }

    public void setPts(String pts) {
        this.pts = pts;
    }

    public String getRespon1() {
        return respon1;
    }

    public void setRespon1(String respon1) {
        this.respon1 = respon1;
    }

    public String getRespon2() {
        return respon2;
    }

    public void setRespon2(String respon2) {
        this.respon2 = respon2;
    }

    public String getRespon3() {
        return respon3;
    }

    public void setRespon3(String respon3) {
        this.respon3 = respon3;
    }
}
