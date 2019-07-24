/*
DESCRIPTION OF AttendanceMode.java
        Dito yung tap mode ng app. Once na scinan ng phone yung RFID, checheck niya kung enrolled
        student siya sa class na pinili ng user.
*/

package com.example.tmt_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class AttendanceMode extends AppCompatActivity {

    private Intent oldIntent;
    private Bundle oldBundle;

    private TextView tvStatement_1;

    private String email, course, section;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance_mode);

        tvStatement_1 = (TextView) findViewById(R.id.tvStatement_1);

        oldIntent = getIntent();
        oldBundle = oldIntent.getExtras();
        email = oldBundle.getString("email");
        course = oldBundle.getString("course");
        section = oldBundle.getString("section");

        init();

    }

    private void init() {

        // Insert NFS Code Here

    }
}
