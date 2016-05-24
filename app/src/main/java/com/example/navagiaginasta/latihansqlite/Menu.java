package com.example.navagiaginasta.latihansqlite;

/**
 * Created by Nava Gigs on 5/19/2016.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;

public class Menu extends Activity implements OnClickListener {

    private Button bTambah;
    private Button bLihat;

    private Button bTambahMhs;
    private Button bLihatMhs;

    private Button bTambahDsn;
    private Button bLihatDsn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        bTambah = (Button) findViewById(R.id.button_tambah);
        bTambah.setOnClickListener(this);
        bLihat = (Button) findViewById(R.id.button_view);
        bLihat.setOnClickListener(this);

        bTambahMhs = (Button) findViewById(R.id.button_tambahMhs);
        bTambahMhs.setOnClickListener(this);
        bLihatMhs = (Button) findViewById(R.id.button_viewMhs);
        bLihatMhs.setOnClickListener(this);

        bTambahDsn = (Button) findViewById(R.id.button_tambahDsn);
        bTambahDsn.setOnClickListener(this);
        bLihatDsn = (Button) findViewById(R.id.button_viewDsn);
        bLihatDsn.setOnClickListener(this);
    }

    //Mahasiswa
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.button_tambah :
                Intent i = new Intent(this, CreateData.class);
                startActivity(i);
                break;
            case R.id.button_view :
                Intent i2 = new Intent(this, ViewData.class);
                startActivity(i2);
                break;

            case R.id.button_tambahMhs :
                Intent i3 = new Intent(this, CreateDataMhs.class);
                startActivity(i3);
                break;
            case R.id.button_viewMhs :
                Intent i4 = new Intent(this, ViewDataMhs.class);
                startActivity(i4);
                break;


            case R.id.button_tambahDsn :
                Intent i5 = new Intent(this, CreateDataDsn.class);
                startActivity(i5);
                break;
            case R.id.button_viewDsn :
                Intent i6 = new Intent(this, ViewDataDsn.class);
                startActivity(i6);
                break;

        }
    }




}
