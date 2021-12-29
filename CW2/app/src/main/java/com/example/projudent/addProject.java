package com.example.projudent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class addProject extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_project);
    }

    public void toWelcome(View view) {
        Intent intent = new Intent(this,welcome.class);
        startActivity(intent);
    }
}