package com.example.quizmad.DataAccess;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.quizmad.model.UserModel;

public class DatabaseHandler extends SQLiteOpenHelper {


    //dora
    //wellplay
    //tes
    //wibu
    // pantat
    // joh
    // ngentot
    private static final String TAG = "DatabaseHandler";

    public static final String DATABASE_NAME = "BLUE_DOLL_DATABASE";
    public static final int DATABASE_VERSION = 1;


    public static final String USER_TABLE = "USER_TABLE";
    public static final String USER_ID_KEY = "USER_ID";
    public static final String USERNAME_KEY = "USERNAME";
    public static final String EMAIL_KEY = "EMAIL";
    public static final String PASSWORD_KEY = "PASSWORD";
    public static final String GENDER_KEY = "GENDER";
    public static final String ROLE_KEY = "ROlE";

    public static final String DOLL_TABLE = "DOLL_TABLE";
    public static final String DOLL_ID_KEY = "DOLL_ID";
    public static final String DOLL_NAME_KEY = "DOLL_NAME";
    public static final String DOLL_DESCRIPTION_KEY = "DOLL_DESCRIPTION";
    public static final String DOLl_IMAGE_KEY = "DOLL_IMAGE";

    private static DatabaseHandler instance = null;


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static DatabaseHandler getInstance(Context context) {
        if(instance == null){
            instance = new DatabaseHandler(context);
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String userTable = "CREATE TABLE " + USER_TABLE + "(" +
                USER_ID_KEY + " TEXT PRIMARY KEY," +
                USERNAME_KEY + " TEXT NOT NULL," +
                EMAIL_KEY + " TEXT NOT NULL," +
                PASSWORD_KEY + " TEXT NOT NULL," +
                GENDER_KEY + " TEXT NOT NULL," +
                ROLE_KEY + " TEXT NOT NULL" +
                ")";

        String dollTable = "CREATE TABLE " + DOLL_TABLE + "(" +
                DOLL_ID_KEY + " TEXT PRIMARY KEY," +
                USER_ID_KEY + " TEXT NOT NULL," +
                DOLL_NAME_KEY + " TEXT NOT NULL," +
                DOLL_DESCRIPTION_KEY + " TEXT NOT NULL," +
                DOLl_IMAGE_KEY + " INTEGER NOT NULL," +
                "FOREIGN KEY (" + USER_ID_KEY +") REFERENCES " + USER_TABLE+ "("+USER_ID_KEY+")" +
                ")";

        sqLiteDatabase.execSQL(userTable);
        sqLiteDatabase.execSQL(dollTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DOLL_TABLE);
        onCreate(sqLiteDatabase);
    }





}
