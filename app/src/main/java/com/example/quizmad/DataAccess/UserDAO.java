package com.example.quizmad.DataAccess;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.quizmad.model.UserModel;

public class UserDAO {

    private static final String TAG = "USER_DAO";

    private DatabaseHandler databaseHandler = null;


    public UserDAO(Context context){
        databaseHandler = DatabaseHandler.getInstance(context);
    }


    //refactor ntr
    public UserModel userLogin(String email , String password) {
        SQLiteDatabase db = databaseHandler.getWritableDatabase();

        String query = "SELECT * " +
                " FROM " + DatabaseHandler.USER_TABLE +
                " WHERE " + DatabaseHandler.EMAIL_KEY + "=?" +
                " AND " + DatabaseHandler.PASSWORD_KEY + "=?";

        Cursor cursor = db.rawQuery(query, new String[]{email, password});
        Log.d(TAG, "userAuth: " + cursor);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                return new UserModel(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5));
            }
        }

        return null;
    }


    public void userRegister(UserModel user){
        SQLiteDatabase db = databaseHandler.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHandler.USER_ID_KEY, user.getUserUID());
        contentValues.put(DatabaseHandler.USERNAME_KEY,user.getUsername());
        contentValues.put(DatabaseHandler.PASSWORD_KEY, user.getUserPassword());
        contentValues.put(DatabaseHandler.GENDER_KEY, user.getUserGender());
        contentValues.put(DatabaseHandler.ROLE_KEY, user.getUserRole());

        db.insert(DatabaseHandler.USER_TABLE, null, contentValues);
        db.close();

    }

    public String generateUserUID(){
        String newID;
        String lastRowId = null;
        SQLiteDatabase db = databaseHandler.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT " + DatabaseHandler.USER_ID_KEY + " FROM " + DatabaseHandler.USER_TABLE, null);

        if(cursor == null){
            return null;
        }

        if(cursor.moveToLast()){
            lastRowId = cursor.getString(0);
        }

        int lastId = Integer.parseInt(lastRowId.substring(lastRowId.length() - 3));
        Log.d(TAG, "UIDGenerator: " + lastId);
        if(lastId < 9){
            newID = "ST00" + (lastId + 1);

        }else if(lastId < 99){
            newID = "ST0" + (lastId + 1);
        }else {
            newID = "ST" + (lastId + 1);
        }


        return newID;

    }

}
