// NOTE: See bottom comment to see ideal Web Service method parameters
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

public class WSTestServices extends AsyncTask<String, Void, Boolean> {

    public AsyncResponse delegate = null;

    private String URL = "INSERT URL";
    private String NAMESPACE = "INSERT NAMESPACE";

    private String TS_METHODNAME = "INSERT METHODNAME";
    private String TS_SOAPACTION = NAMESPACE + TS_METHODNAME;

    protected Boolean wsTestResult;

    public WSTestServices() {
        this.wsTestResult = false;
    }

    @Override
    protected Boolean doInBackground(String... param) {
        SoapObject soapObject = new SoapObject(NAMESPACE, TS_METHODNAME);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(soapObject);

        HttpTransportSE httpTransportSE = new HttpTransportSE(URL);

        try {
            httpTransportSE.call(TS_SOAPACTION, envelope);
            SoapPrimitive soapPrimitive = (SoapPrimitive) envelope.getResponse();
            wsTestResult = Boolean.valueOf(soapPrimitive.toString());
        } catch (SoapFault sf) {
            sf.printStackTrace();
            wsTestResult = false;
        } catch (XmlPullParserException xppe) {
            xppe.printStackTrace();
            wsTestResult = false;
        } catch (IOException ioe) {
            ioe.printStackTrace();
            wsTestResult = false;
        } catch (Exception e) {
            e.printStackTrace();
            wsTestResult = false;
        }

        return wsTestResult;
    }

    @Override
    protected void onPostExecute(Boolean wsTestResult) {
        delegate.processFinish(wsTestResult);
    }
}

/*
 * NOTE: Hold Middle-Click (Mouse3) to highlight the code without including the asterisks
 *
 * public Boolean mobile_services() {
 *      String result = false;
 *
 *      // Insert code connecting to database. Exception error should either set the
 *      // result variable as false or leave it as is.
 *
 *      return result;
 * }
 *
 */