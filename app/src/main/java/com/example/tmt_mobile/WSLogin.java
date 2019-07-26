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

public class WSLogin extends AsyncTask<String, Void, Boolean> {

    private String URL = "INSERT URL";
    private String NAMESPACE = "INSERT NAMESPACE";

    private String L_METHODNAME = "INSERT METHODNAME";
    private String L_SOAPACTION = NAMESPACE + L_METHODNAME;
    private String L_PARAM_1 = "email";
    private String L_PARAM_2 = "pass";

    private Context mContext;

    protected Boolean loginResult;

    public WSLogin(Context context) {
        this.mContext = context;
        this.loginResult = false;
    }

    @Override
    protected Boolean doInBackground(String... param) {

        System.out.println(param.length + " PARAAAAAAAAAAAAAAAAAAAAAAAM LENGTH");

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
            loginResult = Boolean.valueOf(soapPrimitive.toString());
        } catch (SoapFault sf) {
            sf.printStackTrace();
            loginResult = false;
        } catch (XmlPullParserException xppe) {
            xppe.printStackTrace();
            loginResult = false;
        } catch (IOException ioe) {
            ioe.printStackTrace();
            loginResult = false;
        } catch (Exception e) {
            e.printStackTrace();
            loginResult = false;
        }

        return loginResult;
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
