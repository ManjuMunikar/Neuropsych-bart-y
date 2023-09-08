package com.datagrandeur.neuropsych.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private final SQLiteDatabase db;
    private Pump pump;


    public DatabaseHelper(Context context) {
        super(context, Database.DATABASE_NAME, null, Database.DATABASE_VERSION);
        db= this.getWritableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(UserRepository.CREATE);
        db.execSQL(TrialRepository.CREATE);
        db.execSQL(PumpRepository.CREATE);

    }

    public long insertUser(User user, SQLiteDatabase db){

        return UserRepository.insert(user, db);
    }
    public long insertTrial( Trial trial, SQLiteDatabase db){
        return TrialRepository.insert(trial, db);
    }
    public long insertPump( Pump pump, SQLiteDatabase db){
        return PumpRepository.insert(pump, db);
    }
    public long updateTrial( Trial trial, SQLiteDatabase db){
        return TrialRepository.update(trial, db);
    }
    public SQLiteDatabase getDb() {
        return db;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }

}



