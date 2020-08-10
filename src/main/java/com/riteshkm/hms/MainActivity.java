package com.riteshkm.hms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void patient(View view) {
        Intent intent = new Intent(MainActivity.this,Patient.class);
        startActivity(intent);
    }

    public void doctor(View view) {
        Intent intent = new Intent(MainActivity.this,Doctor.class);
        startActivity(intent);
    }

    public void nurse(View view) {
        Intent intent = new Intent(MainActivity.this,Nurse.class);
        startActivity(intent);
    }
}
