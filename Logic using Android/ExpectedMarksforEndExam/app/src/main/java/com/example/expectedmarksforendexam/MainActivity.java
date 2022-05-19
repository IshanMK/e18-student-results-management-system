package com.example.expectedmarksforendexam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //Private variables
    private double marksLabs = 0.0;
    private double marksQuizzesAndAssignments = 0.0;
    private double marksMidExam = 0.0;
    private double marksProjects = 0.0;
    private double marksFinalExam = 0.0;
    private EditText editText;

    private String marksLabsString = "";
    private String marksQuizzesAndAssignmentsString = "";
    private String marksMidExamString = "";
    private String marksProjectsString = "";
    private String marksFinalExamString = "";
    private String expectedGradeString = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Assign to the EditText object
        editText = findViewById(R.id.gradeExpected);

        //((EditText)findViewById(R.id.gradeExpected)).setFilters(new InputFilter[] {new InputFilter.AllCaps()});

        //Force The user to enter only capital Characters
        //setFilters will reset all other attributes which were set via XML
        // (i.e. maxLines, inputType,imeOptions...). To prevent this,
        // add you Filter(s) to the already existing ones.

        InputFilter[] editFilters = editText.getFilters();
        InputFilter[] newFilters = new InputFilter[editFilters.length + 1];
        System.arraycopy(editFilters,0,newFilters,0,editFilters.length);
        newFilters[editFilters.length] = new InputFilter.AllCaps();
        editText.setFilters(newFilters);

    }

    public void calculate(View view){

        //Read the Lab Marks as a string and convert it to double
        marksLabsString = ((EditText)findViewById(R.id.marks_labs)).getText().toString();
        marksLabs = Double.parseDouble(marksLabsString);

        //Read the Quizzes+Assignments Marks as a string and convert it to double
        marksQuizzesAndAssignmentsString = ((EditText)findViewById(R.id.marks_quizzes)).getText().toString();
        marksQuizzesAndAssignments = Double.parseDouble(marksQuizzesAndAssignmentsString);

        //Read the Project Marks as a string and convert it to double
        marksProjectsString = ((EditText)findViewById(R.id.marks_projects)).getText().toString();
        marksProjects = Double.parseDouble(marksProjectsString);

        //Read the Mid Exam Marks as a string and convert it to double
        marksMidExamString = ((EditText)findViewById(R.id.marks_mid_exam)).getText().toString();
        marksMidExam = Double.parseDouble(marksMidExamString);

        //Read the Expected Grade as a string
        expectedGradeString = ((EditText)findViewById(R.id.gradeExpected)).getText().toString();

        //Calculate the marks required for the final Exam
        marksFinalExam = getExpectedGradeMarks(expectedGradeString) - (marksLabs + marksQuizzesAndAssignments + marksProjects + marksMidExam);

        //Log.d("marksMid",getExpectedGradeMarks(expectedGradeString) + "");

        //Required marks should be a positive value
        if(marksFinalExam >= 0){
            marksFinalExamString = marksFinalExam + "";
            ((TextView)findViewById(R.id.marks_end_exam)).setText(marksFinalExamString);
        }
        else{
            double totalMarks = getExpectedGradeMarks(expectedGradeString) + Math.abs(marksFinalExam);
            String totalMarksString = "You have " + totalMarks + " You will get a better grade than expected!!";
            ((TextView)findViewById(R.id.marks_end_exam)).setText(totalMarksString);
        }
    }

    //method to find the Marks for the expected grade
    //Here if there is a unknown grade there , it is assumed that user asks for a C grade
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
            default:
                marksForTheExpectedGrade = 50.0;
                break;
        }

        return marksForTheExpectedGrade;
    }
}