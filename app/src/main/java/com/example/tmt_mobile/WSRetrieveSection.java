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

public class WSRetrieveSection extends AsyncTask<String, Void, String> {

    public AsyncResponse delegate = null;

    private String URL = "INSERT URL";
    private String NAMESPACE = "INSERT NAMESPACE";

    private String RS_METHODNAME = "INSERT METHODNAME";
    private String RS_SOAPACTION = NAMESPACE + RS_METHODNAME;
    private String RS_PARAM_1 = "email";
    private String RS_PARAM_2 = "course";

    protected String sectionResult;

    public WSRetrieveSection() {
        sectionResult = "";
    }

    @Override
    protected String doInBackground(String... param) {

        SoapObject soapObject = new SoapObject(NAMESPACE, RS_METHODNAME);

        PropertyInfo propertyInfo = new PropertyInfo();
        propertyInfo.setName(RS_PARAM_1);
        propertyInfo.setValue(param[0]);
        propertyInfo.setType(String.class);

        soapObject.addProperty(propertyInfo);

        PropertyInfo propertyInfo2 = new PropertyInfo();
        propertyInfo.setName(RS_PARAM_2);
        propertyInfo.setValue(param[1]);
        propertyInfo.setType(String.class);

        soapObject.addProperty(propertyInfo2);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(soapObject);

        HttpTransportSE httpTransportSE = new HttpTransportSE(URL);

        try {
            httpTransportSE.call(RS_SOAPACTION, envelope);
            SoapPrimitive soapPrimitive = (SoapPrimitive) envelope.getResponse();
            sectionResult = soapPrimitive.toString();
        } catch (SoapFault sf) {
            sf.printStackTrace();
            sectionResult = "";
        } catch (XmlPullParserException xppe) {
            xppe.printStackTrace();
            sectionResult = "";
        } catch (IOException ioe) {
            ioe.printStackTrace();
            sectionResult = "";
        } catch (Exception e) {
            e.printStackTrace();
            sectionResult = "";
        }

        return sectionResult;
    }

    @Override
    protected void onPostExecute(String sectionResult) {
        delegate.processFinish(sectionResult);
    }
}

/*
 * NOTE: Hold Middle-Click (Mouse3) to highlight the code without including the asterisks
 *
 * public String mobile_section(email, course) {
 *      String result = "";
 *
 *      // yung format ng nirereturn na result parang ganito sana:
 *      //
 *      // result = "AC1,AC2,BM6";
 *
 *      return result;
 * }
 *
 */