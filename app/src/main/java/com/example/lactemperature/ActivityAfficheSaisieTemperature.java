package com.example.lactemperature;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.NotificationCompatSideChannelService;

public class ActivityAfficheSaisieTemperature extends Activity {
    //declaration et instatation des variables
    public Releve leReleveInser = new Releve(0,0,0,0,0,0,null);
    public Releve leReleveUpdate = new Releve(0,0,0,0,0,0,null);
    String date = "";
    String lac = "";
   String temp = "";
    String temperature = "";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valider_saisie_temperature);


//on va récupérer les trois valeurs provenant de NewReleveActivity grace au getStringExtra
        Intent intent = getIntent();
        if (intent != null) {
            date = intent.getStringExtra("EXTRA_DATE");
            lac = intent.getStringExtra("EXTRA_LAC");
            temp = intent.getStringExtra("EXTRA_TEMP");
            temperature = intent.getStringExtra("EXTRA_TEMPERATURE");
        }

        //remplissage des champs de texte de la date avec les veleure récuperer precedamment
        TextView textdate = findViewById(R.id.TextViewDate);
        textdate.setText(date);

        //remplissage des champs de texte du lac avec les veleure récuperer precedamment
        TextView textLac = findViewById(R.id.TextViewSaisieValiderNomLac);
        textLac.setText(lac);

        //remplissage des champs de texte de l'heure avec les valeurs récuperer precedamment
        TextView textHeure = findViewById(R.id.TextViewSaisieValiderHeure);
        textHeure.setText(temp);

        //remplissage des champs de texte de la température avec les veleure récuperer precedamment
        TextView textTemperature = findViewById(R.id.TextViewTextPersonName3);
        textTemperature.setText(temperature);

        //ouverture de la bdd
        final DAOBdd lacbdd = new DAOBdd(this);
        lacbdd.open();
        //séparation du jour et du mois de la date
        final String[] separated = date.split("/");
        //recupération du relevé correpsondant au mois, au jour, et au lac correspondant.
        final Releve unReleve = lacbdd.getReleveWithMoisAndJourAndLac(lac,Integer.valueOf(separated[0]),Integer.valueOf(separated[1]));


        //création du relevé avec la température en focntion de l'heure
        if (temp.equals("6")) {
              leReleveInser = new Releve(Integer.valueOf(separated[1]),Integer.valueOf(separated[0]),Double.valueOf(temperature),0,0,0,lac);
        }
        if (temp.equals("12")) {
             leReleveInser = new Releve(Integer.valueOf(separated[1]),Integer.valueOf(separated[0]),0,Double.valueOf(temperature),0,0,lac);
        }
        if (temp.equals("18")) {
             leReleveInser = new Releve(Integer.valueOf(separated[1]),Integer.valueOf(separated[0]),0,0,Double.valueOf(temperature),0,lac);
        }
        if (temp.equals("24")) {
             leReleveInser = new Releve(Integer.valueOf(separated[1]),Integer.valueOf(separated[0]),0,0,0,Double.valueOf(temperature),lac);
        }







        // gestion des boutons
        Button buttonSaisieTemperatureValider = findViewById(R.id.buttonValiderSaisieTemperature);
        Button buttonSaisieTemperatureAnnuler = findViewById(R.id.buttonAnnulerSaisieTemperature);


        //on place un écouteur dessus:
        View.OnClickListener ecouteur1 = new View.OnClickListener() {
            //on implémente la méthode onclick
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    //si on clique sur le bouton valider
                    case R.id.buttonValiderSaisieTemperature:
                        // si le releve n'existe pas on le créé
                        if(unReleve.getJour()==0){
                            //insertion du relevé dans la base
                            lacbdd.insererReleve(leReleveInser);
                        }
                        //sinon , on update les relevé en fonction de l'heure du relevé créé.
                        else{
                            if(temp.equals("6")){
                                lacbdd.updateReleve6h(lac, Integer.valueOf(separated[0]), Integer.valueOf(separated[1]), Double.valueOf(temperature));
                            }
                            if(temp.equals("12")){
                                lacbdd.updateReleve12h(lac, Integer.valueOf(separated[0]), Integer.valueOf(separated[1]), Double.valueOf(temperature));
                            }
                            if(temp.equals("18")){
                                lacbdd.updateReleve18h(lac, Integer.valueOf(separated[0]), Integer.valueOf(separated[1]), Double.valueOf(temperature));
                            }
                            if(temp.equals("24")){
                                lacbdd.updateReleve24h(lac, Integer.valueOf(separated[0]), Integer.valueOf(separated[1]), Double.valueOf(temperature));
                            }
                        }

                        finish();
                        Toast.makeText(getApplicationContext(), "Enregistrement des données de la saisie", Toast.LENGTH_LONG).show();
                        finish();
                        //redirection vers le menu
                        Intent i = new Intent(ActivityAfficheSaisieTemperature.this, MainActivity.class);
                        startActivity(i);
                        break;
                    case R.id.buttonAnnulerSaisieTemperature:
                        finish();
                        Toast.makeText(getApplicationContext(), "Annulation de la saisie", Toast.LENGTH_LONG).show();

                        break;


                }

            }
        };
        //on affecte l'écouteur aux boutons:
        buttonSaisieTemperatureValider.setOnClickListener(ecouteur1);
        buttonSaisieTemperatureAnnuler.setOnClickListener(ecouteur1);


    }
}
