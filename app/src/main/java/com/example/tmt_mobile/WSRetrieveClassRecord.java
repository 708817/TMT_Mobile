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

    private String URL = "INSERT URL";
    private String NAMESPACE = "INSERT NAMESPACE";

    private String RCR_METHODNAME = "INSERT METHODNAME";
    private String RCR_SOAPACTION = NAMESPACE + RCR_METHODNAME;
    private String RCR_PARAM_1 = "course";
    private String RCR_PARAM_2 = "section";

    private String classResult;

    @Override
    protected String doInBackground(String... param) {

        SoapObject soapObject = new SoapObject(NAMESPACE, RCR_METHODNAME);

        PropertyInfo propertyInfo_1 = new PropertyInfo();
        propertyInfo_1.setName(RCR_PARAM_1);
        propertyInfo_1.setValue(param[0]);
        propertyInfo_1.setType(String.class);

        soapObject.addProperty(propertyInfo_1);

        PropertyInfo propertyInfo_2 = new PropertyInfo();
        propertyInfo_2.setName(RCR_PARAM_2);
        propertyInfo_2.setValue(param[1]);
        propertyInfo_2.setType(String.class);

        soapObject.addProperty(propertyInfo_2);

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
