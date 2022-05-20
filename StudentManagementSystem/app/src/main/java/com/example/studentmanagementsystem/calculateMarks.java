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
calculateMarks.java
    This gives the end exam mark to get added expected GPA
 */

package com.example.studentmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class calculateMarks extends AppCompatActivity {

    //Private variables
    private double marksLabs = 0.0;
    private double marksQuizzesAndAssignments = 0.0;
    private double marksMidExam = 0.0;
    private double marksProjects = 0.0;
    private double marksFinalExam = 0.0;
    private String marksLabsString = "";
    private String marksQuizzesAndAssignmentsString = "";
    private String marksMidExamString = "";
    private String marksProjectsString = "";
    private String marksFinalExamString = "";
    private String expectedGradeString = "";
    private String subjectItem = "";
    private String gradeItem = "";

    // |||||||||||||||||||||||||||||| Subject List ||||||||||||||||||||||||||||||||

    AutoCompleteTextView subjectList;
    ArrayAdapter<String> subjectAdapter ;

    // |||||||||||||||||||||||||||||| Grade List |||||||||||||||||||||||||||||||||

    AutoCompleteTextView gradeList;
    ArrayAdapter<String> gradeAdapter;

    String[] grades = {"A+", "A", "A-", "B+", "B", "B-", "C+", "C", "C-"};         // Grade list for courses


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_marks);

        // Set calculate button disabled initially
        findViewById(R.id.calculateBTN).setEnabled(false);

        // Getting Applied course list from the database
        Intent i = getIntent();
        String courses = i.getStringExtra("courseList");
        String[] courseStringList = courses.substring(1).split(",");

        // Set Adapter to show list view of subjects
        subjectList = findViewById(R.id.subjects);
        subjectAdapter = new ArrayAdapter<String>(this, R.layout.subject_list, courseStringList);
        subjectList.setAdapter(subjectAdapter);

        // Set event listener for click on menu drawer
        subjectList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                subjectItem = adapterView.getItemAtPosition(position).toString();    // get selected item
                Toast.makeText(getApplicationContext(), subjectItem+" Selected", Toast.LENGTH_SHORT).show();
            }
        });

        // Set adapter to show list view of grades
        gradeList = findViewById(R.id.grades);
        gradeAdapter = new ArrayAdapter<String>(this, R.layout.grade_list, grades);
        gradeList.setAdapter(gradeAdapter);

        // Set event  listener for click on menu drawer
        gradeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                gradeItem = adapterView.getItemAtPosition(position).toString();      // Get selected item
                Toast.makeText(getApplicationContext(), gradeItem +" selected as required grade", Toast.LENGTH_SHORT).show();
            }
        });


    }

    // Onclick method to OK button in Activity (When OK button is clicked send selected courseCode and get marks for specific course)
    public void getMark(View view){
        // Make background object to connect with server in background
        Background background1 = new Background(this);
        background1.execute("userMarks", subjectItem);

        // OK button disabled after clicking
        Button button = (Button) findViewById(R.id.ok);
        button.setEnabled(false);

        // Enable the calculate button
        findViewById(R.id.calculateBTN).setEnabled(true);
    }

    // onclick method to reset button
    public void resetActivity(View view){
        // Enable the OK button and disable calculate button
        Button button = (Button) findViewById(R.id.ok);
        button.setEnabled(true);
        findViewById(R.id.calculateBTN).setEnabled(false);
    }

    // Onclick method to calculate button
    public void calculate(View view){
        String marks[] = Background.getUserMarks().split(",");


        //Read the Quizzes+Assignments Marks as a string and convert it to double
        marksQuizzesAndAssignmentsString = marks[1];
        marksQuizzesAndAssignments = Double.parseDouble(marksQuizzesAndAssignmentsString);

        //Read the Project Marks as a string and convert it to double
        marksProjectsString = marks[0];
        marksProjects = Double.parseDouble(marksProjectsString);

        //Read the Mid Exam Marks as a string and convert it to double
        marksMidExamString = marks[2];
        marksMidExam = Double.parseDouble(marksMidExamString);

        //Read the Expected Grade as a string
        expectedGradeString = gradeItem;

        marksFinalExam = getExpectedGradeMarks(expectedGradeString) - (marksQuizzesAndAssignments + marksProjects + marksMidExam);

        // Check the mark is lower than assigned marks
        if((marksFinalExam != 0) && (marksFinalExam <= Double.parseDouble(marks[3]))){
            marksFinalExamString = marksFinalExam + "";
            ((TextView)findViewById(R.id.marks)).setText(marksFinalExamString);
        }
        else if (marksFinalExam >= Double.parseDouble(marks[3])){
            String totalMarksString = "You can't achieve this grade";
            ((TextView)findViewById(R.id.marks)).setText(totalMarksString);
        }
        else{
            double totalMarks = getExpectedGradeMarks(expectedGradeString) + Math.abs(marksFinalExam);
            String totalMarksString = "You have " + totalMarks + " You will get a better grade than expected!!";
            ((TextView)findViewById(R.id.marks)).setText(totalMarksString);
        }
    }

    // Method to recognize the gpa for grade
    public double getExpectedGradeMarks(String expectedGrade){

        double marksForTheExpectedGrade = 0.0;
        switch (expectedGrade){
            case "A+":
                marksForTheExpectedGrade = 85;
                break;
            case "A":
                marksForTheExpectedGrade = 80;
                break;
            case "A-":
                marksForTheExpectedGrade = 75;
                break;
            case "B+":
                marksForTheExpectedGrade = 70;
                break;
            case "B":
                marksForTheExpectedGrade = 65;
                break;
            case "B-":
                marksForTheExpectedGrade = 60;
                break;
            case "C+":
                marksForTheExpectedGrade = 55;
                break;
            case "C":
                marksForTheExpectedGrade = 50;
                break;
            case "C-":
                marksForTheExpectedGrade = 45;
                break;
            case "D+":
                marksForTheExpectedGrade = 40;
                break;
            case "D":
                marksForTheExpectedGrade = 35;
                break;
        }

        return marksForTheExpectedGrade;
    }
}
