package com.example.lactemperature;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

public class ActivitySaisieTemperature extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saisie_temperature);

        Button buttonSaisieTemperatureValider = findViewById(R.id.buttonSaisieTemperatureValider);
        Button buttonSaisieTemperatureAnnuler = findViewById(R.id.buttonSaisieTemperatureAnnuler);
    }
}
