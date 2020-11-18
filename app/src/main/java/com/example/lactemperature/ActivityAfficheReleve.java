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
        String signe="";
//on va récupérer les trois valeurs provenant de NewReleveActivity
        Intent intent = getIntent();
        if (intent != null) {
            date = intent.getStringExtra("EXTRA_DATE");
            lac=intent.getStringExtra("EXTRA_LAC");
            signe=intent.getStringExtra("EXTRA_SIGNE");
        }

        TextView textdate = findViewById(R.id.textViewDate);
        textdate.setText(date);

        TextView textLac = findViewById(R.id.TextViewSaisieValiderNomLac);
        textLac.setText(lac);


        TextView textSigne6 = findViewById(R.id.TextViewReleveC6h);
        textSigne6.setText(signe);

        TextView textSigne12 = findViewById(R.id.TextViewReleveC12h);
        textSigne12.setText(signe);

        TextView textSigne18 = findViewById(R.id.TextViewReleveC18h);
        textSigne18.setText(signe);

        TextView textSigne24 = findViewById(R.id.TextViewReleveC24h);
        textSigne24.setText(signe);

        final DAOBdd lacbdd = new DAOBdd(this);
        lacbdd.open();
        String[] separated = date.split("/");

         unReleve = lacbdd.getTempByLacAndHeureAndDate(lac,Integer.valueOf(separated[0]),Integer.valueOf(separated[1]));

          double textTemp6h = unReleve.getTempA6h();

            double textTemp12h = unReleve.getTempA12h();

            double textTemp18h = unReleve.getTempA18h();

            double textTemp24h = unReleve.getTempA24h();

if(signe.equals("°F")) {
    textTemp6h = celsToFaren(textTemp6h);
    textTemp12h = celsToFaren(textTemp12h);
    textTemp18h = celsToFaren(textTemp18h);
    textTemp24h = celsToFaren(textTemp24h);
}

        TextView texteTemp6h = findViewById(R.id.textViewReleve6h);
        texteTemp6h.setText(String.valueOf(textTemp6h));
        TextView texteTemp12h = findViewById(R.id.textViewReleve12h);
        texteTemp12h.setText(String.valueOf(textTemp12h));
        TextView texteTemp18h = findViewById(R.id.textViewReleve18h);
        texteTemp18h.setText(String.valueOf(textTemp18h));
        TextView texteTemp24h = findViewById(R.id.textViewReleve24h);
        texteTemp24h.setText(String.valueOf(textTemp24h));


        // gestion des boutons
        Button buttonSaisieTemperatureRetour = findViewById(R.id.buttonRetour);
        //on place un écouteur dessus:
        View.OnClickListener ecouteur1 = new View.OnClickListener() {
            //on implémente la méthode onclick
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.buttonRetour:
                        Toast.makeText(getApplicationContext(), "Retour à la saisie", Toast.LENGTH_LONG).show();
                        finish();
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

