package com.example.farmbnb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void vBooking(View view){
        Intent bookings = new Intent(this,bookings.class);
        startActivity(bookings);
    }

    public void cBooking(View view){
        Intent book = new Intent(this,book.class);
        startActivity(book);
    }
}