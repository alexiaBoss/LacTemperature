package com.example.lactemperature;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class ActivityAddLac extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lac);

        EditText textNomLac = findViewById(R.id.editTextNomLac);
        String nomLac = textNomLac.getText().toString();

        EditText textLatitude = findViewById(R.id.editTextLatitude);
        String latitude = textLatitude.getText().toString();

        EditText textLongitude = findViewById(R.id.editTextLongitude);
        String longitude = textLongitude.getText().toString();



    }
}
