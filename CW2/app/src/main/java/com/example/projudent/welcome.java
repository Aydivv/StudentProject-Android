package com.example.projudent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    public void toProjects(View view) {
        Intent intent = new Intent(this,myProjects.class);
        startActivity(intent);
        overridePendingTransition(R.anim.right_slide_in,R.anim.right_slide_out);
    }
}