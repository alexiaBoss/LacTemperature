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

        TextView textView = findViewById(R.id.textView15);
        String text = "Aucune ligne : Veuillez à créé le fichier CSV avant.";
        try {
            String csvFilename = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS) +"/LacTemperature/CSV_Lac_Temperature.csv" ;
            CSVReader csvReader = new CSVReader(new FileReader(csvFilename));
            String[] row = null;
            while ((row = csvReader.readNext()) != null) {
                text = "";
                for(int i=0; i<row.length;i++) {
                    text = text + row[i] + "\n \n";
                }
            }
            textView.setText(text);
//...
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
