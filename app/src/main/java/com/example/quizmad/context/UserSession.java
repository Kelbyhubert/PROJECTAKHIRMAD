package com.example.quizmad.context;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.quizmad.model.UserModel;

public class UserSession {

    SharedPreferences preferences;
    Context context;
    SharedPreferences.Editor editor;

    public static final String SESSION_NAME = "BLUE_DOLL_SESSION";
    public static final String SESSION_USER_ID = "USER_ID";
    public static final String SESSION_USERNAME = "USERNAME";
    public static final String SESSION_ROLE = "ROLE";

    public UserSession(Context context){
        this.context = context;
        preferences = context.getSharedPreferences(SESSION_NAME,Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public void createUserSession(UserModel userModel){
        editor.putString(SESSION_USER_ID, userModel.getUserUID());
        editor.putString(SESSION_USERNAME , userModel.getUsername());
        editor.putString(SESSION_ROLE, userModel.getUserRole());
        editor.commit();
    }

    public void destroySession(){
        editor.clear();
        editor.commit();
    }

}
