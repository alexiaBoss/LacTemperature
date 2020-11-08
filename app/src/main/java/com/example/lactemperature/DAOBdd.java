package com.example.lactemperature;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.lactemperature.Lac;


public class DAOBdd {

    // version de la bdd :
    static final int VERSION_BDD =2;
    // nom de la base de données
    private static final String NOM_BDD = "bddLacTemperature.db";

    // colonnes de la table Lac
    static final String TABLE_LAC = "tlac";
    static final String COL_ID = "_id";
    static final int NUM_COL_ID = 0;
    static final String COL_NOMLAC = "NomLac";
    static final int NUM_COL_NOMLAC = 1;
    static final String COL_LONGITUDE = "Longitude";
    static final int NUM_COL_LONGITUDE = 2;
    static final String COL_LATITUDE = "Latitude";
    static final int NUM_COL_LATITUDE = 3;

    // colonnes de la table relevé
    static final String TABLE_RELEVE = "treleve";
    static final String COL_IDRELEVE = "_id";
    static final int NUM_COL_IDRELEVE = 0;
    static final String COL_JOUR = "Jour";
    static final int NUM_COL_JOUR = 1;
    static final String COL_MOIS = "MOIS";
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


    // attributs permettant la création de la table lac dans la base
    private CreateBdd tableLac;
    private Context context;
    private SQLiteDatabase db;

    //le constructeur de la base de données
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

    public Lac cursorToArticle(Cursor c){ //Cette méthode permet de convertir un cursor en un lac
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

    public Lac getLacWithNomLac(String nomLac){
        //Récupère dans un Cursor les valeurs correspondant à un article grâce à sa designation)
        Cursor c = db.query(TABLE_LAC, new String[] {COL_ID,COL_NOMLAC, COL_LONGITUDE, COL_LATITUDE}, COL_NOMLAC + " LIKE \"" + nomLac +"\"", null, null, null, null);
        return cursorToArticle(c);
    }



    public Cursor getDataLac(){
        return db.rawQuery("" +
                "SELECT * FROM tlac", null);
    }


    //pour le relevé
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

    private Releve cursorToReleve(Cursor c){ //Cette méthode permet de convertir un cursor en un relevé
        //si aucun élément n'a été retourné dans la requête, on renvoie null
        if (c.getCount() == 0)
            return null;
        //Sinon
        c.moveToFirst(); //on se place sur le premier élément
        Releve unReleve = new Releve(0,0,0,0,0,0, null); //On créé un relevé
        //on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
        unReleve.setJour(c.getInt(NUM_COL_JOUR));
        unReleve.setMois(c.getInt(NUM_COL_MOIS));
        unReleve.setTempA6h(c.getDouble(NUM_COL_TEMPA6H));
        unReleve.setTempA12h(c.getDouble(NUM_COL_TEMPA12H));
        unReleve.setTempA18h(c.getDouble(NUM_COL_TEMPA18H));
        unReleve.setTempA24h(c.getDouble(NUM_COL_TEMPA24H));
        unReleve.setNomLac(c.getString(NUM_COL_NOMLACRELEVE));
        c.close(); //On ferme le cursor
        return unReleve; //On retourne le relevé
    }


    public Releve getReleveWithMoisAndLac(String nomLac, int mois){
        //Récupère dans un Cursor les valeurs correspondant à un relevé grâce au mois
        Cursor c = db.query(TABLE_RELEVE, new String[] {COL_IDRELEVE,COL_JOUR, COL_MOIS, COL_TEMPA6H, COL_TEMPA12H, COL_TEMPA18H, COL_TEMPA24H, COL_NOMLACRELEVE}, COL_NOMLACRELEVE + " = \"" + nomLac +"\"" + COL_MOIS + " = \"" + mois + "\"", null, null, null, null);
        return cursorToReleve(c);
    }

    public Cursor getDataReleve(){
        return db.rawQuery(
                "SELECT * FROM treleve", null);
    }
}

