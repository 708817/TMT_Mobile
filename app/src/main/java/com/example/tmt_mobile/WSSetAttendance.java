package com.example.tmt_mobile;

import android.content.Context;
import android.os.AsyncTask;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

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

        SoapObject soapObject = new SoapObject(NAMESPACE, SA_METHODNAME);

        // Insert PropertyInfos

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(soapObject);

        HttpTransportSE httpTransportSE = new HttpTransportSE(URL);

        try {
            httpTransportSE.call(SA_SOAPACTION, envelope);
            SoapPrimitive soapPrimitive = (SoapPrimitive) envelope.getResponse();
            tapResult = soapObject.toString();
        } catch (SoapFault sf) {
            sf.printStackTrace();
        } catch (XmlPullParserException xppe) {
            xppe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return tapResult;
    }

    @Override
    protected void onPostExecute(String tapResult) {
        delegate.processFinish(tapResult);
    }
}
