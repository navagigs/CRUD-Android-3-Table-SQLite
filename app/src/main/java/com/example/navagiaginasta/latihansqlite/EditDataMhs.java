package com.example.navagiaginasta.latihansqlite;

/**
 * Created by Nava Gigs on 5/24/2016.
 */
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.SQLException;

public class EditDataMhs extends Activity implements OnClickListener  {
    private DBDataSourceMhs dataSource;

    private long id;

    private String kelas;
    private String npm;
    private String nama;

    private EditText edNama;
    private EditText edKelas;
    private EditText edNpm;

    private TextView txId;

    private Button btnSave;
    private Button btnCancel;

    private Mahasiswa mahasiswa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_datamhs);

        edNama = (EditText) findViewById(R.id.editText_nama);
        edNpm = (EditText) findViewById(R.id.editText_npm);
        edKelas = (EditText) findViewById(R.id.editText_kelas);
        txId = (TextView) findViewById(R.id.text_id_mahasiswa);

        dataSource = new DBDataSourceMhs(this);
        try {
            dataSource.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Bundle bun = this.getIntent().getExtras();
        id = bun.getLong("id");
        kelas=bun.getString("kelas");
        npm= bun.getString("npm");
        nama=bun.getString("nama");

        txId.append(String.valueOf(id));

        edNama.setText(nama);
        edKelas.setText(kelas);
        edNpm.setText(npm);

        btnSave = (Button) findViewById(R.id.button_save_update);
        btnSave.setOnClickListener(this);
        btnCancel = (Button) findViewById(R.id.button_cancel_update);
        btnCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.button_save_update:
                mahasiswa = new Mahasiswa();
                mahasiswa.setKelas_mahasiswa(edKelas.getText().toString());
                mahasiswa.setNama_mahasiswa(edNama.getText().toString());
                mahasiswa.setNpm_mahasiswa(edNpm.getText().toString());

                mahasiswa.setId(id);
                dataSource.updateMahasiswa(mahasiswa);
                Intent i = new Intent(this, ViewDataMhs.class);
                startActivity(i);
                EditDataMhs.this.finish();
                dataSource.close();
                break;

            case R.id.button_cancel_update:
                finish();
                dataSource.close();
                break;
        }
    }

}
