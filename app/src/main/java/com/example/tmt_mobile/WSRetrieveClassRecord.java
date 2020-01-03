package com.example.tmt_mobile;

import android.os.AsyncTask;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class WSRetrieveClassRecord extends AsyncTask<String, Void, String> {

    public AsyncResponse delegate = null;

    private String URL = "http://192.168.43.161:8080/STT_Java4_Projects/ws?WSDL";
    private String NAMESPACE = "http://stt/";

    private String RCR_METHODNAME = "viewAttendance";
    private String RCR_SOAPACTION = NAMESPACE + RCR_METHODNAME;

    private String classResult;

    public WSRetrieveClassRecord() {
        this.classResult = "";
    }

    @Override
    protected String doInBackground(String... param) {

        SoapObject soapObject = new SoapObject(NAMESPACE, RCR_METHODNAME);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(soapObject);

        HttpTransportSE httpTransportSE = new HttpTransportSE(URL);

        try {
            httpTransportSE.call(RCR_SOAPACTION, envelope);
            SoapPrimitive soapPrimitive = (SoapPrimitive) envelope.getResponse();
            classResult = soapPrimitive.toString();
        } catch (SoapFault sf) {
            sf.printStackTrace();
            classResult = "";
        } catch (XmlPullParserException xppe) {
            xppe.printStackTrace();
            classResult = "";
        } catch (IOException ioe) {
            ioe.printStackTrace();
            classResult = "";
        } catch (Exception e) {
            e.printStackTrace();
            classResult = "";
        }

        return classResult;
    }

    @Override
    protected void onPostExecute(String classResult) {
        delegate.processFinish(classResult);
    }

}
