package com.example.quizmad;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class DollDetailView extends Fragment {

    Button shareBtn;
    EditText phoneET;
    TextView textView;

    public DollDetailView() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        return inflater.inflate(R.layout.fragment_doll_detail_view, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageView dollImage = (ImageView) view.findViewById(R.id.dollImageDetail);
        textView = (TextView) view.findViewById(R.id.dollNameDetail);
        TextView textView1 = (TextView) view.findViewById(R.id.descriptionDetail);
        Bundle bundle = this.getArguments();


        dollImage.setImageResource(bundle.getInt("DollImage"));
        textView.setText(bundle.getString("DollName"));
        textView1.setText(bundle.getString("DollDesc"));

        shareBtn = view.findViewById(R.id.shareBtn);
        phoneET = view.findViewById(R.id.phoneET);

        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                if(ContextCompat.checkSelfPermission(getContext(),Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED){
                    sendSMS();
                } else {
                    requestPermissions(new String[] {Manifest.permission.SEND_SMS}, 1);
                }

                }
            }
        });


    }

    private void sendSMS() {
//        SharedPreferences preferences = getActivity().getSharedPreferences(UserSession.SESSION_NAME, Context.MODE_PRIVATE);
//        String user = preferences.getString(UserSession.SESSION_NAME,)

        String phoneNo = phoneET.getText().toString().trim();
        String message = "Hey, check this doll from BlueDoll! it's the" + textView + "and it's so awesome! - " ;

        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, message, null, null);

            Toast.makeText(getContext(), "Message is send", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "Failed to send message", Toast.LENGTH_SHORT).show();
        }


    }



}