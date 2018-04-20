package com.example.nguyenvulong.androiddemo.fragment;

import android.content.Intent;
import android.net.Uri;
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
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.nguyenvulong.androiddemo.ExampleInterface;
import com.example.nguyenvulong.androiddemo.IListAction;
import com.example.nguyenvulong.androiddemo.R;
import com.example.nguyenvulong.androiddemo.activity.MainActivity;
import com.example.nguyenvulong.androiddemo.adapter.AnalysisFragmentAdapter;
import com.example.nguyenvulong.androiddemo.adapter.ExampleAdapter;
import com.example.nguyenvulong.androiddemo.adapter.ViewDetailFragmentAdapter;
import com.example.nguyenvulong.androiddemo.entity.AnalysisFragmentContent;
import com.example.nguyenvulong.androiddemo.entity.Content;
import com.example.nguyenvulong.androiddemo.entity.Example;
import com.example.nguyenvulong.androiddemo.entity.ViewCompareContent;
import com.example.nguyenvulong.androiddemo.entity.ViewDetailFragmentContent;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nguyenvulong on 3/16/18.
 */

public class FragmentExample extends Fragment implements ExampleInterface, Serializable {
    String urlPredict = "http://172.20.10.2:3000/predict/";
    private String urlTongPredict;
    private RecyclerView recyclerView;
    private ExampleAdapter exampleAdapter;
    ArrayList<ViewCompareContent> arrayListOutput;
    private Example example;
    private ArrayList<Example> exampleArrayList = new ArrayList<>();
    String output, output2, output3;
    String input;
    private ArrayList<ViewDetailFragmentContent> viewDetailFragmentContentLists;
    private List<AnalysisFragmentContent> analysisFragmentContentList = new ArrayList<>();
    private ArrayList<String> arrayListOutputBackUp;
    private EditText editInput;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.example_fragment, container, false);
        editInput = (EditText) getActivity().findViewById(R.id.editInput);
        viewDetailFragmentContentLists = new ArrayList<>();
        exampleArrayList.clear();
        arrayListOutput = new ArrayList<>();
        exampleArrayList.add(new Example("Hom nay la mot ngay nang dep, toi khong muon di hoc"));
        exampleArrayList.add(new Example("Sap den ngay bao ve do an roi"));
        exampleArrayList.add(new Example("Hom nay la thu hai dau tuan khoi dau mot tuan moi cho moi viec"));
        exampleArrayList.add(new Example("Toi ten la Nguyen Cong Chinh, nam nay toi ba muoi tuoi, toi lam viec rat la cham chi "));
        exampleArrayList.add(new Example("Ngay hom nay la mot ngay dep troi, sao chung ta khong di choi da ngoai"));
        exampleArrayList.add(new Example("Me cua toi la mot nguoi rat kho tính, khó tính trong việc hoc lân ca lam cua toi"));
        exampleArrayList.add(new Example("toi la sinh vien truong dai hoc FPT, toi la sinh vien nam cuoi, dang co gang lam do an de tot nghiep do nha"));
        exampleArrayList.add(new Example("Hom nay toi muon duoc di da banh, cau troi dung mua de toi co the ra ngoai da banh voi moi nguoi"));
        exampleArrayList.add(new Example("May ngay nay troi hay mua, mong sao ngay mai troi dung mua nua de toi con di choi"));
        exampleArrayList.add(new Example("Ngay hom nay la ngay tuyet voi, toi cam thay rat vui vi dat diem cao"));


        if ((ArrayList<ViewDetailFragmentContent>) getActivity().getIntent().getSerializableExtra("object") != null) {
            viewDetailFragmentContentLists = (ArrayList<ViewDetailFragmentContent>) getActivity().getIntent().getSerializableExtra("object");
        }

        if (getArguments() != null) {
            arrayListOutputBackUp = getArguments().getStringArrayList("input");
            for (int i = 0; i < arrayListOutputBackUp.size(); i++) {
                analysisFragmentContentList.add(new AnalysisFragmentContent(arrayListOutputBackUp.get(i)));
            }
        }

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerExample);
        exampleAdapter = new ExampleAdapter(getActivity(), exampleArrayList, this);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(exampleAdapter);
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
    public void eventClickingItem(String inputSentences) {
//        input = inputSentences;
//        urlTongPredict = urlPredict + Uri.encode(input);
//        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
//        JsonObjectRequest jsonObjectRequestPredict = new JsonObjectRequest(Request.Method.GET, urlTongPredict, null, new Response.Listener<JSONObject>() {
//
//
//            @Override
//            public void onResponse(JSONObject response) {
//                try {
//
//                    output = response.getString("output1");
//                    output2 = response.getString("output2");
//                    output3 = response.getString("output3");
//                    ViewCompareContent viewCompareContent = new ViewCompareContent(input, output, output2, output3);
//                    arrayListOutput.add(viewCompareContent);
//                    editInput.setText("");
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.e("Error", error.toString());
//                Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();
//            }
//        });
//
//        requestQueue.add(jsonObjectRequestPredict);
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//                try {
//                    Thread.sleep(1000);
//                    getActivity().getIntent().putExtra("object_output", arrayListOutput);
//                    ViewCompareFragment viewCompareFragment = new ViewCompareFragment();
//                    FragmentManager fm = getActivity().getSupportFragmentManager();
//                    FragmentTransaction transaction = fm.beginTransaction().replace(R.id.exampleFragmentID, viewCompareFragment, "example").addToBackStack("home");
//                    transaction.commit();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//    }).start();
//

        Intent intent = new Intent(getActivity(), MainActivity.class);
        intent.putExtra("object_2",viewDetailFragmentContentLists);
        intent.putStringArrayListExtra("input_2", arrayListOutputBackUp);
        intent.putExtra("text", inputSentences);
        startActivity(intent);
//        editInput.setText(inputSentences);

//          getActivity().onBackPressed();
    }
}
