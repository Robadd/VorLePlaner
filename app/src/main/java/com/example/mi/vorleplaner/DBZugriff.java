package com.example.mi.vorleplaner;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by 5deb on 10.10.17.
 */

public class DBZugriff extends SQLiteOpenHelper {

    public static final String DB_NAME = "vorleplaner.db";

    public static final String TABLE_PROFESSOREN = "Professoren";
    public static final String TABLE_VORLESUNGEN = "Vorlesungen";
    public static final String TABLE_VORLESUNGEN_PROFESSOREN = "Vorlesungs_Professor";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_PROFESSOREN_NAME = "Name";
    public static final String COLUMN_VORLESUNGEN_NAME = "Name";
    public static final String COLUMN_VORLESUNGEN_WEEKDAY = "Wochentag";
    public static final String COLUMN_VORLESUNGEN_STARTTIME = "Startzeit";
    public static final String COLUMN_VORLESUNGEN_ENDTIME = "Endzeit";

    private SQLiteDatabase db;
    public static final String TAG = "DBZugriff";

    public DBZugriff(Context activity, String dbName) {
        super(activity, dbName, null, 1);
        db = getWritableDatabase();
    }

    public void onCreate(SQLiteDatabase db){
        try{
            String tableVorlesungen = "CREATE TABLE " + TABLE_VORLESUNGEN + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    COLUMN_VORLESUNGEN_NAME + " VARCHAR(50) NOT NULL, "+
                    COLUMN_VORLESUNGEN_WEEKDAY + " INTEGER NOT NULL, " +
                    COLUMN_VORLESUNGEN_STARTTIME + " INTEGER NOT NULL, " +
                    COLUMN_VORLESUNGEN_ENDTIME + " INTEGER NOT NULL)";

            String tableProfessoren = "CREATE TABLE " + TABLE_PROFESSOREN + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    COLUMN_PROFESSOREN_NAME + " VARCHAR(50) NOT NULL)";

            String tableVorlProf = "CREATE TABLE " + TABLE_VORLESUNGEN_PROFESSOREN + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    TABLE_PROFESSOREN + "_id INTEGER NOT NULL, "+
                    TABLE_VORLESUNGEN + "_id INTEGER NOT NULL)";

            db.execSQL(tableProfessoren);
            db.execSQL(tableVorlesungen);
            db.execSQL(tableVorlProf);
            Log.d(TAG, "DB created");
        } catch(Exception e){
            Log.e(TAG, e.getMessage());
        }
    }

    public void onUpgrade(SQLiteDatabase db, int a, int b){}

    public synchronized void close(){
        if(db != null){
            db.close();
            db = null;
        }
        super.close();
    }
}
