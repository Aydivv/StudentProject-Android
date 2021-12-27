package com.example.projudent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class myProjects extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_projects);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.left_slide_in,R.anim.left_slide_out);
    }

    public void toWelcome(View view) {
        Intent intent = new Intent(this, welcome.class);
        startActivity(intent);
        overridePendingTransition(R.anim.left_slide_in,R.anim.left_slide_out);
    }

    public void toAddPJ(View view) {
        Intent intent = new Intent(this, addProject.class);
        startActivity(intent);
    }
}