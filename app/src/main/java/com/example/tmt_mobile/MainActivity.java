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

public class MainActivity extends AppCompatActivity {

    private TextView tvLoading, tvLoadingStatus;
    private AlertDialog.Builder builder;
    private Boolean testOutput;
    private int trial1, trial2;
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
        trial1 = 0;
        trial2 = 0;
        // Initial Values Declaration END

        init();
    }

    private void init() {
        do {
            trial1++;
            testOutput = testWebService();
            if (trial1 >= 10) {
                builder.setTitle("Error Occured (1)");
                builder.setMessage("Connection failed after (" + trial1 + ") retries.");
                builder.setCancelable(true);
                builder.show();
                break;
            }
        } while (testOutput == false);

        if (testOutput) {
            testOutput = false;
            do {
                trial2++;
                testOutput = testDatabase();
                if (trial2 >= 10) {
                    builder.setTitle("Error Occured (2)");
                    builder.setMessage("Connection failed after (" + trial1 + ") retries.");
                    builder.setCancelable(true);
                    builder.show();
                    break;
                }
            } while (testOutput == false);
        }

        if (testOutput) {
            // Insert Animation
            intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    }

    private Boolean testWebService() {
        tvLoadingStatus.setText("Connecting to Web Server");

        /*  Insert Web Service method "testWebService()"    */

        return null;
    }

    private Boolean testDatabase() {
        tvLoadingStatus.setText("Connecting to Database");

        /*  Insert Web Service method "testDatabase()"  */

        return null;
    }
}
