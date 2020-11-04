package com.example.lactemperature;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static java.lang.String.valueOf;


public class ActivityListeLac extends Activity {

    final String[] leLac= new String[1];

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afficher_lac);
        Button buttonRetourListeLac = findViewById(R.id.buttonRetourListeLac);

        //on place un écouteur dessus:
        View.OnClickListener ecouteur1 = new View.OnClickListener() {
            //on implémente la méthode onclick
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.buttonRetourListeLac:
                        // enregistrer les données dans la base
                        //i.putExtra("EXTRA_CPT",);
                        Toast.makeText(getApplicationContext(), "Retour au menu", Toast.LENGTH_LONG).show();
                        finish();
                        Intent i = new Intent (ActivityListeLac.this, MainActivity.class);
                        startActivity(i);
                        break;
                }

            }

        };
        //on affecte l'écouteur aux boutons:
        buttonRetourListeLac.setOnClickListener(ecouteur1);

        //gestion de la liste déroulante des lacs
        final Spinner spinnerAfficheLac = (Spinner) findViewById(R.id.spinnerNomLacListeLac);
        final DAOBdd lacbdd = new DAOBdd(this);
        lacbdd.open();
        Cursor c = lacbdd.getDataLac();
        ArrayList<String> leslacs = new ArrayList<String>();
        if(c.moveToFirst()) {
            if (c.getCount() != 0) {

                while (!c.isAfterLast()) {
                    leslacs.add(c.getString(1)); //add the item
                    c.moveToNext();
                }
            }
        }
        ArrayAdapter<String> dataAdapterR = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, leslacs);
        dataAdapterR.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAfficheLac.setAdapter(dataAdapterR);
        spinnerAfficheLac.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                leLac[0] = valueOf(spinnerAfficheLac.getSelectedItem());
                Toast.makeText(ActivityListeLac.this, "Vous avez choisie : " + "\n" + leLac[0], Toast.LENGTH_SHORT).show();
                //Remplissage des champs pour la longitude et la latitude.
                Lac lac =lacbdd.getLacWithNomLac(leLac[0]);
                TextView longitude = findViewById(R.id.textViewLongitude);
                longitude.setText(valueOf(lac.getLongitude()));
                TextView latitude = findViewById(R.id.textViewLatitude);
                latitude.setText(valueOf(lac.getLatitude()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

}
