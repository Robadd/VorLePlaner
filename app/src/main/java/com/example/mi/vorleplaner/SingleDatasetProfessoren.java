package com.example.mi.vorleplaner;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class SingleDatasetProfessoren extends AppCompatActivity {

    private static final String TAG = "SINGLE_DATASET_PROFESSO";
    private DBZugriff dbZugriff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_dataset_professoren);
        Bundle b = getIntent().getExtras();
        long id = b.getLong("id");
        String name = b.getString("name");
        dbZugriff = new DBZugriff(this, DBZugriff.DB_NAME);
        SQLiteDatabase db = dbZugriff.getReadableDatabase();
        TextView titel = (TextView) findViewById(R.id.textViewProfessorenName);
        titel.setText(name);

        String rawQuery = "SELECT * FROM " + DBZugriff.TABLE_VORLESUNGEN + " INNER JOIN " + DBZugriff.TABLE_VORLESUNGEN_PROFESSOREN + " ON " +
                DBZugriff.TABLE_VORLESUNGEN+"."+DBZugriff.COLUMN_ID+"="+DBZugriff.TABLE_VORLESUNGEN_PROFESSOREN+"."+DBZugriff.TABLE_VORLESUNGEN+"_id "+
                "WHERE "+DBZugriff.TABLE_VORLESUNGEN_PROFESSOREN+"." + DBZugriff.TABLE_PROFESSOREN + "_id=?";

        Cursor cursor = db.rawQuery(rawQuery, new String[]{String.valueOf(id)});

        ListView anzeigeListe = (ListView) findViewById(R.id.listViewVorlesungenDatasetProfessoren);
        String[] anzeigeSpalten = new String[]{DBZugriff.COLUMN_VORLESUNGEN_NAME, DBZugriff.COLUMN_VORLESUNGEN_WEEKDAY, DBZugriff.COLUMN_VORLESUNGEN_STARTTIME, DBZugriff.COLUMN_VORLESUNGEN_ENDTIME};
        int[] anzeigeViews = new int[]{R.id.textViewVorlesungName, R.id.textViewVorlesungWochentag, R.id.textViewVorlesungStart, R.id.textViewVorlesungEnde};

        final SimpleCursorAdapter adapter;
        adapter = new SimpleCursorAdapter(this, R.layout.dataset_vorlesungen_uhrzeiten, cursor, anzeigeSpalten, anzeigeViews, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);

        adapter.setViewBinder(new SimpleCursorAdapter.ViewBinder(){

            public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
                // keine Änderung
                Log.d(TAG, cursor.getString(1));
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
