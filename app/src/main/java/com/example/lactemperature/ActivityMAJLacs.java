package com.example.lactemperature;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ActivityMAJLacs extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modif_add_suppr_lac);

        //on associe à un objet java de type Button, un widget repéré physiquement par son id:
        Button buttonAjouterLac = findViewById(R.id.buttonAjouterLac);
        Button buttonSuppressionLac = (Button) findViewById(R.id.buttonSuppressionLac);
        Button buttonModifierLac = (Button) findViewById(R.id.buttonModifierLac);
        Button buttonCSV = (Button) findViewById(R.id.buttonCSV);
        Button buttonlectureCSV = (Button) findViewById(R.id.buttonlectureCSV);

        Button buttonRetour = (Button) findViewById(R.id.buttonRetourMenuLac);

        //on place un écouteur dessus:
        View.OnClickListener ecouteur1 = new View.OnClickListener() {
            //on implémente la méthode onclick
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    //si clic sur le bouton ajouter
                    case R.id.buttonAjouterLac:
                        //redirection vers ActivityAddLac
                        Intent intent = new Intent(ActivityMAJLacs.this, ActivityAddLac.class);
                        startActivity(intent);
                        break;
                        //si clique sur le bouton suppression
                    case R.id.buttonSuppressionLac:
                        //redirection vers ActivitySupprLac
                        Intent intent2 = new Intent(ActivityMAJLacs.this, ActivitySupprLac.class);
                        startActivity(intent2);
                        break;
                        //si clic sur le bouton modifier
                    case R.id.buttonModifierLac:
                        //redirection vers ActivityModifierLac
                        Intent intent3 = new Intent(ActivityMAJLacs.this, ActivityModifierLac.class);
                        startActivity(intent3);
                        break;
                        //si clic sur le bouton ecriture CSV
                    case R.id.buttonCSV:

                        //création du fichier csv
                       try {
                           //création d'un nouveau chemin ( Downloads/LacTempérature )
                            File path = new File(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS) + "/LacTemperature") ;
                            //si le chemin n'existe pas le créé
                           if (!path.exists() ) {

                               path.mkdirs();
                           }
                           //création  du fichier CSV_Lac_Temperature?csv dans le chemin precédant
                           File file = new File(path , "CSV_Lac_Temperature.csv");
                           // if file doesnt exists, then create it
                           if (!file.exists() ) {

                               file.createNewFile();
                           }

                           //création du CSVWritter qui permet d'ecrire dans le fichier csv
                           CSVWriter writer = new CSVWriter(new FileWriter(file.getAbsoluteFile()));

                           //ouverture de la bdd
                           final DAOBdd lacbdd = new DAOBdd(getApplicationContext());
                           lacbdd.open();
                           // On récupère les données de l'hitorique dans un curseur
                           Cursor c = lacbdd.getDataHistorique();
                           // Déclaration et instanciation d'une liste
                           ArrayList<String> lHistorique = new ArrayList<String>();
                           // Sélection du premier élément du curseur
                           if(c.moveToFirst()) {
                               // Si le curseur n'est pas vide
                               if (c.getCount() != 0) {
                                   //pour chaque ligne de l'historique
                                   while (!c.isAfterLast()) {
                                       //on ajoute dans la liste toute la logne de l'historique.
                                       lHistorique.add("Nom Lac : " + c.getString(1) + " - Longitude : "+ c.getString(2) + " - Latitude : " + c.getString(3)
                                       + " - Jour : " + c.getString(4) + " - Mois : " + c.getString(5) + " - Température à 6h : " + c.getString(6) +
                                        " - Température à 12h : " + c.getString(7) + " - Température à 18h : " + c.getString(8) + " - Température à 24h : " + c.getString(9));
                                       c.moveToNext();
                                   }
                               }
                           }


                           //on ecris dans le csv les lignes de l'historique récupéré
                           String[] historique = lHistorique.toArray(new String[0]);
                           writer.writeNext(historique);

                           //on ferme le writter
                           writer.close();



                           Toast.makeText(getApplicationContext(),  "" + lHistorique, Toast.LENGTH_LONG).show();

                           //en cas d'erreur :
                    } catch (IOException e) {
                           e.printStackTrace();
                           Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                       }
                        break;
                       //si on clic sur le bouton lecture CSV
                    case R.id.buttonlectureCSV:
                        //on redirige vers ActivityLectureCsv
                        Intent intent5 = new Intent(ActivityMAJLacs.this, ActivityLectureCsv.class);
                        startActivity(intent5);

                        break;

                        //si on clic sur le bouton retour
                    case R.id.buttonRetourMenuLac:
                        finish();
                        //redirtection vers le menu
                        Intent intent4 = new Intent(ActivityMAJLacs.this, MainActivity.class);
                        startActivity(intent4);
                        break;

                }

            }
        };
        //on affecte l'écouteur aux boutons:
        buttonAjouterLac.setOnClickListener(ecouteur1);
        buttonSuppressionLac.setOnClickListener(ecouteur1);
        buttonModifierLac.setOnClickListener(ecouteur1);
        buttonCSV.setOnClickListener(ecouteur1);
        buttonlectureCSV.setOnClickListener(ecouteur1);
        buttonRetour.setOnClickListener(ecouteur1);



    }
}