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
import android.widget.Toast;

import java.util.ArrayList;


public class ActivityAfficherMoyenneReleves extends Activity {

    final String[] leLac = new String[1];
    final int[] leMois = new int[1];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afficher_moyenne_releves);

        Button buttonAfficherReleveValider = findViewById(R.id.buttonAfficherReleveValider);
        Button buttonAfficherReleveAnnuler = findViewById(R.id.buttonAfficherReleveAnnuler);

        //on place un écouteur dessus:
        View.OnClickListener ecouteur1 = new View.OnClickListener() {
            //on implémente la méthode onclick
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.buttonAfficherReleveValider:
                        // enregistrer les données dans la base
                        //on passer les infos dans l'autre interface
                        Intent i = new Intent(ActivityAfficherMoyenneReleves.this, ActivityAfficheMoyenneReleve.class);
                        i.putExtra("EXTRA_LAC",leLac[0]);
                        i.putExtra("EXTRA_MOIS",leMois[0]);


                        startActivityForResult(i, 0);
                        Toast.makeText(getApplicationContext(), "Ouverture de l'affichage des moyennes", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.buttonAfficherReleveAnnuler:
                        finish();
                        Toast.makeText(getApplicationContext(), "Annulation de l'affichage des moyennes", Toast.LENGTH_LONG).show();
                        break;


                }

            }
        };
        //on affecte l'écouteur aux boutons:
        buttonAfficherReleveValider.setOnClickListener(ecouteur1);
        buttonAfficherReleveAnnuler.setOnClickListener(ecouteur1);

        //gestion de la liste déroulante des Lacs
        final Spinner spinnerAfficheLac = (Spinner) findViewById(R.id.spinnerMoyenneLac);
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
            ArrayAdapter<String> dataAdapterR = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, leslacs);
            dataAdapterR.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerAfficheLac.setAdapter(dataAdapterR);
            spinnerAfficheLac.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    leLac[0] = String.valueOf(spinnerAfficheLac.getSelectedItem());
                    Toast.makeText(ActivityAfficherMoyenneReleves.this, "Vous avez choisie : " + "\n" + leLac[0], Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });


            //gestion de la liste déroulante des Mois
            final Spinner spinnerAfficheMois = (Spinner) findViewById(R.id.spinnerMoyenneMois);
            Integer[] lesMois = {1,2,3,4,5,6,7,8,9,10,11,12};
            ArrayAdapter<Integer> dataAdapterR2 = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, lesMois);
            dataAdapterR2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerAfficheMois.setAdapter(dataAdapterR2);
            spinnerAfficheMois.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    leMois[0] = Integer.valueOf((Integer) spinnerAfficheMois.getSelectedItem());
                    Toast.makeText(ActivityAfficherMoyenneReleves.this, "Vous avez choisi : " + "\n" + leMois[0], Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });


        }

    }

