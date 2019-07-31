package com.example.tmt_mobile;

import android.content.Context;
import android.os.AsyncTask;

public class WSSetAttendance extends AsyncTask<String, Void, String> {

    public AsyncResponse delegate = null;

    private String URL = "INSERT URL";
    private String NAMESPACE = "INSERT NAMESPACE";

    private String SA_METHODNAME = "INSERT METHODNAME";
    private String SA_SOAPACTION = NAMESPACE + SA_METHODNAME;
    // di ako sure anong parameter/s yung pinapass sa RFID

    protected String tapResult;

    public WSSetAttendance() {
        this.tapResult = "";
    }

    @Override
    protected String doInBackground(String... param) {

        // Insert Attendance Web Service here

        return tapResult;
    }

    @Override
    protected void onPostExecute(String tapResult) {
        delegate.processFinish(tapResult);
    }
}
