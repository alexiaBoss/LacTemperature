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

        //declaration et initialisations des variables
        String lac = "";
        int mois = 0;
        String signe ="";

        //on va récupérer des valeur de AfficherMoyenneReleves
        Intent intent = getIntent();
        if (intent != null) {
            //transmition
            lac = intent.getStringExtra("EXTRA_LAC");
            mois = intent.getIntExtra("EXTRA_MOIS", 0);
            signe = intent.getStringExtra("EXTRA_SIGNE");
        }

        //Remmpplissage du champs texte Lac
        TextView textLac = findViewById(R.id.TextViewMoyenneLac);
        textLac.setText(lac);

        //Remplissage des champs des signes pour les différentes ligne des température.

        TextView textSigne6 = findViewById(R.id.TextViewMoyenneC6h);
        textSigne6.setText(signe);

        TextView textSigne12 = findViewById(R.id.TextViewMoyenneC12h);
        textSigne12.setText(signe);

        TextView textSigne18 = findViewById(R.id.TextViewMoyenneC18h);
        textSigne18.setText(signe);

        TextView textSigne24 = findViewById(R.id.TextViewMoyenneC24h);
        textSigne24.setText(signe);


        //ouverture de la bdd
        final DAOBdd lacbdd = new DAOBdd(this);
        lacbdd.open();
        //Récupération des relevé du mois "mois" et du lac "lac" (récuperer dans le intent get EXTRA)
        ArrayList<Releve> lesReleves = new ArrayList<Releve>();
        lesReleves = lacbdd.getReleveWithMoisAndLac(lac, mois);
        int i = 0;
        //création de toutes les Arraylist pour y mettre les température relévé en fonction de l'heure.
        ArrayList<Double> lesTemperatures = new ArrayList();
        ArrayList<Double> lesTemperatures6h = new ArrayList();
        ArrayList<Double> lesTemperatures12h = new ArrayList();
        ArrayList<Double> lesTemperatures18h = new ArrayList();
        ArrayList<Double> lesTemperatures24h = new ArrayList();


        //Pour chaque relevé de la liste,
        while (i < lesReleves.size()) {
            //on créé un releve null
            Releve unReleve = new Releve(0, 0, 0, 0, 0, 0, null);
            //puis on le rempliis avec les information correspondant au "i" du while
            unReleve = lesReleves.get(i);
            //on remplis la liste avec les température pour la moyenne générale
            lesTemperatures.add(unReleve.getTempA6h());
            lesTemperatures.add(unReleve.getTempA12h());
            lesTemperatures.add(unReleve.getTempA18h());
            lesTemperatures.add(unReleve.getTempA24h());
            //on remplis les listes en fonction des horaire pour les moyennes par tranche horaire
            lesTemperatures6h.add(unReleve.getTempA6h());
            lesTemperatures12h.add(unReleve.getTempA12h());
            lesTemperatures18h.add(unReleve.getTempA18h());
            lesTemperatures24h.add(unReleve.getTempA24h());
            i++;
        }

        //Calcul de la moyenne général
        int i2;
        double sumGeneral = 0;
        //pour chaque température de la liste
        for (i2 = 0; i2 < lesTemperatures.size(); i2++) {
            //on ajoute a la somme général
            sumGeneral += lesTemperatures.get(i2);
        }
        //on divise ensuite la somme général par le nombre d'eéléments de la liste
        double moyenneGeneral = sumGeneral / (lesTemperatures.size());
        //on force le format décimal a 2 chiffre apres la virgule.
        DecimalFormat f = new DecimalFormat();
        f.setMaximumFractionDigits(2);

        f.format(moyenneGeneral);



        //caulcule de la moyenne pour 6h
        int i3;
        double sum6h = 0;
        //pour chaque température de la liste
        for (i3 = 0; i3 < lesTemperatures6h.size(); i3++) {
            //on ajoute a la somme
            sum6h += lesTemperatures6h.get(i3);
        }
        //puis on divise par le nombre d'élément
        double moyenne6h = sum6h / (lesTemperatures6h.size());


        f.format(moyenne6h);


        //calcul de la moyenne pour 12h (voir moyenne de 6h pour les details)
        int i4;
        double sum12h = 0;
        for (i4 = 0; i4 < lesTemperatures12h.size(); i4++) {
            sum12h += lesTemperatures12h.get(i4);
        }
        double moyenne12h = sum12h / (lesTemperatures12h.size());


        f.format(moyenne12h);



        //calcul de la moyenne pour 18h (voir moyenne de 6h pour les details)
        int i5;
        double sum18h = 0;
        for (i5 = 0; i5 < lesTemperatures18h.size(); i5++) {
            sum18h += lesTemperatures18h.get(i5);
        }
        double moyenne18h = sum18h / (lesTemperatures18h.size());


        f.format(moyenne18h);




        //calcul de la moyenne pour 24h (voir moyenne de 6h pour les details)
        int i6;
        double sum24h = 0;
        for (i6 = 0; i6 < lesTemperatures24h.size(); i6++) {
            sum24h += lesTemperatures24h.get(i6);
        }
        double moyenne24h = sum24h / (lesTemperatures24h.size());


        f.format(moyenne24h);



        //Si le signe choisi est le Fareighneit on utilise la methode celsToFaren qui permet de modifier la température en paramentre en Fareinheit
        if(signe.equals("°F")) {
            moyenne6h = celsToFaren(moyenne6h);
            moyenne12h = celsToFaren(moyenne12h);
            moyenne18h = celsToFaren(moyenne18h);
            moyenne24h = celsToFaren(moyenne24h);
            moyenneGeneral = celsToFaren(moyenneGeneral);
        }

        //Remplissage du champs de texte de la moyenne des 24h
        TextView textMoyenne24h = findViewById(R.id.textViewMoyenne24h);
        textMoyenne24h.setText(f.format(moyenne24h));

