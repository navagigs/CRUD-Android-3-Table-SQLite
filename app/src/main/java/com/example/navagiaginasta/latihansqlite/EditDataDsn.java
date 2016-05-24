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

public class EditDataDsn extends Activity implements OnClickListener  {
    private DBDataSourceDsn dataSource;

    private long id;

    private String matkul;
    private String nip;
    private String nama;

    private EditText edNama;
    private EditText edMatkul;
    private EditText edNip;

    private TextView txId;

    private Button btnSave;
    private Button btnCancel;

    private Dosen dosen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_datadsn);

        edNama = (EditText) findViewById(R.id.editText_nama);
        edNip = (EditText) findViewById(R.id.editText_nip);
        edMatkul = (EditText) findViewById(R.id.editText_matkul);
        txId = (TextView) findViewById(R.id.text_id_dosen);

        dataSource = new DBDataSourceDsn(this);
        try {
            dataSource.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Bundle bun = this.getIntent().getExtras();
        id = bun.getLong("id");
        matkul=bun.getString("matkul");
        nip= bun.getString("nip");
        nama=bun.getString("nama");

        txId.append(String.valueOf(id));

        edNama.setText(nama);
        edMatkul.setText(matkul);
        edNip.setText(nip);

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
                dosen = new Dosen();
                dosen.setMatkul_dosen(edMatkul.getText().toString());
                dosen.setNama_dosen(edNama.getText().toString());
                dosen.setNip_dosen(edNip.getText().toString());

                dosen.setId(id);
                dataSource.updateDosen(dosen);
                Intent i = new Intent(this, ViewDataDsn.class);
                startActivity(i);
                EditDataDsn.this.finish();
                dataSource.close();
                break;

            case R.id.button_cancel_update:
                finish();
                dataSource.close();
                break;
        }
    }

}
