package com.example.quizmad;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizmad.DataAccess.DummyData;
import com.example.quizmad.model.UserModel;

import java.util.Calendar;

public class RegisterActivity extends AppCompatActivity {


    private EditText nameField, emailField, passwordField , repasswordField , DOB;
    private CheckBox agreement;
    private Button registerButton;
    private RadioButton male , female;
    private TextView loginLink;

    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    private DatePickerDialog datePickerDialog;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        init();

        DOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                datePickerDialog = new DatePickerDialog(RegisterActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        DOB.setText(i2 + "/" + (i1 + 1) + "/" + i);
                    }
                } , year, month , day);

                datePickerDialog.show();

            }
        });


        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String gender;
                String username = nameField.getText().toString();
                String email = emailField.getText().toString();
                String password = passwordField.getText().toString();
                String repassword = repasswordField.getText().toString();

                if(username.isEmpty()){
                    Toast.makeText(getApplicationContext(), "username cannot be empty" , Toast.LENGTH_SHORT).show();
                    return;
                }else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    Toast.makeText(getApplicationContext(), "email inValid" , Toast.LENGTH_SHORT).show();
                    return;
                }else if(password.trim().length() < 6){
                    Toast.makeText(getApplicationContext(), "password must have more than 5 character" , Toast.LENGTH_SHORT).show();
                    return;
                }else if(!password.trim().equals(repassword.trim())){
                    Toast.makeText(getApplicationContext(), "repassword wrong" , Toast.LENGTH_SHORT).show();
                    return;
                }else if (!agreement.isChecked()){
                    Toast.makeText(getApplicationContext(), "agreement must be check" , Toast.LENGTH_SHORT).show();
                    return;
                }else if(!male.isChecked() && !female.isChecked()){
                    Toast.makeText(getApplicationContext(), "Choose the gender" , Toast.LENGTH_SHORT).show();
                    return;
                }else if(DOB.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "DOB cannot be empty" , Toast.LENGTH_SHORT).show();
                    return;
                }

                if(male.isChecked()){
                    gender = "Male";
                }else{
                    gender = "Female";
                }

                DummyData.register(new UserModel(DummyData.UIDGenerator(),username, email , password, gender , "User"));
                Toast.makeText(getApplicationContext(), "User have been register" , Toast.LENGTH_SHORT).show();

            }
        });

        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),LoginActivity.class);
                i.putExtra("done","done");
                startActivity(i);
                finish();
            }
        });
    }


    private void init(){
        nameField = (EditText) findViewById(R.id.nameRegisterField);
        emailField = (EditText) findViewById(R.id.emailRegisterField);
        passwordField = (EditText) findViewById(R.id.passwordRegisterField);
        repasswordField = (EditText) findViewById(R.id.repasswordField);
        DOB = (EditText) findViewById(R.id.dateField);
        agreement = (CheckBox) findViewById(R.id.checkBox);
        male = (RadioButton) findViewById(R.id.male);
        female = (RadioButton) findViewById(R.id.female);
        registerButton = (Button) findViewById(R.id.registerButton);
        loginLink = (TextView) findViewById(R.id.loginLink);

    }
}