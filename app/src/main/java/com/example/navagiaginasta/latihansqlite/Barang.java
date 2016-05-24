package com.example.navagiaginasta.latihansqlite;

/**
 * Created by Nava Gigs on 5/19/2016.
 */
public class Barang {
    private long id;
    private String nama_barang;
    private String merk_barang;
    private String harga_barang;

    public Barang()
    {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNama_barang() {
        return nama_barang;
    }

    public void setNama_barang (String nama_barang) {
        this.nama_barang = nama_barang;
    }

    public String getMerk_barang() {
        return merk_barang;
    }
    public void setMerk_barang (String merk_barang) {
        this.merk_barang = merk_barang;
    }

    public String getHarga_barang() {
        return harga_barang;
    }

    public void setHarga_barang(String harga_barang) {
        this.harga_barang = harga_barang;
    }

    @Override
    public String toString()
    {
        return "Barang "+ nama_barang +" "+ merk_barang + " "+ harga_barang;
    }
}
