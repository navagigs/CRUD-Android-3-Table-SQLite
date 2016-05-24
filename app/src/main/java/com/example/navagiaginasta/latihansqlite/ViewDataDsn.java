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
public class ViewDataDsn extends ListActivity implements AdapterView.OnItemLongClickListener {

    private DBDataSourceDsn dataSource;

    private ArrayList<Dosen> values;
    private Button editButtonDsn;
    private Button delButtonDsn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewdatadsn);

        dataSource = new DBDataSourceDsn(this);

        try {
            dataSource.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        values= dataSource.getAllDosen();

        ArrayAdapter<Dosen> adapter = new ArrayAdapter<Dosen>(this,
                android.R.layout.simple_list_item_1, values);

        setListAdapter(adapter);

        ListView lv = (ListView) findViewById(android.R.id.list);
        lv.setOnItemLongClickListener(this);

    }

    //BUAT DELETE
    public boolean onItemLongClick (final AdapterView<?> adapter, View v, int pos, final long id) {
        final Dialog dialog= new Dialog(this);
        dialog.setContentView(R.layout.dialog_viewdsn);
        dialog.setTitle("Pilih Aksi");
        dialog.show();
        final Dosen b = (Dosen) getListAdapter().getItem(pos);
        editButtonDsn = (Button) dialog.findViewById(R.id.button_edit_dsn);
        delButtonDsn = (Button) dialog.findViewById(R.id.button_delete_dsn);

        editButtonDsn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switchToEdit(b.getId());
                        dialog.dismiss();
                    }
                }
        );
        delButtonDsn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dataSource.deletedosen(b.getId());
                        dialog.dismiss();
                        finish();
                        startActivity(getIntent());
                    }
                }
        );
        return true;
    }

    public void switchToEdit (long id) {
        Dosen b= dataSource.getDosen(id);
        Intent i = new Intent(this, EditDataDsn.class);
        Bundle bun = new Bundle();
        bun.putLong("id", b.getId());
        bun.putString("nama", b.getNama_dosen());
        bun.putString("nip", b.getNip_dosen());
        bun.putString("matkul", b.getMatkul_dosen());
        i.putExtras(bun);
        finale();
        startActivity(i);
    }

    public void finale() {
        ViewDataDsn.this.finish();
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

