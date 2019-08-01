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

    private String URL = "INSERT URL";
    private String NAMESPACE = "INSERT NAMESPACE";

    private String SA_METHODNAME = "INSERT METHODNAME";
    private String SA_SOAPACTION = NAMESPACE + SA_METHODNAME;
    private String SA_PARAM_1 = "hexid";
    private String SA_PARAM_2 = "course";
    private String SA_PARAM_3 = "section";
    // di ako sure anong parameter/s yung pinapass sa RFID

    protected String tapResult;

    public WSSetAttendance() {
        this.tapResult = "";
    }

    @Override
    protected String doInBackground(String... param) {

        SoapObject soapObject = new SoapObject(NAMESPACE, SA_METHODNAME);

        PropertyInfo propertyInfo_1 = new PropertyInfo();
        propertyInfo_1.setName(SA_PARAM_1);
        propertyInfo_1.setValue(param[0]);
        propertyInfo_1.setType(String.class);

        soapObject.addProperty(propertyInfo_1);

        PropertyInfo propertyInfo_2 = new PropertyInfo();
        propertyInfo_2.setName(SA_PARAM_2);
        propertyInfo_2.setValue(param[1]);
        propertyInfo_2.setType(String.class);

        soapObject.addProperty(propertyInfo_2);

        PropertyInfo propertyInfo_3 = new PropertyInfo();
        propertyInfo_3.setName(SA_PARAM_3);
        propertyInfo_3.setValue(param[2]);
        propertyInfo_3.setType(String.class);

        soapObject.addProperty(propertyInfo_3);

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
