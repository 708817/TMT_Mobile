package com.example.tmt_mobile;

import android.content.Context;
import android.os.AsyncTask;

public class WSSetAttendance extends AsyncTask<String, Void, Boolean> {

    private String URL = "INSERT URL";
    private String NAMESPACE = "INSERT NAMESPACE";

    private String SA_METHODNAME = "INSERT METHODNAME";
    private String SA_SOAPACTION = NAMESPACE + SA_METHODNAME;
    // di ako sure anong parameter/s yung pinapass sa RFID

    private Context context;

    protected Boolean tapResult;

    public WSSetAttendance(Context context) {
        this.context = context;
        this.tapResult = false;
    }

    @Override
    protected Boolean doInBackground(String... param) {

        // Insert Attendance Web Service here

        return tapResult;
    }
}
