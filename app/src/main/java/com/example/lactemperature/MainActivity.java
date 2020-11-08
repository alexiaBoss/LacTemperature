package com.example.lactemperature;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        DAOBdd lacbdd = new DAOBdd(this);
        lacbdd.open();
        if(lacbdd.getDataLac().getCount() == 0) {
            remplirTableLac();
            remplirTableReleve();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //on associe à un objet java de type Button, un widget repéré physiquement par son id:
        Button buttonSaisirTemperature = findViewById(R.id.buttonSaisirTemperature);
        Button buttonAfficherReleve = findViewById(R.id.buttonAfficherReleve);
        Button buttonAfficherMoyenneReleves = (Button) findViewById(R.id.buttonAfficherMoyenneReleves);
        Button buttonListeLac = (Button) findViewById(R.id.buttonListeLac);


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
                    case R.id.buttonListeLac:
                        Intent intent4 = new Intent(MainActivity.this, ActivityListeLac.class);
                        startActivity(intent4);
                        break;

                }

            }
        };
        //on affecte l'écouteur aux boutons:
        buttonSaisirTemperature.setOnClickListener(ecouteur1);
        buttonAfficherReleve.setOnClickListener(ecouteur1);
        buttonAfficherMoyenneReleves.setOnClickListener(ecouteur1);
        buttonListeLac.setOnClickListener(ecouteur1);

    }

    public void remplirTableLac() {
        DAOBdd lacBdd = new DAOBdd(this);
        Lac lac1 = new Lac("Lac Léman", 6.507111649175288, 46.44845480529758);
        Lac lac2 = new Lac("Lac du Bouchet", 3.7903668, 44.9090216);
        Lac lac3 = new Lac("Lac de Sainte-Croix", 6.1258921, 43.7672616);
        Lac lac4 = new Lac("Lac de Allos", 6.7021268, 44.2335854);
        Lac lac5 = new Lac("Lac de Serre-Ponçon", 6.3386898, 44.5004577);
        Lac lac6 = new Lac("Lac de Pavin", 2.8835359, 45.4959024);
        Lac lac7 = new Lac("Lac de Aiguebelette", 5.777172, 45.5576451);
        Lac lac8 = new Lac("Lac du Salagou", 3.3291225, 43.6564847);
        Lac lac9 = new Lac("Lac de Annecy", 6.110467, 45.8493348);
        Lac lac10 = new Lac("Lac de Oô", 0.4879528, 42.740553);
        //on ouvre la base de données
        lacBdd.open();
        //on insère tous les lacs
        lacBdd.insererLac(lac1);
        lacBdd.insererLac(lac2);
        lacBdd.insererLac(lac3);
        lacBdd.insererLac(lac4);
        lacBdd.insererLac(lac5);
        lacBdd.insererLac(lac6);
        lacBdd.insererLac(lac7);
        lacBdd.insererLac(lac8);
        lacBdd.insererLac(lac9);
        lacBdd.insererLac(lac10);
        //le curseur pour afficher le nombre de Lac dans la base
        Cursor c = lacBdd.getDataLac();
        Toast.makeText(getApplicationContext(), " il y a " + c.getCount() +
                " lacs ", Toast.LENGTH_LONG).show();
    }

    public void remplirTableReleve() {
        DAOBdd releveBdd = new DAOBdd(this);
        Releve releve1 = new Releve(2, 11, 3.5, 3.7, 3.6, 3.2, "Lac du Bouchet");
        Releve releve2 = new Releve(3, 11, 3.6, 3.8, 3.7, 3.3, "Lac du Bouchet");
        Releve releve3 = new Releve(3, 8, 5.6,5.4,5.5,5.1, "Lac Léman");
        Releve releve4 = new Releve(4, 11, 5.7,5.3,5.5,5.4, "Lac Léman");
        Releve releve5 = new Releve(5, 11, 5.8,5.6,5.6,5.1, "Lac Léman");
        Releve releve6 = new Releve(5, 6, 10.5,10.6,10.6,10.1, "Lac de Sainte-Croix");
        Releve releve7 = new Releve(6, 6,10.6,10.7,10.4,10.3, "Lac de Sainte-Croix");
        Releve releve8 = new Releve(7, 6, 10.7,10.6,10.4,10.5, "Lac de Sainte-Croix");
        Releve releve9 = new Releve(15, 2, 11.0,12.0,12.8,12.4, "Lac de Allos");
        Releve releve10 = new Releve(16, 2, 11.6,12.3,12.4,12.6, "Lac de Allos");
        Releve releve11 = new Releve(17, 2, 11.7,11.6,11.8,11.2, "Lac de Allos");
        Releve releve12 = new Releve(15, 11, 2.6,2.7,2.3,2.3, "Lac de Serre-Ponçon");
        Releve releve13 = new Releve(16, 11, 2.8,2.7,2.4,2.8, "Lac de Serre-Ponçon");
        Releve releve14 = new Releve(17, 11, 2.9,2.8,2.6,2.1, "Lac de Serre-Ponçon");
        Releve releve15 = new Releve(20, 12, 3.6,3.7,3.8,3.4, "Lac de Pavin");
        Releve releve16 = new Releve(21, 12, 3.4,3.2,3.6,3.1, "Lac de Pavin");
        Releve releve17 = new Releve(22, 12, 3.5,3.6,3.8,3.7, "Lac de Pavin");
        Releve releve18 = new Releve(23, 3, 8.6,8.4,8.8,8.5, "Lac de Aiguebelette");
        Releve releve19 = new Releve(24, 3, 8.1,8.4,8.7,8.6, "Lac de Aiguebelette");
        Releve releve20 = new Releve(25, 3, 8.9,8.9,8.8,8.7, "Lac de Aiguebelette");
        Releve releve21 = new Releve(2, 6, 5.8,5.2,5.1,5.0, "Lac de Salagou");
        Releve releve22 = new Releve(3, 6, 5.9,5.4,5.6,5.1, "Lac de Salagou");
        Releve releve23 = new Releve(4, 6, 5.2,5.5,5.4,5.7, "Lac de Salagou");
        Releve releve24 = new Releve(29, 7, 16.5,16.8,16.9,16.5, "Lac deAnnecy");
        Releve releve25 = new Releve(30, 7, 16.4,16.6,16.8,16.4, "Lac deAnnecy");
        Releve releve26 = new Releve(31, 7, 16.9,16.8,16.4,16.3, "Lac de Annecy");
        Releve releve27 = new Releve(5, 5, 15.2,15.3,15.6,15.1, "Lac de Oô");
        Releve releve28 = new Releve(6, 5, 15.8,15.6,15.6,15.7, "Lac de Oô");
        Releve releve29 = new Releve(7, 5, 15.7,15.3,15.4,15.1, "Lac de Oô");
        Releve releve30 = new Releve(8, 5, 15.8,15.1,15.4,15.6, "Lac de Oô");
        Releve releve31 = new Releve(9, 5, 15.7,15.6,15.2,15.1, "Lac de Oô");
        Releve releve32 = new Releve(10, 5, 15.4,15.3,15.1,15.2, "Lac de Oô");
        Releve releve33 = new Releve(11, 5, 15.8,16.2,16.6,16.1, "Lac de Oô");
        Releve releve34 = new Releve(12, 5, 15.8,15.6,15.6,15.1, "Lac de Oô");

        // Relevés de Janvier sur le Lac d'Oô ( POUR LE JEU D'ESSAI)
        Releve releve35 = new Releve(1, 1, 6.2, 6.5, 7.2, 7.1, "Lac de Oô");
        Releve releve36 = new Releve(2, 1, 6.4, 6.9, 8.0, 8.1, "Lac de Oô");
        Releve releve37 = new Releve(3, 1, 8.6, 8.4, 8.8, 8.5, "Lac de Oô");
        Releve releve38 = new Releve(4, 1, 6.2, 4.5, 4.2, 7.1, "Lac de Oô");
        Releve releve39 = new Releve(5, 1, 6.2, 6.8, 7.8, 5.8, "Lac de Oô");
        Releve releve40 = new Releve(6, 1, 8.5, 4.5, 7.2, 4.3, "Lac de Oô");
        Releve releve41 = new Releve(7, 1, 8.2, 5.8, 3.8, 3.1, "Lac de Oô");
        Releve releve42 = new Releve(8, 1, 4.5, 6.5, 7.8, 4.1, "Lac de Oô");
        Releve releve43 = new Releve(9, 1, 6.0, 6.5, 7.2, 4.2, "Lac de Oô");
        Releve releve44 = new Releve(10, 1, 6.2, 6.5, 7.2, 4.1, "Lac de Oô");
        Releve releve45 = new Releve(11, 1, 6.2, 6.0, 7.0, 6.1, "Lac de Oô");
        Releve releve46 = new Releve(12, 1, 5.2, 5.5, 5.8, 6.9, "Lac de Oô");
        Releve releve47 = new Releve(13, 1, 6.0, 6.5, 7.4, 5.3, "Lac de Oô");
        Releve releve48 = new Releve(14, 1, 6.7, 6.8, 7.7, 6.1, "Lac de Oô");
        Releve releve49 = new Releve(15, 1, 4.2, 4.5, 6.9, 7.2, "Lac de Oô");
        Releve releve50 = new Releve(16, 1, 7.4, 5.7, 6.1, 7.1, "Lac de Oô");
        Releve releve51 = new Releve(17, 1, 6.5, 4.5, 7.7, 8.3, "Lac de Oô");
        Releve releve52 = new Releve(18, 1, 6.8, 3.8, 7.8, 8.8, "Lac de Oô");
        Releve releve53 = new Releve(19, 1, 6.2, 6.5, 7.1, 7.3, "Lac de Oô");
        Releve releve54 = new Releve(20, 1, 4.4, 6.4, 7.5, 5.1, "Lac de Oô");
        Releve releve55 = new Releve(21, 1, 6.5, 5.5, 7.2, 4.1, "Lac de Oô");
        Releve releve56 = new Releve(22, 1, 6.2, 6.7, 5.7, 5.3, "Lac de Oô");
        Releve releve57 = new Releve(23, 1, 8.3, 6.5, 7.4, 7.2, "Lac de Oô");
        Releve releve58 = new Releve(24, 1, 6.2, 4.5, 6.2, 5.8, "Lac de Oô");
        Releve releve59 = new Releve(25, 1, 8.2, 5.5, 7.5, 5.1, "Lac de Oô");
        Releve releve60 = new Releve(26, 1, 6.3, 6.5, 7.4, 5.8, "Lac de Oô");
        Releve releve61 = new Releve(27, 1, 8.6, 6.5, 7.2, 4.1, "Lac de Oô");
        Releve releve62 = new Releve(28, 1, 6.0, 6.2, 6.8, 6.1, "Lac de Oô");
        Releve releve63 = new Releve(29, 1, 7.2, 3.8, 8.2, 7.1, "Lac de Oô");
        Releve releve64 = new Releve(30, 1, 6.2, 3.8, 7.4, 9.1, "Lac de Oô");
        Releve releve65 = new Releve(31, 1, 6.7, 3.9, 8.2, 8.1, "Lac de Oô");
        //on ouvre la base de données
        releveBdd.open();
        //on insère tout les relevés
        releveBdd.insererReleve(releve1);
        releveBdd.insererReleve(releve2);
        releveBdd.insererReleve(releve3);
        releveBdd.insererReleve(releve4);
        releveBdd.insererReleve(releve5);
        releveBdd.insererReleve(releve6);
        releveBdd.insererReleve(releve7);
        releveBdd.insererReleve(releve8);
        releveBdd.insererReleve(releve9);
        releveBdd.insererReleve(releve10);
        releveBdd.insererReleve(releve11);
        releveBdd.insererReleve(releve12);
        releveBdd.insererReleve(releve13);
        releveBdd.insererReleve(releve14);
        releveBdd.insererReleve(releve15);
        releveBdd.insererReleve(releve16);
        releveBdd.insererReleve(releve17);
        releveBdd.insererReleve(releve18);
        releveBdd.insererReleve(releve19);
        releveBdd.insererReleve(releve20);
        releveBdd.insererReleve(releve21);
        releveBdd.insererReleve(releve22);
        releveBdd.insererReleve(releve23);
        releveBdd.insererReleve(releve24);
        releveBdd.insererReleve(releve25);
        releveBdd.insererReleve(releve26);
        releveBdd.insererReleve(releve27);
        releveBdd.insererReleve(releve28);
        releveBdd.insererReleve(releve27);
        releveBdd.insererReleve(releve28);
        releveBdd.insererReleve(releve29);
        releveBdd.insererReleve(releve30);
        releveBdd.insererReleve(releve31);
        releveBdd.insererReleve(releve32);
        releveBdd.insererReleve(releve33);
        releveBdd.insererReleve(releve34);

        // Ajout des relevés de Janvier sur le Lac d'Oô ( POUR LE JEU D'ESSAI)
        releveBdd.insererReleve(releve35);
        releveBdd.insererReleve(releve36);
        releveBdd.insererReleve(releve37);
        releveBdd.insererReleve(releve38);
        releveBdd.insererReleve(releve39);
        releveBdd.insererReleve(releve40);
        releveBdd.insererReleve(releve41);
        releveBdd.insererReleve(releve42);
        releveBdd.insererReleve(releve43);
        releveBdd.insererReleve(releve44);
        releveBdd.insererReleve(releve45);
        releveBdd.insererReleve(releve46);
        releveBdd.insererReleve(releve47);
        releveBdd.insererReleve(releve48);
        releveBdd.insererReleve(releve49);
        releveBdd.insererReleve(releve50);
        releveBdd.insererReleve(releve51);
        releveBdd.insererReleve(releve52);
        releveBdd.insererReleve(releve53);
        releveBdd.insererReleve(releve54);
        releveBdd.insererReleve(releve55);
        releveBdd.insererReleve(releve56);
        releveBdd.insererReleve(releve57);
        releveBdd.insererReleve(releve58);
        releveBdd.insererReleve(releve59);
        releveBdd.insererReleve(releve60);
        releveBdd.insererReleve(releve61);
        releveBdd.insererReleve(releve62);
        releveBdd.insererReleve(releve63);
        releveBdd.insererReleve(releve64);
        releveBdd.insererReleve(releve65);

        //le curseur pour afficher le nombre de relevés dans la base
        Cursor c = releveBdd.getDataReleve();
        Toast.makeText(getApplicationContext(), " il y a " + String.valueOf(c.getCount()) + " releve ", Toast.LENGTH_LONG).show();
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
            case R.id.menuListeLac:
                Toast.makeText(getApplicationContext(), "Ouverture de la fenêtre : Liste des lacs", Toast.LENGTH_LONG).show();
                Intent intent4 = new Intent(MainActivity.this, ActivityListeLac.class);
                startActivity(intent4);
                return true;
        }
        return false;
    }
}