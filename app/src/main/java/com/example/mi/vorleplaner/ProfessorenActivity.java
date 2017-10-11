package com.example.mi.vorleplaner;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class ProfessorenActivity extends AppCompatActivity {

    private static final String TAG = "PROFESSOREN_ACTIVITY";
    private DBZugriff db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professoren);
        db = new DBZugriff(this,"VorLePlaner");
        final Context thisActivity = this;
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(thisActivity);
                builder.setTitle("Hinzufügen");
                builder.setView(R.layout.input_professoren);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which){
                        Log.d(TAG,"ok");
                        ContentValues daten = new ContentValues();
                        Log.d( TAG, "name: "+ ((EditText)findViewById(R.id.textInputProfessoren)).getText().toString() );
                    }
                });
                builder.setNegativeButton("Abbrechen", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which){
                        Log.d(TAG,"Abbrechen");
                    }
                });
                builder.show();
            }
        });

    }
}
