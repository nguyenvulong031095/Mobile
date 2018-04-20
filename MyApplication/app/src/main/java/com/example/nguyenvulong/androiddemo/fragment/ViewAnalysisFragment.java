package com.example.nguyenvulong.androiddemo.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.anychart.anychart.AnyChart;
import com.anychart.anychart.AnyChartView;
import com.anychart.anychart.DataEntry;
import com.anychart.anychart.Pie;
import com.anychart.anychart.UiLabel;
import com.anychart.anychart.ValueDataEntry;
import com.anychart.anychart.chart.common.Event;
import com.anychart.anychart.chart.common.ListenersInterface;
import com.example.nguyenvulong.androiddemo.R;
import com.example.nguyenvulong.androiddemo.adapter.ViewDetailFragmentAdapter;
import com.example.nguyenvulong.androiddemo.entity.ViewDetailFragmentContent;
import com.example.nguyenvulong.androiddemo.showAlertChart;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Templates;

/**
 * Created by nguyenvulong on 3/16/18.
 */

public class ViewAnalysisFragment extends Fragment implements showAlertChart {

    private RecyclerView recyclerView;
    private ViewDetailFragmentAdapter viewDetailFragmentAdapter;
    private String input;
    private EditText editInput;
    private ArrayList<ViewDetailFragmentContent> showViewDetailFragmentList;
    private ArrayList<ViewDetailFragmentContent> viewDetailFragmentContentList;
    //    private AnyChartView anyChart;
    private PieChart pieChart;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.custom_view_analysis_fragment, container, false);
        if (getArguments() != null) {
            input = getArguments().getString("stringne");
            System.out.println(input);
        } else {
            System.out.println("Empty");
        }


        showViewDetailFragmentList = new ArrayList<>();
        Intent data = getActivity().getIntent();
        viewDetailFragmentContentList = (ArrayList<ViewDetailFragmentContent>) data.getSerializableExtra("object");

        for (int i = 0; i < viewDetailFragmentContentList.size(); i++) {
            if (input.equals(viewDetailFragmentContentList.get(i).getInput())) {
                showViewDetailFragmentList.add(viewDetailFragmentContentList.get(i));
            }
        }

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewDetailFragment);
        viewDetailFragmentAdapter = new ViewDetailFragmentAdapter(getActivity(), showViewDetailFragmentList, this);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(viewDetailFragmentAdapter);
        return view;
    }

    @Override
    public void onPause() {
        Log.e("DEBUG", "OnPause of loginFragment");
        editInput = (EditText) getActivity().findViewById(R.id.editInput);
        editInput.setEnabled(false);
        super.onPause();
    }


    @Override
    public void showAlertChat(String customProba, String customLabel) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
