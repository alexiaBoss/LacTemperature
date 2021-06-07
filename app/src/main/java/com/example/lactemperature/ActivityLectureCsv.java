package com.example.lactemperature;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;

public class ActivityLectureCsv extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecture_csv);

        //récupération du champs de texte contenu le texte du fichier csv
        TextView textView = findViewById(R.id.textView15);
        //instantation du texte au cas ou le fichier est vide
        String text = "Aucune ligne : Veillez à créé le fichier CSV avant.";
        textView.setText(text);
        //lecture du fichier csv
        try {
            //recherche du fichier dans les dossier du téléphone (directory download/lacTempérature/CSV_Lac_Temperature.csv)
            String csvFilename = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS) +"/LacTemperature/CSV_Lac_Temperature.csv" ;
            //création du csvReader
            CSVReader csvReader = new CSVReader(new FileReader(csvFilename));
            String[] row = null;
            //pour chaque ligne du cfichier csv, on créé une ligne contenant le texte du fichier csv
            while ((row = csvReader.readNext()) != null) {
                text = "";
                for(int i=0; i<row.length;i++) {
                    text = text + row[i] + "\n \n";
                }
            }
            //on affecte le texte au champs de texte correspondant
            textView.setText(text);
//...
            //on ferme le csvReader
            csvReader.close();

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

        // gestion des boutons
        Button buttonRetourCSV = findViewById(R.id.buttonRetourCSV);


        //on place un écouteur dessus:
        View.OnClickListener ecouteur1 = new View.OnClickListener() {
            //on implémente la méthode onclick
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    //retour au menu des mises a jour de lacs
                    case R.id.buttonRetourCSV:
                        finish();
                        Intent i = new Intent(ActivityLectureCsv.this, ActivityMAJLacs.class);
                        startActivity(i);
                        break;
                }

            }
        };
        //on affecte l'écouteur aux boutons:
        buttonRetourCSV.setOnClickListener(ecouteur1);

    }


}
