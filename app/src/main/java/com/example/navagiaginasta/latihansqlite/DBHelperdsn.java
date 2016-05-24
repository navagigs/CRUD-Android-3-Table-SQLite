package com.example.navagiaginasta.latihansqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Nava Gigs on 5/19/2016.
 */
public class DBHelperdsn extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "data_dosen";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "nama_dosen";
    public static final String COLUMN_nip = "nip_dosen";
    public static final String COLUMN_matkul = "matkul_dosen";
    private static final String db_name="dosen.db";
    private static final int db_version=1;

    private static final String db_create = "create table "
            + TABLE_NAME + " ("
            + COLUMN_ID +" integer primary key autoincrement, "
            + COLUMN_NAME+ " varchar(50) not null, "
            + COLUMN_nip+ " varchar(50) not null, "
            + COLUMN_matkul+ " varchar(50) not null);";

    public DBHelperdsn(Context context) {
        super(context, db_name, null, db_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(db_create);
    }

    @Override
    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DBHelper.class.getName(),"Upgrading database from version " + oldVersion + " to "
                + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
