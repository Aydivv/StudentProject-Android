package com.example.farmbnb;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class bookings extends AppCompatActivity {
    static String fromDate = "";
    static String toDate = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookings);
        if(!fromDate.equals("")) {
            TextView from = findViewById(R.id.fromDateText);
            from.setText("From: " + fromDate);
            TextView to = findViewById(R.id.toDateText);
            to.setText("To: " + toDate);
        }
    }
}