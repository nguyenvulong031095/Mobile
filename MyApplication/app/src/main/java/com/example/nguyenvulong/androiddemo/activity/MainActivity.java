package com.example.nguyenvulong.androiddemo.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.nguyenvulong.androiddemo.entity.Content;
import com.example.nguyenvulong.androiddemo.R;
import com.example.nguyenvulong.androiddemo.adapter.ChatAdapter;
import com.example.nguyenvulong.androiddemo.entity.ViewCompareContent;
import com.example.nguyenvulong.androiddemo.entity.ViewDetailFragmentContent;
import com.example.nguyenvulong.androiddemo.fragment.FragmentBox;
import com.example.nguyenvulong.androiddemo.fragment.FragmentExample;
import com.example.nguyenvulong.androiddemo.fragment.ViewCompareFragment;
import com.example.nguyenvulong.androiddemo.showAlertChart;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Serializable,NavigationView.OnNavigationItemSelectedListener {
    private List<Content> contentList;
    private ChatAdapter chatAdapter;
    private EditText editInput;
    private RecyclerView recyclerView;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private ArrayList<ViewDetailFragmentContent> viewDetailFragmentContentList;


    ImageButton btnSend;
    Button btnAnalysis;
    String urlPredict = "http://172.20.10.2:3000/predict/";
    String urlAnalysis = "http://172.20.10.2:3000/analysis/";
    //    String urlPicture = "http://192.168.1.142:3000/picture/";
    String urlTongPredict;
    String urlTongAnalysis;
    String output, output2, output3;
    String input;
    ArrayList<String> arrayListInput;
    ArrayList<ViewCompareContent> arrayListOutput;
    JSONArray jsonArray;
    String formatConfusionMatrix;
    Content content;
    int count = 0;
    int countCharacter = 0;
    int countSpace = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_main);


        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        viewDetailFragmentContentList = new ArrayList<>();
        arrayListInput = new ArrayList<>();
        arrayListOutput = new ArrayList<>();
        btnSend = (ImageButton) findViewById(R.id.btnSend);
        editInput = (EditText) findViewById(R.id.editInput);


        if ((ArrayList<ViewDetailFragmentContent>) getIntent().getSerializableExtra("object_2") != null) {
            viewDetailFragmentContentList = (ArrayList<ViewDetailFragmentContent>) getIntent().getSerializableExtra("object_2");
        }
        if ((ArrayList<String>) getIntent().getSerializableExtra("input_2") != null) {
            arrayListInput = (ArrayList<String>) getIntent().getSerializableExtra("input_2");
        }


        editInput.setText(getIntent().getStringExtra("text"));
        addControl();

        drawerLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                hideKeyboard(v);
                return false;

            }
        });

        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                count = 0;
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input = editInput.getText().toString().trim();
                if (input.isEmpty()) {
                    System.out.println("khong lam nhe");
                } else {
                    urlTongPredict = urlPredict + Uri.encode(input);
                    urlTongAnalysis = urlAnalysis + Uri.encode(input);

                    RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
                    JsonObjectRequest jsonObjectRequestPredict = new JsonObjectRequest(Request.Method.GET, urlTongPredict, null, new Response.Listener<JSONObject>() {


                        @Override
                        public void onResponse(JSONObject response) {
                            try {

                                output = response.getString("output1").trim().replace("[", "").replace("]", "");
                                output2 = response.getString("output2").trim().replace("[", "").replace("]", "");
                                output3 = response.getString("output3").trim().replace("[", "").replace("]", "");

                                if (output.equals(output2)) {
                                    content = new Content(input, output, true);
                                } else {
                                    content = new Content(input, output, output2, output3);
                                    arrayListInput.add(input);
                                }


                                ViewCompareContent viewCompareContent = new ViewCompareContent(input, output, output2, output3);
                                contentList.add(content);
                                chatAdapter.notifyDataSetChanged();

                                arrayListOutput.add(viewCompareContent);
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


                    JsonObjectRequest jsonObjectRequestAnalysis = new JsonObjectRequest(Request.Method.GET, urlTongAnalysis, null, new Response.Listener<JSONObject>() {


                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                jsonArray = response.getJSONArray("result");
                                calculateJsonArray(jsonArray);
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
                    jsonObjectRequestAnalysis.setRetryPolicy(new DefaultRetryPolicy(20000,
                            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                    requestQueue.add(jsonObjectRequestPredict);
                    requestQueue.add(jsonObjectRequestAnalysis);
                    hideKeyboard(v);

                }
            }
        });


    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//
//        return true;
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void calculateJsonArray(JSONArray jsonArray) throws JSONException {
        for (int i = 0; i <= jsonArray.length() - 1; i++) {
            JSONObject object = (JSONObject) jsonArray.get(i);
            String stringFormat = object.getString("classification_report");
            String classificationFormat = "    " + "Class".trim() + " " + stringFormat.trim();
            String ConfusionMatrix = object.getString("confusion_matrix");
            int length = ConfusionMatrix.length();
            if (length == 0) {
                formatConfusionMatrix = ConfusionMatrix;
            } else {
                formatConfusionMatrix = ConfusionMatrix.substring(1, length - 1);

            }
            ViewDetailFragmentContent viewDetailFragmentContent = new ViewDetailFragmentContent(object.getString("word"), object.getString("probability"), object.getString("predict_List"),
                    input, object.getString("prediction_value"), object.getString("all_Lable"), classificationFormat, formatConfusionMatrix);
            viewDetailFragmentContentList.add(viewDetailFragmentContent);
        }
    }

    public void hideKeyboard(View v) {
        InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        in.hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.viewAnalysis:
                showViewAnalysisFragment();
                drawerLayout.closeDrawer(Gravity.LEFT);
                return true;
            case R.id.example:
                showExampleFragment();
                drawerLayout.closeDrawer(Gravity.LEFT);
                return true;
//            case R.id.compare:
//                showCompareFragment();
//                drawerLayout.closeDrawer(Gravity.LEFT);
//                return true;
            default:
                return true;
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.openNavigation:
                if (count == 0) {
                    drawerLayout.openDrawer(Gravity.LEFT);
                    count++;
                } else {
                    drawerLayout.closeDrawer(Gravity.LEFT);
                    count = 0;
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }


    }


    public void showViewAnalysisFragment() {
        getIntent().putExtra("object", viewDetailFragmentContentList);
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("input", arrayListInput);
        FragmentBox fragmentBox = new FragmentBox();
        fragmentBox.setArguments(bundle);
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction = fm.beginTransaction().replace(R.id.fragmentContent, fragmentBox).addToBackStack(null);
        transaction.commit();
        editInput.setEnabled(false);


    }

    public void showExampleFragment() {
        FragmentExample fragmentExample = new FragmentExample();
        getIntent().putExtra("object", viewDetailFragmentContentList);
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("input", arrayListInput);
        fragmentExample.setArguments(bundle);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction().replace(R.id.fragmentContent, fragmentExample, "home").addToBackStack(null);
        transaction.commit();
        editInput.setEnabled(false);

    }


    public void showCompareFragment() {
//        getIntent().putExtra("object_output", arrayListOutput);
        ViewCompareFragment viewCompareFragment = new ViewCompareFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction().replace(R.id.fragmentContent, viewCompareFragment, "home").addToBackStack(null);
        transaction.commit();
        editInput.setEnabled(false);

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
