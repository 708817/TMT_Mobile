/*
DESCRIPTION OF SectionsActivity.java
        After ng CoursesActivity, mapupunta sa Sections activity yung app. Dito magseselect ng
        section yung user. Kapat napili na yung section, mapupunta na sa class info
        (ClassActivity.java).
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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class SectionsActivity extends AppCompatActivity implements AsyncResponse {

    private WSRetrieveSection wsRetrieveSection;

    private Intent oldIntent, intent, intentOut;
    private Bundle oldBundle, bundle;

    private TextView tvSections;
    private ImageView ivLogoff;
    private ListView lvSections;

    private String sSections, email, course, section;
    private String[] saSections;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sections);

        // XML UI Assignment START
        tvSections = (TextView) findViewById(R.id.tvSections);
        ivLogoff = (ImageView) findViewById(R.id.ivLogoff);
        lvSections = (ListView) findViewById(R.id.lvSections);
        // XML UI Assignment END

        // GetExtras START
        oldIntent = getIntent();
        oldBundle = oldIntent.getExtras();
        email = oldBundle.getString("email");
        course = oldBundle.getString("course");
        // GetExtras END

        // Assigning and Declaring Variables START
        section = "";
        tvSections.setText(course + "Sections");
        // Assigning and Declaring Variables END

        wsRetrieveSection = new WSRetrieveSection();
        wsRetrieveSection.delegate = this;

        init();

    }

    private void init() {

        ivLogoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SectionsActivity.this, R.style.CustomAlertDialog);
                builder.setTitle("Logging Off");
                builder.setMessage("Are you sure?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        intentOut = new Intent(SectionsActivity.this, LoginActivity.class);
                        intentOut.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intentOut);
                    }
                });
                builder.setNegativeButton("No", null);
                builder.show();
            }
        });

        wsRetrieveSection.execute(email, course);

    }

    @Override
    public void processFinish(Object output) {

        sSections = (String) output;

        if (sSections.isEmpty()) {

        } else {
            saSections = sSections.split(";");

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(SectionsActivity.this,
                    android.R.layout.simple_list_item_1,
                    saSections);
            lvSections.setAdapter(adapter);
            lvSections.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    section = saSections[i];

                    intent = new Intent(SectionsActivity.this, ClassActivity.class);
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
}
