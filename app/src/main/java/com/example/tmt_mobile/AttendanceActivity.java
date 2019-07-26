/*
DESCRIPTION OF AttendanceActivity.java
        Dito yung tap mode ng app. Once na scinan ng phone yung RFID, checheck niya kung enrolled
        student siya sa class na pinili ng user.
*/

package com.example.tmt_mobile;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

public class AttendanceActivity extends AppCompatActivity {

    private WSSetAttendance wsSetAttendance;

    private Intent oldIntent;
    private Bundle oldBundle;

    private TextView tvStatement_1;
    private Toast toast;
    private AlertDialog.Builder builder;

    private String email, course, section;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);

        tvStatement_1 = (TextView) findViewById(R.id.tvStatement_1);

        oldIntent = getIntent();
        oldBundle = oldIntent.getExtras();
        email = oldBundle.getString("email");
        course = oldBundle.getString("course");
        section = oldBundle.getString("section");

        init();

    }

    private void init() {

        // Insert NFC Code Here

        wsSetAttendance = new WSSetAttendance(AttendanceActivity.this);
        wsSetAttendance.execute(/*insert parameters here*/);

        if (!wsSetAttendance.tapResult) {
            builder = new AlertDialog.Builder(AttendanceActivity.this, R.style.CustomAlertDialog);
            builder.setTitle("Error Occurred");
            builder.setMessage("Attendance Failed");
            builder.setCancelable(true);
            builder.show();
        } else {
            toast = Toast.makeText(getApplicationContext(), "Attendance Successful", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.show();
        }
    }
}
