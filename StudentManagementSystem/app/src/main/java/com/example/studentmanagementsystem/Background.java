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
Background.java
    This class manages all the background processes that is being running in android app. This has the connection with the server
 */

package com.example.studentmanagementsystem;

import android.accessibilityservice.AccessibilityService;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class Background extends AsyncTask<String, Void, String> {
    // to get data from other Activities
    Context context;

    // Object for alert dialog
    AlertDialog alertDialog;

    // Private variables
    private String result = "";
    private int adminFlag = 0;
    private static String marks = "";
    private static String usersGPA = "";
    private static String userMarks = "";

    // Constructor
    Background(Context cnt){
        context = cnt;
    }

    // Do in background function
    @Override
    protected String doInBackground(String... strings) {  // What should be done in background
        // first parameter is type, to get type of background process
        String type = strings[0];

        // URLs to connect with server
        String loginURL = "http://192.168.43.155/Student_Reg/login.php";
        String regURL = "http://192.168.43.155/Student_Reg/insert.php";
        String courseRegURL = "http://192.168.43.155/Student_Reg/coursereg.php";
        String insertMarksURL = "http://192.168.43.155/Student_Reg/insertMarks.php";
        String courseListURL1 = "http://192.168.43.155/Student_Reg/CoursesList1.php";
        String courseListURL2 = "http://192.168.43.155/Student_Reg/CoursesList2.php";
        String courseInfoURL = "http://192.168.43.155/Student_Reg/CoursesInfo.php";
        String GPAURL = "http://192.168.43.155/Student_Reg/CurrentGPA.php";
        String userMarksURL = "http://192.168.43.155/Student_Reg/userMarks.php";
        String rankURL = "http://192.168.43.155/Student_Reg/getRank.php";


        // Check what is the type and do things according to that type

        // If we want login details
        if (type.equals("login")){
            // Getting input variables
                String user_name = strings[1];
                String password = strings[2];

                try {
                    // Post data string
                    String postData = URLEncoder.encode("user_name", "UTF-8") + "="
                            + URLEncoder.encode(user_name, "UTF-8") + "&"
                            + URLEncoder.encode("password", "UTF-8") + "="
                            + URLEncoder.encode(password, "UTF-8");

                    // post data to server and getting result
                    result = postData(loginURL, postData);
                    adminFlag = Integer.parseInt(result.substring(0,1));
                    return  result.substring(1);

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
        }

        // If we want to register
        else if(type.equals("Register")){

            // Getting input variables
                String password = strings[1];
                String firstName = strings[2];
                String lastName = strings[3];
                String regNo = strings[4];
                String email = strings[5];
                String field = strings[6];

                try {
                    // encoding post data
                    String postData = URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8") + "&"
                            + URLEncoder.encode("firstname", "UTF-8") + "=" + URLEncoder.encode(firstName, "UTF-8") + "&"
                            + URLEncoder.encode("lastname", "UTF-8") + "=" + URLEncoder.encode(lastName, "UTF-8") + "&"
                            + URLEncoder.encode("regno", "UTF-8") + "=" + URLEncoder.encode(regNo, "UTF-8") + "&"
                            + URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8") + "&"
                            + URLEncoder.encode("field", "UTF-8") + "=" + URLEncoder.encode(field, "UTF-8");

                    // getting result
                    return postData(regURL, postData);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
        }

        // If want to register a course
        else if (type.equals("CourseRegister")){
                // Getting input variables
                String courseName = strings[1];
                String courseCode = strings[2].toUpperCase();
                int courseCredit = Integer.parseInt(strings[3]);    // Converting string inputs into integers
                int project = Integer.parseInt(strings[4]);
                int assign = Integer.parseInt(strings[5]);
                int mid = Integer.parseInt(strings[6]);
                int end = Integer.parseInt(strings[7]);


            try {
                // encoding post data
                String postData = URLEncoder.encode("courseName", "UTF-8")+"="+URLEncoder.encode(courseName, "UTF-8")+"&"
                        +URLEncoder.encode("courseCode", "UTF-8")+"="+URLEncoder.encode(courseCode, "UTF-8")+"&"
                        +URLEncoder.encode("courseCredit", "UTF-8")+"="+courseCredit+"&"
                        +URLEncoder.encode("project", "UTF-8")+"="+project+"&"
                        +URLEncoder.encode("assign", "UTF-8")+"="+assign+"&"
                        +URLEncoder.encode("mid", "UTF-8")+"="+mid+"&"
                        +URLEncoder.encode("end", "UTF-8")+"="+end;

                // Getting result from the server
                return postData(courseRegURL, postData);

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        // If we want to enter user's marks
        else if (type.equals("Enter Marks")){
            // Getting inout variables from function and convert them into specific data types
            int regNo = Integer.parseInt(strings[1]);
            String courseCode = strings[2];
            int marks4assignment = Integer.parseInt(strings[3]);
            int marks4project = Integer.parseInt(strings[4]);
            int marks4mid = Integer.parseInt(strings[5]);
            int marks4end = Integer.parseInt(strings[6]);
            double gpa = Double.parseDouble(strings[7]);



            try{
                // encoding post data
                String postData = URLEncoder.encode("regNo", "UTF-8")+"="+regNo+"&"
                        +URLEncoder.encode("courseCode", "UTF-8")+"="+URLEncoder.encode(courseCode, "UTF-8")+"&"
                        +URLEncoder.encode("marks4assignment", "UTF-8")+"="+marks4assignment+"&"
                        +URLEncoder.encode("marks4project", "UTF-8")+"="+marks4project+"&"
                        +URLEncoder.encode("marks4mid", "UTF-8")+"="+marks4mid+"&"
                        +URLEncoder.encode("marks4end", "UTF-8")+"="+marks4end+"&"
                        +URLEncoder.encode("gpa", "UTF-8")+"="+gpa;

                // Getting result
                return postData(insertMarksURL, postData);
            }
            catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        // If we want to get course list that available in server to enter marks
        else if (type.equals("CourseList1")){
            // Encoding post data to send
            try {
                String postData = URLEncoder.encode("command", "UTF-8")+"="+1;

                // Getting result
                return postData(courseListURL1, postData);

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        // getting course list to calculate expected mark
        else if (type.equals("CourseList2")){
            try {
                String postData = URLEncoder.encode("command", "UTF-8")+"="+1;

                // getting data from user
                return postData(courseListURL2, postData);

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        // Getting credits and assigned marks of the course for entermarks
        else if (type.equals("CourseInfo")){
            // Getting the course code from the server
            String course = strings[1];

            try {
                // Encode post data
                String postData = URLEncoder.encode("course", "UTF-8")+"="+course;
                // Getting result
                return postData(courseInfoURL, postData);

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        }

        // Getting current gpa for user
        else if(type.equals("getGPA")){

            // Getting user reg number
            int regNo = Integer.parseInt(strings[1]);

            try {
                String postData = URLEncoder.encode("regNo", "UTF-8")+"="+regNo;
                return postData(GPAURL, postData);

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        // Getting user's marks for calculate expected end exam marks
        else if (type.equals("userMarks")) {
            // getting variable from function
            String course = strings[1];
            int regNo = Integer.parseInt(MainActivity.getLoggeduser()); // Getting logged user's reg number from main activity

            try {
                // Encode post data
                String postData = URLEncoder.encode("course", "UTF-8") + "=" + course+"&"
                        +URLEncoder.encode("regNo", "UTF-8")+"="+regNo;

                // Getting result from the server
                return postData(userMarksURL, postData);

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        // Getting current gpa and rank from the server
        else if (type.equals("get gpa")){
            // Getting logged user's reg number from main activity
            int regNo = Integer.parseInt(MainActivity.getLoggeduser());

            try {
                // Encoding post data
                String postData = URLEncoder.encode("regNo", "UTF-8") + "=" + regNo;

                // getting results from server
                return postData(rankURL, postData);

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }




        return null;
    }

    @Override
    protected void onPreExecute() {     // Setting alertdialog before executing
        alertDialog = new AlertDialog.Builder(context).create();
    }

    // this method runs after executing doinbackground() method
    @Override
    protected void onPostExecute(String result) {   // When executing

        // This if else checks what is server gives as key value (Key value is in first strings in server massage)

        // When login massage arrived
        if (result.equals("Login Success")){
            Toast.makeText(context, "Welcome ", Toast.LENGTH_SHORT).show();

            // Go to login activity
            Intent login = new Intent(context, Login.class); // user name for mainActivity
            login.putExtra("adminFlag", adminFlag);
            context.startActivity(login);               // Start User Management window
        }

        // When register reply arrived
        else if (result.equals("Successfully Registered")){
            // Show register status
            alertDialog.setTitle("Sign up Status");
            alertDialog.setMessage(result+"\nGo back and Login");
            alertDialog.show();
        }

        // If login failed massage arrived
        else if(result.equals("Login Failed")){
            alertDialog.setTitle("Login Status");
            alertDialog.setMessage(result+"\nRegNo or Password Incorrect!");
            alertDialog.show();
            Toast.makeText(context, "Login Failed", Toast.LENGTH_SHORT).show();
        }

        // If successfully course registered massage arrived
        else if (result.equals("Successfully Registered the Course")){
            alertDialog.setTitle("Register Status");
            alertDialog.setMessage(result);
            alertDialog.show();
        }

        // If successfully added marks massage arrived
        else if (result.equals("Successfully Added the marks")){
            alertDialog.setTitle("Insert Status");
            alertDialog.setMessage(result);
            alertDialog.show();
        }

        // if course list to enter marks arrived
        else if (result.substring(0,1).equals("1")){
            // Make intent to entermarks class and give course list to that intent
            Intent list = new Intent(context, enterMarks.class);
            list.putExtra("courseList", result);
            context.startActivity(list);
        }

        // if course list for expected marks arrived
        else if (result.substring(0,1).equals("2")){
            // Make intent to calculateMarks class and give course list to that intent
            Intent list = new Intent(context, calculateMarks.class);
            list.putExtra("courseList", result);
            context.startActivity(list);
        }

        // if result is assigned marks for requested course
        else if (result.substring(0,6).equals("course")){
            marks = result.substring(6);
        }

        // Getting GPA from logged user
        else if (result.substring(0,3).equals("gpa")){
            // Extract data and assign to varibles to return
            usersGPA = result.substring(3);

            // Make intent to expectedGrade class and start the activity
            Intent expectedGrade = new Intent(context, expectedGrade.class);
            context.startActivity(expectedGrade);

        }

        // If the result is marks of user
        else if (result.substring(0,5).equals("Marks")){
            // Extract data and assign to varibles to return
            userMarks = result.substring(5);

        }

        // If result is rank and gpa for logged user
        else if (result.substring(0,4).equals("Rank")){
            // Assigning results to string array
            String userRank[] = result.substring(4).split(",");

            // Massage to display
            String message = "Your Current GPA: " + userRank[0].substring(0, 4) + "\n" + "Your Rank: " + userRank[1];

            // Show massage using alertDialog
            alertDialog.setTitle("Current Grade");
            alertDialog.setMessage(message);
            alertDialog.show();
        }

        // If there is different massage from server, display it directly
        else{
            alertDialog.setMessage(result);
            alertDialog.show();
        }


    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    // Function to communicate with server
    public static String postData(String serverURL, String postData){
        try{
            URL url = new URL(serverURL);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);        // true for post method (giving a input)
            httpURLConnection.setDoInput(true);        // Here we getting the output

            //||||||||||||||||||||||||| Giving input |||||||||||||||||||||||||||||||||||||||||||||
            // Making the output stream
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));

            // Write this to the url
            bufferedWriter.write(postData);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();

            //||||||||||||||||||||||||| Getting Output |||||||||||||||||||||||||||||||||||||||||||||
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String result = "";

            String line = "";
            while ((line = bufferedReader.readLine()) != null){
                result = result + line;
            }

            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();

            return result;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    // Getter method to get credit
    public static String getMarks(){
        return marks;
    }

    // Getter method to get gpa
    public static String getUsersGPA(){
        return usersGPA;
    }

    // Getter method to get userMarks
    public static String getUserMarks(){
        return userMarks;
    }

    // Getter method for get result
    public String getResult(){
        return result;
    }

    // Getter method for get AdminFlag
    public int getAdminFlag(){
        return adminFlag;
    }

}
