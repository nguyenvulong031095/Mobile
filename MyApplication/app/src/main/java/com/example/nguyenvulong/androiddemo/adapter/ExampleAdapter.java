package com.example.nguyenvulong.androiddemo.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nguyenvulong.androiddemo.ExampleInterface;
import com.example.nguyenvulong.androiddemo.R;
import com.example.nguyenvulong.androiddemo.entity.Example;

import java.util.List;

/**
 * Created by nguyenvulong on 3/24/18.
 */


public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {
    private List<Example> exampleList;
    private ExampleInterface e;
    private Activity activity;
    /**Contructor*/
    public ExampleAdapter(Activity activity,List<Example> exampleList,ExampleInterface e) {
        this.activity = activity;
        this.exampleList = exampleList;
        this.e = e;
    }
    /** Create ViewHolder*/
    public class ExampleViewHolder extends  RecyclerView.ViewHolder {
        private TextView example;


        public ExampleViewHolder(View itemView) {
            super(itemView);
            example = (TextView) itemView.findViewById(R.id.tvExample01);



        }
    }
    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        /** Get layout */
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.example_item,parent,false);
        return new ExampleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {
        /** Set Value*/
        final Example example = exampleList.get(position);
        holder.example.setText(example.getExmaple());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                e.eventClickingItem(example.getExmaple());
            }
        });
    }

    @Override
    public int getItemCount() {
        return exampleList.size();
    }
}