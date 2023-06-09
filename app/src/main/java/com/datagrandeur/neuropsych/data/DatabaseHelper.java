package com.datagrandeur.neuropsych.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private final SQLiteDatabase db;


    public DatabaseHelper(Context context) {
        super(context, Database.DATABASE_NAME, null, Database.DATABASE_VERSION);
        db= this.getWritableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase db) { //DB create vayesi oncreate call
        db.execSQL(UserRepository.CREATE);
        db.execSQL(TrialRepository.CREATE);

    }

    public long insertUser(User user, SQLiteDatabase db){
        return UserRepository.insert(user, db);
    }
    public long insertTrial( Trial trial, SQLiteDatabase db){
        return TrialRepository.insert(trial, db);
    }
    public SQLiteDatabase getDb() {
        return db;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}



