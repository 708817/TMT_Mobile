package com.example.tmt_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ClassActivity extends AppCompatActivity {

    private TextView tvSection;
    private Button bClassRecord, bStartAttendance, bBack;

    private Intent oldIntent, intent;
    private Bundle oldBundle, bundle;

    private String email, course, section;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class);

        // XML UI Assignment START
        tvSection = (TextView) findViewById(R.id.tvSection);
        bClassRecord = (Button) findViewById(R.id.bClassRecord);
        bStartAttendance = (Button) findViewById(R.id.bStartAttendance);
        bBack = (Button) findViewById(R.id.bBack);
        // XML UI Assignment END

        // GetExtras START
        oldIntent = getIntent();
        oldBundle = oldIntent.getExtras();
        email = oldBundle.getString("email");
        course = oldBundle.getString("course");
        section = oldBundle.getString("section");
        // GetExtras END

        init();
    }

    private void init() {

        tvSection.setText(course + "/" + section);
        bClassRecord.setText("Class Record");
        bStartAttendance.setText("Attendance Mode");
        bBack.setText("Back");

        bClassRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*intent = new Intent(ClassActivity.this, ClassRecord.class);
                bundle = new Bundle();

                bundle.putString("email", email);
                bundle.putString("course", course);
                bundle.putString("section", section);
                intent.putExtras(bundle);

                startActivity(intent);*/
            }
        });

        bStartAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*intent = new Intent(ClassActivity.this, AttendanceActivity.class);
                bundle = new Bundle();

                bundle.putString("email", email);
                bundle.putString("course", course);
                bundle.putString("section", section);
                intent.putExtras(bundle);

                startActivity(intent);*/
            }
        });

        bBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}
