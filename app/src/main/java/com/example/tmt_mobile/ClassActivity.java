/*
DESCRIPTION OF ClassActivity.java
        Most likely di na magaganap yung class mismo ng prof. May option siyang magpatap ng ID
        yung mga estudyante. Di ko pa alam kung lalagyan pa ba natin ung nireretrieve yung class
        record.
*/
package com.example.tmt_mobile;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ClassActivity extends AppCompatActivity {

    private ImageView ivLogoff;
    private TextView tvClass;
    private Button bClassRecord, bStartAttendance;

    private Intent oldIntent, intent, intentOut;
    private Bundle oldBundle, bundle;

    private String email, course, section;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class);

        // XML UI Assignment START
        ivLogoff = (ImageView) findViewById(R.id.ivLogoff);
        tvClass = (TextView) findViewById(R.id.tvClass);
        bClassRecord = (Button) findViewById(R.id.bClassRecord);
        bStartAttendance = (Button) findViewById(R.id.bStartAttendance);
        // XML UI Assignment END

        // GetExtras START
        oldIntent = getIntent();
        oldBundle = oldIntent.getExtras();
        email = oldBundle.getString("email");
        course = oldBundle.getString("course");
        section = oldBundle.getString("section");
        // GetExtras END

        // Assigning and Declaring Variables START
        tvClass.setText(course + "/" + section);
        bClassRecord.setText("Class Record");
        bStartAttendance.setText("Attendance Mode");
        // Assigning and Declaring Variables END

        init();
    }

    private void init() {

        ivLogoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ClassActivity.this, R.style.CustomAlertDialog);
                builder.setTitle("Logging Off");
                builder.setMessage("Are you sure?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        intentOut = new Intent(ClassActivity.this, LoginActivity.class);
                        intentOut.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intentOut);
                    }
                });
                builder.setNegativeButton("No", null);
                builder.show();
            }
        });

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
                intent = new Intent(ClassActivity.this, AttendanceActivity.class);
                bundle = new Bundle();

                bundle.putString("email", email);
                bundle.putString("course", course);
                bundle.putString("section", section);
                intent.putExtras(bundle);

                startActivity(intent);
            }
        });

    }
}
