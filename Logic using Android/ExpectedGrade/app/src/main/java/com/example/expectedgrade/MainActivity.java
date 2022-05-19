package com.example.expectedgrade;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //private variables
    private int noOfUpcomingSemesters = 0;
    private double expectedGPA = 0.0;
    private double currentGPA = 0.0;
    private double averageGPAForUpcomingSemesters = 0.0;

    private String noOfUpcomingSemestersString ;
    private String expectedGPAString ;
    private String currentGPAString ;
    private String averageGPAForUpcomingSemestersString ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void calculateExpectedGPA(View view){

        //Read the no of upcoming semesters as a String and typecast it to int
        noOfUpcomingSemestersString = ((EditText)findViewById(R.id.inputNoOfSemesters)).getText().toString();

        //Read the expected Grade as String and typecast it to double
        expectedGPAString = ((EditText)findViewById(R.id.inputExpectedGrade)).getText().toString();

        //Read the current GPA as a string and typecast it to double
        currentGPAString = ((EditText)findViewById(R.id.inputCurrentGPA)).getText().toString();

        if(TextUtils.isEmpty(noOfUpcomingSemestersString) || TextUtils.isEmpty(expectedGPAString) || TextUtils.isEmpty(currentGPAString)){

            //Display Required GPA for Upcoming Semesters
            ((TextView)findViewById(R.id.averageGPAOutput)).setText("Inputs Cannot be empty!");
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

            //Display Required GPA for Upcoming Semesters
            ((TextView) findViewById(R.id.averageGPAOutput)).setText(averageGPAForUpcomingSemestersString);
        }
        else{

            //Display Required GPA for Upcoming Semesters
            ((TextView) findViewById(R.id.averageGPAOutput)).setText("You Cannot Achieve that GPA!");
            Toast.makeText(this,"You Cannot Achieve that GPA!!",Toast.LENGTH_LONG).show();
        }

    }

}