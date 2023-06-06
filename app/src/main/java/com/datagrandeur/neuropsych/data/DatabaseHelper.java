package com.datagrandeur.neuropsych.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private final SQLiteDatabase db;
    public static final String DATABASE_NAME = "bart-y.db";

    public static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        db= this.getWritableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase db) { //DB create vayesi oncreate call
        db.execSQL(UserRepository.CREATE);


    }

    public long insertUser(User user, SQLiteDatabase db){
        return UserRepository.insert(user, db);
    }
    public SQLiteDatabase getDb() {
        return db;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
