package com.example.nguyenvulong.androiddemo;

import android.app.Activity;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * Created by nguyenvulong on 3/3/18.
 */


public class AlertAdapter extends RecyclerView.Adapter<AlertAdapter.AlertViewHolder> {
    private List<AlertContent> alertContentList;
    private Activity activity;
    /**Contructor*/
    public AlertAdapter(Activity activity,List<AlertContent> alertContentList) {
        this.activity = activity;
        this.alertContentList = alertContentList;
    }

    public void add(AlertContent alertContent){
        alertContentList.add(alertContent);
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
                .inflate(R.layout.item_alert_dialog,parent,false);
        return new AlertViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AlertViewHolder holder, int position) {
        /** Set Value*/
        AlertContent alertContent = alertContentList.get(position);
        holder.outPutAlertMessage.setText(alertContent.getOutputAlertMessageText());
        holder.btnAnlysis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
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

    public void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setView(R.layout.analysis);
        builder.setCancelable(true);
        builder.create();
        builder.show();
    }

    @Override
    public int getItemCount() {
        return alertContentList.size();
    }
}