//package com.datagrandeur.neuropsych;
//
//import androidx.activity.result.ActivityResult;
//import androidx.activity.result.ActivityResultLauncher;
//import androidx.activity.result.contract.ActivityResultContracts;
//import androidx.appcompat.app.ActionBar;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.app.ActivityCompat;
//import androidx.core.content.ContextCompat;
//
//import android.app.Activity;
//import android.content.Context;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.content.pm.PackageManager;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.net.Uri;
//import android.os.Bundle;
//import android.os.Environment;
//import android.preference.PreferenceManager;
//import android.provider.DocumentsContract;
//import android.util.Log;
//import android.view.MenuItem;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import com.datagrandeur.neuropsych.data.DatabaseHelper;
//import com.datagrandeur.neuropsych.utils.CSVWriter;
//import com.example.neuropsych.*;
//import com.example.neuropsych.R;
//
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.Objects;
//
//
//public class DataExportActivity extends AppCompatActivity {
//    private static final int REQUEST_CODE = 1;
//    private static final int CREATE_FILE = 1;
//    private ActivityResultLauncher<Intent> launcher; // Initialise this object in Activity.onCreate()
//    private Uri baseDocumentTreeUri;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_data_export);
//
//        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
//            if (result.getResultCode() == Activity.RESULT_OK) {
//                Intent data = result.getData();
//                if (data != null) {
//                    baseDocumentTreeUri = data.getData();
//                    // Perform any required operations with the selected Uri
//                    // ...
//                }
//            }
//        });
//
//        setupActionBar();
//        TextView tvDataExport = findViewById(R.id.tvDataExport);
//        tvDataExport.setText("Data Exporting In Progress");
//        export();
//
//
//        tvDataExport.setText("Data export completed");
//
//    }
//    public void launchBaseDirectoryPicker() {
//        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT_TREE);
//        launcher.launch(intent);
//    }
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (resultCode == Activity.RESULT_OK) {
//            if (requestCode == REQUEST_CODE) {
//                baseDocumentTreeUri = Objects.requireNonNull(data).getData();
//                final int takeFlags = (Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
//
//                // Take persistable Uri Permission for future use
//                getContentResolver().takePersistableUriPermission(data.getData(), takeFlags);
//                SharedPreferences preferences = getSharedPreferences("com.example.geofriend.fileutility", Context.MODE_PRIVATE);
//                preferences.edit().putString("filestorageuri", data.getData().toString()).apply();
//            }
//        } else {
//            Log.e("FileUtility", "Some Error Occurred: " + resultCode);
//        }
//    }
//
//
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        int id = item.getItemId();
//
//        if (id == android.R.id.home) {
//            startActivity(new Intent(DataExportActivity.this, MainActivity.class));
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//
//    }
//
//    private void setupActionBar() {
//        ViewGroup rootView = (ViewGroup) findViewById(R.id.action_bar_root); //id from appcompat
//
//        if (rootView != null) {
//            View view = getLayoutInflater().inflate(R.layout.toolbar_setting, rootView, false);
//            rootView.addView(view, 0);
//
//            setSupportActionBar(findViewById(R.id.toolbar));
//
//        }
//
//        ActionBar actionBar = getSupportActionBar();
//        if (actionBar != null) {
//            // Show the Up button in the action bar.
//            actionBar.setDisplayHomeAsUpEnabled(true);
//        }
//    }
//
//
//    private void export() {
//
//        DatabaseHelper dbHelper = new DatabaseHelper(this);
//
//
//
//        private void createFile(Uri pickerInitialUri) {
//            Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
//            intent.addCategory(Intent.CATEGORY_OPENABLE);
//            intent.setType("application/csv");
//            intent.putExtra(Intent.EXTRA_TITLE, "barty.csv");
//
//            // Optionally, specify a URI for the directory that should be opened in
//            // the system file picker when your app creates the document.
//            intent.putExtra(DocumentsContract.EXTRA_INITIAL_URI, pickerInitialUri);
//
//            startActivityForResult(intent, CREATE_FILE);
//        }
//
////        File exportDir = new File(Environment.getExternalStorageDirectory(), "barty");
////
////        if(!exportDir.exists()){
////            exportDir.mkdir();
////
////        }
////        Log.w("Data Export", "Created file");
////
////        Cursor result = null;
////        File file = new File(exportDir, "barty");
////        try{
////            file.createNewFile();
////            CSVWriter csvWriter = new CSVWriter(new FileWriter(file));
////            SQLiteDatabase db = dbHelper.getReadableDatabase();
////            result= db.rawQuery("SELECT * FROM User", null);
////            while(result.moveToNext()){
////                String arrStr[] ={
////                        result.getColumnName(0),
////                        result.getColumnName(1)
////                };
////                csvWriter.writeNext(arrStr);
////            }
////            csvWriter.close();
////            result.close();
////
////            Log.i("Data Export", "Created file");
////
////
////        }catch (IOException e){
////            Log.e("Data Export", e.getMessage(), e);
////
////        }finally {
////            if(result!=null && !result.isClosed()){
////                result.close();
////            }
////        }
////
////
//
//
//
//    }
//}