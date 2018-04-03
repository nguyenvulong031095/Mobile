package com.example.nguyenvulong.androiddemo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.nguyenvulong.androiddemo.R;
import com.example.nguyenvulong.androiddemo.adapter.ViewCompareAdapter;
import com.example.nguyenvulong.androiddemo.entity.ViewCompareContent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nguyenvulong on 3/26/18.
 */

public class ViewCompareFragment extends Fragment {

    private RecyclerView recyclerView;
    private ViewCompareAdapter viewCompareAdapter;
    private ViewCompareContent viewCompareContent;
    private ArrayList<ViewCompareContent> viewCompareContentArrayList;

    private ArrayList<ViewCompareContent> showViewCompareContentArrayList;
    private EditText editInput;
    private TextView tvExample, tvTitle, tvCocCoc;
    private LinearLayout scroll;
    private ImageView imgCocCoc;
    private int img_icon = 0;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.compare_coc_coc_items, container, false);
        tvExample = (TextView) view.findViewById(R.id.tvCompare);
        tvTitle = (TextView) view.findViewById(R.id.Ex);
        tvCocCoc = (TextView) view.findViewById(R.id.Coccoc);
        imgCocCoc = (ImageView) view.findViewById(R.id.imgViewCompare);
        Intent data = getActivity().getIntent();
        viewCompareContentArrayList = (ArrayList<ViewCompareContent>) data.getSerializableExtra("object_output");
        showViewCompareContentArrayList = new ArrayList<>();
        tvExample.setMovementMethod(new ScrollingMovementMethod());
        try {
            if (viewCompareContentArrayList == null) {
                tvExample.setVisibility(View.GONE);
                tvCocCoc.setVisibility(View.GONE);
                tvTitle.setVisibility(View.GONE);
            } else {
                for (int i = 0; i < viewCompareContentArrayList.size(); i++) {
                    if (viewCompareContentArrayList.get(i).getInputCompare().equals("Hom nay la mot ngay nang dep, toi khong muon di hoc")) {
                        img_icon = R.drawable.ex0;
                    } else if (viewCompareContentArrayList.get(i).getInputCompare().equals("Sap den ngay bao ve do an roi")) {
                        img_icon = R.drawable.ex2;
                    } else if (viewCompareContentArrayList.get(i).getInputCompare().equals("Hom nay la thu hai dau tuan khoi dau mot tuan moi cho moi viec")) {
                        img_icon = R.drawable.ex1;
                    } else if (viewCompareContentArrayList.get(i).getInputCompare().equals("Toi ten la Nguyen Cong Chinh, nam nay toi ba muoi tuoi, toi lam viec rat la cham chi ")) {
                        img_icon = R.drawable.ex3;

                    } else if (viewCompareContentArrayList.get(i).getInputCompare().equals("Ngay hom nay la mot ngay dep troi, sao chung ta khong di choi da ngoai")) {
                        img_icon = R.drawable.ex4;
                    } else if (viewCompareContentArrayList.get(i).getInputCompare().equals("Me cua toi la mot nguoi rat kho tính, khó tính trong việc hoc lân ca lam cua toi")) {
                        img_icon = R.drawable.ex5;
                    } else if (viewCompareContentArrayList.get(i).getInputCompare().equals("toi la sinh vien truong dai hoc FPT, toi la sinh vien nam cuoi, dang co gang lam do an de tot nghiep do nha")) {
                        img_icon = R.drawable.ex6;
                    } else if (viewCompareContentArrayList.get(i).getInputCompare().equals("Hom nay toi muon duoc di da banh, cau troi dung mua de toi co the ra ngoai da banh voi moi nguoi")) {
                        img_icon = R.drawable.ex7;
                    } else if (viewCompareContentArrayList.get(i).getInputCompare().equals("May ngay nay troi hay mua, mong sao ngay mai troi dung mua nua de toi con di choi")) {
                        img_icon = R.drawable.ex8;
                    } else if (viewCompareContentArrayList.get(i).getInputCompare().equals("Ngay hom nay la ngay tuyet voi, toi cam thay rat vui vi dat diem cao")) {
                        img_icon = R.drawable.ex9;
                    }

                    tvExample.setText("-"+viewCompareContentArrayList.get(i).getOutputCompare1() + "\n"
                            + "-" + viewCompareContentArrayList.get(i).getOutputCompare2() + "\n" +
                            "-"+viewCompareContentArrayList.get(i).getOutputCompare3());
                    imgCocCoc.setImageResource(img_icon);
//                showViewCompareContentArrayList.add(new ViewCompareContent(viewCompareContentArrayList.get(i).getInputCompare(), viewCompareContentArrayList.get(i).getOutputCompare1()
//                        , viewCompareContentArrayList.get(i).getOutputCompare2(), viewCompareContentArrayList.get(i).getOutputCompare3()));
                }
            }
        } catch (Exception e) {
            System.out.println("Loi" + e);
        }


//        recyclerView = (RecyclerView) view.findViewById(R.id.recycleCompare);
//        viewCompareAdapter = new ViewCompareAdapter(getActivity(), showViewCompareContentArrayList);
//        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
//        recyclerView.setLayoutManager(mLayoutManager);
//        recyclerView.setAdapter(viewCompareAdapter);
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
