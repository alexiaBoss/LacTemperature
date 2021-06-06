package com.example.lactemperature;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.EditText;
import android.widget.TextView;

public class ActivityAddLac extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lac);

        //ouverture de la bdd
        final DAOBdd lacbdd = new DAOBdd(this);
        lacbdd.open();

        //on associe à un objet java de type Button, un widget repéré physiquement par son id:
        Button buttonRetourMenuLac = findViewById(R.id.buttonRetourMenuLac);
        Button buttonValiderMAJLac = (Button) findViewById(R.id.buttonValiderMAJLac);

        //on place un écouteur dessus:
        View.OnClickListener ecouteur1 = new View.OnClickListener() {
            //on implémente la méthode onclick
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    //si le bouton est le bouton valider alors
                    case R.id.buttonValiderMAJLac:
                        //création d'un intente redirigeant vers la page ActivityMAJLac
                        Intent intent = new Intent(ActivityAddLac.this, ActivityMAJLacs.class);

                        //récupération du champs nom Lac
                        EditText textNomLac = findViewById(R.id.editTextNomLac);
                        String nomLac = textNomLac.getText().toString();

                        //récupération du champs lattitude
                        EditText textLatitude = findViewById(R.id.editTextLatitude);
                        Double latitude = Double.valueOf(textLatitude.getText().toString());

                        //récupération du champs longitude
                        EditText textLongitude = findViewById(R.id.editTextLongitude);
                        Double longitude = Double.valueOf(textLongitude.getText().toString());

                        //création du lac avec les donnée récupéré (en Objet Lac)
                        Lac lac = new Lac(nomLac,longitude, latitude);

                        //insertion de l'objet Lac dans la bdd
                        lacbdd.insererLac(lac);


                        startActivity(intent);
                        Toast.makeText(getApplicationContext(), "Enregistrement des données de la saisie", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.buttonRetourMenuLac:
                        //retour vers la page précédente
                        finish();
                        Toast.makeText(getApplicationContext(), "Retour", Toast.LENGTH_LONG).show();
                        finish();
                        break;

                }

            }
        };
        //on affecte l'écouteur aux boutons:
        buttonRetourMenuLac.setOnClickListener(ecouteur1);
        buttonValiderMAJLac.setOnClickListener(ecouteur1);





    }
}
