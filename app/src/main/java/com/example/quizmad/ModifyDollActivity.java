package com.example.quizmad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.example.quizmad.DataAccess.DummyData;
import com.example.quizmad.model.DollModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class ModifyDollActivity extends AppCompatActivity {

    private EditText dollName , desc;
    private Button saveButton , deleteButton;
    private Spinner imageSpinner;
    private List<Integer> imageList = new ArrayList<>() ;
    private List<String> contentList = new ArrayList<>();
    String UID = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_doll);
        imageList.add(R.drawable.download);
        imageList.add(R.drawable.doll2);
        imageList.add(R.drawable.doll3);

        contentList.add("Doll 1");
        contentList.add("Doll 2");
        contentList.add("Doll 3");
        init();



        String FORM_TYPE;




        ImageSpinnerAdapter arrayAdapter = new ImageSpinnerAdapter(this, contentList , imageList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        imageSpinner.setAdapter(arrayAdapter);

        if(getIntent().getExtras() != null){
            FORM_TYPE = "MODIFY";
            DollModel doll = (DollModel) getIntent().getSerializableExtra("DollObject");
            UID = doll.getDollUID();
            dollName.setText(doll.getDollName());
            desc.setText(doll.getDescription());
            imageSpinner.setSelection(imageList.indexOf(doll.getDollImage()));


        }else{
            FORM_TYPE = "INSERT";
        }

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dollName.getText().toString().isEmpty()){
                    return;
                }
                if(desc.getText().toString().isEmpty()){
                    return;
                }

                if(FORM_TYPE == "MODIFY"){
//                    if(DummyData.modify(UID,imageList.get(imageSpinner.getSelectedItemPosition()) ,dollName.getText().toString(),desc.getText().toString())){
//                        Toast.makeText(getApplicationContext(), "data have been modify", Toast.LENGTH_SHORT).show();
//                        Intent i = new Intent(getApplicationContext(),MainActivity.class);
//                        i.putExtra("done", "done");
//                        startActivity(i);
//                        finish();
//                    }else {
//                        Toast.makeText(getApplicationContext(), "Id not found", Toast.LENGTH_SHORT).show();
//                        return;
//                    }
                }else if (FORM_TYPE == "INSERT"){
//                    DollModel newDoll = new DollModel(imageList.get(imageSpinner.getSelectedItemPosition()),dollName.getText().toString(),DummyData.getUserName(),desc.getText().toString(), UUID.randomUUID().toString());
//                    if(DummyData.insert(newDoll)){
//                        Toast.makeText(getApplicationContext(), "data have been insert", Toast.LENGTH_SHORT).show();
//                        Intent i = new Intent(getApplicationContext(),MainActivity.class);
//                        i.putExtra("done", "done");
//                        startActivity(i);
//                        finish();
//                    }else {
//                        Toast.makeText(getApplicationContext(), "doll name already in database", Toast.LENGTH_SHORT).show();
//                        return;
//                    }
                }


            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                DollModel doll = (DollModel) getIntent().getSerializableExtra("DollObject");
//                if("Admin".equals(DummyData.getRole()) || doll.getCreatorName().equals(DummyData.getUserName()) ){
//
//
//                    DummyData.removeDoll(UID);
//                    Toast.makeText(getApplicationContext(),"Doll have been remove" , Toast.LENGTH_SHORT).show();
//                    Intent i = new Intent(getBaseContext(),MainActivity.class);
//                    i.putExtra("done", "done");
//                    finish();
//                    startActivity(i);
//                }else {
//                    Toast.makeText(getApplicationContext(), "Only Admin can delete", Toast.LENGTH_SHORT).show();
//                }



            }
        });

    }

    private void init(){

        dollName = (EditText) findViewById(R.id.dollNameField);
        desc = (EditText) findViewById(R.id.descriptionModify);
        imageSpinner = (Spinner) findViewById(R.id.imageSpinner);
        saveButton = (Button) findViewById(R.id.saveButton);
        deleteButton = (Button) findViewById(R.id.deleteButtonEdit);

    }
}