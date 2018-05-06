package com.example.nguyenvulong.androiddemo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.nguyenvulong.androiddemo.IListAction;
import com.example.nguyenvulong.androiddemo.R;
import com.example.nguyenvulong.androiddemo.entity.ViewDetailFragmentContent;
import com.example.nguyenvulong.androiddemo.adapter.AnalysisFragmentAdapter;
import com.example.nguyenvulong.androiddemo.entity.AnalysisFragmentContent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nguyenvulong on 3/7/18.
 */

public class FragmentBox extends Fragment implements IListAction {


    private RecyclerView recyclerView;
    private List<AnalysisFragmentContent> analysisFragmentContentList = new ArrayList<>();
    private AnalysisFragmentAdapter analysisFragmentAdapter  ;
    private ArrayList<ViewDetailFragmentContent> viewDetailFragmentContentList = new ArrayList<>();

    private ArrayList<String> arrayListOutput = new ArrayList<>();
    private EditText editInput;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.custom_analysis_fragment, container, false);

        Intent data = getActivity().getIntent();
        viewDetailFragmentContentList = (ArrayList<ViewDetailFragmentContent>) data.getSerializableExtra("object");


        if (getArguments() != null) {
            arrayListOutput = getArguments().getStringArrayList("input");
        }
        for (int i = 0; i < arrayListOutput.size(); i++) {
            analysisFragmentContentList.add(new AnalysisFragmentContent(arrayListOutput.get(i)));
        }
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerAlertDialog);
        analysisFragmentAdapter = new AnalysisFragmentAdapter(getActivity(), analysisFragmentContentList, this);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(analysisFragmentAdapter);
        return view;
    }

    @Override
    public void onPause() {
        Log.e("DEBUG", "OnPause of loginFragment");
        editInput = (EditText) getActivity().findViewById(R.id.editInput);
        editInput.setEnabled(true);
        super.onPause();
    }


    @Override
    public void doSomething(String input) {
        Bundle bundle = new Bundle();
        bundle.putString("stringne",input);
        getActivity().getIntent().putExtra("object",viewDetailFragmentContentList);
        ViewAnalysisFragment viewAnalysisFragment = new ViewAnalysisFragment();
        viewAnalysisFragment.setArguments(bundle);
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.fragmentAnalysis, viewAnalysisFragment).addToBackStack("home").commit();
    }

    @Override
    public String keyReturn(String input) {
        return null;
    }


}
