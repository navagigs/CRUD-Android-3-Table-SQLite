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
public class ViewData extends ListActivity implements AdapterView.OnItemLongClickListener {

    private DBDataSource dataSource;

    private ArrayList<Barang> values;
    private Button editButton;
    private Button delButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewdata);

        dataSource = new DBDataSource(this);

        try {
            dataSource.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        values= dataSource.getAllBarang();

        ArrayAdapter<Barang> adapter = new ArrayAdapter<Barang>(this,
                android.R.layout.simple_list_item_1, values);

        setListAdapter(adapter);

        ListView lv = (ListView) findViewById(android.R.id.list);
        lv.setOnItemLongClickListener(this);

    }

    public boolean onItemLongClick (final AdapterView<?> adapter, View v, int pos, final long id) {
        final Dialog dialog= new Dialog(this);
        dialog.setContentView(R.layout.dialog_view);
        dialog.setTitle("Pilih Aksi");
        dialog.show();
        final Barang b = (Barang) getListAdapter().getItem(pos);
        editButton = (Button) dialog.findViewById(R.id.button_edit_data);
        delButton = (Button) dialog.findViewById(R.id.button_delete_data);

        editButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switchToEdit(b.getId());
                        dialog.dismiss();
                    }
                }
        );
        delButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dataSource.deleteBarang(b.getId());
                        dialog.dismiss();
                        finish();
                        startActivity(getIntent());
                    }
                }
        );
        return true;
    }

    public void switchToEdit (long id) {
        Barang b= dataSource.getBarang(id);
        Intent i = new Intent(this, EditData.class);
        Bundle bun = new Bundle();
        bun.putLong("id", b.getId());
        bun.putString("nama", b.getNama_barang());
        bun.putString("merk", b.getMerk_barang());
        bun.putString("harga", b.getHarga_barang());
        i.putExtras(bun);
        finale();
        startActivity(i);
    }

    public void finale() {
        ViewData.this.finish();
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
