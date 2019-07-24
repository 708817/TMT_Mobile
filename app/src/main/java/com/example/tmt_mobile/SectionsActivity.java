/*
DESCRIPTION OF SectionsActivity.java
        After ng MDActivity, mapupunta sa Sections activity yung app. Dito magseselect ng
        section yung user.
*/

package com.example.tmt_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SectionsActivity extends AppCompatActivity {

    private WSRetrieveSection wsRetrieveSection;
    private ListView lvSections;

    private Intent oldIntent, intent;
    private Bundle oldBundle, bundle;

    private String sSections, email, course, section;
    private String[] saSections;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sections);

        // XML UI Assignment START
        lvSections = (ListView) findViewById(R.id.lvSections);
        // XML UI Assignment END

        // GetExtras START
        oldIntent = getIntent();
        oldBundle = oldIntent.getExtras();
        email = oldBundle.getString("email");
        course = oldBundle.getString("course");
        // GetExtras END

        section = "";

        init();

    }

    private void init() {

        wsRetrieveSection = new WSRetrieveSection(SectionsActivity.this);
        wsRetrieveSection.execute(email, course);
        sSections = wsRetrieveSection.sectionResult;

        if (sSections.isEmpty()) {

        } else {
            saSections = sSections.split(",");

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(SectionsActivity.this,
                    android.R.layout.simple_list_item_1,
                    saSections);
            lvSections.setAdapter(adapter);
            lvSections.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    section = saSections[i];

                    /*intent = new Intent(SectionsActivity.this, ClassActivity.class);
                    bundle = new Bundle();

                    bundle.putString("email", email);
                    bundle.putString("couse", course);
                    bundle.putString("section", section);
                    intent.putExtras(bundle);

                    startActivity(intent);*/
                }
            });
        }

    }
}
