package com.example.nguyenvulong.androiddemo.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.nguyenvulong.androiddemo.entity.AnalysisFragmentContent;
import com.example.nguyenvulong.androiddemo.IListAction;
import com.example.nguyenvulong.androiddemo.R;

import java.util.List;

/**
 * Created by nguyenvulong on 3/3/18.
 */


public class AnalysisFragmentAdapter extends RecyclerView.Adapter<AnalysisFragmentAdapter.AlertViewHolder>  {
    private List<AnalysisFragmentContent> analysisFragmentContentList;
    private IListAction i;
    private Activity activity;
    /**Contructor*/
    public AnalysisFragmentAdapter(Activity activity, List<AnalysisFragmentContent> analysisFragmentContentList, IListAction i) {
        this.activity = activity;
        this.analysisFragmentContentList = analysisFragmentContentList;
        this.i = i;
    }

    public void add(AnalysisFragmentContent analysisFragmentContent){
        analysisFragmentContentList.add(analysisFragmentContent);
        notifyDataSetChanged();
    }

    /** Create ViewHolder*/
    public class AlertViewHolder extends  RecyclerView.ViewHolder {
        private TextView outPutAlertMessage;
        private Button btnAnlysis;


        public AlertViewHolder(View itemView) {
            super(itemView);
            outPutAlertMessage = (TextView) itemView.findViewById(R.id.tvOutPutAlertMessenge);
            btnAnlysis = (Button)itemView.findViewById(R.id.btnAnlysis);

        }
    }
    @Override
    public AlertViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        /** Get layout */
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_analysis_fragment,parent,false);
        return new AlertViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AlertViewHolder holder, int position) {
        /** Set Value*/
        final AnalysisFragmentContent analysisFragmentContent = analysisFragmentContentList.get(position);
        holder.outPutAlertMessage.setText(analysisFragmentContent.getOutputAlertMessageText());
        holder.btnAnlysis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i.doSomething(analysisFragmentContent.getOutputAlertMessageText());
            }
        });


       /*Sự kiện click vào item*/
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("aaaaaaaaaaabbbbbbbbbbbcccccc");
            }
        });
    }



    @Override
    public int getItemCount() {
        return analysisFragmentContentList.size();
    }
}