package com.example.mobiledataorwificheck;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void checkMobileDataOrWiFi(View view){

        if(isNetworkConnected(this)){
            Toast.makeText(this,"Connection Available",Toast.LENGTH_LONG).show();
        }
        else{
            //showDialog();
            Toast.makeText(this,"Please Connect to the Internet",Toast.LENGTH_LONG).show();
        }
    }


    //Check internet connection
    private boolean isNetworkConnected(MainActivity mainActivity) {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

}