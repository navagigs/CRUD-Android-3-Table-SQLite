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

public class CreateDataDsn extends Activity implements OnClickListener {

    private Button buttonSubmit;
    private EditText edNama;
    private EditText edNip;
    private EditText edMatkul;

    private DBDataSourceDsn dataSource;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView(R.layout.create_datadsn);

        buttonSubmit = (Button) findViewById(R.id.button_AddDsn);
        buttonSubmit.setOnClickListener(this);
        edNama = (EditText) findViewById(R.id.nama_dosen);
        edNip = (EditText) findViewById(R.id.nip_dosen);
        edMatkul = (EditText) findViewById(R.id.matkul_dosen);

        dataSource = new DBDataSourceDsn(this);
        try {
            dataSource.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        String nama = null;
        String nip = null;
        String matkul = null;
        @SuppressWarnings("unused")

        Dosen dosen = null;
        if(edNama.getText() !=null && edNip.getText() != null && edMatkul.getText() !=null) {
            nama = edNama.getText().toString();
            nip = edNip.getText().toString();
            matkul = edMatkul.getText().toString();
        }

        switch (v.getId()) {
            case R.id.button_AddDsn:

                dosen= dataSource.createDosen(nama, nip, matkul);

                Toast.makeText(this, "masuk dosen\n" +
                                "nama :" + dosen.getNama_dosen() +
                                " nip :" + dosen.getNip_dosen() +
                                " matkul :" + dosen.getMatkul_dosen(),
                        Toast.LENGTH_LONG) .show();
                break;
        }
    }
}
