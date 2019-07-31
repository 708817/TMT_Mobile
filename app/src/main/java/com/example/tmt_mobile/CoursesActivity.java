/*
DESCRIPTION OF CoursesActivity.java
        After ng LoginActivity, mapupunta sa Main Dashboard activity yung app. Dito magseselect ng
        course yung user. Once na sinelect yung course, mapupunta sa list of sections
        (SectionsActivity.java)
*/

package com.example.tmt_mobile;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class CoursesActivity extends AppCompatActivity implements AsyncResponse {

    private WSRetrieveInfo wsRetrieveInfo;

    private Intent oldIntent, intent, intentOut;
    private Bundle oldBundle, bundle;

    private TextView tvCourses;
    private ListView lvCourses;
    private ImageView ivLogoff;

    private String email;
    private String course;
    private String sCourses;
    private String[] saCourses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        // XML UI Assignment START
        tvCourses = (TextView) findViewById(R.id.tvCourses);
        ivLogoff = (ImageView) findViewById(R.id.ivLogoff);
        lvCourses = (ListView) findViewById(R.id.lvCourses);
        // XML UI Assignment END

        // GetExtras START
        oldIntent = getIntent();
        oldBundle = oldIntent.getExtras();
        email = oldBundle.getString("email");
        // GetExtras END

        // Assigning and Declaring Variables START
        sCourses = "";
        tvCourses.setText("Courses");
        // Assigning and Declaring Variables END

        wsRetrieveInfo = new WSRetrieveInfo();
        wsRetrieveInfo.delegate = this;

        init();

    }

    private void init() {

        ivLogoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(CoursesActivity.this, R.style.CustomAlertDialog);
                builder.setTitle("Logging Off");
                builder.setMessage("Are you sure?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        intentOut = new Intent(CoursesActivity.this, LoginActivity.class);
                        intentOut.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intentOut);
                    }
                });
                builder.setNegativeButton("No", null);
                builder.show();
            }
        });

        wsRetrieveInfo.execute(email);

    }

    @Override
    public void processFinish(Object output) {
        sCourses = (String) output;

        if (sCourses.isEmpty()) {

        } else {
            saCourses = sCourses.split(";");
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1,
                    saCourses);
            lvCourses.setAdapter(adapter);
            lvCourses.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    course = saCourses[i];

                    intent = new Intent(CoursesActivity.this, SectionsActivity.class);
                    bundle = new Bundle();

                    bundle.putString("course", course);
                    bundle.putString("email", email);
                    intent.putExtras(bundle);

                    startActivity(intent);

                }
            });
        }
    }
}
