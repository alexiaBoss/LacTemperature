package com.example.lactemperature;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import static java.lang.String.valueOf;

public class ActivitySupprLac  extends Activity {
    //decalration des variables
    final String[] leLac= new String[1];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suppr_lac);

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
                    //lors d'un clic sur valider
                    case R.id.buttonValiderMAJLac:
                        //redirection vers ActivityMAJLacs
                        Intent intent = new Intent(ActivitySupprLac.this, ActivityMAJLacs.class);

                        // recupération du lac séléctionner sous un objet LAC
                        Lac lac = lacbdd.getLacWithNomLac(leLac[0]);

                        //insertion des relevé du lac dans la table historique
                        lacbdd.insererHistorique(lac);
                        //suppression du lac
                        lacbdd.deleteLac(leLac[0]);


                        startActivity(intent);
                        Toast.makeText(getApplicationContext(), "Suppression du Lac", Toast.LENGTH_LONG).show();
                        break;
                        //si on clic sur retour
                    case R.id.buttonRetourMenuLac:
                        //retour au menu de la mise a jour des lacs
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
//ouverture de la bdd
        lacbdd.open();
        //recupération des données des lacs
        Cursor c = lacbdd.getDataLac();

        ArrayList<String> leslacs = new ArrayList<String>();
        if (c.moveToFirst()) {

            if (c.getCount() != 0) {

                //remplissage de la liste deroulantes des lacs
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
            //si un item est selectionner
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //on le recupére dans la variable leLac
                leLac[0] = String.valueOf(spinnerAfficheLac.getSelectedItem());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}
