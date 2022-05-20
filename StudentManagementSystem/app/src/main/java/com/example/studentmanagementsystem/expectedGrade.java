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
expectedGrade.java
    This class gives the expected GPA should achieve in upcoming semesters that added by user
 */

package com.example.studentmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class expectedGrade extends AppCompatActivity {

    // Getting logged gpa
    private String currentGPAString = Background.getUsersGPA();



    private int noOfUpcomingSemesters = 0;
    private double expectedGPA = 0.0;
    private double averageGPAForUpcomingSemesters = 0.0;
    private double currentGPA = 0.0;
    private String noOfUpcomingSemestersString ;
    private String expectedGPAString ;
    private String averageGPAForUpcomingSemestersString ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expected_grade);
    }

    // Onclick method to calculate Expected GPA
    public void calculateExpectedGPA(View view){

        //Read the no of upcoming semesters as a String and typecast it to int
        noOfUpcomingSemestersString = ((EditText)findViewById(R.id.semesters)).getText().toString();

        //Read the expected Grade as String and typecast it to double
        expectedGPAString = ((EditText)findViewById(R.id.expextedGrade)).getText().toString();

        // If text field is empty, inform the user
        if(TextUtils.isEmpty(noOfUpcomingSemestersString) || TextUtils.isEmpty(expectedGPAString)){

            //Display Required GPA for Upcoming Semesters
            ((TextView)findViewById(R.id.averageGPAOutput1)).setText("Inputs Cannot be empty!");
            Toast.makeText(this,"Inputs Cannot be empty!!",Toast.LENGTH_LONG).show();
            return;
        }

        //Convert the Strings to double
        noOfUpcomingSemesters = Integer.parseInt(noOfUpcomingSemestersString);
        expectedGPA = Double.parseDouble(expectedGPAString);
        currentGPA = Double.parseDouble(currentGPAString);

        //Calculate the required average GPA for upcoming Semesters
        averageGPAForUpcomingSemesters = (expectedGPA * (noOfUpcomingSemesters + 1) - currentGPA) / noOfUpcomingSemesters;


        //If the Average Required is a positive number then you can achieve it otherwise not
        if(0 < averageGPAForUpcomingSemesters && averageGPAForUpcomingSemesters  <= 4){
            averageGPAForUpcomingSemestersString = "Average GPA Required : " + averageGPAForUpcomingSemesters;
            // String averageGPAForUpcomingSemestersStringrounded = averageGPAForUpcomingSemestersString.substring(0,28);

            //Display Required GPA for Upcoming Semesters
            ((TextView) findViewById(R.id.averageGPAOutput1)).setText(averageGPAForUpcomingSemestersString);
        }
        else{

            //Display Required GPA for Upcoming Semesters
            ((TextView) findViewById(R.id.averageGPAOutput1)).setText("You Cannot Achieve that GPA!");
            Toast.makeText(this,"You Cannot Achieve that GPA!!",Toast.LENGTH_LONG).show();
        }

    }
}