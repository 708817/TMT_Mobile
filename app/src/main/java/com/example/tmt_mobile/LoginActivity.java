/*
DESCRIPTION OF LoginActivity.java
        Dito mag-iinput yung user credentials niya. Tatawag siya ng webservice na magrereturn ng
        Boolean. Kung true yung kinalabasan, ibubundle lang yung e-mail niya tapos pupunta sa
        list of activity_courses (CourseActivity.java). Otherwise, mag-eerror lang.
*/

package com.example.tmt_mobile;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity implements AsyncResponse {

    private WSLogin wsLogin;

    private EditText etEmail, etPassword;
    private CheckBox cbRemember;
    private Button bLogin;

    private Intent intent;
    private Bundle bundle;

    private String email, pass;
    private Boolean result, saveLogin;

    private AlertDialog.Builder builder;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor sharedPreferencesEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // XML UI Assignment START
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        cbRemember = (CheckBox) findViewById(R.id.cbRemember);
        bLogin = (Button) findViewById(R.id.bLogin);
        // XML UI Assignment END

        // Assigning and Declaring Variables START
        etEmail.setHint("E-mail");
        etPassword.setHint("Password");
        cbRemember.setText("Remember Me");
        bLogin.setText("Login");
        wsLogin = new WSLogin();
        wsLogin.delegate = this;
        // Assigning and Declaring Variables END

        // Remember Me START
        sharedPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        sharedPreferencesEditor = sharedPreferences.edit();
        saveLogin = sharedPreferences.getBoolean("saveLogin", false);

        if (saveLogin == true) {
            etEmail.setText(sharedPreferences.getString("email", ""));
            etPassword.setText(sharedPreferences.getString("pass", ""));
            cbRemember.setChecked(true);
        }
        // Remember Me END

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

            if (cbRemember.isChecked()) {
                sharedPreferencesEditor.putBoolean("saveLogin", true);
                sharedPreferencesEditor.putString("email", email);
                sharedPreferencesEditor.putString("pass", pass);
                sharedPreferencesEditor.commit();
            } else {
                sharedPreferencesEditor.clear();
                sharedPreferencesEditor.commit();
            }

            intent = new Intent(LoginActivity.this, CoursesActivity.class);
            bundle = new Bundle();

            bundle.putString("email", email);
            intent.putExtras(bundle);

            startActivity(intent);
        }
    }
}
