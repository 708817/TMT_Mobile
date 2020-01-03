package com.example.tmt_mobile;

import android.content.Context;
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

public class WSSetAttendance extends AsyncTask<String, Void, String> {

    public AsyncResponse delegate = null;

    private String URL = "http://192.168.43.161:8080/STT_Java4_Projects/ws?WSDL";
    private String NAMESPACE = "http://stt/";

    private String SA_METHODNAME = "getDetails";
    private String SA_SOAPACTION = NAMESPACE + SA_METHODNAME;
    private String SA_PARAM_1 = "hexno";
    // di ako sure anong parameter/s yung pinapass sa RFID

    protected String tapResult;

    public WSSetAttendance() {
        this.tapResult = "";
    }

    @Override
    protected String doInBackground(String... param) {

        SoapObject soapObject = new SoapObject(NAMESPACE, SA_METHODNAME);

        PropertyInfo propertyInfo1 = new PropertyInfo();
        propertyInfo1.setName(SA_PARAM_1);
        propertyInfo1.setValue(param[0]);
        propertyInfo1.setType(String.class);

        soapObject.addProperty(propertyInfo1);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(soapObject);

        HttpTransportSE httpTransportSE = new HttpTransportSE(URL);

        try {
            httpTransportSE.call(SA_SOAPACTION, envelope);
            SoapPrimitive soapPrimitive = (SoapPrimitive) envelope.getResponse();
            tapResult = soapPrimitive.toString();
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
