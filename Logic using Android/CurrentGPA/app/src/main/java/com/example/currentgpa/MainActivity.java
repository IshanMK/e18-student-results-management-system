package com.example.currentgpa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Variables
    private String noOfSubjectsString = "";
    private int noOfSubjects = 0;
    private static int subjectsCounter = 0 ;
    private int creditsForTheSubject = 0;
    private int totalCredits = 0;
    private double plusIncrement = 0.3;
    private double minusIncrement = 0.3;
    private double totalGPA = 0.0;
    private double currentGPA = 0.0 ;
    private String currentGPAString = "";
    private String creditsForTheSubjectsString = "";
    private EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Assign to the EditText object
        editText = findViewById(R.id.inputGrade);

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

    //This method will enable All the functions
    public void reset(View view){

        //Clear the no_Of_Subjects and inputGPA , credits input
        ((EditText)findViewById(R.id.no_of_subjects)).setText("");
        ((EditText)findViewById(R.id.inputGrade)).setText("");
        ((EditText)findViewById(R.id.creditInput)).setText("");

        //Reset all the values
        noOfSubjects = 0;
        subjectsCounter = 0;
        totalGPA = 0.0;
        currentGPA = 0.0;
        creditsForTheSubject = 0;
        totalCredits = 0;

        //Enable the Confirm btn and the No_Of_Subjects Plain Text
        findViewById(R.id.confirm_btn).setEnabled(true);
        findViewById(R.id.no_of_subjects).setEnabled(true);

        //Enable the OK button and the inputGPA,credits input PlainText
        findViewById(R.id.ok_btn).setEnabled(true);
        findViewById(R.id.inputGrade).setEnabled(true);
        findViewById(R.id.creditInput).setEnabled(true);

        //Display the Average current GPA
        ((TextView)findViewById(R.id.outputGPA)).setText("Current GPA : ");

        //Update the subjectsCounter TextView
        ((TextView)findViewById(R.id.subjectCounter)).setText("Subject NO : " + (subjectsCounter));

    }
    public void validateGPA(String currentGPAString,String creditsForTheSubjectsString){
        double marks ;
        creditsForTheSubject = Integer.parseInt(creditsForTheSubjectsString);

        //Validating The GPA value Entered
        //If the user entered only the marks
        try{
            //Try to convert it to double
            marks = Double.parseDouble(currentGPAString);

            if((marks > 100.0) || (marks < 0.0)){
                subjectsCounter--;

                //Update the subjectsCounter TextView
                ((TextView)findViewById(R.id.subjectCounter)).setText("Subject NO : " + (subjectsCounter + 1));

                //Error show regarding marks > 100
                Toast.makeText(this,"Please Enter a Valid value!!",Toast.LENGTH_LONG).show();
            }
            else if(marks >= 85){
                findGPA("A+",creditsForTheSubject);
            }
            else if(marks >= 80){
                findGPA("A",creditsForTheSubject);
            }
            else if(marks >= 75){
                findGPA("A-",creditsForTheSubject);
            }
            else if(marks >= 70){
                findGPA("B+",creditsForTheSubject);
            }
            else if(marks >= 65){
                findGPA("B",creditsForTheSubject);
            }
            else if(marks >= 60){
                findGPA("B-",creditsForTheSubject);
            }
            else if(marks >= 55){
                findGPA("C+",creditsForTheSubject);
            }
            else if(marks >= 50){
                findGPA("C",creditsForTheSubject);
            }
            else if(marks >= 45){
                findGPA("C-",creditsForTheSubject);
            }
            else if(marks >= 40){
                findGPA("D+",creditsForTheSubject);
            }
            else if(marks >= 35){
                findGPA("D",creditsForTheSubject);
            }
            else if(marks < 35){
                findGPA("E",creditsForTheSubject);
            }
        }
        catch (Exception e){

            //If the conversion fails then try find that is it a valid Grade
            findGPA(currentGPAString,creditsForTheSubject);
        }

    }

    public void findGPA(String grade,int creditsForTheSubject){
        switch (grade){
            case "A+":
                currentGPA = 4.0 + 0.0;
                break;
            case "A":
                currentGPA = 4.0 + 0;
                break;
            case "A-":
                currentGPA = 4.0 - minusIncrement;
                break;
            case "B+":
                currentGPA = 3.0 + plusIncrement;
                break;
            case "B":
                currentGPA = 3.0;
                break;
            case "B-":
                currentGPA = 3.0 - minusIncrement;
                break;
            case "C+":
                currentGPA = 2.0 + plusIncrement;
                break;
            case "C":
                currentGPA = 2.0;
                break;
            case "C-":
                currentGPA = 2.0 - minusIncrement;
                break;
            case "D+":
                currentGPA = 1.0 + plusIncrement;
                break;
            case "D":
                currentGPA = 1.0;
                break;
            case "E":
                currentGPA = 0.0;
                break;
            default:
                currentGPA = 0;
                creditsForTheSubject = 0;
                subjectsCounter--;

                //Update the subjectsCounter TextView
                ((TextView)findViewById(R.id.subjectCounter)).setText("Subject NO : " + (subjectsCounter + 1));

                //Error show regarding invalid value for 2nd char
                Toast.makeText(this,"Invalid Grade Value!!",Toast.LENGTH_LONG).show();
                break;
        }

        //Add the current GPA to final GPA and get the addition of credits
        totalGPA = totalGPA + currentGPA * creditsForTheSubject;
        totalCredits = totalCredits + creditsForTheSubject;

    }

    //method to confirm the input this will disable some buttons
    public void confirm(View view){

        //Get the number of subjects from the No_Of_Subjects as a String
        noOfSubjectsString = ((EditText)findViewById(R.id.no_of_subjects)).getText().toString();

        //If the NoOfSubjects value is empty
        if(TextUtils.isEmpty(noOfSubjectsString)){
            Toast.makeText(this,"Please Enter a value for Number of Subjects",Toast.LENGTH_LONG).show();
            return;

        }

        int zeroInput = Integer.parseInt(noOfSubjectsString);

        //If noOfSubjects are zeros or less than zero
        if(zeroInput <= 0){

            //Disable the OK button and the inputGPA PlainText
            findViewById(R.id.ok_btn).setEnabled(false);
            findViewById(R.id.inputGrade).setEnabled(false);

            //Enable the RESET button
            findViewById(R.id.reset_btn).setEnabled(true);

        }
        else{
            //Convert the String to an integer
            noOfSubjects = zeroInput;
        }
        //Update the subjectsCounter TextView
        ((TextView)findViewById(R.id.subjectCounter)).setText("Subject NO : " + (subjectsCounter + 1));

        //Disable the Confirm btn and the No_Of_Subjects Plain Text
        findViewById(R.id.confirm_btn).setEnabled(false);
        findViewById(R.id.no_of_subjects).setEnabled(false);
    }

    //method Regarding the OK Button
    public void gotoNext(View view){

        //Check whether the subjectsCounter is less than the NoOfSubjects
        if(subjectsCounter < noOfSubjects){

            //Increment the subjectsCounter
            subjectsCounter++;

            //Update the subjectsCounter TextView
            ((TextView)findViewById(R.id.subjectCounter)).setText("Subject NO : " + (subjectsCounter + 1));

            //Get the Current GPA for that subject as a String
            currentGPAString = ((EditText)findViewById(R.id.inputGrade)).getText().toString();

            //Get the credits for the subject as a string
            creditsForTheSubjectsString = ((EditText)findViewById(R.id.creditInput)).getText().toString();

            //If the GPA value is empty or noOfCredits is empty
            if(TextUtils.isEmpty(currentGPAString) || TextUtils.isEmpty(creditsForTheSubjectsString)){
                Toast.makeText(this,"Empty Values are not possible!!",Toast.LENGTH_LONG).show();
                subjectsCounter--;

                //Update the subjectsCounter TextView
                ((TextView)findViewById(R.id.subjectCounter)).setText("Subject NO : " + (subjectsCounter + 1));

                return;

            }

            //Call the validate GPA function
            validateGPA(currentGPAString,creditsForTheSubjectsString);

            //If the subjectsCounter == noOfSubjects then this will execute
            if(subjectsCounter == noOfSubjects){

                //Display the Average current GPA
                ((TextView)findViewById(R.id.outputGPA)).setText("Current GPA : " + totalGPA / totalCredits);

                //Disable the OK button and the inputGPA,credits input PlainText
                findViewById(R.id.ok_btn).setEnabled(false);
                findViewById(R.id.inputGrade).setEnabled(false);
                findViewById(R.id.creditInput).setEnabled(false);

                //Enable the RESET button
                findViewById(R.id.reset_btn).setEnabled(true);
            }
        }
    }
}