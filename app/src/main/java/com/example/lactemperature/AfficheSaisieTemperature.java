package com.example.lactemperature;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class AfficheSaisieTemperature extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valider_saisie_temperature);


        //on va récupérer les valeurs provenant de ActivitySaisieTemperature
        Intent intent = getIntent();
        if (intent != null) {
            // cptlu= intent.getStringExtra("EXTRA_CPT");
            //hplu=intent.getStringExtra("EXTRA_HP");
            //hclu=intent.getStringExtra("EXTRA_HC");
            //raisonlu=intent.getStringExtra("EXTRA_RAISE");
        }
    }
}
