package com.datagrandeur.neuropsych.data;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

final class PumpRepository implements BaseColumns {

    private PumpRepository(){}

    public static final String TABLE_NAME = "pump";
    public static final String COLUMN_NAME_PUMP_ID = "pump_id";
    public static final String COLUMN_NAME_TRIAL_ID = "trial_id";
    public static final String COLUMN_NAME_TRIAL_SEQUENCE = "trial_sequence";
    public static final String COLUMN_NAME_PUMP_SEQUENCE = "pump_sequence";
    public static final String COLUMN_NAME_CURRENT_PUMP_TIME = "current_pump_time";
    public static final String COLUMN_NAME_LAST_PUMP_TIME = "last_pump_time";
    public static final String COLUMN_NAME_PUMP_BTW_PUMP = "pump_btw_pump";
    public static final String COLUMN_NAME_BALLOON_HEIGHT = "balloon_height";
    public static final String COLUMN_NAME_BALLOON_WIDTH = "balloon_width";
    public static final String COLUMN_NAME_EXPLOSION = "explosion";

    public  static final String CREATE=
            "CREATE TABLE "+ PumpRepository.TABLE_NAME+" ( "+
                    PumpRepository._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    PumpRepository.COLUMN_NAME_TRIAL_ID +" TEXT, " +
                    PumpRepository.COLUMN_NAME_TRIAL_SEQUENCE +" INTEGER, " +
                    PumpRepository.COLUMN_NAME_PUMP_SEQUENCE +" INTEGER, " +
                    PumpRepository.COLUMN_NAME_CURRENT_PUMP_TIME +" TEXT, " +
                    PumpRepository.COLUMN_NAME_LAST_PUMP_TIME +" TEXT, " +
                    PumpRepository.COLUMN_NAME_PUMP_BTW_PUMP +" TEXT, " +
                    PumpRepository.COLUMN_NAME_BALLOON_HEIGHT +" TEXT, " +
                    PumpRepository.COLUMN_NAME_BALLOON_WIDTH +" TEXT, " +
                    PumpRepository.COLUMN_NAME_EXPLOSION +" TEXT " +
                    ")";
    public static long insert(Pump pump, SQLiteDatabase db){
        ContentValues values=new ContentValues();


        values.put(PumpRepository.COLUMN_NAME_TRIAL_ID,pump.getTrialId());
        values.put(PumpRepository.COLUMN_NAME_TRIAL_SEQUENCE,pump.getTrialSequence());
        values.put(PumpRepository.COLUMN_NAME_PUMP_SEQUENCE,pump.getPumpSequence());
        values.put(PumpRepository.COLUMN_NAME_CURRENT_PUMP_TIME,pump.getCurrentPumpTime());
        values.put(PumpRepository.COLUMN_NAME_LAST_PUMP_TIME,pump.getLastPumpTime());
        values.put(PumpRepository.COLUMN_NAME_PUMP_BTW_PUMP,pump.getPumpBtwPumps());
        values.put(PumpRepository.COLUMN_NAME_BALLOON_HEIGHT,pump.getBalloonHeight());
        values.put(PumpRepository.COLUMN_NAME_BALLOON_WIDTH,pump.getBalloonWidth());
        values.put(PumpRepository.COLUMN_NAME_EXPLOSION,pump.isExploded());
        return db.insert(TrialRepository.TABLE_NAME,null,values);
    }

}
