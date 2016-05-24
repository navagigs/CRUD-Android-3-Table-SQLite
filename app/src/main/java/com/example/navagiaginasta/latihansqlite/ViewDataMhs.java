package com.example.navagiaginasta.latihansqlite;

/**
 * Created by Nava Gigs on 5/19/2016.
 */
import android.app.Dialog;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.sql.SQLException;
import java.util.ArrayList;
public class ViewDataMhs extends ListActivity implements AdapterView.OnItemLongClickListener {

    private DBDataSourceMhs dataSource;

    private ArrayList<Mahasiswa> values;
    private Button editButtonMhs;
    private Button delButtonMhs;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewdatamhs);

        dataSource = new DBDataSourceMhs(this);

        try {
            dataSource.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        values= dataSource.getAllMahasiswa();

        ArrayAdapter<Mahasiswa> adapter = new ArrayAdapter<Mahasiswa>(this,
                android.R.layout.simple_list_item_1, values);

        setListAdapter(adapter);

        ListView lv = (ListView) findViewById(android.R.id.list);
        lv.setOnItemLongClickListener(this);

    }

    //BUAT DELETE
    public boolean onItemLongClick (final AdapterView<?> adapter, View v, int pos, final long id) {
        final Dialog dialog= new Dialog(this);
        dialog.setContentView(R.layout.dialog_viewmhs);
        dialog.setTitle("Pilih Aksi");
        dialog.show();
        final Mahasiswa b = (Mahasiswa) getListAdapter().getItem(pos);
        editButtonMhs = (Button) dialog.findViewById(R.id.button_edit_mhs);
        delButtonMhs = (Button) dialog.findViewById(R.id.button_delete_mhs);

        editButtonMhs.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switchToEdit(b.getId());
                        dialog.dismiss();
                    }
                }
        );
        delButtonMhs.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dataSource.deleteMahasiswa(b.getId());
                        dialog.dismiss();
                        finish();
                        startActivity(getIntent());
                    }
                }
        );
        return true;
    }

    public void switchToEdit (long id) {
        Mahasiswa b= dataSource.getMahasiswa(id);
        Intent i = new Intent(this, EditDataMhs.class);
        Bundle bun = new Bundle();
        bun.putLong("id", b.getId());
        bun.putString("nama", b.getNama_mahasiswa());
        bun.putString("npm", b.getNpm_mahasiswa());
        bun.putString("kelas", b.getKelas_mahasiswa());
        i.putExtras(bun);
        finale();
        startActivity(i);
    }

    public void finale() {
        ViewDataMhs.this.finish();
        dataSource.close();
    }

    @Override
    protected void onResume() {
        try {
            dataSource.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        super.onResume();
    }
    @Override
    protected void onPause() {
        dataSource.close();
        super.onPause();
    }

}

