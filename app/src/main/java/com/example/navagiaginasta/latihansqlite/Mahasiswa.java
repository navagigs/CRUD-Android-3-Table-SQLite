package com.example.navagiaginasta.latihansqlite;

/**
 * Created by Nava Gigs on 5/19/2016.
 */
public class Mahasiswa {
    private long id;
    private String nama_mahasiswa;
    private String npm_mahasiswa;
    private String kelas_mahasiswa;

    public Mahasiswa()
    {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNama_mahasiswa() {
        return nama_mahasiswa;
    }

    public void setNama_mahasiswa (String nama_mahasiswa) {
        this.nama_mahasiswa = nama_mahasiswa;
    }

    public String getNpm_mahasiswa() {
        return npm_mahasiswa;
    }
    public void setNpm_mahasiswa (String npm_mahasiswa) {
        this.npm_mahasiswa = npm_mahasiswa;
    }

    public String getKelas_mahasiswa() {
        return kelas_mahasiswa;
    }

    public void setKelas_mahasiswa(String kelas_mahasiswa) {
        this.kelas_mahasiswa = kelas_mahasiswa;
    }

    @Override
    public String toString()
    {
        return "mahasiswa "+ nama_mahasiswa +" "+ npm_mahasiswa + " "+ kelas_mahasiswa;
    }
}
