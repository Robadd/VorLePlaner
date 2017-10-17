package com.example.mi.vorleplaner;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class ProfessorenActivity extends AppCompatActivity {

    private static final String TAG = "PROFESSOREN_ACTIVITY";
    private DBZugriff dbZugriff;
    private static Cursor cursor;

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
                        refreshView();
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
        refreshView();
    }

    private void refreshView(){
        cursor = DBZugriff.createListViewCursor(DBZugriff.TABLE_PROFESSOREN);
        ListView anzeigeListe = (ListView) findViewById(R.id.listViewProfessoren);
        String[] anzeigeSpalten = new String[]{DBZugriff.COLUMN_PROFESSOREN_NAME};
        int[] anzeigeViews = new int[]{R.id.professorenTextViewName};

        final SimpleCursorAdapter adapter;
        adapter = new SimpleCursorAdapter(this, R.layout.dataset_professoren, cursor, anzeigeSpalten, anzeigeViews, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);

        adapter.setViewBinder(new SimpleCursorAdapter.ViewBinder(){

            public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
               // keine Änderung
                Log.d(TAG, String.valueOf(columnIndex));
                return false;
            }
        });

        anzeigeListe.setAdapter(adapter);
        anzeigeListe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Cursor tmpCursor = (Cursor)adapterView.getItemAtPosition(i);

                Log.d(TAG, tmpCursor.getString(1));
            }
        });
    }


}
