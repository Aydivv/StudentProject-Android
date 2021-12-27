package com.example.projudent;

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

    public void toRegister(View view) {
        Intent intent = new Intent(this, createAccount.class);
        startActivity(intent);
        overridePendingTransition(R.anim.right_slide_in,R.anim.right_slide_out);
    }

    public void toWelcome(View view) {
        Intent intent = new Intent(this, welcome.class);
        startActivity(intent);
    }
}