package com.example.navagiaginasta.latihansqlite;

/**
 * Created by Nava Gigs on 5/19/2016.
 */
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.view.View.OnClickListener;
import android.widget.Toast;

import java.sql.SQLException;

public class CreateData extends Activity implements OnClickListener {

    private Button buttonSubmit;
    private EditText edNama;
    private EditText edMerk;
    private EditText edHarga;

    private DBDataSource dataSource;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView(R.layout.create_data);

        //Inisialisasi elemen2 pada layout
        buttonSubmit = (Button) findViewById(R.id.button_submit);
        buttonSubmit.setOnClickListener(this);
        edNama = (EditText) findViewById(R.id.nama_barang);
        edMerk = (EditText) findViewById(R.id.merk_barang);
        edHarga = (EditText) findViewById(R.id.harga_barang);

        //inisialisasi kelas DBDataSource
        dataSource = new DBDataSource(this);
        try {
            //membuat sambungan baru ke database
            dataSource.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Ketika tombol submit diklik
    @Override
    public void onClick(View v) {
        String nama = null;
        String merk = null;
        String harga = null;
        @SuppressWarnings("unused")

        Barang barang = null;
        if(edNama.getText() !=null && edMerk.getText() != null && edHarga.getText() !=null) {
            //Jika field kosong validasi
            nama = edNama.getText().toString();
            merk = edMerk.getText().toString();
            harga = edHarga.getText().toString();
        }

        switch (v.getId()) {
            case R.id.button_submit:
                //insert data barang baru
                barang= dataSource.createBarang(nama, merk, harga);

                //konfimasi bila bershasil ditambahkan
                Toast.makeText(this, "masuk barang\n" +
                                "nama :" + barang.getNama_barang() +
                                " merk :" + barang.getMerk_barang() +
                                " harga :" + barang.getHarga_barang(),
                        Toast.LENGTH_LONG) .show();
                break;
        }
    }
}
