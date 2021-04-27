package com.example.lactemperature;

import android.app.Activity;
import android.database.Cursor;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import static java.lang.String.valueOf;

public class ActivityModifierLac  extends Activity {
    final String[] leLac= new String[1];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modif_lac);

        final DAOBdd lacbdd = new DAOBdd(this);
        lacbdd.open();

        //on associe à un objet java de type Button, un widget repéré physiquement par son id:
        Button buttonRetourMenuLac = findViewById(R.id.buttonRetourMenuLac);
        Button buttonValiderMAJLac = (Button) findViewById(R.id.buttonValiderMAJLac);

        //on place un écouteur dessus:
        View.OnClickListener ecouteur1 = new View.OnClickListener() {
            //on implémente la méthode onclick
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.buttonValiderMAJLac:
                        Intent intent = new Intent(ActivityModifierLac.this, ActivityMAJLacs.class);


                        EditText textLatitude = findViewById(R.id.editTextLatitude);
                        Double latitude = Double.valueOf(textLatitude.getText().toString());

                        EditText textLongitude = findViewById(R.id.editTextLongitude);
                        Double longitude = Double.valueOf(textLongitude.getText().toString());


                        lacbdd.updateLac(leLac[0], longitude, latitude);


                        startActivity(intent);
                        Toast.makeText(getApplicationContext(), "Enregistrement des données de la saisie", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.buttonRetourMenuLac:
                        finish();
                        Toast.makeText(getApplicationContext(), "Retour", Toast.LENGTH_LONG).show();
                        finish();
                        break;

                }

            }
        };
        //on affecte l'écouteur aux boutons:
        buttonRetourMenuLac.setOnClickListener(ecouteur1);
        buttonValiderMAJLac.setOnClickListener(ecouteur1);


        //gestion de la liste déroulante des Lacs
        final Spinner spinnerAfficheLac = (Spinner) findViewById(R.id.spinnerNomLacListeLac);

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
                //Remplissage des champs pour la longitude et la latitude.
                Lac lac =lacbdd.getLacWithNomLac(leLac[0]);
                TextView longitude = findViewById(R.id.editTextLongitude);
                longitude.setText(valueOf(lac.getLongitude()));
                TextView latitude = findViewById(R.id.editTextLatitude);
                latitude.setText(valueOf(lac.getLatitude()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}
