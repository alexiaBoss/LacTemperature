package com.example.lactemperature;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ActivityMAJLacs extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modif_add_suppr_lac);

        //on associe à un objet java de type Button, un widget repéré physiquement par son id:
        Button buttonAjouterLac = findViewById(R.id.buttonAjouterLac);
        Button buttonSuppressionLac = (Button) findViewById(R.id.buttonSuppressionLac);
        Button buttonModifierLac = (Button) findViewById(R.id.buttonModifierLac);
        Button buttonRetour = (Button) findViewById(R.id.buttonRetourMenuLac);

        //on place un écouteur dessus:
        View.OnClickListener ecouteur1 = new View.OnClickListener() {
            //on implémente la méthode onclick
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.buttonAjouterLac:
                        Intent intent = new Intent(ActivityMAJLacs.this, ActivityAddLac.class);
                        startActivity(intent);
                        break;
                    case R.id.buttonSuppressionLac:
                        Intent intent2 = new Intent(ActivityMAJLacs.this, ActivitySupprLac.class);
                        startActivity(intent2);
                        break;
                    case R.id.buttonModifierLac:
                        Intent intent3 = new Intent(ActivityMAJLacs.this, ActivityModifierLac.class);
                        startActivity(intent3);
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
        buttonAjouterLac.setOnClickListener(ecouteur1);
        buttonSuppressionLac.setOnClickListener(ecouteur1);
        buttonModifierLac.setOnClickListener(ecouteur1);
        buttonRetour.setOnClickListener(ecouteur1);



    }
}