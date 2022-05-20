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
about.java
    This directs the user to the html page that contains usage of the app and about the developers
 */

package com.example.studentmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class about extends AppCompatActivity {

    WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        webview = findViewById(R.id.about);
        webview.loadUrl("file:///android_asset/about.html");

    }
}