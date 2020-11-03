package com.example.lactemperature;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CreateBdd extends SQLiteOpenHelper {
    private static final String TABLE_LAC = "tlac";
    static final String COL_ID = "_id";
    private static final String COL_NOM_LAC = "NomLac";
    private static final String COL_LONGITUDE = "Longitude";
    private static final String COL_LATITUDE = "Latitude";
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_LAC + " ("+COL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+ COL_NOM_LAC + " TEXT NOT NULL, " + COL_LONGITUDE + " DOUBLE NOT NULL, " + COL_LATITUDE + " DOUBLE NOT NULL);";
    private static final String TABLE_RELEVE = "treleve";
    static final String COL_IDRELEVE = "_id";
    private static final String COL_JOUR = "Jour";
    private static final String COL_MOIS = "Mois";
    private static final String COL_TEMPA6H= "TempA6h";
    private static final String COL_TEMPA12H = "TempA12h";
    private static final String COL_TEMPA18H = "TempA18h";
    private static final String COL_TEMPA24H = "TempA24h";
    private static final String COL_NOMLACRELEVE = "Lac";
    private static final String CREATE_TABLERELEVE = "CREATE TABLE " + TABLE_RELEVE + " ("+COL_IDRELEVE+" INTEGER PRIMARY KEY AUTOINCREMENT,"+ COL_JOUR + " INTEGER NOT NULL, " + COL_MOIS + " INTEGER NOT NULL, " + COL_TEMPA6H + " DOUBLE NOT NULL," + COL_TEMPA12H + " DOUBLE NOT NULL,"+ COL_TEMPA18H + " DOUBLE NOT NULL,"+ COL_TEMPA24H + " DOUBLE NOT NULL ,"+ COL_NOMLACRELEVE + " TEXT NOT NULL," + " Foreign Key ("+COL_NOMLACRELEVE+") REFERENCES "+ TABLE_LAC + "("+COL_NOM_LAC+"))";

    //constructeur paramétré
    public CreateBdd(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    //méthodes d'instance permettant la gestion de l'objet
    @Override
    public void onCreate(SQLiteDatabase db) {
        //appelée lorsqu’aucune base n’existe et que la classe utilitaire doit en créer une
        //on créé la table à partir de la requête écrite dans la variable CREATE_BDD
        db.execSQL(CREATE_TABLE);
        db.execSQL(CREATE_TABLERELEVE);
    }
    // appelée si la version de la base a changé

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //On peut supprimer la table et la recréer
        db.execSQL("DROP TABLE " + TABLE_LAC + ";");
        db.execSQL("DROP TABLE " + TABLE_RELEVE + ";");
        onCreate(db);
    }
}

