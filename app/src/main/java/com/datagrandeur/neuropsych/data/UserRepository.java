package com.datagrandeur.neuropsych.data;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class UserRepository {

    private UserRepository(){ }

    public static final String TABLE_NAME = "user";
    public static final String COLUMN_NAME_USER_ID = "user_id";
    public static final String COLUMN_NAME_FULLNAME = "fullName";

    public static final String CREATE =
        "CREATE TABLE " + UserRepository.TABLE_NAME + " (" +
                UserRepository.COLUMN_NAME_FULLNAME + " TEXT, " +
                UserRepository.COLUMN_NAME_USER_ID + " TEXT" +
                ")";

    public static Long insert(User user, SQLiteDatabase db){

        ContentValues values = new ContentValues();

        values.put(UserRepository.COLUMN_NAME_FULLNAME, user.getFullName());
        values.put(UserRepository.COLUMN_NAME_USER_ID, user.getUserId());

        return db.insert(UserRepository.TABLE_NAME,null,values);

    }


}
