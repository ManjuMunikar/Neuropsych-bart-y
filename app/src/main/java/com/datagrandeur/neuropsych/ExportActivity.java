package com.datagrandeur.neuropsych;

import static android.os.Environment.DIRECTORY_DOCUMENTS;
import static android.os.Environment.DIRECTORY_DOWNLOADS;
import static android.os.Environment.DIRECTORY_MOVIES;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.datagrandeur.neuropsych.data.DatabaseHelper;
import com.datagrandeur.neuropsych.utils.CSVWriter;
import com.example.neuropsych.R;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ExportActivity extends AppCompatActivity {

    public static String[] permissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            return new String[]{
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.MANAGE_EXTERNAL_STORAGE

            };
        } else {
            return new String[]{

                    android.Manifest.permission.READ_MEDIA_IMAGES,
                    android.Manifest.permission.READ_MEDIA_AUDIO,
                    Manifest.permission.READ_MEDIA_VIDEO

            };
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_export);


        TextView tvDataExport = findViewById(R.id.tvDataExport);
        setupActionBar();
        tvDataExport.setText("Data Exporting In Progress");
        export();


        tvDataExport.setText("Data export completed");

   }

    private void export() {

        DatabaseHelper dbHelper = new DatabaseHelper(this);
        if(ContextCompat.checkSelfPermission(ExportActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(ExportActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        }

        //ActivityCompat.requestPermissions(this, permissions(), 23);
        //File exportDir = new File(getApplicationContext().getFilesDir(), "barty");
        //File exportDir = new File(getApplicationContext().getExternalFilesDir(DIRECTORY_DOCUMENTS), "barty");

        File exportDir = new File(Environment.getExternalStoragePublicDirectory(DIRECTORY_DOWNLOADS), "barty");
        if(!exportDir.exists()){
            exportDir.mkdir();
        }
        Log.w("Data Export", "Created file");
        Cursor result = null;
        File file = new File(exportDir, "barty.csv");
        try{
            file.createNewFile();
            CSVWriter csvWriter = new CSVWriter(new FileWriter(file));
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            result= db.rawQuery("SELECT * FROM User", null);
            csvWriter.writeNext(result.getColumnNames());
            while(result.moveToNext()){
                String arrStr[] ={
                        result.getString(0),
                        result.getString(1)
                };
                csvWriter.writeNext(arrStr);
            }
            csvWriter.close();
            result.close();

            Log.i("Data Export", "Created file");


        }catch (IOException e){
            Log.e("Data Export", e.getMessage(), e);

        }finally {
            if(result!=null && !result.isClosed()){
                result.close();
            }
        }

    }

    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == android.R.id.home) {
            startActivity(new Intent(ExportActivity.this, MainActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);

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