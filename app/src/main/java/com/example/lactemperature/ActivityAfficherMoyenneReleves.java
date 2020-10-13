package com.example.lactemperature;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;


public class ActivityAfficherMoyenneReleves extends Activity {

    final String[] leLac= new String[1];
    final String[] leMois= new String[1];
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
                        Intent i = new Intent (ActivityAfficherMoyenneReleves.this, ActivityAfficheMoyenneReleve.class);
                        //i.putExtra("EXTRA_CPT",);
                        startActivityForResult(i,0);
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
        String[] leslacs = {"Lac Lément", "Etang de Berre", "Etang de Thau", "Etang de Vaccarès", "Lac d'Hourtin", "Lac de Grand-Lieu"};
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
        String[] lesMois = {"Janvier", "Fevrier", "Mars", "Avril", "Mai", "Juin", "Juillet", "Août", "Septembre", "Octobre", "Novembre", "Decembre"};
        ArrayAdapter<String> dataAdapterR2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lesMois);
        dataAdapterR2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAfficheMois.setAdapter(dataAdapterR2);
        spinnerAfficheMois.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                leLac[0] = String.valueOf(spinnerAfficheMois.getSelectedItem());
                Toast.makeText(ActivityAfficherMoyenneReleves.this, "Vous avez choisie : " + "\n" + leMois[0], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

}
