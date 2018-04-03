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
        private TextView tvWord, tvProba, tvPredict, tvPredictionValue, tvAllLabelWord, tvClassificationReport, tvConfusionMatrix;
        private ImageButton btnShowChart;


        public ViewDetailHolder(View itemView) {
            super(itemView);
            tvWord = (TextView) itemView.findViewById(R.id.tvWord);
            tvProba = (TextView) itemView.findViewById(R.id.tvProba);
            tvPredict = (TextView) itemView.findViewById(R.id.tvPredict);
            tvPredictionValue = (TextView) itemView.findViewById(R.id.tvPredictionValue);
            tvAllLabelWord = (TextView) itemView.findViewById(R.id.tvAllLabelWord);
            tvClassificationReport = (TextView) itemView.findViewById(R.id.tvlassificationeport);
            tvConfusionMatrix = (TextView) itemView.findViewById(R.id.tvConfusionMatrix);
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

//        Pie pie = AnyChart.pie();
//
//        List<DataEntry> data = new ArrayList<>();
        final String customProba = viewDetailFragmentContent.getProba().replace("[","").replace("]","");
//        String proba[] = customProba.split("\\s");
//        List<Float> dataFloat = new ArrayList<>();
//        List<Integer> dataInt = new ArrayList<>();
//        for (int i=0; i<=proba.length-1;i++){
////           data.add(new ValueDataEntry(i,Integer.parseInt(proba[i])*1000000));
////            float a = Float.parseFloat(proba[i]);
//            if (!proba[i].isEmpty()){
//                dataFloat.add(Float.parseFloat(proba[i]));
//            }
//        }
//
//        for (int j = 0; j<=dataFloat.size()-1;j++){
//            float h = dataFloat.get(j)*1000000;
//            int a = Math.round(h);
//            dataInt.add(a);
//        }
//
//        for (int k = 0; k<=dataInt.size()-1;k++){
//            data.add(new ValueDataEntry(k,dataInt.get(k)));
//        }
//        data.add(new ValueDataEntry("John", 10000));
//        data.add(new ValueDataEntry("Jake", 12000));
//        data.add(new ValueDataEntry("Peter", 15000));

//        pie.setData(data);
//
//        holder.chartView.setChart(pie);

        holder.btnShowChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertChart.showAlertChat(customProba, viewDetailFragmentContent.getAllLabelWord());
            }
        });
        holder.tvWord.setText("TỪ: " + viewDetailFragmentContent.getWord());
        holder.tvProba.setText("XÁC XUẤT: " + viewDetailFragmentContent.getProba());
        holder.tvPredict.setText("DANH SÁCH DỰ ĐOÁN: " + viewDetailFragmentContent.getPredict());
        holder.tvAllLabelWord.setText(("NHÃN TỪ: " + viewDetailFragmentContent.getAllLabelWord()));
        holder.tvPredictionValue.setText("GIÁ TRỊ DỰ ĐOÁN: " + viewDetailFragmentContent.getPredictionValue());

        if (viewDetailFragmentContent.getClassificationReport().isEmpty() || viewDetailFragmentContent.getClassificationReport().trim().equals("Class")) {
            holder.tvClassificationReport.setVisibility(View.GONE);
        } else {
            holder.tvClassificationReport.setText("BÁO CÁO PHÂN TÍCH: " + "\n" + viewDetailFragmentContent.getClassificationReport());
        }
        if (viewDetailFragmentContent.getConfusionMatrix().isEmpty()) {
            holder.tvConfusionMatrix.setVisibility(View.GONE);
        } else {
            System.out.println("ket qua");
//            System.out.println(viewDetailFragmentContent.getConfusionMatrix());
            holder.tvConfusionMatrix.setText("CONFUSIONMATRIX: " + "\n" + viewDetailFragmentContent.getConfusionMatrix().toString());
            System.out.println("CONFUSIONMATRIX: " + "\n" + "[" + viewDetailFragmentContent.getConfusionMatrix().toString() + "]");
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