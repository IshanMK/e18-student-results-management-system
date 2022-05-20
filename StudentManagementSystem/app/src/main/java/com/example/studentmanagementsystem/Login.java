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
Login.java
    This class directs the user to all the options
 */

package com.example.studentmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Login extends AppCompatActivity {
    public int admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Getting admin flag from login page
        Intent i = getIntent();
        admin = i.getIntExtra("adminFlag", 0);

        Button regCourse = findViewById(R.id.CourseRegister);

        // Set register course button invisible if user is not an admin
        if (admin == 0){
            regCourse.setVisibility(View.INVISIBLE);
        }
    }


    // Method for open Marks for "Course Registration"
    public void courseReg(View v){

        Intent register = new Intent(this, CourseReg.class);
        startActivity(register);
    }


    // Method for open window "Enter Marks"
    public void enterMarks(View v){
        String type = "CourseList1";

        // Create an instance of Background class to execute and transfer data
        Background background = new Background(this);
        background.execute(type);
    }

    // Method for open Window "Calculate GPA"
    public void expectedMarks(View v){
        String type = "CourseList2";

        // Create an instance of Background class to execute and transfer data
        Background background = new Background(this);
        background.execute(type);
    }

    //Method for open window "Expected Grade"
    public void expectedGrade(View v){
        String type = "getGPA";
        String regNo = MainActivity.getLoggeduser();

        // Create an instance of Background class to execute and transfer data
        Background background = new Background(this);
        background.execute(type, regNo);
    }

    // Method for open Window "Current GPA"
    public void getCurrentGPA(View v){
        String type = "get gpa";

        // Create an instance of Background class to execute and transfer data
        Background background = new Background(this);
        background.execute(type);
    }


}