package com.datagrandeur.neuropsych.data;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

final class TrialRepository  {
    private TrialRepository(){}
    public static final String TABLE_NAME="trial";
    public static final String COLUMN_NAME_TRIAL_SEQUENCE_NUMBER ="trial_sequence";
    public static final String COLUMN_NAME_REWARD ="reward";
    public static final String COLUMN_NAME_USER_ID ="user_id";
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
                    TrialRepository.COLUMN_NAME_TRIAL_SEQUENCE_NUMBER +" INTEGER, " +
                    TrialRepository.COLUMN_NAME_REWARD +" REAL, " +
                    TrialRepository.COLUMN_NAME_USER_ID +" TEXT, " +
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
        values.put(TrialRepository.COLUMN_NAME_REWARD,trial.getReward());
        values.put(TrialRepository.COLUMN_NAME_USER_ID,trial.getUserId());
        values.put(TrialRepository.COLUMN_NAME_BALLOON_START_HEIGHT,trial.balloonStartHeight);
        values.put(TrialRepository.COLUMN_NAME_BALLOON_START_WIDTH,trial.balloonStartWidth);
        values.put(TrialRepository.COLUMN_NAME_BALLOON_END_HEIGHT,trial.getBalloonEndHeight());
        values.put(TrialRepository.COLUMN_NAME_BALLOON_END_WIDTH,trial.getBalloonEndWidth());
        values.put(TrialRepository.COLUMN_NAME_START_TIME_OF_TRIAL,trial.getStartTimeOfTrial());
        values.put(TrialRepository.COLUMN_NAME_END_TIME_OF_TRIAL,trial.getEndTimeOfTrial());
        return db.insert(TrialRepository.TABLE_NAME,null,values);
    }



}
