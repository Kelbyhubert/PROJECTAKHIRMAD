package com.example.quizmad;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quizmad.model.DollModel;


public class DollDetailView extends Fragment {

    public DollDetailView(){

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
        TextView textView = (TextView) view.findViewById(R.id.dollNameDetail);
        TextView textView1 = (TextView) view.findViewById(R.id.descriptionDetail);
        Bundle bundle = this.getArguments();


        dollImage.setImageResource(bundle.getInt("DollImage"));
        textView.setText(bundle.getString("DollName"));
        textView1.setText(bundle.getString("DollDesc"));




    }
}