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

//on va récupérer des valeur de AfficherMoyenneReleves
        Intent intent = getIntent();
        if (intent != null) {
            lac = intent.getStringExtra("EXTRA_LAC");
            mois = intent.getIntExtra("EXTRA_MOIS", 0);
        }

        TextView textLac = findViewById(R.id.TextViewMoyenneLac);
        textLac.setText(lac);


        final DAOBdd lacbdd = new DAOBdd(this);
        lacbdd.open();
        ArrayList<Releve> lesReleves = new ArrayList<Releve>();
        lesReleves = lacbdd.getReleveWithMoisAndLac(lac, mois);
        int i = 0;
        ArrayList<Double> lesTemperatures = new ArrayList();

        while (i < lesReleves.size()) {
            Releve unReleve = new Releve(0, 0, 0, 0, 0, 0, null);
            unReleve = lesReleves.get(i);
            lesTemperatures.add(unReleve.getTempA6h());
            lesTemperatures.add(unReleve.getTempA12h());
            lesTemperatures.add(unReleve.getTempA18h());
            lesTemperatures.add(unReleve.getTempA24h());
            i++;
        }

        int i2;
        double sum = 0;
        for (i2 = 1; i2 < lesTemperatures.size(); i2++) {
            sum += lesTemperatures.get(i);
        }
        double moyenne = sum / (i2 - 1);
        DecimalFormat f = new DecimalFormat();
        f.setMaximumFractionDigits(2);

        f.format(moyenne);

        TextView textMoyenne = findViewById(R.id.TextViewMoyenneReleveReponse);
        textMoyenne.setText(f.format(moyenne));

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





            int count = lesTemperatures.size();
            DataPoint[] values = new DataPoint[count];
            for (int i4=0; i4<count; i4++) {
                double x = i4;
                double y = lesTemperatures.get(i4);
                DataPoint v = new DataPoint(x, y);
                values[i4] = v;
            }

        GraphView graph = (GraphView) findViewById(R.id.graph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(values);



        graph.addSeries(series);
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxX(lesTemperatures.size());


    }
}


