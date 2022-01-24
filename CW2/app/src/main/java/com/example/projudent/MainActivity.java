package com.example.projudent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText loginID;
    private EditText loginPassword;
    private TextView btnLogin;
    private User user;
    private ArrayList<Integer> IDs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IDs = new ArrayList<Integer>();
    }

    public void toRegister(View view) {
        Intent intent = new Intent(this, createAccount.class);
        startActivity(intent);
        overridePendingTransition(R.anim.right_slide_in,R.anim.right_slide_out);
    }

    public void toWelcome(View view) {
        boolean login = false;
        loginID = findViewById(R.id.etStudentID);
        loginPassword = findViewById(R.id.etPassword);
        if(loginID.length()==0){
            loginID.setError("Enter StudentID");
        }
        else if(loginPassword.length()==0){
            loginPassword.setError("Enter Password");
        }
        else {
            int ID = Integer.parseInt(loginID.getText().toString());
            String pword = loginPassword.getText().toString();
            login = checkLogin(ID, pword);

            if (login) {
                Toast.makeText(this, "correct", Toast.LENGTH_SHORT).show();
                ArrayList<String> names = getNames(ID);
                user = new User(ID, names.get(0),names.get(1), pword);
                Intent intent = new Intent(this, welcome.class);
                intent.putExtra("User", user);
                startActivity(intent);
            } else {
                boolean exists = false;
                for (int x : IDs) {
                    if (x == ID)
                        exists = true;
                }
                if (exists) {
                    Toast.makeText(this, "Incorrect Password.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "StudentID does not exist. Please Register.", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public boolean checkLogin(int ID, String pword){
        boolean value = false;
        try {
            FileInputStream is = openFileInput("users.txt");
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(is, StandardCharsets.UTF_8)
            );
            String user;
            while ((user = reader.readLine()) != null) {
                String[] tokens = user.split(",");
                IDs.add(Integer.parseInt(tokens[0]));
                if((Integer.parseInt(tokens[0])==ID) && (tokens[1].equals(pword))){
                    value = true;
                }

            }
        } catch (IOException e){
            e.printStackTrace();
            Toast.makeText(this,"error reading file",Toast.LENGTH_SHORT).show();
        }

        return value;
    }

    public ArrayList<String> getNames(int ID){
        ArrayList<String> names = new ArrayList<>();
        try {
            FileInputStream is = openFileInput("users.txt");
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, StandardCharsets.UTF_8)
        );
        String user;

            while ((user = reader.readLine()) != null) {
                String[] tokens = user.split(",");
                if(Integer.parseInt(tokens[0])==ID){
                    names.add(tokens[2]);
                    names.add(tokens[3]);
                    return names;
                }

            }
        } catch (IOException e){
            e.printStackTrace();
            Toast.makeText(this,"error reading file",Toast.LENGTH_SHORT).show();
        }
        return names;
    }

}