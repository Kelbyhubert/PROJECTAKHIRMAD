package com.example.quizmad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizmad.context.UserSession;
import com.example.quizmad.model.DollModel;

public class DollDetailViewActivity extends AppCompatActivity {
    private static final String TAG = "DollDetailViewActivity";
    Button shareBtn;
    EditText phoneET;
    TextView textView;
    private DollModel doll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doll_detail_view);

        ImageView dollImage = (ImageView) findViewById(R.id.dollImageDetail);
        textView = (TextView) findViewById(R.id.dollNameDetail);
        TextView textView1 = (TextView) findViewById(R.id.descriptionDetail);

        doll = (DollModel) getIntent().getSerializableExtra("DollObject");


        dollImage.setImageResource(doll.getDollImage());
        textView.setText(doll.getDollName());
        textView1.setText(doll.getDescription());

        shareBtn = findViewById(R.id.shareBtn);
        phoneET = findViewById(R.id.phoneET);

        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                    if(checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED){
                        sendSMS();
                    } else {
                        requestPermissions(new String[] {Manifest.permission.SEND_SMS}, 1);
                    }

                }
            }
        });

    }



    private void sendSMS() {
        SharedPreferences preferences = this.getSharedPreferences(UserSession.SESSION_NAME, Context.MODE_PRIVATE);
        String user = preferences.getString(UserSession.SESSION_USERNAME,null);

        String phoneNo = phoneET.getText().toString().trim();
        String message = "Hey, check this doll from BlueDoll! it's the" + textView.getText().toString().trim() + "and it's so awesome! - " + user ;

        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, message, null, null);

            Toast.makeText(DollDetailViewActivity.this, "Message is send", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(DollDetailViewActivity.this, "Failed to send message", Toast.LENGTH_SHORT).show();
        }


    }

}