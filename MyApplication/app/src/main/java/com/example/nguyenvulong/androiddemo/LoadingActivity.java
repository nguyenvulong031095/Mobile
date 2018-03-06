package com.example.nguyenvulong.androiddemo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by nguyenvulong on 3/5/18.
 */

public class LoadingActivity extends AppCompatActivity {
    private int progressBarStatus = 0;
    private Handler progressBarHandler = new Handler();
    private long fileSize = 0;
    private ProgressBar progressBars;
    private TextView txtPercentage;
    private String status;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading_main);
      //  txtPercentage = (TextView) findViewById(R.id.txtPercentage);
//        //reset progress bar status
//        progressBarStatus = 0;
//        //reset filesize
//        fileSize = 0;
//        progressBars.setProgress(0);
//        progressBars.setMax(100);
        new Thread(new Runnable() {
            public void run() {
                while (progressBarStatus < 100) {

                    // process some tasks
                    progressBarStatus = fileDownloadStatus();

                    //  sleep 1 second to show the progress
                    try {
                        Thread.sleep(1000);
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    // Update the progress bar
                    progressBarHandler.post(new Runnable() {
                        public void run() {
//                            progressBars.setProgress(progressBarStatus);
                        }
                    });
                }

                // when, file is downloaded 100%,
                if (progressBarStatus >= 100) {

                    // sleep for  2 seconds, so that you can see the 100% of file download
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    // close the progress bar dialog
//                    progressBar.dismiss();
                    Intent intent = new Intent(LoadingActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        }).start();
    }





    //method returns the % of file downloaded
    public int fileDownloadStatus()
    {

        while (fileSize <= 1000000) {

            fileSize++;

            if (fileSize == 100000) {
                status = "Loading";
                return 10;
            } else if (fileSize == 200000) {
                status = "Loading";
                return 20;
            } else if (fileSize == 300000) {
                status = "Loading";
                return 30;
            }
            else if (fileSize == 400000) {
                status = "Loading";
                return 40;
            } else if (fileSize == 500000) {
                status = "Loading";
                return 50;
            } else if (fileSize == 600000) {
                status = "Loading";
                return 60;
            }else if (fileSize == 700000) {
                status = "Loading";
                return 70;
            }else if (fileSize == 800000) {
                status = "Loading";
                return 80;
            }else if (fileSize == 900000) {
                status = "Loading";
                return 90;
            }
            // write your code here
        }
        status = "Loading";
        return 100;

    }
}
