package com.example.projudent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.effect.Effect;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText loginID;
    private EditText loginPassword;
    private TextView btnLogin;
    private User user;
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
        loginID = findViewById(R.id.etStudentID);
        loginPassword = findViewById(R.id.etPassword);
        user = new User(Integer.parseInt(loginID.getText().toString()),"newname","lastname",loginPassword.getText().toString());
        Intent intent = new Intent(this, welcome.class);
        intent.putExtra("User",user);
        startActivity(intent);
    }
}