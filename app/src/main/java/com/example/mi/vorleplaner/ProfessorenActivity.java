package com.example.mi.vorleplaner;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class ProfessorenActivity extends AppCompatActivity {

    private static final String TAG = "PROFESSOREN_ACTIVITY";
    private DBZugriff dbZugriff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professoren);
        dbZugriff = new DBZugriff(this,"VorLePlaner");
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater inflater = getLayoutInflater();
                View alertLayout = inflater.inflate(R.layout.input_professoren, null);
                final EditText inputName = (EditText) alertLayout.findViewById(R.id.textInputProfessoren);

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Hinzufügen");
                builder.setView(alertLayout);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which){
                        ContentValues daten = new ContentValues();
                        String name = inputName.getText().toString();
                        daten.put(DBZugriff.COLUMN_PROFESSOREN_NAME, name);
                        SQLiteDatabase db = dbZugriff.getWritableDatabase();
                        long insertId = db.insert(DBZugriff.TABLE_PROFESSOREN, null, daten);
                        Log.d(TAG, String.valueOf(insertId));
                    }
                });

                builder.setNegativeButton("Abbrechen", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which){

                    }
                });

                builder.show();
            }
        });

    }
}
