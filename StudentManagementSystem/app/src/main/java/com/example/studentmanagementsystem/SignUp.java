/*
 * CO225 - Group Project
 * Group 07
 *       E/18/028 - Ariyawansha P.H.J.U.
 *       E/18/173 - Kasthuripitiya K.A.I.M.
 *       E/18/285 - Ranasinghe S.M.T.S.C.
 *
 * Student Results management system
 *    This android app manages the students results. this is a app with online data base of students results
 *
 */

/*
SignUp.java
    This class provides the sign up page to server for user
 */

package com.example.studentmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {

    // Object variables for input fields
    EditText passwordOBJ, firstnameOBJ, lastnameOBJ, regnoOBJ, emailOBJ, fieldOBJ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up2);

        // Defining input objects
        passwordOBJ = (EditText) findViewById(R.id.RegPassword);
        firstnameOBJ = (EditText) findViewById(R.id.RegFirstname);
        lastnameOBJ = (EditText) findViewById(R.id.RegLastname);
        regnoOBJ = (EditText) findViewById(R.id.RegRegNo);
        emailOBJ = (EditText) findViewById(R.id.RegEmail);
        fieldOBJ = (EditText) findViewById(R.id.RegField);
    }

    public void checkMobileDataOrWiFi(View view){

        if(isNetworkConnected(this)){
            //Toast.makeText(this,"Connection Available",Toast.LENGTH_LONG).show();
            regActivity(view);
        }
        else{
            //showDialog();
            Toast.makeText(this,"Please Connect to the Internet",Toast.LENGTH_LONG).show();
        }
    }


    //Check internet connection
    private boolean isNetworkConnected(SignUp signUp) {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    public void regActivity(View view){
        String passwordTXT = passwordOBJ.getText().toString();
        String firstnameTXT = firstnameOBJ.getText().toString();
        String lastnameTXT = lastnameOBJ.getText().toString();
        String regnoTXT = regnoOBJ.getText().toString();
        String emailTXT = emailOBJ.getText().toString();
        String fieldTXT = fieldOBJ.getText().toString();

        String type = "Register";

        // Create an instance of Background class to execute and transfer data
        Background background = new Background(this);
        background.execute(type, passwordTXT, firstnameTXT, lastnameTXT, regnoTXT, emailTXT, fieldTXT);
    }
}