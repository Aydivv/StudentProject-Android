package com.example.farmbnb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Confirmation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);
        TextView from = findViewById(R.id.fromConfirmDate);
        from.setText("From: " + bookings.fromDate);
        TextView to = findViewById(R.id.toConfirmDate);
        to.setText("To: " + bookings.toDate);
    }

    public void goHome(View view){
        Intent home = new Intent(this, home.class);
        startActivity(home);
    }
}