//        Pie pie = AnyChart.pie();
//        List<DataEntry> data = new ArrayList<>();
//        String proba[] = customProba.split("\\s");
//        String label[] = customLabel.split("\\s");
//        List<Float> dataFloat = new ArrayList<>();
//        List<Integer> dataInt = new ArrayList<>();
//        for (int i = 0; i <= proba.length - 1; i++) {
//            if (!proba[i].isEmpty()) {
//                dataFloat.add(Float.parseFloat(proba[i]));
//            }
//        }
//
//        for (int j = 0; j <= dataFloat.size() - 1; j++) {
//            float h = dataFloat.get(j) * 1000000;
//            int a = Math.round(h);
//            dataInt.add(a);
//        }
//
//        for (int k = 0; k <= dataInt.size() - 1; k++) {
//            data.add(new ValueDataEntry(label[k], dataInt.get(k)));
//        }
//
//        pie.getNormal().setHatchFill(["divot", "grid", "vertical", "horizontal"]);
//        pie.getNormal().setHatchFill("forward-diagonal", "#669999",0.1,1.0);
//        pie.setSort("desc");
//        pie.getLabels().setFormat("{%x}");
//        pie.getLabels().setFontSize(10.0);
//        pie.setRadius("40%");
//        pie.setTitle("VIEW DETAIL ANALYSIS CHART");
//        pie.setData(data);
        View view = inflater.inflate(R.layout.popup_chart, null);
        pieChart = (PieChart) view.findViewById(R.id.any_chart_view);
        pieChart.setUsePercentValues(true);
        pieChart.setExtraOffsets(5, 10, 5, 5);
        pieChart.setDragDecelerationFrictionCoef(1f);
        pieChart.setDrawHoleEnabled(false);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setTransparentCircleRadius(100f);

        pieChart.setDrawCenterText(true);


        Description description = new Description();
        description.setText("VIEW DETAIL");
        description.setTextAlign(Paint.Align.RIGHT);
        description.setTextSize(20);
        pieChart.setDescription(description);

        ArrayList<PieEntry> yValues = new ArrayList<>();
        String proba[] = customProba.split("\\s");
        String label[] = customLabel.split("\\s");
        List<Float> dataFloat = new ArrayList<>();
        List<Integer> dataInt = new ArrayList<>();
        for (int i = 0; i <= proba.length - 1; i++) {
            if (!proba[i].isEmpty()) {
                dataFloat.add(Float.parseFloat(proba[i]));
            }
        }

        for (int j = 0; j <= dataFloat.size() - 1; j++) {
            float h = dataFloat.get(j) * 1000000;
            int a = Math.round(h);
            dataInt.add(a);
        }

        for (int k = 0; k <= dataInt.size() - 1; k++) {
            yValues.add(new PieEntry(dataInt.get(k), label[k]));
        }


        PieDataSet dataSet = new PieDataSet(yValues, "words");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(2f);
//        dataSet.setColors( Color.rgb(187,177,197),Color.rgb(149,177,197),Color.rgb(158, 236, 197));
        dataSet.setColors(Color.rgb(187, 177, 197), Color.rgb(149, 177, 197), Color.rgb(158, 236, 197),
                Color.rgb(45, 148, 197),  Color.rgb(131, 148, 42),
                Color.rgb(233, 238, 249), Color.rgb(233, 55, 249), Color.rgb(153, 11, 249),
                Color.rgb(26, 172, 238), Color.rgb(26, 85, 32), Color.rgb(225, 240, 32),
                Color.rgb(255, 240, 25), Color.rgb(255, 137, 25), Color.rgb(255, 189, 140),
                Color.rgb(233, 148, 42), Color.rgb(229 ,70 ,70), Color.rgb(199,195,0),
                Color.rgb(131,199,93), Color.rgb(0,160,107), Color.rgb(0,166,173),Color.rgb(0,140,94),
                Color.rgb(115,136,193), Color.rgb(160,149,196), Color.rgb(183,183,183)
        );
        PieData data = new PieData(dataSet);


        Legend legend = pieChart.getLegend();
        legend.setEnabled(true);
        legend.setTextSize(20);
        legend.setWordWrapEnabled(true);

//        legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);


        data.setValueFormatter(new MyValueFormatter());
        if(label.length>14){
            data.setValueTextSize(9f);
        }else{
            data.setValueTextSize(15f);
        }

        data.setValueTextColor(Color.YELLOW);

        pieChart.animateY(1000, Easing.EasingOption.EaseInOutCubic);
        pieChart.setData(data);
//        anyChart = (AnyChartView) view.findViewById(R.id.any_chart_view);
        builder.setView(view);
//        anyChart.setChart(pie);
        builder.create();
        builder.show();
    }
}

class MyValueFormatter implements IValueFormatter {

    private DecimalFormat mFormat;

    public MyValueFormatter() {
        mFormat = new DecimalFormat("###,###,##0.0"); // use one decimal
    }

    @Override
    public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
        // write your logic here
        return mFormat.format(value) + "%"; // e.g. append a dollar-sign
    }
}