package com.example.lactemperature;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityAfficheReleve extends Activity {

    Releve unReleve = new Releve(0,0,0,0,0,0,null);

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affiche_releve_temperature);

        String date ="";
        String lac="";
        String temp="";
        String signe="";
//on va récupérer les trois valeurs provenant de NewReleveActivity
        Intent intent = getIntent();
        if (intent != null) {
            date = intent.getStringExtra("EXTRA_DATE");
            lac=intent.getStringExtra("EXTRA_LAC");
            temp=intent.getStringExtra("EXTRA_TEMP");
            signe=intent.getStringExtra("EXTRA_SIGNE");
        }

        TextView textdate = findViewById(R.id.textViewDate);
        textdate.setText(date);

        TextView textLac = findViewById(R.id.TextViewNomLac);
        textLac.setText(lac);

        TextView textHeure = findViewById(R.id.TextViewHeure);
        textHeure.setText(temp);

        TextView textSigne = findViewById(R.id.textViewSigne);
        textSigne.setText(signe);

        final DAOBdd lacbdd = new DAOBdd(this);
        lacbdd.open();
        String[] separated = date.split("/");

         unReleve = lacbdd.getTempByLacAndHeureAndDate(lac,Integer.valueOf(separated[0]),Integer.valueOf(separated[1]));
        double textTemp = 0;
        if (temp.equals("6")) {
             textTemp = unReleve.getTempA6h();
        }
        if (temp.equals("12")) {
             textTemp = unReleve.getTempA12h();
        }
        if (temp.equals("18")) {
             textTemp = unReleve.getTempA18h();
        }
        if (temp.equals("24")) {
             textTemp = unReleve.getTempA24h();
        }

        if(signe.equals("°F")){
            textTemp = celsToFaren(textTemp);
        }

        TextView texteTemp = findViewById(R.id.TextViewTemp);
        texteTemp.setText(String.valueOf(textTemp));







        // gestion des boutons
        Button buttonSaisieTemperatureRetour = findViewById(R.id.buttonRetour);
        //on place un écouteur dessus:
        View.OnClickListener ecouteur1 = new View.OnClickListener() {
            //on implémente la méthode onclick
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.buttonRetour:
                        // enregistrer les données dans la base

                        Toast.makeText(getApplicationContext(), "Retour au menu", Toast.LENGTH_LONG).show();
                        finish();
                        Intent i = new Intent (ActivityAfficheReleve.this, MainActivity.class);
                        startActivity(i);
                        break;
                }

            }
        };
        //on affecte l'écouteur aux boutons:
        buttonSaisieTemperatureRetour.setOnClickListener(ecouteur1);
    }
    public double celsToFaren(double tempCels){
       double tempFaren;
        tempFaren = tempCels*(9/5)+32;

        return tempFaren;
    }
}

