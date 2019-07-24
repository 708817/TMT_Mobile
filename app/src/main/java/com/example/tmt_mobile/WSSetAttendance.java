package com.example.tmt_mobile;

import android.content.Context;
import android.os.AsyncTask;

public class WSSetAttendance extends AsyncTask<String, Void, Boolean> {

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
