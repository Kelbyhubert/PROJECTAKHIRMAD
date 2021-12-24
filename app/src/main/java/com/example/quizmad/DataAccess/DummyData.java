package com.example.quizmad.DataAccess;

import android.util.Log;

import com.example.quizmad.R;
import com.example.quizmad.model.DollModel;
import com.example.quizmad.model.UserModel;

import java.util.ArrayList;

public class DummyData {

    private static final String TAG = "DummyData";

    private static ArrayList<UserModel> dummyUserList = new ArrayList<>();
    private static ArrayList<DollModel> dummyDollList = new ArrayList<>();
    private static String userName = null, role = null;

    public static void initData(){
        dummyUserList.add(new UserModel("US001", "Admin", "Admin@email.com", "Admin","Male","Admin"));
    }

    public static void setUserName(String userName) {
        DummyData.userName = userName;
    }

    public static String getUserName() {
        return userName;
    }

    public static String getRole() {
        return role;
    }

    public static void setRole(String role) {
        DummyData.role = role;
    }

    public static void initDollData(){
//        dummyDollList.add(new DollModel(R.drawable.doll2,"doll","alvin","doll","0"));
//        dummyDollList.add(new DollModel(R.drawable.download,"Pink doll","alvin","Pink Doll","1"));
//        dummyDollList.add(new DollModel(R.drawable.doll3,"Tanjiro doll","alvin","Tanjiro doll","2"));

    }

    public static ArrayList<DollModel> getDummyDollList() {
        return dummyDollList;
    }

    public static ArrayList<UserModel> getDummyUserList(){
        return dummyUserList;
    }

    public static void register(UserModel newUser){


        dummyUserList.add(newUser);
        Log.d(TAG, "register: " + newUser.toString());
    }

    public static boolean insert(DollModel newDoll){

        for(DollModel doll : dummyDollList){
            if(doll.getDollName().equals(newDoll.getDollName())){
                return false;
            }
        }
        dummyDollList.add(newDoll);
        Log.d(TAG , "insert" + newDoll.toString());
        return true;
    }

    public static boolean modify(String UID , int image , String dollName , String desc){
        for(DollModel doll : dummyDollList){
            if(doll.getDollUID().equals(UID)){
                doll.setDollImage(image);
                doll.setDollName(dollName);
                doll.setDescription(desc);

                return true;
            }
        }
        return false;
    }

    public static void removeDoll(DollModel doll){
        dummyDollList.remove(doll);
        Log.d(TAG, "removeDoll: " + dummyDollList);
    }

    public static void removeDoll(String UID){
        for(DollModel doll : dummyDollList){
            if(doll.getDollUID().equals(UID)){
                dummyDollList.remove(doll);
                break;
            }
        }
    }


    public static String UIDGenerator(){
        String newUserId = null;
        String lastUserId = dummyUserList.get(dummyUserList.size() - 1).getUserUID();
        int lastId = Integer.parseInt(lastUserId.substring(lastUserId.length() - 3));
        Log.d(TAG, "UIDGenerator: " + lastId);
        if(lastId < 9){
            newUserId = "ST00" + (lastId + 1);

        }else if(lastId < 99){
            newUserId = "ST0" + (lastId + 1);
        }else {
            newUserId = "ST" + (lastId + 1);
        }
        return newUserId;
    }



}
