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

import java.util.ArrayList;


public class ActivityAfficheMoyenneReleve extends Activity{
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affiche_moyenne_releve_temperature);

        String lac= "";
        int mois = 0;

//on va récupérer des valeur de AfficherMoyenneReleves
        Intent intent = getIntent();
        if (intent != null) {
            lac= intent.getStringExtra("EXTRA_LAC");
            mois=intent.getIntExtra("EXTRA_MOIS", 0);
        }

        TextView textLac = findViewById(R.id.TextViewMoyenneLac);
        textLac.setText(lac);


        final DAOBdd lacbdd = new DAOBdd(this);
        lacbdd.open();
        ArrayList<Releve> lesReleves = new ArrayList<Releve>();
        lesReleves = lacbdd.getReleveWithMoisAndLac(lac,mois);
        Releve unReleve = new Releve(0,0,0,0,0,0,null);

        unReleve = lesReleves.get(0);


        TextView textMoyenne = findViewById(R.id.TextViewMoyenneReleveReponse);
        textMoyenne.setText(unReleve.toString());

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
                        Intent i = new Intent (ActivityAfficheMoyenneReleve.this, MainActivity.class);
                        startActivity(i);
                        break;
                }

            }
        };
        //on affecte l'écouteur aux boutons:
        buttonSaisieTemperatureValider.setOnClickListener(ecouteur1);

        double $y1=7.26;
        GraphView graph = (GraphView) findViewById(R.id.graph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(0, $y1),
                new DataPoint(1, 6.25),
                new DataPoint(2, 6.13),
                new DataPoint(3, 6),
                new DataPoint(4, 6.23),
                new DataPoint(5, 6.12),
                new DataPoint(6, 6.25),
                new DataPoint(7, 6.36),
                new DataPoint(8, 6.5),
                new DataPoint(9, 6.75),
                new DataPoint(10.5, 6.42),
                new DataPoint(11, 6.96),
                new DataPoint(12, 6.23),
                new DataPoint(13, 6.45),
                new DataPoint(14, 6.22),
                new DataPoint(15, 6.12),
                new DataPoint(16, 6.42),
                new DataPoint(17, 5.96),
                new DataPoint(18, 6.23),
                new DataPoint(19, 6.25),
                new DataPoint(20, 7.05),
                new DataPoint(21, 6.25),
                new DataPoint(22, 6.35),
                new DataPoint(23, 6.14),
                new DataPoint(24, 6),
                new DataPoint(25, 6.336),
                new DataPoint(26, 5.85),
                new DataPoint(27, 5.62),
                new DataPoint(28, 6.36),
                new DataPoint(29, 6.42),
                new DataPoint(30, 6.47),
                new DataPoint(31, 6.47),
                new DataPoint(32, 6.47),
                new DataPoint(33, 6.47),
                new DataPoint(34, 6.47),
                new DataPoint(35, 6.47),
                new DataPoint(36, 6.47),
                new DataPoint(37, 6.47),
                new DataPoint(38, 6.47),
                new DataPoint(39, 6.47),
                new DataPoint(40, 6.47),
                new DataPoint(41, 6.47),
                new DataPoint(42, 6.47),
                new DataPoint(43, 6.47),
                new DataPoint(44, 6.47),
                new DataPoint(45, 6.47),
                new DataPoint(46, 6.47),
                new DataPoint(47, 6.47),
                new DataPoint(48, 6.47),
                new DataPoint(49, 6.47),
                new DataPoint(50, 6.47),
                new DataPoint(51, 6.47),
                new DataPoint(52, 6.47),
                new DataPoint(53, 6.47),
                new DataPoint(54, 6.47),
                new DataPoint(55, 6.47),
                new DataPoint(56, 6.47),
                new DataPoint(57, 6.47),
                new DataPoint(58, 6.47),
                new DataPoint(59, 6.47),
                new DataPoint(60, 6.47),
                new DataPoint(61, 6.47),
                new DataPoint(62, 6.47),
                new DataPoint(63, 6.47),
                new DataPoint(64, 6.47),
                new DataPoint(65, 6.47),
                new DataPoint(66, 6.47),
                new DataPoint(67, 6.47),
                new DataPoint(68, 6.47),
                new DataPoint(69, 6.47),
                new DataPoint(70, 6.47),
                new DataPoint(71, 6.47),
                new DataPoint(72, 6.47),
                new DataPoint(73, 6.47),
                new DataPoint(74, 6.47),
                new DataPoint(75, 6.47),
                new DataPoint(76, 6.47),
                new DataPoint(77, 6.47),
                new DataPoint(78, 6.47),
                new DataPoint(79, 6.47),
                new DataPoint(80, 6.47),
                new DataPoint(81, 6.47),
                new DataPoint(82, 6.47),
                new DataPoint(83, 6.47),
                new DataPoint(84, 6.47),
                new DataPoint(85, 6.47),
                new DataPoint(86, 6.47),
                new DataPoint(87, 6.47),
                new DataPoint(88, 6.47),
                new DataPoint(89, 6.47),
                new DataPoint(90, 6.47),
                new DataPoint(91, 6.47),
                new DataPoint(92, 6.47),
                new DataPoint(93, 6.47),
                new DataPoint(94, 6.47),
                new DataPoint(95, 6.47),
                new DataPoint(96, 6.47),
                new DataPoint(97, 6.47),
                new DataPoint(98, 6.47),
                new DataPoint(99, 6.47),
                new DataPoint(100, 6.47),
                new DataPoint(101, 6.47),
                new DataPoint(102, 6.47),
                new DataPoint(103, 6.47),
                new DataPoint(104, 6.47),
                new DataPoint(105, 6.47),
                new DataPoint(106, 6.47),
                new DataPoint(107, 6.47),
                new DataPoint(108, 6.47),
                new DataPoint(109, 6.47),
                new DataPoint(110, 6.47),
                new DataPoint(111, 6.47),
                new DataPoint(112, 6.47),
                new DataPoint(113, 6.47),
                new DataPoint(114, 6.47),
                new DataPoint(115, 6.47),
                new DataPoint(116, 6.47),
                new DataPoint(117, 6.47),
                new DataPoint(118, 6.47),
                new DataPoint(119, 6.47),
                new DataPoint(120, 6.47),
                new DataPoint(121, 6.47),
                new DataPoint(122, 6.47),
                new DataPoint(123, 6.47),
                new DataPoint(124, 6.47),
        });
        graph.addSeries(series);
        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxX(124);


    }
}


