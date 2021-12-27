package com.example.quizmad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizmad.DataAccess.DummyData;
import com.example.quizmad.DataAccess.UserDAO;
import com.example.quizmad.context.UserSession;
import com.example.quizmad.model.UserModel;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {


    private static final String TAG = "LoginActivity";

    private EditText emailField , passwordField ;
    private Button loginButton;
    private TextView registerLink;




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        DummyData.insertUserEarlyData(getApplicationContext());
        DummyData.insertDollEarlyData(getApplicationContext());

        SharedPreferences preferences = getSharedPreferences(UserSession.SESSION_NAME, Context.MODE_PRIVATE);
        if(preferences.getString(UserSession.SESSION_USER_ID,null) != null){
            Intent i = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(i);
        }

        init();



        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!Patterns.EMAIL_ADDRESS.matcher(emailField.getText().toString()).matches()){
                    Toast.makeText(getApplicationContext(), "InValid Email" , Toast.LENGTH_SHORT).show();
                    return;
                }else if(emailField.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Email Cannot Be Empty" , Toast.LENGTH_SHORT).show();
                    return;

                } else if(passwordField.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Password Cannot Be Empty" , Toast.LENGTH_SHORT).show();
                    return;
                }



                UserModel user = new UserDAO(getApplicationContext()).userLogin(emailField.getText().toString(), passwordField.getText().toString());

                if(user == null){
                    Toast.makeText(getApplicationContext(), "Email or Password is wrong" , Toast.LENGTH_SHORT).show();
                    return;
                }

                // auth disini
                new UserSession(getApplicationContext()).createUserSession(user);

                // intent disini
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);

            }
        });


        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(i);
            }
        });

    }

    private void init(){
        emailField = (EditText) findViewById(R.id.emailField);
        passwordField = (EditText) findViewById(R.id.passwordField);
        loginButton = (Button) findViewById(R.id.loginButton);
        registerLink = (TextView) findViewById(R.id.registerLink);

    }
}