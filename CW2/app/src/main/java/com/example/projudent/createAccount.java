package com.example.projudent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

public class createAccount extends AppCompatActivity {
    private EditText etID;
    private EditText etpassword;
    private EditText etrPassword;
    private EditText etfname;
    private EditText etlname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.left_slide_in, R.anim.left_slide_out);
    }

    public void toHome(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.left_slide_in, R.anim.left_slide_out);
    }

    public void toWelcome(View view) {
        etID = findViewById(R.id.etStudentIDca);
        etpassword = findViewById(R.id.etPasswordca);
        etrPassword = findViewById(R.id.etRepeatPasswordca);
        etfname = findViewById(R.id.etFirstName);
        etlname = findViewById(R.id.etLastName);

        if (etID.length() == 0) {
            etID.setError("Enter Student ID");
        } else if (etpassword.length() == 0) {
            etpassword.setError("Enter Password");
        } else if (etrPassword.length() == 0) {
            etrPassword.setError("Repeat Password");
        } else if (!etpassword.getText().toString().equals(etrPassword.getText().toString())) {
            etrPassword.setError("Password does not match");
        } else if (etfname.length() == 0) {
            etfname.setError("Enter First Name.");
        } else if (etlname.length() == 0) {
            etlname.setError("Enter Second Name");
        } else {
            int ID = Integer.parseInt(etID.getText().toString().trim());
            String password = etpassword.getText().toString().trim();
            String fname = etfname.getText().toString().trim();
            String lname = etlname.getText().toString().trim();
            createUser(ID, password, fname, lname);
            Toast.makeText(createAccount.this, "Account Created.", Toast.LENGTH_SHORT).show();

            User user = new User(ID, fname, lname, password);
            Intent intent = new Intent(this, welcome.class);
            intent.putExtra("User", user);
            startActivity(intent);
        }
    }

    public void createUser(int ID, String pword, String fname, String lname) {
        try {
            int p = pword.hashCode();
            FileOutputStream fop = openFileOutput("users.txt", Context.MODE_APPEND);
            fop.write((String.valueOf(ID) + "," + String.valueOf(p) + "," + fname + "," + lname + "\n").getBytes(StandardCharsets.UTF_8));
            fop.close();
        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }
}