//Remplissage du champs de texte de la moyenne des 18h
        TextView textMoyenne18h = findViewById(R.id.textViewMoyenne18h);
        textMoyenne18h.setText(f.format(moyenne18h));

        //Remplissage du champs de texte de la moyenne des 12h
        TextView textMoyenne12h = findViewById(R.id.textViewMoyenne12h);
        textMoyenne12h.setText(f.format(moyenne12h));

        //Remplissage du champs de texte de la moyenne des 6h
        TextView textMoyenne6h = findViewById(R.id.textViewMoyenne6h);
        textMoyenne6h.setText(f.format(moyenne6h));

        //Remplissage du champs de texte de la moyenne général
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

                        Toast.makeText(getApplicationContext(), "Retour au menu", Toast.LENGTH_LONG).show();
                        finish();
                        //on retourne a la page MainActivity
                        Intent i = new Intent(ActivityAfficheMoyenneReleve.this, MainActivity.class);
                        startActivity(i);
                        break;
                }

            }
        };
        //on affecte l'écouteur aux boutons:
        buttonSaisieTemperatureValider.setOnClickListener(ecouteur1);




        // Affichage du graphique de la moyenne
        //récupération du nombre de température
            int count = lesTemperatures.size();
            //création des datapoints
            DataPoint[] values = new DataPoint[count];
            //pour le nombre de ttémpérature
            for (int i7=0; i7<count; i7++) {
                //on pose x en temps que position de la tempréture
                double x = i7;
                //et y en temps que température
                double y = lesTemperatures.get(i7);
                //si le signe est fareighneit on change grace a la methde cels to Faren
                if (signe.equals("°F")) {
                        y = celsToFaren(y);
                    }

                //on créé un nouveau dataPoint avec x et y
                DataPoint v = new DataPoint(x, y);
                values[i7] = v;
            }

            //création du graphe linéaire
        GraphView graph = (GraphView) findViewById(R.id.graph);
            //création de la série du graphe
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(values);



        //ajout des série dans le graphe + parametrage du graphe
        graph.addSeries(series);
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxX(lesTemperatures.size());


    }
    //Methode permettant de changer la température qui esst en parametre en Fareinheit (les température sont enregistrer en celcius dans la base)
    public double celsToFaren(double tempCels){
        double tempFaren;
        tempFaren = tempCels*(9/5)+32;

        return tempFaren;
    }
}


