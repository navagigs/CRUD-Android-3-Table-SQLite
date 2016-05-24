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

public class DBDataSourceDsn {
    private SQLiteDatabase database;

    private DBHelperdsn dbHelperdsn;

    private String[] allColumns = { DBHelperdsn.COLUMN_ID, DBHelperdsn.COLUMN_NAME,
            DBHelperdsn.COLUMN_nip, DBHelperdsn.COLUMN_matkul};

    public DBDataSourceDsn(Context context) {
        dbHelperdsn = new DBHelperdsn(context);
    }

    public void open() throws SQLException {
        database = dbHelperdsn.getWritableDatabase();
    }

    public void close() {
        dbHelperdsn.close();
    }

    public Dosen createDosen(String nama, String nip, String matkul) {
        ContentValues values = new ContentValues();
        values.put(DBHelperdsn.COLUMN_NAME, nama);
        values.put(DBHelperdsn.COLUMN_nip, nip);
        values.put(DBHelperdsn.COLUMN_matkul, matkul);

        long insertId = database.insert(DBHelperdsn.TABLE_NAME, null, values);

        Cursor cursor = database.query(DBHelperdsn.TABLE_NAME,allColumns, DBHelperdsn.COLUMN_ID + " = "
                + insertId, null, null, null, null);

        cursor.moveToFirst();

        Dosen newdosen = cursorToDosen(cursor);

        cursor.close();

        return newdosen;
    }

    private Dosen cursorToDosen(Cursor cursor) {
        Dosen dosen = new Dosen();

        Log.v("info", "The getLONG " + cursor.getLong(0));
        Log.v("info", "The setLatLng" + cursor.getString(1) + "," + cursor.getString(2));

        dosen.setId(cursor.getLong(0));
        dosen.setNama_dosen(cursor.getString(1));
        dosen.setNip_dosen(cursor.getString(2));
        dosen.setMatkul_dosen(cursor.getString(3));

        return dosen;
    }

    public ArrayList<Dosen> getAllDosen() {
        ArrayList<Dosen> daftardosen = new ArrayList<Dosen>();

        Cursor cursor = database.query(DBHelperdsn.TABLE_NAME, allColumns, null,null,null,null,null);

        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            Dosen dosen = cursorToDosen(cursor);
            daftardosen.add(dosen);
            cursor.moveToNext();
        }
        cursor.close();
        return daftardosen;
    }

    public Dosen getDosen(long id) {
        Dosen dosen = new Dosen();


        Cursor cursor = database.query(DBHelperdsn.TABLE_NAME, allColumns, "_id="+id ,null,null,null,null);

        cursor.moveToFirst();

        dosen = cursorToDosen(cursor);

        cursor.close();
        return dosen;
    }


    public void updateDosen (Dosen b) {
        String strFilter = "_id=" + b.getId();

        ContentValues args = new ContentValues();

        args.put(DBHelperdsn.COLUMN_NAME, b.getNama_dosen());
        args.put(DBHelperdsn.COLUMN_nip, b.getNip_dosen());
        args.put(DBHelperdsn.COLUMN_matkul, b.getMatkul_dosen());

        database.update(DBHelperdsn.TABLE_NAME, args, strFilter, null);
    }

    public void deletedosen(long id) {
        String strFilter = "_id=" +id;
        database.delete(DBHelperdsn.TABLE_NAME, strFilter, null);
    }


}


