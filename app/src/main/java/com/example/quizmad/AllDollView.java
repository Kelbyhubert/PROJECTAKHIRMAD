package com.example.quizmad;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.quizmad.DataAccess.DollDAO;
import com.example.quizmad.DataAccess.DummyData;
import com.example.quizmad.model.DollModel;

import java.util.ArrayList;
import java.util.List;


public class AllDollView extends Fragment {

    private static final String TAG = "AllDollView";
    private List<DollModel> dollList;
    private DollAdapter dollAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_all_doll_view, container, false);


        dollList = new DollDAO(getActivity().getApplicationContext()).getDollList();
        ListView listView = (ListView) view.findViewById(R.id.dollList);

        if(dollAdapter == null){
            dollAdapter = new DollAdapter(getActivity(),R.layout.doll_item, dollList);
            listView.setAdapter(dollAdapter);
        }
        else {
            dollAdapter.clear();
            dollAdapter.addAll(dollList);
            dollAdapter.notifyDataSetChanged();
        }





        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

    }
}