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

public class CreateDataMhs extends Activity implements OnClickListener {

    private Button buttonSubmit;
    private EditText edNama;
    private EditText edNpm;
    private EditText edKelas;

    private DBDataSourceMhs dataSource;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView(R.layout.create_datamhs); //layout

        buttonSubmit = (Button) findViewById(R.id.button_AddMhs);
        buttonSubmit.setOnClickListener(this);
        edNama = (EditText) findViewById(R.id.nama_mahasiswa);
        edNpm = (EditText) findViewById(R.id.npm_mahasiswa);
        edKelas = (EditText) findViewById(R.id.kelas_mahasiswa);

        dataSource = new DBDataSourceMhs(this);
        try {
            dataSource.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        String nama = null;
        String npm = null;
        String kelas = null;
        @SuppressWarnings("unused")

        Mahasiswa Mahasiswa = null;
        if(edNama.getText() !=null && edNpm.getText() != null && edKelas.getText() !=null) {
            nama = edNama.getText().toString();
            npm = edNpm.getText().toString();
            kelas = edKelas.getText().toString();
        }

        switch (v.getId()) {
            case R.id.button_AddMhs:

                    Mahasiswa= dataSource.createMahasiswa(nama, npm, kelas);

                Toast.makeText(this, "masuk mahasiswa\n" +
                                "nama :" + Mahasiswa.getNama_mahasiswa() +
                                " npm :" + Mahasiswa.getNpm_mahasiswa() +
                                " kelas :" + Mahasiswa.getKelas_mahasiswa(),
                        Toast.LENGTH_LONG) .show();
                break;
        }
    }
}
