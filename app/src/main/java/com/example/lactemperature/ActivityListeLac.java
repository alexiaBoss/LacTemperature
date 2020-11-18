package com.example.lactemperature;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import static java.lang.String.valueOf;


public class ActivityListeLac extends AppCompatActivity implements OnMapReadyCallback {

    final String[] leLac= new String[1];
    GoogleMap map;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afficher_lac);
        Button buttonRetourListeLac = findViewById(R.id.buttonRetourListeLac);



        //on place un écouteur dessus:
        View.OnClickListener ecouteur1 = new View.OnClickListener() {
            //on implémente la méthode onclick
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.buttonRetourListeLac:
                        // enregistrer les données dans la base
                        //i.putExtra("EXTRA_CPT",);
                        Toast.makeText(getApplicationContext(), "Retour au menu", Toast.LENGTH_LONG).show();
                        finish();
                        Intent i = new Intent (ActivityListeLac.this, MainActivity.class);
                        startActivity(i);
                        break;
                }

            }

        };
        //on affecte l'écouteur aux boutons:
        buttonRetourListeLac.setOnClickListener(ecouteur1);

        //gestion de la liste déroulante des lacs
        // déclaration et instanciation du spinner
        final Spinner spinnerAfficheLac = (Spinner) findViewById(R.id.spinnerNomLacListeLac);
        // on instancie le DAO
        final DAOBdd lacbdd = new DAOBdd(this);
        // On ouvre le DAO
        lacbdd.open();
        // On récupère les données des lacs dans un curseur
        Cursor c = lacbdd.getDataLac();
        // Déclaration et instanciation d'une liste
        ArrayList<String> leslacs = new ArrayList<String>();
        // Sélection du premier élément du curseur
        if(c.moveToFirst()) {
            // Si le curseur n'est pas vide
            if (c.getCount() != 0) {
                // On ajoute les lacs
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
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // l'item selectionné va dans une variable leLac[]
                leLac[0] = valueOf(spinnerAfficheLac.getSelectedItem());
                //Remplissage des champs pour la longitude et la latitude.
                Lac lac =lacbdd.getLacWithNomLac(leLac[0]);
                TextView longitude = findViewById(R.id.textViewLongitude);
                longitude.setText(valueOf(lac.getLongitude()));
                TextView latitude = findViewById(R.id.textViewLatitude);
                latitude.setText(valueOf(lac.getLatitude()));
                double latitude2 = lac.getLatitude();
                double longitude2 = lac.getLongitude();
                LatLng lacCoo = new LatLng(latitude2, longitude2);

                map.clear();

                map.addMarker(new MarkerOptions().position(lacCoo).title("Marker positionner sur le" + lac.getNomLac()));
                map.moveCamera(CameraUpdateFactory.newLatLng(lacCoo));
                map.setMinZoomPreference((float) 4.75);
                map.setMaxZoomPreference((float) 4.75);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // Get the SupportMapFragment and request notification
        // when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }
    public void onMapReady(GoogleMap googleMap) {
        this.map = googleMap;


    }

}
