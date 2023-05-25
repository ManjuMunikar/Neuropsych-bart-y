package com.datagrandeur.neuropsych.data;

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


}
