package com.datagrandeur.neuropsych.data;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

final class TrialRepository  {
    private TrialRepository(){}
    public static final String TABLE_NAME="trial";
    public static final String COLUMN_NAME_TRIAL_SEQUENCE_NUMBER ="trial_sequence";
    public static final String COLUMN_NAME_TOTAL_REWARD ="total_reward";
    public static final String COLUMN_NAME_TRIAL_REWARD ="trial_reward";

    public static final String COLUMN_NAME_USER_ID ="user_id";

    public static final String COLUMN_NAME_PUMP_COUNT = "pump_count";
    public static final String COLUMN_NAME_EXPLOSION_POINT = "explosion_point";


    public static final String COLUMN_NAME_POPPED = "popped";
    public static final String COLUMN_NAME_BALLOON_START_HEIGHT="balloon_start_height";
    public static final String COLUMN_NAME_BALLOON_START_WIDTH="balloon_start_width";
    public static final String COLUMN_NAME_BALLOON_END_HEIGHT="balloon_end_height";
    public static final String COLUMN_NAME_BALLOON_END_WIDTH="balloon_end_width";
    public static final String COLUMN_NAME_START_TIME_OF_TRIAL ="start_time_of_trial";
    public static final String COLUMN_NAME_END_TIME_OF_TRIAL ="end_time_of_trial";

    private static final String COLUMN_NAME_ID = "id";
    public  static final String CREATE=
            "CREATE TABLE "+ TrialRepository.TABLE_NAME+" ( "+
                    TrialRepository.COLUMN_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    TrialRepository.COLUMN_NAME_USER_ID +" TEXT, " +
                    TrialRepository.COLUMN_NAME_TRIAL_SEQUENCE_NUMBER +" INTEGER, " +
                    TrialRepository.COLUMN_NAME_POPPED +" BOOLEAN, " +
                    TrialRepository.COLUMN_NAME_PUMP_COUNT +" INTEGER, " +
                    TrialRepository.COLUMN_NAME_EXPLOSION_POINT +" INTEGER, " +
                    TrialRepository.COLUMN_NAME_TRIAL_REWARD +" REAL, " +
                    TrialRepository.COLUMN_NAME_TOTAL_REWARD +" REAL, " +
                    TrialRepository.COLUMN_NAME_BALLOON_START_HEIGHT +" TEXT, " +
                    TrialRepository.COLUMN_NAME_BALLOON_START_WIDTH +" TEXT, " +
                    TrialRepository.COLUMN_NAME_BALLOON_END_HEIGHT +" TEXT, " +
                    TrialRepository.COLUMN_NAME_BALLOON_END_WIDTH +" TEXT, " +
                    TrialRepository.COLUMN_NAME_START_TIME_OF_TRIAL +" TEXT, " +
                    TrialRepository.COLUMN_NAME_END_TIME_OF_TRIAL +" TEXT " +
                    " ) ";
    public static long insert(Trial trial, SQLiteDatabase db){
        ContentValues values=new ContentValues();

        values.put(TrialRepository.COLUMN_NAME_TRIAL_SEQUENCE_NUMBER,trial.getTrialSequence());
        values.put(TrialRepository.COLUMN_NAME_USER_ID,trial.getUserId());
        values.put(TrialRepository.COLUMN_NAME_TRIAL_REWARD,trial.getTrialReward());
        values.put(TrialRepository.COLUMN_NAME_TOTAL_REWARD,trial.getTotalReward());
        values.put(TrialRepository.COLUMN_NAME_EXPLOSION_POINT,trial.getExplosionPoint());
        values.put(TrialRepository.COLUMN_NAME_PUMP_COUNT,trial.getPumpCount());
        values.put(TrialRepository.COLUMN_NAME_POPPED,trial.isPopped());
        values.put(TrialRepository.COLUMN_NAME_BALLOON_START_HEIGHT,trial.balloonStartHeight);
        values.put(TrialRepository.COLUMN_NAME_BALLOON_START_WIDTH,trial.balloonStartWidth);
        values.put(TrialRepository.COLUMN_NAME_BALLOON_END_HEIGHT,trial.getBalloonEndHeight());
        values.put(TrialRepository.COLUMN_NAME_BALLOON_END_WIDTH,trial.getBalloonEndWidth());
        values.put(TrialRepository.COLUMN_NAME_START_TIME_OF_TRIAL,trial.getStartTimeOfTrial());
        values.put(TrialRepository.COLUMN_NAME_END_TIME_OF_TRIAL,trial.getEndTimeOfTrial());
        return db.insert(TrialRepository.TABLE_NAME,null,values);
    }
    public static long update(Trial trial, SQLiteDatabase db){
        ContentValues contentValues=new ContentValues();
        contentValues.put(TrialRepository.COLUMN_NAME_TRIAL_REWARD,trial.getTrialReward());
        contentValues.put(TrialRepository.COLUMN_NAME_TOTAL_REWARD,trial.getTotalReward());
        contentValues.put(TrialRepository.COLUMN_NAME_EXPLOSION_POINT,trial.getExplosionPoint());
        contentValues.put(TrialRepository.COLUMN_NAME_PUMP_COUNT,trial.getPumpCount());
        contentValues.put(TrialRepository.COLUMN_NAME_POPPED,trial.isPopped());
        contentValues.put(TrialRepository.COLUMN_NAME_BALLOON_END_HEIGHT,trial.getBalloonEndHeight());
        contentValues.put(TrialRepository.COLUMN_NAME_BALLOON_END_WIDTH,trial.getBalloonEndWidth());
        contentValues.put(TrialRepository.COLUMN_NAME_END_TIME_OF_TRIAL,trial.getEndTimeOfTrial());

        String whereClause = TrialRepository.COLUMN_NAME_ID + " = ?";
        String[] whereArgs = {String.valueOf(trial.getId())};
        return db.update(TrialRepository.TABLE_NAME,contentValues, whereClause, whereArgs);
    }




}
