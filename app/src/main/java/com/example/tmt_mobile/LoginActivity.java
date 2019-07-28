/*
DESCRIPTION OF LoginActivity.java
        Dito mag-iinput yung user credentials niya. Tatawag siya ng webservice na magrereturn ng
        Boolean. Kung true yung kinalabasan, ibubundle lang yung e-mail niya. Otherwise, mag-eerror
        lang.
*/

package com.example.tmt_mobile;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity implements AsyncResponse{

    private WSLogin wsLogin;
    private EditText etEmail, etPassword;
    private String email, pass;
    private Button bLogin;
    private Intent intent;
    private Bundle bundle;
    private Boolean result;
    private AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // XML UI Assignment START
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        bLogin = (Button) findViewById(R.id.bLogin);
        // XML UI Assignment END

        wsLogin = new WSLogin();
        wsLogin.delegate = this;

        init();

    }

    private void init() {

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = etEmail.getText().toString();
                pass = etPassword.getText().toString();
                wsLogin.execute(email, pass);
            }
        });

    }


    @Override
    public void processFinish(Object output) {
        result = (Boolean) output;

        if (!result) {
            builder = new AlertDialog.Builder(LoginActivity.this, R.style.CustomAlertDialog);
            builder.setMessage("Log-in Failed. Please try again.");
            builder.setCancelable(true);
            builder.show();
        } else {
            intent = new Intent(LoginActivity.this, MDActivity.class);
            bundle = new Bundle();

            bundle.putString("email", email);
            intent.putExtras(bundle);

            startActivity(intent);
        }
    }
}
