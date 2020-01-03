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

public class WSLogin extends AsyncTask<String, Void, String> {

    public AsyncResponse delegate = null;

    private String URL = "http://192.168.43.161:8080/STT_Java4_Projects/ws?WSDL";
    private String NAMESPACE = "http://stt/";

    private String L_METHODNAME = "professorLogin";
    private String L_SOAPACTION = NAMESPACE + L_METHODNAME;
    private String L_PARAM_1 = "email";
    private String L_PARAM_2 = "password";

    protected String loginResult;

    public WSLogin() {
        this.loginResult = "";
    }

    @Override
    protected String doInBackground(String... param) {

        SoapObject soapObject = new SoapObject(NAMESPACE, L_METHODNAME);

        PropertyInfo propertyInfo1 = new PropertyInfo();
        propertyInfo1.setName(L_PARAM_1);
        propertyInfo1.setValue(param[0]);
        propertyInfo1.setType(String.class);

        soapObject.addProperty(propertyInfo1);

        PropertyInfo propertyInfo2 = new PropertyInfo();
        propertyInfo2.setName(L_PARAM_2);
        propertyInfo2.setValue(param[1]);
        propertyInfo2.setType(String.class);

        soapObject.addProperty(propertyInfo2);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(soapObject);

        HttpTransportSE httpTransportSE = new HttpTransportSE(URL);

        try {
            httpTransportSE.call(L_SOAPACTION, envelope);
            SoapPrimitive soapPrimitive = (SoapPrimitive) envelope.getResponse();
            loginResult = soapPrimitive.toString();
        } catch (SoapFault sf) {
            sf.printStackTrace();
            loginResult = "";
        } catch (XmlPullParserException xppe) {
            xppe.printStackTrace();
            loginResult = "";
        } catch (IOException ioe) {
            ioe.printStackTrace();
            loginResult = "";
        } catch (Exception e) {
            e.printStackTrace();
            loginResult = "";
        }

        return loginResult;
    }

    @Override
    protected void onPostExecute(String loginResult) {
        delegate.processFinish(loginResult);
    }

}

/*
 * NOTE: Hold Middle-Click (Mouse3) to highlight the code without including the asterisks
 *
 * public Boolean mobile_login(email, pass) {
 *      Boolean result = false;
 *
 *      // Insert code that checks the database of the user with the same email and pass.
 *
 *      return result;
 * }
 *
*/
