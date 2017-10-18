package com.example.mi.vorleplaner;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class VorlesungenActivity extends AppCompatActivity {

    private DBZugriff dbZugriff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vorlesungen);
        dbZugriff = new DBZugriff(this, DBZugriff.DB_NAME);

        Button btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbZugriff.getWritableDatabase();
                ContentValues daten = new ContentValues();
                daten.put(DBZugriff.COLUMN_VORLESUNGEN_NAME, "Vorlesung0");
                daten.put(DBZugriff.COLUMN_VORLESUNGEN_WEEKDAY, "Montag");
                daten.put(DBZugriff.COLUMN_VORLESUNGEN_STARTTIME, "11:30");
                daten.put(DBZugriff.COLUMN_VORLESUNGEN_ENDTIME, "13:00");
                db.insert(DBZugriff.TABLE_VORLESUNGEN, null, daten);

                daten = new ContentValues();
                daten.put(DBZugriff.COLUMN_VORLESUNGEN_NAME, "Vorlesung0");
                daten.put(DBZugriff.COLUMN_VORLESUNGEN_WEEKDAY, "Montag");
                daten.put(DBZugriff.COLUMN_VORLESUNGEN_STARTTIME, "11:30");
                daten.put(DBZugriff.COLUMN_VORLESUNGEN_ENDTIME, "13:00");
                db.insert(DBZugriff.TABLE_VORLESUNGEN, null, daten);

                daten = new ContentValues();
                daten.put(DBZugriff.COLUMN_VORLESUNGEN_NAME, "Vorlesung0");
                daten.put(DBZugriff.COLUMN_VORLESUNGEN_WEEKDAY, "Montag");
                daten.put(DBZugriff.COLUMN_VORLESUNGEN_STARTTIME, "11:30");
                daten.put(DBZugriff.COLUMN_VORLESUNGEN_ENDTIME, "13:00");
                db.insert(DBZugriff.TABLE_VORLESUNGEN, null, daten);

                daten = new ContentValues();
                daten.put(DBZugriff.TABLE_PROFESSOREN+"_id", "0");
                daten.put(DBZugriff.TABLE_VORLESUNGEN+"_id", "0");
                db.insert(DBZugriff.TABLE_VORLESUNGEN_PROFESSOREN, null, daten);

                daten = new ContentValues();
                daten.put(DBZugriff.TABLE_PROFESSOREN+"_id", "0");
                daten.put(DBZugriff.TABLE_VORLESUNGEN+"_id", "1");
                db.insert(DBZugriff.TABLE_VORLESUNGEN_PROFESSOREN, null, daten);

                daten = new ContentValues();
                daten.put(DBZugriff.TABLE_PROFESSOREN+"_id", "1");
                daten.put(DBZugriff.TABLE_VORLESUNGEN+"_id", "2");
                db.insert(DBZugriff.TABLE_VORLESUNGEN_PROFESSOREN, null, daten);
            }
        });

    }
}
