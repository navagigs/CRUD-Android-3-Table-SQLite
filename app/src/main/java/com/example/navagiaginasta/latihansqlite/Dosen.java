package com.example.navagiaginasta.latihansqlite;

/**
 * Created by Nava Gigs on 5/19/2016.
 */
public class Dosen {
    private long id;
    private String nama_dosen;
    private String nip_dosen;
    private String matkul_dosen;

    public Dosen()
    {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNama_dosen() {
        return nama_dosen;
    }

    public void setNama_dosen (String nama_dosen) {
        this.nama_dosen = nama_dosen;
    }

    public String getNip_dosen() {
        return nip_dosen;
    }
    public void setNip_dosen (String nip_dosen) {
        this.nip_dosen = nip_dosen;
    }

    public String getMatkul_dosen() {
        return matkul_dosen;
    }

    public void setMatkul_dosen(String matkul_dosen) {
        this.matkul_dosen = matkul_dosen;
    }

    @Override
    public String toString()
    {
        return "dosen "+ nama_dosen +" "+ nip_dosen + " "+ matkul_dosen;
    }
}
