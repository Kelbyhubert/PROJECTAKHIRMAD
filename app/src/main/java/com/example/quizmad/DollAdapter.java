package com.example.quizmad;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.quizmad.DataAccess.DollDAO;
import com.example.quizmad.DataAccess.DummyData;
import com.example.quizmad.context.UserSession;
import com.example.quizmad.model.DollModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DollAdapter extends ArrayAdapter<DollModel> {
    private static final String TAG = "dollAdapter";

    private final int resource;
    private Context context;
    private List<DollModel> dollList;

    public DollAdapter(@NonNull Context context, int resource,  List<DollModel> dollList) {
        super(context, resource , dollList);
        this.dollList = dollList;
        this.resource = resource;
        this.context = context;
        Log.d(TAG, "DollAdapter: " + this.dollList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        DollModel doll = dollList.get(position);


        SharedPreferences preferences = context.getSharedPreferences(UserSession.SESSION_NAME, Context.MODE_PRIVATE);
        LayoutInflater inflater = LayoutInflater.from(this.context);
        if(convertView == null){
            convertView = inflater.inflate(resource, parent , false);
        }

        ImageView dollImage = (ImageView) convertView.findViewById(R.id.dollImage);
        TextView dollName = (TextView) convertView.findViewById(R.id.dollName);
        TextView dollCreator = (TextView) convertView.findViewById(R.id.creatorName);
        TextView dollDesc = (TextView) convertView.findViewById(R.id.description);

        Button viewButton = (Button) convertView.findViewById(R.id.viewButton);
        Button editButton = (Button) convertView.findViewById(R.id.editButton);
        Button deleteButton = (Button) convertView.findViewById(R.id.deleteButton);




        dollImage.setImageResource(doll.getDollImage());
        dollName.setText(doll.getDollName());
        dollCreator.setText(doll.getDollOwner().getUsername());
        dollDesc.setText(doll.getDescription());



        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("DollName", doll.getDollName());
                bundle.putString("DollDesc",doll.getDescription());
                bundle.putInt("DollImage",doll.getDollImage());
                DollDetailView dollDetailView = new DollDetailView();
                dollDetailView.setArguments(bundle);
                ((AppCompatActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_view,dollDetailView).commit();
            }
        });

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if("Admin".equals(preferences.getString(UserSession.SESSION_ROLE,null)) || doll.getDollOwner().getUserUID().equals(preferences.getString(UserSession.SESSION_USER_ID,null))) {
                    Intent i = new Intent(context, ModifyDollActivity.class);
                    i.putExtra("DollObject", doll);
                    context.startActivity(i);
                }else {
                    Toast.makeText(getContext(),"Only Admin and owner can update", Toast.LENGTH_SHORT).show();
                }



            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if("Admin".equals(preferences.getString(UserSession.SESSION_ROLE,null)) || doll.getDollOwner().getUserUID().equals(preferences.getString(UserSession.SESSION_USER_ID,null))){
                    new DollDAO(context).deleteDoll(doll.getDollUID());
                    ((AppCompatActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.fragment_view,AllDollView.class,null).commit();

                }else{
                    Toast.makeText(getContext(),"Only Admin and owner can delete", Toast.LENGTH_SHORT).show();
                }
            }
        });





        return convertView;
    }


}
