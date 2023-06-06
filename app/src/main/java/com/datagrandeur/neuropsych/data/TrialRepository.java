package com.datagrandeur.neuropsych.data;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

final class TrialRepository implements BaseColumns {
    private TrialRepository(){}
    public static final String TABLE_NAME="trial";
    public static final String TRIAL_ID="trial_id";
    public static final String COLUMN_NAME_BALLOON_START_HEIGHT="balloon_start_height";
    public static final String COLUMN_NAME_BALLOON_START_WIDTH="balloon_start_width";
    public static final String COLUMN_NAME_TIME_BEFORE_FIRST_PUMP="time_before_first_pump";
    public static final String COLUMN_NAME_TIME_BTW_LAST_PUMP_AND_COLLECT="time_btw_last_pump_and_collect";

    public  static final String CREATE=
            "CREATE TABLE "+ TrialRepository.TABLE_NAME+"("+
                    TrialRepository._ID + "INTEGER PRIMARY KEY AUTOINCREMENT," +
                    TrialRepository.TRIAL_ID +"TEXT," +
                    TrialRepository.COLUMN_NAME_BALLOON_START_HEIGHT +"TEXT," +
                    TrialRepository.COLUMN_NAME_BALLOON_START_WIDTH +"TEXT," +
                    TrialRepository.COLUMN_NAME_TIME_BEFORE_FIRST_PUMP +"TEXT," +
                    TrialRepository.COLUMN_NAME_TIME_BTW_LAST_PUMP_AND_COLLECT +"TEXT," +
                    ")";
    public static long insert(Trial trial, SQLiteDatabase db){
        ContentValues values=new ContentValues();

        values.put(TrialRepository.TRIAL_ID,trial.getTrialSequence());
        values.put(TrialRepository.COLUMN_NAME_BALLOON_START_HEIGHT,trial.getBalloonStartHeight());
        values.put(TrialRepository.COLUMN_NAME_BALLOON_START_WIDTH,trial.getBalloonStartWidth());
        values.put(TrialRepository.COLUMN_NAME_TIME_BEFORE_FIRST_PUMP,trial.getTimeBeforeFirstPump());
        values.put(TrialRepository.COLUMN_NAME_TIME_BTW_LAST_PUMP_AND_COLLECT,trial.getTimeBtwLastPumpAndCollect());
        return db.insert(TrialRepository.TABLE_NAME,null,values);
    }



}
