package com.datagrandeur.neuropsych;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Environment;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.datagrandeur.neuropsych.data.DatabaseHelper;
import com.example.neuropsych.Manifest;
import com.example.neuropsych.R;

import java.io.File;
import java.util.prefs.Preferences;

public class DataExportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_export);
        setupActionBar();

        TextView tvDataExport  = findViewById(R.id.tvDataExport);
        tvDataExport.setText("Data Exporting In Progress");
        export();

//        if (getIntent().getStringExtra("preferenceKey").equals("btnExport")) {
//            // Perform your desired action here
//            export();
//        }

        tvDataExport.setText("Data export completed");

    }
    private void export() {

        DatabaseHelper dbHelper = new DatabaseHelper(this);
        if(ContextCompat.checkSelfPermission(DataExportActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(DataExportActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);

            File exportDir = new File(Environment.getExternalStorageDirectory(),"barty");

            if (!exportDir.exists())
            {
                exportDir.mkdirs();
            }

            Log.w("Data Export", "Created file");

            Cursor curCSV = null;

            File file = new File(exportDir, "barty.csv");

            SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()); // 0 - for private mode
            String timeline = pref.getString("prefEnterTimeline", "");



        }


    }
    private void setupActionBar() {
        ViewGroup rootView = (ViewGroup) findViewById(R.id.action_bar_root); //id from appcompat

        if (rootView != null) {
            View view = getLayoutInflater().inflate(R.layout.toolbar_setting, rootView, false);
            rootView.addView(view, 0);

            setSupportActionBar(findViewById(R.id.toolbar));

        }

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Show the Up button in the action bar.
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }


}