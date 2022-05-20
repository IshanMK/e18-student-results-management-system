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
MainActivity.java
    This class provides the login activity for user. this is the launching activity. From this user can be directed to About activity and Sign uo activity.
 */

package com.example.studentmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // variables for user inputs
    EditText UsernameText, PasswordText;

    public static String loggeduser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Assigning texts from user
        UsernameText = (EditText) findViewById(R.id.RegRegNo);
        PasswordText = (EditText) findViewById(R.id.RegPassword);
    }

    public void checkMobileDataOrWiFi(View view){

        if(isNetworkConnected(this)){
            //Toast.makeText(this,"Connection Available",Toast.LENGTH_LONG).show();
            login(view);
        }
        else{
            //showDialog();
            Toast.makeText(this,"Please Connect to the Internet",Toast.LENGTH_LONG).show();
        }
    }


    //Check internet connection
    private boolean isNetworkConnected(MainActivity mainActivity) {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    //Method for Login
    public void login(View v){

        // Getting text from Edit text field
        loggeduser = UsernameText.getText().toString();
        String password = PasswordText.getText().toString();
        String type = "login";

        // Create an instance of Background class to execute and transfer data
        Background background = new Background(this);
        background.execute(type, loggeduser, password);
    }

    //Method when Admin is not registered
    public void signUp( View v )
    {
        Intent signup = new Intent(this, SignUp.class);
        startActivity(signup);              // opens Signup window
    }

    public static String getLoggeduser() {
        return loggeduser;
    }

    // Method for loading about page
    public void loadAbout(View v){
        Intent aboutActivity = new Intent(this, about.class);
        startActivity(aboutActivity);
    }
}