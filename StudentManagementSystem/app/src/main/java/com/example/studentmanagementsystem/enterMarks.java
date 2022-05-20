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
enterMarks.java
    This activity can get user's marks of the course that has added into the database by admin
 */

package com.example.studentmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class enterMarks extends AppCompatActivity {

    // |||||||||||||||||||||||||||||| Subject List ||||||||||||||||||||||||||||||||
    AutoCompleteTextView courseList;
    ArrayAdapter<String> courseAdapter ;

    //private variables
    private double marksForLabs = 0.0;
    private double marksForAssignments = 0.0;
    private double marksForProjects = 0.0;
    private double marksForMidExam = 0.0;
    private double marksForEndExam = 0.0;
    private double courseCredits = 0.0;
    private double totalMarks = 0.0;

    private double creditInt;
    // getting logged user's reg no from main activity
    private String regNo = MainActivity.getLoggeduser();
    private String subjectItem;

    // Define object variables
    EditText courseCodeOBJ, marks4assignOBJ, marks4projectOBJ, marks4midOBJ, marks4endOBJ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_marks);

        // Set calculate button disabled initially
        findViewById(R.id.Submit).setEnabled(false);

        // Defining text field objects
        courseCodeOBJ = (EditText) findViewById(R.id.courseCode);
        marks4assignOBJ = (EditText) findViewById(R.id.assignments);
        marks4projectOBJ = (EditText) findViewById(R.id.projects);
        marks4midOBJ = (EditText) findViewById(R.id.midExam);
        marks4endOBJ = (EditText) findViewById(R.id.endExam);

        // Getting Applied course list from the database
        Intent i = getIntent();
        String courses = i.getStringExtra("courseList");
        String[] courseStringList = courses.substring(1).split(",");



        // Set Adapter to show list view of subjects
        courseList = findViewById(R.id.subjects);

        courseAdapter = new ArrayAdapter<String>(this, R.layout.subject_list, courseStringList);
        courseList.setAdapter(courseAdapter);

        // Set event listener for click on menu drawer
        courseList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                subjectItem = adapterView.getItemAtPosition(position).toString();    // get selected items
            }
        });
    }

    // Method to OK button (this gives number of credits for selected course)
    public void getCredit(View view){
        // Access to background process to connect with server
        Background background1 = new Background(this);
        background1.execute("CourseInfo", subjectItem);

        // Disable OK button
        Button button = (Button) findViewById(R.id.ok);
        button.setEnabled(false);

        // Enable the submit button
        findViewById(R.id.Submit).setEnabled(true);
    }

    // Onclick method to reset button
    public void resetActivity(View view){
        // Set empty the text fields
        marks4endOBJ.setText("");
        marks4projectOBJ.setText("");
        marks4midOBJ.setText("");
        marks4assignOBJ.setText("");

        // Enable the ok button
        Button button = (Button) findViewById(R.id.ok);
        button.setEnabled(true);

        // disable the submit button
        findViewById(R.id.Submit).setEnabled(false);
    }

    // Onclick method to submit button
    public void enteringMarks(View view){

        // Getting texts from text fields
        String marks4assignmentTXT = marks4assignOBJ.getText().toString();
        String marks4projectTXT = marks4projectOBJ.getText().toString();
        String marks4midTXT = marks4midOBJ.getText().toString();
        String marks4endTXT = marks4endOBJ.getText().toString();

        // Get marks for subject to string array
        String info[] = Background.getMarks().split(",");

        // get credit as double
        creditInt = Double.parseDouble(info[0]);

        //Check whether the String is empty or null
        boolean isAssignmentsEmpty = TextUtils.isEmpty(marks4assignmentTXT);
        boolean isProjectsEmpty = TextUtils.isEmpty(marks4projectTXT);
        boolean isMidExamEmpty = TextUtils.isEmpty(marks4midTXT);
        boolean isEndExamEmpty = TextUtils.isEmpty(marks4endTXT);

        //If one these fields are empty or null
        if(isAssignmentsEmpty || isProjectsEmpty || isMidExamEmpty || isEndExamEmpty){

            Toast.makeText(this,"Empty Fields!!",Toast.LENGTH_LONG).show();
            return;
        }

        //If all of the field are not empty or null then convert them to double
        marksForAssignments = Double.parseDouble(marks4assignmentTXT);
        marksForProjects = Double.parseDouble(marks4projectTXT);
        marksForMidExam = Double.parseDouble(marks4midTXT);
        marksForEndExam = Double.parseDouble(marks4endTXT);

        //Calculate the Total Marks Obtained for the course
        totalMarks = marksForAssignments + marksForProjects + marksForMidExam + marksForEndExam;

        //Calling the setFinalGPA function
        String GPA = Double.toString(setFinalGPA(totalMarks, creditInt));

        String type = "Enter Marks";

        // Create an instance of Background class to execute and transfer data
        Background background = new Background(this);
        background.execute(type, regNo, subjectItem, marks4assignmentTXT, marks4projectTXT, marks4midTXT, marks4endTXT, GPA);

    }

    // Method to get GPA for marks
    public double setFinalGPA(double totalMarks , double courseCredits){

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

        return GPAMultipliedByCredits;

    }
}