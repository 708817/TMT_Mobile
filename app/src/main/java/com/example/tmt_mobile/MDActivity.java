package com.example.tmt_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MDActivity extends AppCompatActivity {

    private WSRetrieveInfo wsRetrieveInfo;
    private Intent oldIntent, intent;
    private Bundle oldBundle, bundle;
    private ListView lvCourses;

    private String email;
    private String course;
    private String sCourses;
    private String[] saCourses;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_md);

        // XML UI Assignment START
        lvCourses = (ListView) findViewById(R.id.lvCourses);
        // XML UI Assignment END

        // GetExtras START
        oldIntent = getIntent();
        oldBundle = oldIntent.getExtras();
        email = oldBundle.getString("email");
        // GetExtras END

        init();

    }

    private void init() {

        wsRetrieveInfo = new WSRetrieveInfo(MDActivity.this);
        wsRetrieveInfo.execute(email);
        sCourses = wsRetrieveInfo.retrieveResult;

        saCourses = sCourses.split(",");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                saCourses);
        lvCourses.setAdapter(adapter);
        lvCourses.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                course = saCourses[i];

                // SectionsActivity to be added (as of v0.11)
                /*intent = new Intent(MDActivity.this, SectionsActivity.java);
                bundle = new Bundle();

                bundle.putString("course", course);
                intent.putExtras(bundle);

                startActivity(intent);*/

            }
        });
    }
}
