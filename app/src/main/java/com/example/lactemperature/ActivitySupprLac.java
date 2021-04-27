package com.example.lactemperature;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ActivitySupprLac  extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suppr_lac);

        //on associe à un objet java de type Button, un widget repéré physiquement par son id:
        Button buttonRetourMenuLac = findViewById(R.id.buttonRetourMenuLac);
        Button buttonValiderMAJLac = (Button) findViewById(R.id.buttonValiderMAJLac);

        //on place un écouteur dessus:
        View.OnClickListener ecouteur1 = new View.OnClickListener() {
            //on implémente la méthode onclick
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.buttonValiderMAJLac:
                        Intent intent = new Intent(ActivitySupprLac.this, ActivityMAJLacs.class);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(), "Suppression du Lac", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.buttonRetourMenuLac:
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
