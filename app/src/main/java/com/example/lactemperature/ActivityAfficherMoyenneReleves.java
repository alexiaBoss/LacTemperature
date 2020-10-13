package com.example.lactemperature;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class ActivityAfficherMoyenneReleves extends Activity {

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
                        Intent i = new Intent (ActivityAfficherMoyenneReleves.this, AfficheReleve.class);
                        //i.putExtra("EXTRA_CPT",);
                        finish();
                        Toast.makeText(getApplicationContext(), "Ouverture de l'affichage", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.buttonAfficherReleveAnnuler:
                        finish();
                        Toast.makeText(getApplicationContext(), "Annulation de l'affichage", Toast.LENGTH_LONG).show();
                        break;


                }

            }
        };
        //on affecte l'écouteur aux boutons:
        buttonAfficherReleveValider.setOnClickListener(ecouteur1);
        buttonAfficherReleveAnnuler.setOnClickListener(ecouteur1);
    }

}
