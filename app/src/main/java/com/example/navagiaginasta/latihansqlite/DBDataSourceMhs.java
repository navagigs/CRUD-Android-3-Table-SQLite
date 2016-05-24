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

public class DBDataSourceMhs {
    private SQLiteDatabase database;

    private DBHelpermhs dbHelpermhs;

    private String[] allColumns = { DBHelpermhs.COLUMN_ID, DBHelpermhs.COLUMN_NAME,
            DBHelpermhs.COLUMN_npm, DBHelpermhs.COLUMN_kelas};

    public DBDataSourceMhs(Context context) {
        dbHelpermhs = new DBHelpermhs(context);
    }

    public void open() throws SQLException {
        database = dbHelpermhs.getWritableDatabase();
    }

    public void close() {
        dbHelpermhs.close();
    }

    public Mahasiswa createMahasiswa(String nama, String npm, String kelas) {
        ContentValues values = new ContentValues();
        values.put(DBHelpermhs.COLUMN_NAME, nama);
        values.put(DBHelpermhs.COLUMN_npm, npm);
        values.put(DBHelpermhs.COLUMN_kelas, kelas);

        long insertId = database.insert(DBHelpermhs.TABLE_NAME, null, values);

        Cursor cursor = database.query(DBHelpermhs.TABLE_NAME,allColumns, DBHelpermhs.COLUMN_ID + " = "
                + insertId, null, null, null, null);

        cursor.moveToFirst();

        Mahasiswa newMahasiswa = cursorToMahasiswa(cursor);

        cursor.close();

        return newMahasiswa;
    }

    private Mahasiswa cursorToMahasiswa(Cursor cursor) {
      Mahasiswa mahasiswa = new Mahasiswa();

        Log.v("info", "The getLONG " + cursor.getLong(0));
        Log.v("info", "The setLatLng" + cursor.getString(1) + "," + cursor.getString(2));

        mahasiswa.setId(cursor.getLong(0));
        mahasiswa.setNama_mahasiswa(cursor.getString(1));
        mahasiswa.setNpm_mahasiswa(cursor.getString(2));
        mahasiswa.setKelas_mahasiswa(cursor.getString(3));

        return mahasiswa;
    }

    public ArrayList<Mahasiswa> getAllMahasiswa() {
        ArrayList<Mahasiswa> daftarmahasiswa = new ArrayList<Mahasiswa>();

        Cursor cursor = database.query(DBHelpermhs.TABLE_NAME, allColumns, null,null,null,null,null);

        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            Mahasiswa mahasiswa = cursorToMahasiswa(cursor);
            daftarmahasiswa.add(mahasiswa);
            cursor.moveToNext();
        }
        cursor.close();
        return daftarmahasiswa;
    }

    public Mahasiswa getMahasiswa(long id) {
        Mahasiswa mahasiswa = new Mahasiswa();


        Cursor cursor = database.query(DBHelpermhs.TABLE_NAME, allColumns, "_id="+id ,null,null,null,null);

        cursor.moveToFirst();

        mahasiswa = cursorToMahasiswa(cursor);

        cursor.close();
        return mahasiswa;
    }


    public void updateMahasiswa (Mahasiswa b) {
        String strFilter = "_id=" + b.getId();

        ContentValues args = new ContentValues();

        args.put(DBHelpermhs.COLUMN_NAME, b.getNama_mahasiswa());
        args.put(DBHelpermhs.COLUMN_npm, b.getNpm_mahasiswa());
        args.put(DBHelpermhs.COLUMN_kelas, b.getKelas_mahasiswa());

        database.update(DBHelpermhs.TABLE_NAME, args, strFilter, null);
    }

    public void deleteMahasiswa(long id) {
        String strFilter = "_id=" +id;
        database.delete(DBHelpermhs.TABLE_NAME, strFilter, null);
    }


}

