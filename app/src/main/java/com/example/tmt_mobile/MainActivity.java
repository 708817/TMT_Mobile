/*
DESCRIPTION OF MainActivity.java
        Eto yung magiging Loading Screen ng mobile application. It will check if the connections to
        the web service and database are working. It will not proceed to the login activity
        (LoginActivity.java) as long as the connections keep failing.
*/

package com.example.tmt_mobile;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements AsyncResponse {

    // TEST

    private WSTestServices wsTestServices;
    private TextView tvLoading, tvLoadingStatus;
    private AlertDialog.Builder builder;
    private Boolean testOutput;
    private int trial;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // XML UI Assignment START
        tvLoading = (TextView) findViewById(R.id.tvLoading);
        tvLoadingStatus = (TextView) findViewById(R.id.tvLoadingStatus);
        // XML UI Assignment END

        // Initial Values Declaration START
        tvLoading.setText("LOADING...");
        tvLoadingStatus.setText("Setting up connection");
        builder = new AlertDialog.Builder(MainActivity.this, R.style.CustomAlertDialog);
        testOutput = false;
        trial = 0;
        // Initial Values Declaration END

        init();
    }

    private void init() {
        testServices();
    }

    private void testServices() {
        wsTestServices = new WSTestServices();
        wsTestServices.delegate = this;

        tvLoadingStatus.setText("Connecting to Online Services");

        wsTestServices.execute();
    }

    @Override
    public void processFinish(Object output) {
        Boolean result;
        result = (Boolean) output;
        if (!result) {
            if (trial >= 10) {
                builder.setTitle("Error Occured");
                builder.setMessage("Connection failed after (" + trial + ") retries.");
                builder.setCancelable(true);
                builder.show();
                tvLoadingStatus.setText("Connection Failed");
                return;
            }
            testServices();
        } else {
            // Insert Animation
            intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    }
}