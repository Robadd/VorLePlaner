package com.example.mi.vorleplaner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class VorlesungenActivity extends AppCompatActivity {

    private DBZugriff db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vorlesungen);
        db = new DBZugriff(this,"VorLePlaner");
    }
}
