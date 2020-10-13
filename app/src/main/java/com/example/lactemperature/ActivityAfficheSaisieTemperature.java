package com.example.lactemperature;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ActivityAfficheSaisieTemperature extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valider_saisie_temperature);
        // gestion des boutons
        Button buttonSaisieTemperatureValider = findViewById(R.id.buttonSaisieTemperatureValider);
        Button buttonSaisieTemperatureAnnuler = findViewById(R.id.buttonSaisieTemperatureAnnuler);


        //on place un écouteur dessus:
        View.OnClickListener ecouteur1 = new View.OnClickListener() {
            //on implémente la méthode onclick
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.buttonSaisieTemperatureValider:
                        // enregistrer les données dans la base
                        //i.putExtra("EXTRA_CPT",);
                        finish();
                        Toast.makeText(getApplicationContext(), "Enregistrement des données de la saisie", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.buttonSaisieTemperatureAnnuler:
                        finish();
                        Toast.makeText(getApplicationContext(), "Annulation de la saisie", Toast.LENGTH_LONG).show();
                        break;


                }

            }
        };
        //on affecte l'écouteur aux boutons:
        buttonSaisieTemperatureValider.setOnClickListener(ecouteur1);
        buttonSaisieTemperatureAnnuler.setOnClickListener(ecouteur1);

        //on va récupérer les valeurs provenant de ActivitySaisieTemperature
        //Intent intent = getIntent();
        //if (intent != null) {
            // cptlu= intent.getStringExtra("EXTRA_CPT");
            //hplu=intent.getStringExtra("EXTRA_HP");
            //hclu=intent.getStringExtra("EXTRA_HC");
            //raisonlu=intent.getStringExtra("EXTRA_RAISE");
        //}
    }
}
