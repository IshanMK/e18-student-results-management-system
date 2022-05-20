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
CoureReg.java
    This class getting the inputs from admins to register a course in the database. This activity can be opened by only admins.
 */


package com.example.studentmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class CourseReg extends AppCompatActivity {
    // Object variables for input fields
    EditText nameOBJ, codeOBJ, creditOBJ, projectOBJ, assignOBJ, midOBJ, endOBJ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_reg);

        // Defining input objects
        nameOBJ = (EditText) findViewById(R.id.courseName);
        codeOBJ = (EditText) findViewById(R.id.courseCode);
        creditOBJ = (EditText) findViewById(R.id.courseCredit);
        projectOBJ = (EditText) findViewById(R.id.projectMarks);
        assignOBJ = (EditText) findViewById(R.id.assignMarks);
        midOBJ = (EditText) findViewById(R.id.midMarks);
        endOBJ = (EditText) findViewById(R.id.endMarks);
    }

    public void courseReg(View view){
        // Getting texts from input fields
        String nameTXT = nameOBJ.getText().toString();
        String codeTXT = codeOBJ.getText().toString();
        String creditTXT = creditOBJ.getText().toString();
        String projectTXT = projectOBJ.getText().toString();
        String assignTXT = assignOBJ.getText().toString();
        String midTXT = midOBJ.getText().toString();
        String endTXT = endOBJ.getText().toString();

        // Set type to register
        String type = "CourseRegister";

        // Create an instance of Background class to execute and transfer data
        Background background = new Background(this);
        background.execute(type, nameTXT, codeTXT, creditTXT, projectTXT, assignTXT, midTXT, endTXT);

    }
}