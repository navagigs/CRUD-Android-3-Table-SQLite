package com.example.navagiaginasta.latihansqlite;

/**
 * Created by Nava Gigs on 5/19/2016.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.sql.SQLException;
import java.util.ArrayList;

public class DBDataSource {
    //Inisialisasi SQLite Database
    private SQLiteDatabase database;

    //Inisialisasi kelas DBHelper
    private DBHelper dbHelper;

    //Ambil semua nama kolom
    private String[] allColumns = { DBHelper.COLUMN_ID, DBHelper.COLUMN_NAME,
            DBHelper.COLUMN_MERK, DBHelper.COLUMN_HARGA};

    //DBHelper diinstantiasi pada constructor
    public DBDataSource(Context context) {
        dbHelper = new DBHelper(context);
    }

    //membuka atau membuat sambungan baru ke database
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    //menutup sambungan ke database
    public void close() {
        dbHelper.close();
    }

    //method untuk create/insert barang ke database
    public Barang createBarang(String nama, String merk, String harga) {
       //Membuat sebuah Content Values, yang berfungsi
        //untuk memasangkan data dengan nama
        //kolom pada database
        ContentValues values = new ContentValues();
        values.put(DBHelper.COLUMN_NAME, nama);
        values.put(DBHelper.COLUMN_MERK, merk);
        values.put(DBHelper.COLUMN_HARGA, harga);

        //mengeksekusi perinta SQL insert data
        //yang akan mengembalikan sebuah insert ID
        long insertId = database.insert(DBHelper.TABLE_NAME, null, values);

        //setelah data dimasukan, memanggil
        //perintah SQL select menggunakan Cursor untuk
        //melihat apakah data tadi benar2 sudah masuk
        //dengan menyesuakain ID= insertID
        Cursor cursor = database.query(DBHelper.TABLE_NAME,allColumns, DBHelper.COLUMN_ID + " = "
                + insertId, null, null, null, null);
        //Pindah ke data paling pertama
        cursor.moveToFirst();

        //mengubah objek pada kursor pertama tadi
        //ke dalam objek barang
        Barang newBarang = cursorToBarang(cursor);

        //close cursor
        cursor.close();

        //mengembalikan barang baru
        return newBarang;
    }

    private Barang cursorToBarang(Cursor cursor) {
        //buat objek barang baru
        Barang barang = new Barang();
        //debug logcat
        Log.v("info", "The getLONG " + cursor.getLong(0));
        Log.v("info", "The setLatLng" + cursor.getString(1) + "," + cursor.getString(2));

        //set atribut pada objek barang dengan
        //data kursor yang diambil dari database
        barang.setId(cursor.getLong(0));
        barang.setNama_barang(cursor.getString(1));
        barang.setMerk_barang(cursor.getString(2));
        barang.setHarga_barang(cursor.getString(3));
        //kembalikan sebagai objek barang
        return barang;
    }

    public ArrayList<Barang> getAllBarang() {
        ArrayList<Barang> daftarBarang = new ArrayList<Barang>();

        Cursor cursor = database.query(DBHelper.TABLE_NAME, allColumns, null,null,null,null,null);

        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            Barang barang = cursorToBarang(cursor);
            daftarBarang.add(barang);
            cursor.moveToNext();
        }
        cursor.close();
        return daftarBarang;
    }

    public Barang getBarang(long id) {
        Barang barang = new Barang();


        Cursor cursor = database.query(DBHelper.TABLE_NAME, allColumns, "_id="+id ,null,null,null,null);

        cursor.moveToFirst();

        barang = cursorToBarang(cursor);

        cursor.close();
        return barang;
    }


    public void updateBarang (Barang b) {
        String strFilter = "_id=" + b.getId();

        ContentValues args = new ContentValues();

        args.put(DBHelper.COLUMN_NAME, b.getNama_barang());
        args.put(DBHelper.COLUMN_MERK, b.getMerk_barang());
        args.put(DBHelper.COLUMN_HARGA, b.getHarga_barang());

        database.update(DBHelper.TABLE_NAME, args, strFilter, null);
    }

    public void deleteBarang(long id) {
        String strFilter = "_id=" +id;
        database.delete(DBHelper.TABLE_NAME, strFilter, null);
    }


}

