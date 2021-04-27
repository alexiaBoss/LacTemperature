package com.example.lactemperature;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import static java.lang.String.valueOf;

public class ActivitySupprLac  extends Activity {
    final String[] leLac= new String[1];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suppr_lac);

        //gestion de la liste déroulante des Lacs
        final Spinner spinnerAfficheLac = (Spinner) findViewById(R.id.spinnerNomLacListeLac);
        final DAOBdd lacbdd = new DAOBdd(this);
        lacbdd.open();
        Cursor c = lacbdd.getDataLac();
        ArrayList<String> leslacs = new ArrayList<String>();
        if (c.moveToFirst()) {

            if (c.getCount() != 0) {

                while (!c.isAfterLast()) {
                    leslacs.add(c.getString(1)); //add the item
                    c.moveToNext();
                }
            }
        }
        ArrayAdapter<String> dataAdapterR = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,leslacs);
        dataAdapterR.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAfficheLac.setAdapter(dataAdapterR);
        spinnerAfficheLac.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                leLac[0] = String.valueOf(spinnerAfficheLac.getSelectedItem());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}
