package com.example.gpaforaspecificsubject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //private variables
    private double marksForLabs = 0.0;
    private double marksForAssignments = 0.0;
    private double marksForProjects = 0.0;
    private double marksForMidExam = 0.0;
    private double marksForEndExam = 0.0;
    private double courseCredits = 0.0;
    private double totalMarks = 0.0;

    private String marksForLabsString = "";
    private String marksForAssignmentsString = "";
    private String marksForProjectsString = "";
    private String marksForMidExamString = "";
    private String marksForEndExamString = "";
    private String courseCreditsString = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void calculateGPAForTheSubject(View view){

        //Read all the data
        marksForLabsString = ((EditText)findViewById(R.id.inputLabs)).getText().toString();         //LabMarks as String
        marksForAssignmentsString = ((EditText)findViewById(R.id.inputAssignments)).getText().toString();
        marksForProjectsString = ((EditText)findViewById(R.id.inputProjects)).getText().toString();
        marksForMidExamString = ((EditText)findViewById(R.id.inputMidExam)).getText().toString();
        marksForEndExamString = ((EditText)findViewById(R.id.inputEndExam)).getText().toString();
        courseCreditsString = ((EditText)findViewById(R.id.inputCredits)).getText().toString();

        //Check whether the String is empty or null
        boolean isLabMarksEmpty = TextUtils.isEmpty(marksForLabsString);
        boolean isAssignmentsEmpty = TextUtils.isEmpty(marksForAssignmentsString);
        boolean isProjectsEmpty = TextUtils.isEmpty(marksForProjectsString);
        boolean isMidExamEmpty = TextUtils.isEmpty(marksForMidExamString);
        boolean isEndExamEmpty = TextUtils.isEmpty(marksForEndExamString);
        boolean isCreditsEmpty = TextUtils.isEmpty(courseCreditsString);

        //If one these fields are empty or null
        if(isLabMarksEmpty || isAssignmentsEmpty || isProjectsEmpty || isMidExamEmpty || isEndExamEmpty || isCreditsEmpty){

            Toast.makeText(this,"Empty Fields!!",Toast.LENGTH_LONG).show();
            return;
        }

        //If all of the field are not empty or null then convert them to double
        marksForLabs = Double.parseDouble(marksForLabsString);
        marksForAssignments = Double.parseDouble(marksForAssignmentsString);
        marksForProjects = Double.parseDouble(marksForProjectsString);
        marksForMidExam = Double.parseDouble(marksForMidExamString);
        marksForEndExam = Double.parseDouble(marksForEndExamString);
        courseCredits = Double.parseDouble(courseCreditsString);

        //Calculate the Total Marks Obtained for the course
        totalMarks = marksForLabs + marksForAssignments + marksForProjects + marksForMidExam + marksForEndExam;

        //Calling the setFinalGPA function
        setFinalGPA(totalMarks,courseCredits);

    }

    public void setFinalGPA(double totalMarks , double courseCredits){

        double GPA = 0.0;
        double GPAMultipliedByCredits = 0.0;
        String GPAMultipliedByCreditsString = "";

        if(totalMarks >= 85){
            GPA = 4.0;
        }
        else if(totalMarks >= 80){
            GPA = 4.0;
        }
        else if(totalMarks >= 75){
            GPA = 3.7;
        }
        else if(totalMarks >= 70){
            GPA = 3.3;
        }
        else if(totalMarks >= 65){
            GPA = 3.0;
        }
        else if(totalMarks >= 60){
            GPA = 2.7;
        }
        else if(totalMarks >= 55){
            GPA = 2.3;
        }
        else if(totalMarks >= 50){
            GPA = 2.0;
        }
        else if(totalMarks >= 45){
            GPA = 1.7;
        }
        else if(totalMarks >= 40){
            GPA = 1.3;
        }
        else if(totalMarks >= 35){
            GPA = 1.0;
        }
        else{
            GPA = 0.0;
        }

        GPAMultipliedByCredits =  GPA * courseCredits;
        GPAMultipliedByCreditsString = "GPA X Course Credits = " + GPAMultipliedByCredits ;

        ((TextView)findViewById(R.id.outputGPA)).setText(GPAMultipliedByCreditsString);

    }
}