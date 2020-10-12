package com.example.lactemperature;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //on associe à un objet java de type Button, un widget repéré physiquement par son id:
        Button buttonSaisirTemperature = findViewById(R.id.buttonSaisirTemperature);
        Button buttonAfficherReleve = findViewById(R.id.buttonAfficherReleve);
        Button buttonAfficherMoyenneReleves = (Button) findViewById(R.id.buttonAfficherMoyenneReleves);


        //on place un écouteur dessus:
        View.OnClickListener ecouteur1 = new View.OnClickListener() {
            //on implémente la méthode onclick
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.buttonSaisirTemperature:
                        Intent intent = new Intent(MainActivity.this, ActivitySaisieTemperature.class);
                        startActivity(intent);
                        break;
                    case R.id.buttonAfficherReleve:
                        Intent intent2 = new Intent(MainActivity.this, ActivityAfficherReleve.class);
                        startActivity(intent2);
                        break;
                    case R.id.buttonAfficherMoyenneReleves:
                        Intent intent3 = new Intent(MainActivity.this, ActivityAfficherMoyenneReleves.class);
                        startActivity(intent3);
                        break;

                }

            }
        };
        //on affecte l'écouteur aux boutons:
        buttonSaisirTemperature.setOnClickListener(ecouteur1);
        buttonAfficherReleve.setOnClickListener(ecouteur1);
        buttonAfficherMoyenneReleves.setOnClickListener(ecouteur1);

    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuSaisieTemperature:
                Toast.makeText(getApplicationContext(), "Ouverture de la fenêtre : Saisie d'une température", Toast.LENGTH_LONG).show();
                Intent intent1 = new Intent(MainActivity.this, ActivitySaisieTemperature.class);
                startActivity(intent1);
                return true;

            case R.id.menuAfficherUnReleve:
                Toast.makeText(getApplicationContext(), "Ouverture de la fenêtre : Afficher un relevé", Toast.LENGTH_LONG).show();
                Intent intent2 = new Intent(MainActivity.this, ActivityAfficherReleve.class);
                startActivity(intent2);
                return true;

            case R.id.menuAfficherMoyenneReleves:
                Toast.makeText(getApplicationContext(), "Ouverture de la fenêtre : Afficher la moyenne des relevés", Toast.LENGTH_LONG).show();
                Intent intent3 = new Intent(MainActivity.this, ActivityAfficherMoyenneReleves.class);
                startActivity(intent3);
                return true;
        }
        return false;
    }
}