package com.example.lactemperature;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityAfficheSaisieTemperature extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valider_saisie_temperature);

        String date = "";
        String lac = "";
        String temp = "";
        String temperature = "";
//on va récupérer les trois valeurs provenant de NewReleveActivity
        Intent intent = getIntent();
        if (intent != null) {
            date = intent.getStringExtra("EXTRA_DATE");
            lac = intent.getStringExtra("EXTRA_LAC");
            temp = intent.getStringExtra("EXTRA_TEMP");
            temperature = intent.getStringExtra("EXTRA_TEMPERATURE");
        }

        TextView textdate = findViewById(R.id.TextViewDate);
        textdate.setText(date);

        TextView textLac = findViewById(R.id.TextViewSaisieValiderNomLac);
        textLac.setText(lac);

        TextView textHeure = findViewById(R.id.TextViewSaisieValiderHeure);
        textHeure.setText(temp);

        TextView textTemperature = findViewById(R.id.TextViewTextPersonName3);
        textTemperature.setText(temperature);

        final DAOBdd lacbdd = new DAOBdd(this);
        lacbdd.open();
        String[] separated = date.split("/");
        final Releve unReleve = lacbdd.getReleveWithMoisAndJourAndLac(lac,Integer.valueOf(separated[0]),Integer.valueOf(separated[1]));

        // gestion des boutons
        Button buttonSaisieTemperatureValider = findViewById(R.id.buttonValiderSaisieTemperature);
        Button buttonSaisieTemperatureAnnuler = findViewById(R.id.buttonAnnulerSaisieTemperature);


        //on place un écouteur dessus:
        View.OnClickListener ecouteur1 = new View.OnClickListener() {
            //on implémente la méthode onclick
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.buttonValiderSaisieTemperature:
                        // enregistrer les données dans la base
                        


                        finish();
                        Toast.makeText(getApplicationContext(), "Enregistrement des données de la saisie", Toast.LENGTH_LONG).show();
                        finish();
                        Intent i = new Intent(ActivityAfficheSaisieTemperature.this, MainActivity.class);
                        startActivity(i);
                        break;
                    case R.id.buttonAnnulerSaisieTemperature:
                        finish();
                        Toast.makeText(getApplicationContext(), "Annulation de la saisie", Toast.LENGTH_LONG).show();
                        finish();
                        Intent i2 = new Intent(ActivityAfficheSaisieTemperature.this, ActivitySaisieTemperature.class);
                        startActivity(i2);
                        break;


                }

            }
        };
        //on affecte l'écouteur aux boutons:
        buttonSaisieTemperatureValider.setOnClickListener(ecouteur1);
        buttonSaisieTemperatureAnnuler.setOnClickListener(ecouteur1);


    }
}
