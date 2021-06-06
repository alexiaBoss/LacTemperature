package com.example.lactemperature;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import static java.lang.String.valueOf;

public class ActivitySaisieTemperature extends Activity {

    //declarations des variables
    final String[] leLac= new String[1];
    final String[] laTemp= new String[1];

    final Context context = this;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saisie_temperature);


        // gestion des boutons
        Button buttonSaisieTemperatureValider = findViewById(R.id.buttonSaisieTemperatureValider);
        Button buttonSaisieTemperatureAnnuler = findViewById(R.id.buttonSaisieTemperatureAnnuler);

        //recupération des champs de la date et de la température
        final EditText Date = findViewById(R.id.dateReleve);
        final EditText temperature = findViewById(R.id.editTextTemperatureSaisie);
        laTemp[0]= "";

        //on place un écouteur dessus:
        View.OnClickListener ecouteur1 = new View.OnClickListener() {
            //on implémente la méthode onclick
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    //si on clic sur valider
                    case R.id.buttonSaisieTemperatureValider:
                        // enregistrer les données dans la base
                        //on passer les infos dans l'autre interface
                       String veriftemperature = temperature.getText().toString();
                       String heure = laTemp[0];
                       //si la temp et l'heure ne sont pas null alors
                        if(veriftemperature.length() != 0 && heure.length() != 0)
                        {

                            //on passer les infos dans l'autre interface grace au putExtra
                        Intent i = new Intent(ActivitySaisieTemperature.this, ActivityAfficheSaisieTemperature.class);
                        i.putExtra("EXTRA_LAC", leLac[0]);
                        i.putExtra("EXTRA_TEMP", laTemp[0]);
                        i.putExtra("EXTRA_DATE", Date.getText().toString());
                        i.putExtra("EXTRA_TEMPERATURE", temperature.getText().toString());

                        startActivity(i);
                        Toast.makeText(getApplicationContext(), "Enregistrement des données de la saisie", Toast.LENGTH_LONG).show();
                        //sinon
                    }else{

                            //création d'une alerte
                            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                            //set title
                            alertDialogBuilder.setTitle("Alerte");

                            //set dialog message
                            alertDialogBuilder
                                    .setMessage("Vous n'avez pas rempli le champs de la température et/ou pas coché d'horaire ")
                                    .setCancelable(false)
                                    .setNegativeButton("Ok",new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog,int id) {
                                            //if this button is clicked, just close
                                            //the dialog box and do nothing
                                            dialog.cancel();
                                        }
                                    });

                            //create alert dialog
                            AlertDialog alertDialog = alertDialogBuilder.create();

                            //show it
                            alertDialog.show();
                        }

                        break;
                        //lors du clic sur retour
                    case R.id.buttonSaisieTemperatureAnnuler:
                        finish();
                        //on est redirgier vers le menu
                        Toast.makeText(getApplicationContext(), "Annulation de la saisie", Toast.LENGTH_LONG).show();
                        finish();
                        break;


                }

            }
        };
        //on affecte l'écouteur aux boutons:
        buttonSaisieTemperatureValider.setOnClickListener(ecouteur1);
        buttonSaisieTemperatureAnnuler.setOnClickListener(ecouteur1);

        //programmation des boutons radios
        RadioGroup radioGroupTemp = findViewById(R.id.radioGroupSaisieTemp);
        radioGroupTemp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged (RadioGroup radioGroupTemp,int i){

                switch (i) {
                    //si le bouton coché est 6h alors l'heure deviens 6
                    case R.id.radioButtonSaisie6:
                        laTemp[0] ="6";
                        break;
                    //si le bouton coché est 12h alors l'heure deviens 12
                    case R.id.radioButtonSaisie12:
                        laTemp[0] ="12";
                        break;
                    //si le bouton coché est 18h alors l'heure deviens 18
                    case R.id.radioButtonSaisie18:
                        laTemp[0] ="18";
                        break;
                    //si le bouton coché est 24h alors l'heure deviens 24
                    case R.id.radioButtonSaisie24:
                        laTemp[0] ="24";
                        break;
                }
            }

        });

        //gestion de la liste déroulante des Lacs
        final Spinner spinnerAfficheLac = (Spinner) findViewById(R.id.spinnerSaisieLac);
        //ouverture de la bdd
        final DAOBdd lacbdd = new DAOBdd(this);
        lacbdd.open();
        //récupération des données des lacs
        Cursor c = lacbdd.getDataLac();
        ArrayList<String> leslacs = new ArrayList<String>();
        if (c.moveToFirst()) {

            if (c.getCount() != 0) {

                //remplissage de la liste déroulante avec les données des lacs
                while (!c.isAfterLast()) {
                    leslacs.add(c.getString(1)); //add the item
                    c.moveToNext();
                }
            }
        }
        ArrayAdapter<String> dataAdapterR = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,leslacs);
        dataAdapterR.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAfficheLac.setAdapter(dataAdapterR);
        spinnerAfficheLac.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //si un item est séléctionner
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //on récupére le lac dans leLac
                leLac[0] = String.valueOf(spinnerAfficheLac.getSelectedItem());
                //récupération des info du lac
                Lac lac =lacbdd.getLacWithNomLac(leLac[0]);
                //Remplissage des champs pour la longitude et la latitude.
                TextView longitude = findViewById(R.id.textViewLongitudeLac);
                longitude.setText(valueOf(lac.getLongitude()));
                TextView latitude = findViewById(R.id.textViewLatitudeLac);
                latitude.setText(valueOf(lac.getLatitude()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });




        //création du calendrier
        final  Calendar myCalendar = Calendar.getInstance();
        //récupération du champs de texte du calendrier
        final EditText edittext = findViewById(R.id.dateReleve);
        edittext.requestFocus();
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // TODO Auto-generated method stub

                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel(edittext, myCalendar);
            }

        };

        edittext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(ActivitySaisieTemperature.this, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

        private void updateLabel(EditText edittext, Calendar myCalendar){
            String myFormat = "MM/dd/yy"; //In which you need put here
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

            edittext.setText(sdf.format(myCalendar.getTime()));
        }

}
