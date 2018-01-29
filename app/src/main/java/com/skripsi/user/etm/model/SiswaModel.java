package com.skripsi.user.etm.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by USER on 1/16/2018.
 */

public class SiswaModel {

    @SerializedName("id_daftar_siswa")
    public int id;
    @SerializedName("etm_nama_siswa")
    public String nama;
    @SerializedName("etm_no_hp")
    public String nohp;
    @SerializedName("etm_email_siswa")
    public String email;
    @SerializedName("etm_alamat_siswa")
    public String alamat;
    @SerializedName("etm_kode_pos")
    public String kodepos;
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
    @SerializedName("etm_deskripsi_trilogi")
    public String deskripsi;
    @SerializedName("etm_tanggal_update")
    public String tgl;
    @SerializedName("etm_status_siswa")
    public String status;

    public SiswaModel(int id, String nama, String nohp, String email, String alamat, String kodepos, String asalSekolah, String kelas, String jurusan, String jurusanKuliah, String ptn, String pts, String deskripsi, String tgl, String status) {
        this.id = id;
        this.nama = nama;
        this.nohp = nohp;
        this.email = email;
        this.alamat = alamat;
        this.kodepos = kodepos;
        this.asalSekolah = asalSekolah;
        this.kelas = kelas;
        this.jurusan = jurusan;
        this.jurusanKuliah = jurusanKuliah;
        this.ptn = ptn;
        this.pts = pts;
        this.deskripsi = deskripsi;
        this.tgl = tgl;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNohp() {
        return nohp;
    }

    public void setNohp(String nohp) {
        this.nohp = nohp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getKodepos() {
        return kodepos;
    }

    public void setKodepos(String kodepos) {
        this.kodepos = kodepos;
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

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getTgl() {
        return tgl;
    }

    public void setTgl(String tgl) {
        this.tgl = tgl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
