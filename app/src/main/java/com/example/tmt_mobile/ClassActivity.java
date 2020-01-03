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
import android.os.Environment;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ClassActivity extends AppCompatActivity implements AsyncResponse {

    private WSRetrieveClassRecord wsRetrieveClassRecord;

    private ImageView ivLogoff;
    private TextView tvClass;
    private ImageButton ibClassRecord, ibStartAttendance;

    private Intent oldIntent, intent, intentOut;
    private Bundle oldBundle, bundle;

    DateFormat dateFormat;
    private String email, course, section, date, result;
    private String[] rows, columns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class);

        // XML UI Assignment START
        ivLogoff = (ImageView) findViewById(R.id.ivLogoff);
        tvClass = (TextView) findViewById(R.id.tvClass);
        ibClassRecord = (ImageButton) findViewById(R.id.ibClassRecord);
        ibStartAttendance = (ImageButton) findViewById(R.id.ibStartAttendance);
        // XML UI Assignment END

        // GetExtras START
        oldIntent = getIntent();
        oldBundle = oldIntent.getExtras();
        email = oldBundle.getString("email");
        course = oldBundle.getString("course");
        section = oldBundle.getString("section");
        // GetExtras END

        // Assigning and Declaring Variables START
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        tvClass.setText(course + "/" + section);
        // Assigning and Declaring Variables END

        wsRetrieveClassRecord = new WSRetrieveClassRecord();
        wsRetrieveClassRecord.delegate = this;

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

        ibClassRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wsRetrieveClassRecord.execute();
            }
        });

        ibStartAttendance.setOnClickListener(new View.OnClickListener() {
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

    private void createNewCSV(File file) {
        try {
            file.createNewFile();

            FileWriter fwNew = new FileWriter(file, true);
            BufferedWriter bwNew = new BufferedWriter(fwNew);

            bwNew.write("CLASS NUMBER, DATE, STATUS, STUDENT NUMBER");
            bwNew.flush();
            fwNew.flush();
            bwNew.close();
            fwNew.close();

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }

    @Override
    public void processFinish(Object output) {
        result = (String) output;

        date = (String) dateFormat.format(Calendar.getInstance().getTime());

        File filepath = new File(Environment.getExternalStorageDirectory().toString() + "/TMT_Mobile");
        if (!filepath.exists()) {
            filepath.mkdirs();
        }

        File file = new File(filepath, course + "-" + section + " " + date + ".csv");
        if (file.exists()) {
            file.delete();
            createNewCSV(file);
        } else {
            createNewCSV(file);
        }

        rows = result.split(";");

        try {
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);

            for (String column : rows) {
                System.out.println(column);
                bw.newLine();
                bw.write(column);
            }


            bw.flush();
            fw.flush();
            bw.close();
            fw.close();

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        Toast toast = Toast.makeText(getApplicationContext(), "Class List Recorded Successfully", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.show();

    }
}
