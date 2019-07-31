/*
DESCRIPTION OF AttendanceActivity.java
        Dito yung tap mode ng app. Once na nascan ng phone yung RFID, checheck niya kung enrolled
        student siya sa class na pinili ng user.
*/

package com.example.tmt_mobile;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class AttendanceActivity extends AppCompatActivity implements AsyncResponse{

    private WSSetAttendance wsSetAttendance;

    private Intent oldIntent;
    private Bundle oldBundle;

    private ImageView ivStudent;
    private TextView tvStatement_1;
    private Toast toast;
    private AlertDialog.Builder builder;

    private String email, course, section, name, result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);

        // XML UI Assignment START
        ivStudent = (ImageView) findViewById(R.id.ivStudent);
        tvStatement_1 = (TextView) findViewById(R.id.tvStatement_1);
        // XML UI Assignment END

        // GetExtras START
        oldIntent = getIntent();
        oldBundle = oldIntent.getExtras();
        email = oldBundle.getString("email");
        course = oldBundle.getString("course");
        section = oldBundle.getString("section");
        // GetExtras END

        // Assigning and Declaring Variables START
        tvStatement_1.setText("Tap the student ID at the back of the phone to start scanning");
        // Assigning and Declaring Variables END

        wsSetAttendance = new WSSetAttendance();
        wsSetAttendance.delegate = this;

        init();

    }

    private void init() {

        // Insert NFC Code Here

        ivStudent.setVisibility(View.INVISIBLE);

        wsSetAttendance.execute(/*insert parameters here*/);
    }

    @Override
    public void processFinish(Object output) {
        result = (String) output;
        if (result.isEmpty()) {
            // Dito sana iseset yung image ng student
            ivStudent.setVisibility(View.INVISIBLE);
            builder = new AlertDialog.Builder(AttendanceActivity.this, R.style.CustomAlertDialog);
            builder.setTitle("Error Occurred");
            builder.setMessage("Attendance Failed");
            builder.setCancelable(true);
            builder.show();
        } else {
            ivStudent.setVisibility(View.VISIBLE);
            toast = Toast.makeText(getApplicationContext(), "Attendance Successful", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.show();
        }
    }
}
