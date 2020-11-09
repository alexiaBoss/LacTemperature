package com.example.lactemperature;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.DecimalFormat;
import java.util.ArrayList;


public class ActivityAfficheMoyenneReleve extends Activity{
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affiche_moyenne_releve_temperature);

        String lac = "";
        int mois = 0;
        String signe ="";

//on va récupérer des valeur de AfficherMoyenneReleves
        Intent intent = getIntent();
        if (intent != null) {
            lac = intent.getStringExtra("EXTRA_LAC");
            mois = intent.getIntExtra("EXTRA_MOIS", 0);
            signe = intent.getStringExtra("EXTRA_SIGNE");
        }

        TextView textLac = findViewById(R.id.TextViewMoyenneLac);
        textLac.setText(lac);

        TextView textSigne6 = findViewById(R.id.TextViewMoyenneC6h);
        textSigne6.setText(signe);

        TextView textSigne12 = findViewById(R.id.TextViewMoyenneC12h);
        textSigne12.setText(signe);

        TextView textSigne18 = findViewById(R.id.TextViewMoyenneC18h);
        textSigne18.setText(signe);

        TextView textSigne24 = findViewById(R.id.TextViewMoyenneC24h);
        textSigne24.setText(signe);


        final DAOBdd lacbdd = new DAOBdd(this);
        lacbdd.open();
        ArrayList<Releve> lesReleves = new ArrayList<Releve>();
        lesReleves = lacbdd.getReleveWithMoisAndLac(lac, mois);
        int i = 0;
        ArrayList<Double> lesTemperatures = new ArrayList();
        ArrayList<Double> lesTemperatures6h = new ArrayList();
        ArrayList<Double> lesTemperatures12h = new ArrayList();
        ArrayList<Double> lesTemperatures18h = new ArrayList();
        ArrayList<Double> lesTemperatures24h = new ArrayList();

        while (i < lesReleves.size()) {
            Releve unReleve = new Releve(0, 0, 0, 0, 0, 0, null);
            unReleve = lesReleves.get(i);
            //moyenne générale
            lesTemperatures.add(unReleve.getTempA6h());
            lesTemperatures.add(unReleve.getTempA12h());
            lesTemperatures.add(unReleve.getTempA18h());
            lesTemperatures.add(unReleve.getTempA24h());
            //moyenne par tranche horaire
            lesTemperatures6h.add(unReleve.getTempA6h());
            lesTemperatures12h.add(unReleve.getTempA12h());
            lesTemperatures18h.add(unReleve.getTempA18h());
            lesTemperatures24h.add(unReleve.getTempA24h());
            i++;
        }

        int i2;
        double sumGeneral = 0;
        for (i2 = 0; i2 < lesTemperatures.size(); i2++) {
            sumGeneral += lesTemperatures.get(i2);
        }
        double moyenneGeneral = sumGeneral / (lesTemperatures.size());
        DecimalFormat f = new DecimalFormat();
        f.setMaximumFractionDigits(2);

        f.format(moyenneGeneral);



        int i3;
        double sum6h = 0;
        for (i3 = 0; i3 < lesTemperatures6h.size(); i3++) {
            sum6h += lesTemperatures6h.get(i3);
        }
        double moyenne6h = sum6h / (lesTemperatures6h.size());


        f.format(moyenne6h);


        int i4;
        double sum12h = 0;
        for (i4 = 0; i4 < lesTemperatures12h.size(); i4++) {
            sum12h += lesTemperatures12h.get(i4);
        }
        double moyenne12h = sum12h / (lesTemperatures12h.size());


        f.format(moyenne12h);



        int i5;
        double sum18h = 0;
        for (i5 = 0; i5 < lesTemperatures18h.size(); i5++) {
            sum18h += lesTemperatures18h.get(i5);
        }
        double moyenne18h = sum18h / (lesTemperatures18h.size());


        f.format(moyenne18h);




        int i6;
        double sum24h = 0;
        for (i6 = 0; i6 < lesTemperatures24h.size(); i6++) {
            sum24h += lesTemperatures24h.get(i6);
        }
        double moyenne24h = sum24h / (lesTemperatures24h.size());


        f.format(moyenne24h);



        if(signe.equals("°F")) {
            moyenne6h = celsToFaren(moyenne6h);
            moyenne12h = celsToFaren(moyenne12h);
            moyenne18h = celsToFaren(moyenne18h);
            moyenne24h = celsToFaren(moyenne24h);
            moyenneGeneral = celsToFaren(moyenneGeneral);
        }

        TextView textMoyenne24h = findViewById(R.id.textViewMoyenne24h);
        textMoyenne24h.setText(f.format(moyenne24h));

        TextView textMoyenne18h = findViewById(R.id.textViewMoyenne18h);
        textMoyenne18h.setText(f.format(moyenne18h));

        TextView textMoyenne12h = findViewById(R.id.textViewMoyenne12h);
        textMoyenne12h.setText(f.format(moyenne12h));

        TextView textMoyenne6h = findViewById(R.id.textViewMoyenne6h);
        textMoyenne6h.setText(f.format(moyenne6h));

        TextView textMoyenneGeneral = findViewById(R.id.TextViewMoyenneReleveReponse);
        textMoyenneGeneral.setText(f.format(moyenneGeneral));

        // gestion des boutons
        Button buttonSaisieTemperatureValider = findViewById(R.id.buttonRetour);


        //on place un écouteur dessus:
        View.OnClickListener ecouteur1 = new View.OnClickListener() {
            //on implémente la méthode onclick
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.buttonRetour:
                        // enregistrer les données dans la base
                        //i.putExtra("EXTRA_CPT",);
                        Toast.makeText(getApplicationContext(), "Retour au menu", Toast.LENGTH_LONG).show();
                        finish();
                        Intent i = new Intent(ActivityAfficheMoyenneReleve.this, MainActivity.class);
                        startActivity(i);
                        break;
                }

            }
        };
        //on affecte l'écouteur aux boutons:
        buttonSaisieTemperatureValider.setOnClickListener(ecouteur1);




        // Affichage du graphique de la moyenne
            int count = lesTemperatures.size();
            DataPoint[] values = new DataPoint[count];
            for (int i7=0; i7<count; i7++) {
                double x = i7;
                double y = lesTemperatures.get(i7);
                if (signe.equals("°F")) {
                        y = celsToFaren(y);
                    }

                DataPoint v = new DataPoint(x, y);
                values[i7] = v;
            }

        GraphView graph = (GraphView) findViewById(R.id.graph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(values);



        graph.addSeries(series);
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxX(lesTemperatures.size());


    }
    public double celsToFaren(double tempCels){
        double tempFaren;
        tempFaren = tempCels*(9/5)+32;

        return tempFaren;
    }
}


