package com.example.nguyenvulong.androiddemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nguyenvulong on 3/7/18.
 */

public class FragmentBox extends Fragment {


    private RecyclerView recyclerView;
    private List<AlertContent> alertContentList = new ArrayList<>();
    private AlertAdapter alertAdapter;
    private Activity activity;
    private String output;
    private AlertContent alertContent;
    private ArrayList<String> arrayListOutput;
    private EditText editInput;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.custom_alert_dialog, container, false);
        if (getArguments() != null) {
            arrayListOutput = getArguments().getStringArrayList("output");
        }
        for(int i=0;i<arrayListOutput.size();i++){
            alertContentList.add(new AlertContent(arrayListOutput.get(i)));
        }
        System.out.println("sfsdfdf"+arrayListOutput);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerAlertDialog);
        alertAdapter = new AlertAdapter(getActivity(), alertContentList);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
//        recyclerView.addItemDecoration(new E);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(alertAdapter);
        return view;
    }

    @Override
    public void onPause() {
        Log.e("DEBUG", "OnPause of loginFragment");
        editInput = (EditText) getActivity().findViewById(R.id.editInput);
        editInput.setEnabled(true);
        super.onPause();
    }


}
