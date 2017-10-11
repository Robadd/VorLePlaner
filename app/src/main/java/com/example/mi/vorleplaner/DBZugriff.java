package com.example.mi.vorleplaner;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by 5deb on 10.10.17.
 */

public class DBZugriff extends SQLiteOpenHelper {

    private SQLiteDatabase db;
    public static final String TAG = "DBZugriff";

    public DBZugriff(Context activity, String dbName) {
        super(activity, dbName, null, 1);
        db = getWritableDatabase();
    }

    public void onCreate(SQLiteDatabase db){
        try{
            String tableVorlesungen = "CREATE TABLE Vorlesungen (id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    "name VARCHAR(50) NOT NULL, "+
                    "vorlesungsZeit_id INTEGER NOT NULL)";
            String tableProfessoren = "CREATE TABLE Professoren (id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    "name VARCHAR(50) NOT NULL)";
            String tableZeiten = "CREATE TABLE Zeiten (id INTEGER PRIMARY KEY AUTOINCREMEN),"+
                    "wochentag VARCHAR(10) NOT NULL,"+
                    "uhrzeit VARCHAR(10) NOT NULL)";
            String tableVorlProf = "CREATE TABLE Vorlesungs_Professor (id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    "vorlesung_id INTEGER NOT NULL,"+
                    "professor_id INTEGER NOT NULL)";

            db.execSQL(tableProfessoren);
            db.execSQL(tableVorlesungen);
            db.execSQL(tableZeiten);
            db.execSQL(tableVorlProf);


        } catch(Exception e){
            Log.e(TAG, e.getMessage());
        }
    }


    public void onUpgrade(SQLiteDatabase db, int a, int b){

    }

    public synchronized void close(){
        if(db != null){
            db.close();
            db = null;
        }
        super.close();
    }



}
