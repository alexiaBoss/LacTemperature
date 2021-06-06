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
    //declaration des variables
    final String[] leLac= new String[1];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modif_lac);

        //ouverture de la bdd
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
                    //si on clic sur valider
                    case R.id.buttonValiderMAJLac:
                        //on est redriger vers le menu des la mises a jour des lacs
                        Intent intent = new Intent(ActivityModifierLac.this, ActivityMAJLacs.class);


                        //on récuprere les valeurs des champs lattitude et longitude
                        EditText textLatitude = findViewById(R.id.editTextLatitude);
                        Double latitude = Double.valueOf(textLatitude.getText().toString());

                        EditText textLongitude = findViewById(R.id.editTextLongitude);
                        Double longitude = Double.valueOf(textLongitude.getText().toString());


                        //on update le lac avec les valeur recuperer precedemment
                        lacbdd.updateLac(leLac[0], longitude, latitude);


                        startActivity(intent);
                        Toast.makeText(getApplicationContext(), "Enregistrement des données de la saisie", Toast.LENGTH_LONG).show();
                        break;
                        //si on clic sur retour
                    case R.id.buttonRetourMenuLac:
                        //on retourne au menu
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

        //recupereation des données des lacs
        Cursor c = lacbdd.getDataLac();
        ArrayList<String> leslacs = new ArrayList<String>();
        if (c.moveToFirst()) {

            if (c.getCount() != 0) {

                //remplissage de la liste avec les donnée des lacs
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
            //sio on selectionne un item
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //on recupere le lac selectionner dans leLac
                leLac[0] = String.valueOf(spinnerAfficheLac.getSelectedItem());
                //on recupère les donnée du lac
                Lac lac =lacbdd.getLacWithNomLac(leLac[0]);
                //Remplissage des champs pour la longitude et la latitude.
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
