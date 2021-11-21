package com.example.farmbnb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void goHomepage(View view){
        EditText number = (EditText) findViewById(R.id.name);
        EditText pass = (EditText) findViewById(R.id.createPassword);
        if(number.length()==0){
            number.setError("Enter Number");
        }
        else if(pass.length()==0){
            pass.setError("Enter Password");
        }
        else if(number.getText().toString().equals("0000") && pass.getText().toString().equals("test")){
            Toast.makeText(MainActivity.this,"Logged in.", Toast.LENGTH_SHORT).show();
            Intent home = new Intent(this,home.class);
            startActivity(home);
        }
        else{
            if (!number.getText().toString().equals("0000")){
                number.setError("Phone Number not registered.");
            }
            else if (!pass.getText().toString().equals("test")){
                pass.setError("Wrong Password.");
            }
            Toast.makeText(MainActivity.this,"Incorrect login.", Toast.LENGTH_SHORT).show();
        }
    }


    public void goCreateAcc(View view){
        Intent create = new Intent(this,createAccount.class);
        startActivity(create);
    }
}