// NOTE: See bottom comment to see ideal Web Service method parameters
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

public class WSRetrieveInfo extends AsyncTask<String, Void, String> {

    public AsyncResponse delegate = null;

    private String URL = "INSERT URL";
    private String NAMESPACE = "INSERT NAMESPACE";

    private String RI_METHODNAME = "INSERT METHODNAME";
    private String RI_SOAPACTION = NAMESPACE + RI_METHODNAME;
    private String RI_PARAM = "email";

    protected String retrieveResult;

    public WSRetrieveInfo() {
        this.retrieveResult = "";
    }

    @Override
    protected String doInBackground(String... param) {

        SoapObject soapObject = new SoapObject(NAMESPACE, RI_METHODNAME);

        PropertyInfo propertyInfo = new PropertyInfo();
        propertyInfo.setName(RI_PARAM);
        propertyInfo.setValue(param[0]);
        propertyInfo.setType(String.class);

        soapObject.addProperty(propertyInfo);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(soapObject);

        HttpTransportSE httpTransportSE = new HttpTransportSE(URL);

        try {
            httpTransportSE.call(RI_SOAPACTION, envelope);
            SoapPrimitive soapPrimitive = (SoapPrimitive) envelope.getResponse();
            retrieveResult = soapPrimitive.toString();
        } catch (SoapFault sf) {
            sf.printStackTrace();
            retrieveResult = "";
        } catch (XmlPullParserException xpe) {
            xpe.printStackTrace();
            retrieveResult = "";
        } catch (IOException ioe) {
            ioe.printStackTrace();
            retrieveResult = "";
        } catch (Exception e) {
            e.printStackTrace();
            retrieveResult = "";
        }

        return retrieveResult;
    }

    @Override
    protected void onPostExecute(String retrieveResult) {
        delegate.processFinish(retrieveResult);
    }
}

/*
 * NOTE: Hold Middle-Click (Mouse3) to highlight the code without including the asterisks
 *
 * public String mobile_info(email) {
 *      String result = "";
 *
 *      // yung format ng nirereturn na result parang ganito sana:
 *      //
 *      // result = "CS129,CS184-1P,CS153L";
 *
 *      return result;
 * }
 *
 */