package com.example.quizmad.DataAccess;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;

import com.example.quizmad.model.DollModel;
import com.example.quizmad.model.UserModel;

import java.util.ArrayList;
import java.util.List;

public class DollDAO {

    private static final String TAG = "DOLL_DAO";

    private DatabaseHandler databaseHandler = null;

    public DollDAO(Context context){
        this.databaseHandler = DatabaseHandler.getInstance(context);
    }

    // buat masukin doll baru

    public boolean insertNewDoll(DollModel doll){
        SQLiteDatabase db = databaseHandler.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHandler.DOLL_ID_KEY, doll.getDollUID());
        contentValues.put(DatabaseHandler.DOLL_NAME_KEY, doll.getDollName());
        contentValues.put(DatabaseHandler.DOLL_DESCRIPTION_KEY , doll.getDescription());
        contentValues.put(DatabaseHandler.DOLl_IMAGE_KEY,doll.getDollImage());
        contentValues.put(DatabaseHandler.USER_ID_KEY, doll.getDollOwner().getUserUID());

        Long newRowId =  db.insert(DatabaseHandler.DOLL_TABLE, null , contentValues);
        db.close();
        if(newRowId != null){
            return true;
        }

        return false;
    }

    // buat update doll

    public boolean modifyDoll(DollModel doll){
        SQLiteDatabase db = databaseHandler.getWritableDatabase();

        // masukan semua data doll ke contentvalue dulu
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHandler.DOLL_NAME_KEY, doll.getDollName());
        contentValues.put(DatabaseHandler.DOLL_DESCRIPTION_KEY , doll.getDescription());
        contentValues.put(DatabaseHandler.DOLl_IMAGE_KEY,doll.getDollImage());

        // where nya
        String selections = DatabaseHandler.DOLL_ID_KEY + " = ?";
        String[] selectionArgs = {doll.getDollUID()};

        // jika dijadiin query = UPDATE DOLL_TABLE SET DOLL_NAME = doll.getDollname() dan seterusnya WHERE DOLL_ID = doll.getDollUID
        int totalUpdate =  db.update(DatabaseHandler.DOLL_TABLE,contentValues, selections, selectionArgs);
        db.close();
        if(totalUpdate > 0){
            return true;
        }

        return false;

    }


    // buat ambil data doll
    public List<DollModel> getDollList(){
        List<DollModel> dollList = new ArrayList<>();

        SQLiteDatabase db = databaseHandler.getReadableDatabase();
        // lu pada dah tau tentang join lah :D
        String query = "Select * FROM " +
                DatabaseHandler.DOLL_TABLE +
                " JOIN " + DatabaseHandler.USER_TABLE +
                " ON " + DatabaseHandler.DOLL_TABLE + "." + DatabaseHandler.USER_ID_KEY + " = " + DatabaseHandler.USER_TABLE + "." + DatabaseHandler.USER_ID_KEY;

        Cursor cursor = db.rawQuery(query,null);
        if(cursor == null){
            return null;
        }

        // ntr baru gua jelasin
        if(cursor.moveToFirst()){
            do{
                DollModel dollModel = new DollModel(
                        cursor.getInt(cursor.getColumnIndex(DatabaseHandler.DOLl_IMAGE_KEY)),
                        cursor.getString(cursor.getColumnIndex(DatabaseHandler.DOLL_NAME_KEY)),
                        new UserModel(
                                cursor.getString(cursor.getColumnIndex(DatabaseHandler.USER_ID_KEY)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHandler.USERNAME_KEY)),
                                cursor.getString(cursor.getColumnIndex(DatabaseHandler.ROLE_KEY))
                        ),
                        cursor.getString(cursor.getColumnIndex(DatabaseHandler.DOLL_DESCRIPTION_KEY)),
                        cursor.getString(cursor.getColumnIndex(DatabaseHandler.DOLL_ID_KEY))
                );

                dollList.add(dollModel);

            }while (cursor.moveToNext());
        }


        return dollList;
    }

    // buat delete doll
    public void deleteDoll(String id){
        SQLiteDatabase db = databaseHandler.getWritableDatabase();

        // selection ini adalah where
        String selections = DatabaseHandler.DOLL_ID_KEY + " = ?";
        String[] selectionsArgs = {id};


        // jika jadi query = DELETE FROM DOLL_TABLE WHERE DOLL_ID + ID <- (id dari parameter)
        db.delete(DatabaseHandler.DOLL_TABLE, selections,selectionsArgs);
        db.close();
    }
}
