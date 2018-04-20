package com.example.nguyenvulong.androiddemo.adapter;

import android.app.Activity;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.anychart.anychart.AnyChart;
import com.anychart.anychart.AnyChartView;
import com.anychart.anychart.DataEntry;
import com.anychart.anychart.Pie;
import com.anychart.anychart.ValueDataEntry;
import com.example.nguyenvulong.androiddemo.R;
import com.example.nguyenvulong.androiddemo.entity.ViewDetailFragmentContent;
import com.example.nguyenvulong.androiddemo.showAlertChart;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nguyenvulong on 3/16/18.
 */


public class ViewDetailFragmentAdapter extends RecyclerView.Adapter<ViewDetailFragmentAdapter.ViewDetailHolder> {
    private List<ViewDetailFragmentContent> viewDetailFragmentContentList;
    private Activity activity;
    private showAlertChart showAlertChart;
    private String Word;
//    private LoadImage loadImage;

    /**
     * Contructor
     */
    public ViewDetailFragmentAdapter(Activity activity, List<ViewDetailFragmentContent> viewDetailFragmentContentList, showAlertChart showAlertChart) {
        this.activity = activity;
        this.viewDetailFragmentContentList = viewDetailFragmentContentList;
        this.showAlertChart = showAlertChart;
//        this.loadImage = loadImage;
    }

    public void add(ViewDetailFragmentContent ViewDetailFragmentContent) {
        viewDetailFragmentContentList.add(ViewDetailFragmentContent);
        notifyDataSetChanged();
    }

    /**
     * Create ViewHolder
     */
    public class ViewDetailHolder extends RecyclerView.ViewHolder {
        private TextView tvWord, tvPredict, tvPredictionValue, tvAllLabelWord, tvClassificationReport;
        private ImageButton btnShowChart;


        public ViewDetailHolder(View itemView) {
            super(itemView);
            tvWord = (TextView) itemView.findViewById(R.id.tvWord);
            tvPredict = (TextView) itemView.findViewById(R.id.tvPredict);
            tvPredictionValue = (TextView) itemView.findViewById(R.id.tvPredictionValue);
            tvAllLabelWord = (TextView) itemView.findViewById(R.id.tvAllLabelWord);
            tvClassificationReport = (TextView) itemView.findViewById(R.id.tvlassificationeport);
            btnShowChart = (ImageButton) itemView.findViewById(R.id.btnShowChart);


        }
    }

    @Override
    public ViewDetailHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        /** Get layout */
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_custom_view_analysis_fragment, parent, false);
        return new ViewDetailHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewDetailHolder holder, int position) {
        /** Set Value*/
        final ViewDetailFragmentContent viewDetailFragmentContent = viewDetailFragmentContentList.get(position);
        int count =0;
        String allLabel ="";

        final String customProba = viewDetailFragmentContent.getProba().replace("[","").replace("]","");


        String label[] = viewDetailFragmentContent.getAllLabelWord().trim().split("\\s");
        holder.btnShowChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertChart.showAlertChat(customProba, viewDetailFragmentContent.getAllLabelWord());
            }
        });
        for (int i=0; i<=label.length-1;i++){
            if(count<4){
                allLabel = allLabel + " "+label[i];
                count++;
            }else{
                allLabel = allLabel + " "+ label[i]+"\n";
                count = 0;
            }
        }
        holder.tvWord.setText("TỪ: " + "\n"+ viewDetailFragmentContent.getWord());
        holder.tvPredict.setText("DANH SÁCH DỰ ĐOÁN: " +"\n"+ viewDetailFragmentContent.getPredict());
        System.out.println(allLabel);
        holder.tvAllLabelWord.setText(("NHÃN TỪ: " +"\n" + allLabel));
        holder.tvPredictionValue.setText("GIÁ TRỊ DỰ ĐOÁN: " +"\n"+ viewDetailFragmentContent.getPredictionValue());

        if (viewDetailFragmentContent.getClassificationReport().isEmpty() || viewDetailFragmentContent.getClassificationReport().trim().equals("Class")) {
            holder.tvClassificationReport.setVisibility(View.GONE);
        } else {
            holder.tvClassificationReport.setText("BÁO CÁO PHÂN TÍCH: " + "\n" + viewDetailFragmentContent.getClassificationReport());
        }


//        Picasso.with(activity.getApplicationContext())
//                .load(viewDetailFragmentContent.getLinkImage())
//                .into(holder.imageView);


    }




    @Override
    public int getItemCount() {
        return viewDetailFragmentContentList.size();
    }
}