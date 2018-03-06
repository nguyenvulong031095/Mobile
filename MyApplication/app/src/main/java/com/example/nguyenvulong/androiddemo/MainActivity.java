package com.example.nguyenvulong.androiddemo;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener  {
    private List<Content> contentList;
    private List<AlertContent> alertContentList;
    private ChatAdapter chatAdapter;
    private AlertAdapter alertAdapter;
    private EditText editInput;
    private RecyclerView recyclerView;
    private RecyclerView recyclerViewAlert;
    private TextView example01;
    private TextView example02;
//private ListView recyclerViewAlert;

    ImageButton btnSend;
    Button btnAnalysis;
    String url = "http://192.168.1.104:3000/predict/";
    String urlTong;
    String output;
    String input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        btnSend = (ImageButton) findViewById(R.id.btnSend);
        editInput = (EditText) findViewById(R.id.editInput);

        example02 = (TextView) findViewById(R.id.tvExample02);

        addControl();
        alertContentList = new ArrayList<>();
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input = editInput.getText().toString();
                urlTong = url + Uri.encode(input);

                RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, urlTong, null, new Response.Listener<JSONObject>() {


                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            System.out.println(response);
                            output = response.getString("output");
                            Log.e("A", output);
                            Content content = new Content(input, output);
                            contentList.add(content);
                            chatAdapter.notifyDataSetChanged();
                            alertContentList.add(new AlertContent(output));
                            editInput.setText("");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Error", error.toString());
                        Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                });

                requestQueue.add(jsonObjectRequest);
            }
        });



    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.activity_main, menu);
//        return true;
//    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
//        int id = item.getItemId();

//        if (id == R.id.viewAnalysis) {
//            // Handle the camera action
//            showViewAnalysisDialog();
//            return true;
//        }
//        return true;
        switch (item.getItemId()) {
            case R.id.viewAnalysis:
                showViewAnalysisDialog();
                return true;
            case R.id.example:
                showExampleDialog();
                return true;
            default:
                return true;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.viewAnalysis:
                showViewAnalysisDialog();
                return true;
            case R.id.example:
                showExampleDialog();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void showViewAnalysisDialog() {
        alertAdapter = new AlertAdapter(this, alertContentList);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(R.layout.custom_alert_dialog);
        builder.setCancelable(true);
        builder.create();
        AlertDialog a = builder.show();
        recyclerViewAlert = (RecyclerView) a.findViewById(R.id.recyclerAlertDialog);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewAlert.setLayoutManager(mLayoutManager);
        recyclerViewAlert.setAdapter(alertAdapter);


    }

    public void showExampleDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(R.layout.example_alert_dialog);
        builder.setCancelable(true);
        builder.create();
        final AlertDialog dialog = builder.show();
        example01 = (TextView) dialog.findViewById(R.id.tvExample01);
        example01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editInput.setText(example01.getText());
                dialog.dismiss();
            }
        });

    }
    private void addControl() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerChat);
        contentList = new ArrayList<>();
        chatAdapter = new ChatAdapter(this, contentList);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mLayoutManager.setStackFromEnd(true);
        mLayoutManager.scrollToPosition(contentList.size() - 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(chatAdapter);
    }


}
