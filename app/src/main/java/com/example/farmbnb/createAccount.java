package com.example.farmbnb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class createAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
    }

    public void goHome(View view) {
        EditText name = (EditText) findViewById(R.id.createName);
        EditText address = (EditText) findViewById(R.id.createAddress);
        EditText number = (EditText) findViewById(R.id.createNumber);
        EditText password = (EditText) findViewById(R.id.createPassword);
        EditText rpassword = (EditText) findViewById(R.id.repeatPassword);
        if (name.length() == 0) {
            name.setError("Enter Name");
        } else if (address.length() == 0) {
            address.setError("Enter Address");
        } else if (number.length() == 0) {
            number.setError("Enter Number");
        } else if (password.length() == 0) {
            password.setError("Enter Password");
        } else if (rpassword.length() == 0) {
            rpassword.setError("Repeat Password");
        } else if (!password.getText().toString().equals(rpassword.getText().toString())) {
            rpassword.setError("Password does not match");
        } else {
            Intent home = new Intent(this, home.class);
            startActivity(home);
        }
    }
}
