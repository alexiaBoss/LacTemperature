package com.example.lactemperature;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.lactemperature.Lac;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class DAOBdd {

    static final int VERSION_BDD =11;
    private static final String NOM_BDD = "bddLacTemperature.db";

    //table Lac
    static final String TABLE_LAC = "tlac";
    static final String COL_ID = "_id";
    static final int NUM_COL_ID = 0;
    static final String COL_NOMLAC = "NomLac";
    static final int NUM_COL_NOMLAC = 1;
    static final String COL_LONGITUDE = "Longitude";
    static final int NUM_COL_LONGITUDE = 2;
    static final String COL_LATITUDE = "Latitude";
    static final int NUM_COL_LATITUDE = 3;

    //table relevé
    static final String TABLE_RELEVE = "treleve";
    static final String COL_IDRELEVE = "_id";
    static final int NUM_COL_IDRELEVE = 0;
    static final String COL_JOUR = "Jour";
    static final int NUM_COL_JOUR = 1;
    static final String COL_MOIS = "Mois";
    static final int NUM_COL_MOIS = 2;
    static final String COL_TEMPA6H = "TempA6h";
    static final int NUM_COL_TEMPA6H = 3;
    static final String COL_TEMPA12H = "TempA12h";
    static final int NUM_COL_TEMPA12H = 4;
    static final String COL_TEMPA18H = "TempA18h";
    static final int NUM_COL_TEMPA18H = 5;
    static final String COL_TEMPA24H = "TempA24h";
    static final int NUM_COL_TEMPA24H = 6;
    static final String COL_NOMLACRELEVE = "Lac";
    static final int NUM_COL_NOMLACRELEVE = 7;

    //table historique
    static final String TABLE_HISTORIQUE = "thistorique";
    static final String COL_IDRELEVEH = "_id";
    static final int NUM_COL_IDRELEVEH = 0;
    static final String COL_NOMLACH = "Lac";
    static final int NUM_COL_NOMLACH = 1;
    static final String COL_LONGITUDEH = "Longitude";
    static final int NUM_COL_LONGITUDEH = 2;
    static final String COL_LATITUDEH = "Latitude";
    static final int NUM_COL_LATITUDEH = 3;
    static final String COL_JOURH = "Jour";
    static final int NUM_COL_JOURH = 4;
    static final String COL_MOISH = "Mois";
    static final int NUM_COL_MOISH = 5;
    static final String COL_TEMPA6HH = "TempA6h";
    static final int NUM_COL_TEMPA6HH = 6;
    static final String COL_TEMPA12HH = "TempA12h";
    static final int NUM_COL_TEMPA12HH = 7;
    static final String COL_TEMPA18HH = "TempA18h";
    static final int NUM_COL_TEMPA18HH = 8;
    static final String COL_TEMPA24HH = "TempA24h";
    static final int NUM_COL_TEMPA24HH = 9;
    static final String COL_NOMLACRELEVEH = "Lac";
    static final int NUM_COL_NOMLACRELEVEH = 10;


    private CreateBdd tableLac;
    private Context context;
    private SQLiteDatabase db;
    //le constructeur
    public DAOBdd(Context context){
        this.context = context;
        tableLac = new CreateBdd(context, NOM_BDD, null, VERSION_BDD);
    }
    //si la bdd n’existe pas, l’objet SQLiteOpenHelper exécute la méthode onCreate
    // si la version de la base a changé, la méthode onUpgrade sera lancée
    // dans les 2 cas l’appel à getWritableDatabase ou getReadableDatabase renverra la base
    // de données en cache, nouvellement ouverte, nouvellement créée ou mise à jour
    //les méthodes d'instance
    public DAOBdd open(){
        db = tableLac.getWritableDatabase();
        return this;
    }
    public DAOBdd close (){
        db.close();
        return null;
    }
    //methode pour inserer un lac
    public long insererLac (Lac unLac){
        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associé à une clé (qui est le nom de la colonne où on veut mettre la valeur)
        values.put(COL_NOMLAC, unLac.getNomLac());
        values.put(COL_LONGITUDE, unLac.getLongitude());
        values.put(COL_LATITUDE, unLac.getLatitude());
        //on insère l'objet dans la BDD via le ContentValues
        return db.insert(TABLE_LAC, null, values);
    }

    //methode pour mettre un jour un lac
    public long updateLac (String nomLac, double longitude, double latitude){
        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associé à une clé (qui est le nom de la colonne où on veut mettre la valeur)
        values.put(COL_LONGITUDE, longitude);
        values.put(COL_LATITUDE, latitude);
        //on insère l'objet dans la BDD via le ContentValues
        return db.update(TABLE_LAC, values, COL_NOMLAC + " LIKE \"" + nomLac +"\"" ,null );
    }

    //Cette méthode permet de convertir un cursor en un lac
    public Lac cursorToArticle(Cursor c){
        //si aucun élément n'a été retourné dans la requête, on renvoie null
        if (c.getCount() == 0)
            return null;
        //Sinon
        c.moveToFirst(); //on se place sur le premier élément
        Lac unLac = new Lac(null,0,0); //On créé un lac
        //on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
        unLac.setNomLac(c.getString(NUM_COL_NOMLAC));
        unLac.setLongitude(c.getDouble(NUM_COL_LONGITUDE));
        unLac.setLatitude(c.getDouble(NUM_COL_LATITUDE));
        c.close(); //On ferme le cursor
        return unLac; //On retourne le lac
    }


//recupération d'un lac grace a son nom
    public Lac getLacWithNomLac(String nomLac){
        //Récupère dans un Cursor les valeurs correspondant à un article grâce à sa designation)
        Cursor c = db.query(TABLE_LAC, new String[] {COL_ID,COL_NOMLAC, COL_LONGITUDE, COL_LATITUDE}, COL_NOMLAC + " LIKE \"" + nomLac +"\"", null, null, null, null);
        return cursorToArticle(c);
    }


//récupération de toutes les données du lac
    public Cursor getDataLac(){
        return db.rawQuery("SELECT * FROM tlac", null);
    }

    //suppression d'un lac grace a son nom
    public int deleteLac(String nomLac){ return db.delete(TABLE_LAC,COL_NOMLAC + " LIKE \"" + nomLac +"\"",null);}

    //pour le relevé

    //insertion d'un relevé
    public long insererReleve (Releve unReleve){
        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associé à une clé (qui est le nom de la colonne où on veut mettre la valeur)
        values.put(COL_JOUR, unReleve.getJour());
        values.put(COL_MOIS, unReleve.getMois());
        values.put(COL_TEMPA6H, unReleve.getTempA6h());
        values.put(COL_TEMPA12H, unReleve.getTempA12h());
        values.put(COL_TEMPA18H, unReleve.getTempA18h());
        values.put(COL_TEMPA24H, unReleve.getTempA24h());
        values.put(COL_NOMLACRELEVE, unReleve.getNomLac());
        //on insère l'objet dans la BDD via le ContentValues
        return db.insert(TABLE_RELEVE, null, values);
    }

    //mise a jour du relevé de 6h
    public long updateReleve6h (String nomLac, int mois, int jour , double temp){
        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associé à une clé (qui est le nom de la colonne où on veut mettre la valeur)
        values.put(COL_TEMPA6H, temp);
        //on insère l'objet dans la BDD via le ContentValues
        return db.update(TABLE_RELEVE, values, COL_NOMLACRELEVE + " LIKE \"" + nomLac +"\" AND " + COL_MOIS + " = " + mois+" AND " + COL_JOUR + " = " + jour,null );
    }

    //mise a jour du relevé de 12h
    public long updateReleve12h (String nomLac, int mois, int jour , double temp){
        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associé à une clé (qui est le nom de la colonne où on veut mettre la valeur)
        values.put(COL_TEMPA12H, temp);
        //on insère l'objet dans la BDD via le ContentValues
        return db.update(TABLE_RELEVE, values, COL_NOMLACRELEVE + " LIKE \"" + nomLac +"\" AND " + COL_MOIS + " = " + mois+" AND " + COL_JOUR + " = " + jour,null );
    }
    //mise a jour du relevé de 18h
    public long updateReleve18h (String nomLac, int mois, int jour , double temp){
        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associé à une clé (qui est le nom de la colonne où on veut mettre la valeur)
        values.put(COL_TEMPA18H, temp);
        //on insère l'objet dans la BDD via le ContentValues
        return db.update(TABLE_RELEVE, values, COL_NOMLACRELEVE + " LIKE \"" + nomLac +"\" AND " + COL_MOIS + " = " + mois+" AND " + COL_JOUR + " = " + jour,null );
    }
    //mise a jour du relevé de 24h
    public long updateReleve24h (String nomLac, int mois, int jour , double temp){
        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associé à une clé (qui est le nom de la colonne où on veut mettre la valeur)
        values.put(COL_TEMPA24H, temp);
        //on insère l'objet dans la BDD via le ContentValues
        return db.update(TABLE_RELEVE, values, COL_NOMLACRELEVE + " LIKE \"" + nomLac +"\" AND " + COL_MOIS + " = " + mois+" AND " + COL_JOUR + " = " + jour,null );
    }

    //Cette méthode permet de convertir un cursor en un relevé
    private Releve cursorToReleve(Cursor c){

        //si aucun élément n'a été retourné dans la requête, on renvoie null
        if (c.getCount() == 0) {
            Releve unReleve = new Releve(0, 0, 0, 0, 0, 0, null);
            return unReleve;
        }
        //Sinon
        c.moveToLast();

            //on se place sur le premier élément
            Releve unReleve = new Releve(0, 0, 0, 0, 0, 0, null); //On créé un relevé

            //on lui affecte toutes les infos grâce aux infos contenues dans le Cursor

            unReleve.setJour(c.getInt(0));
            unReleve.setMois(c.getInt(1));
            unReleve.setTempA6h(c.getDouble(2));
            unReleve.setTempA12h(c.getDouble(3));
            unReleve.setTempA18h(c.getDouble(4));
            unReleve.setTempA24h(c.getDouble(5));
            unReleve.setNomLac(c.getString(6));


        c.close(); //On ferme le cursor
        return unReleve; //On retourne le relevé
    }

    ////Cette méthode permet de convertir un cursor en une liste de releve
    public ArrayList<Releve> cursortoListeReleve(Cursor c) {
        ArrayList<Releve> lesReleves = new ArrayList<>();

        if (c.getCount() > 0) {

            //c.moveToFirst();
            //on se place sur le premier élément
            while (c.moveToNext()) {
                Releve unReleve = new Releve(0, 0, 0, 0, 0, 0, null); //On créé un relevé
                //on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
                unReleve.setJour(c.getInt(0));
                unReleve.setMois(c.getInt(1));
                unReleve.setTempA6h(c.getDouble(2));
                unReleve.setTempA12h(c.getDouble(3));
                unReleve.setTempA18h(c.getDouble(4));
                unReleve.setTempA24h(c.getDouble(5));
                unReleve.setNomLac(c.getString(6));
                lesReleves.add(unReleve);

            }
        }
        c.close();
        return lesReleves;
    }
    //recupération des relevés grace au mois et au nom du lac
    public ArrayList<Releve> getReleveWithMoisAndLac(String nomLac, int mois){
        //Récupère dans un Cursor les valeurs correspondant à un relevé grâce au mois, Lac;
        Cursor c = db.query(TABLE_RELEVE, new String[] { COL_JOUR, COL_MOIS, COL_TEMPA6H, COL_TEMPA12H, COL_TEMPA18H, COL_TEMPA24H, COL_NOMLACRELEVE}, COL_NOMLACRELEVE + " LIKE \"" + nomLac +"\" AND " + COL_MOIS + " = " + mois, null, null, null, null);
        ArrayList <Releve> lesReleves = cursortoListeReleve(c);
        return lesReleves;

    }

    //recupération des relevés grace au nom du lac
    public ArrayList<Releve> getReleveWithLac(String nomLac){
        //Récupère dans un Cursor les valeurs correspondant à un relevé Lac;
        Cursor c = db.query(TABLE_RELEVE, new String[] { COL_JOUR, COL_MOIS, COL_TEMPA6H, COL_TEMPA12H, COL_TEMPA18H, COL_TEMPA24H, COL_NOMLACRELEVE}, COL_NOMLACRELEVE + " LIKE \"" + nomLac +"\"" , null, null, null, null);
        ArrayList <Releve> lesReleves = cursortoListeReleve(c);
        return lesReleves;

    }

    //recupérer les relevé grace au mois , au jour et au lac
    public Releve getReleveWithMoisAndJourAndLac(String nomLac, int mois, int jour){
        //Récupère dans un Cursor les valeurs correspondant à un relevé grâce au mois, jour, lac et heure
        Cursor c = db.query(TABLE_RELEVE, new String[] { COL_JOUR,COL_MOIS,COL_TEMPA6H, COL_TEMPA12H, COL_TEMPA18H, COL_TEMPA24H, COL_NOMLACRELEVE}, COL_NOMLACRELEVE + " LIKE \"" + nomLac +"\" AND " + COL_MOIS + " = " + mois+" AND " + COL_JOUR + " = " + jour, null, null, null, null);
        return cursorToReleve(c);
    }

    ////recupérer les relevé grace au mois , au jour et au lac
    public Releve getTempByLacAndHeureAndDate(String nomLac, int mois, int jour){
        //Récupère dans un Cursor les valeurs correspondant à un relevé grâce au mois, jour, lac et heure
        Cursor c = db.query(TABLE_RELEVE, new String[] { COL_JOUR, COL_MOIS, COL_TEMPA6H, COL_TEMPA12H, COL_TEMPA18H, COL_TEMPA24H, COL_NOMLACRELEVE}, COL_NOMLACRELEVE + " LIKE \"" + nomLac +"\" AND " + COL_MOIS + " = " + mois+" AND " + COL_JOUR + " = " + jour, null, null, null, null);
        return cursorToReleve(c);
    }

    //récupérer toutes les données de la table releve
    public Cursor getDataReleve(){
        return db.rawQuery("SELECT * FROM treleve", null);
    }


    //pour l' Historique
    //pour inserer un histoirque
    public void insererHistorique (Lac unLac){
        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        ArrayList<Releve> lesReleves = getReleveWithLac(unLac.getNomLac());
        for(Releve unReleve : lesReleves) {
            //on lui ajoute une valeur associé à une clé (qui est le nom de la colonne où on veut mettre la valeur)
            values.put(COL_NOMLACH, unLac.getNomLac());
            values.put(COL_LONGITUDEH, unLac.getLongitude());
            values.put(COL_LATITUDEH, unLac.getLatitude());
            values.put(COL_JOURH, unReleve.getJour());
            values.put(COL_MOISH, unReleve.getMois());
            values.put(COL_TEMPA6HH, unReleve.getTempA6h());
            values.put(COL_TEMPA12HH, unReleve.getTempA12h());
            values.put(COL_TEMPA18HH, unReleve.getTempA18h());
            values.put(COL_TEMPA24HH, unReleve.getTempA24h());
            db.insert(TABLE_HISTORIQUE, null, values);
        }

    }

    //recupérér toutes les données de la table historique
    public Cursor getDataHistorique(){
        return db.rawQuery("SELECT * FROM thistorique", null);
    }
}
