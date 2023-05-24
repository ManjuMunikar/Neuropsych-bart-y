package com.datagrandeur.neuropsych.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {
    private final SQLiteDatabase db;
    public static final int DATABASE_VERSION=1;
    public static final String DATABASE_NAME="bart-y.db";

    public Database(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        db=this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TrialRepository.CREATE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
