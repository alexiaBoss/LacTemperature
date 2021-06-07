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
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;


public class ActivityAfficherReleve extends Activity {


    final String[] leLac= new String[1];
    final String[] leSigne= new String[1];

    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afficher_releve);

        //recupération des boutons et de la date
        final EditText Date = findViewById(R.id.dateReleveAfficherReleve);
        Button buttonAfficherReleveValider = findViewById(R.id.buttonAfficherReleveValider);
        Button buttonAfficherReleveAnnuler = findViewById(R.id.buttonAfficherReleveAnnuler);

        leSigne[0]= "";


        //on place un écouteur dessus:
        View.OnClickListener ecouteur1 = new View.OnClickListener() {
            //on implémente la méthode onclick
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    //lors du clic sur le bouton valider
                    case R.id.buttonAfficherReleveValider:
                        //on passer les infos dans l'autre interface grace au putExtra
                        String signe = leSigne[0];
                        //si le signe a été coché alors
                        if(signe.length() != 0) {
                            Intent i = new Intent(ActivityAfficherReleve.this, ActivityAfficheReleve.class);
                            i.putExtra("EXTRA_LAC", leLac[0]);
                            i.putExtra("EXTRA_DATE", Date.getText().toString());
                            i.putExtra("EXTRA_SIGNE", leSigne[0]);
                            startActivityForResult(i, 0);
                            //si le signe n'a pas été coché alors
                        }else{

                            //création d'une alerte
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                        //set title
                        alertDialogBuilder.setTitle("Alerte");

                        //set dialog message
                        alertDialogBuilder
                                .setMessage("Vous n'avez pas coché votre unité ")
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
                        //lors du clic du bouton retour
                    case R.id.buttonAfficherReleveAnnuler:
                        Toast.makeText(getApplicationContext(), "Retour au menu", Toast.LENGTH_LONG).show();
                        finish();
                        break;


                }

            }
        };
        //on affecte l'écouteur aux boutons:
        buttonAfficherReleveValider.setOnClickListener(ecouteur1);
        buttonAfficherReleveAnnuler.setOnClickListener(ecouteur1);






//programmation des boutons radios
        RadioGroup radioGroupSigne = findViewById(R.id.radioGroupSigne);
        radioGroupSigne.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged (RadioGroup radioGroupSigne,int i){
                switch (i) {
                    case R.id.radioButton:
                        leSigne[0] ="°C";
                        break;
                    case R.id.radioButton2:
                        leSigne[0] ="°F";
                        break;

                }
            }

        });

        //gestion de la liste déroulante des Lacs
        final Spinner spinnerAfficheLac = (Spinner) findViewById(R.id.spinnerAfficheLac);
        //ouverture de la bdd
        DAOBdd lacbdd = new DAOBdd(this);
        lacbdd.open();
        //récupération des données des lacs
        Cursor c = lacbdd.getDataLac();
        ArrayList<String> leslacs = new ArrayList<String>();
        if (c.moveToFirst()) {

            if (c.getCount() != 0) {

                //remplissage de la liste deroulante avec les données récupéré
                while (!c.isAfterLast()) {
                    leslacs.add(c.getString(1)); //add the item
                    c.moveToNext();
                }
            }
        }
        ArrayAdapter<String> dataAdapterR = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, leslacs);
        dataAdapterR.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAfficheLac.setAdapter(dataAdapterR);
        spinnerAfficheLac.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //lors de la séléction d'un item,
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //recupération du lac slééctioner dans la variable leLac
                leLac[0] = String.valueOf(spinnerAfficheLac.getSelectedItem());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });





//gestion du calendrier
        //création du calendrier
            final Calendar myCalendar = Calendar.getInstance();
            ///récupération de l'eddittexte  du calendrier
        final EditText edittext = findViewById(R.id.dateReleveAfficherReleve);
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
                new DatePickerDialog(ActivityAfficherReleve.this, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void updateLabel(EditText edittext, Calendar myCalendar){
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        edittext.setText(sdf.format(myCalendar.getTime()));
    }


}