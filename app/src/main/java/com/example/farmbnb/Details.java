package com.example.farmbnb;



import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;



public class Details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void goPayment(View view) {
        EditText noOfPeople = (EditText) findViewById(R.id.detailNOP);
        EditText noOfChildren = (EditText) findViewById(R.id.detailNOC);
        EditText arrival = (EditText) findViewById(R.id.arrivalDate);
        EditText departure = (EditText) findViewById(R.id.departureDate);
        String aDate = arrival.getText().toString();
        bookings.fromDate = aDate;
        String dDate = departure.getText().toString();
        bookings.toDate = dDate;
        if (noOfPeople.length()==0){
            noOfPeople.setError("Cannot be empty");
        } else if (noOfChildren.length()==0){
            noOfChildren.setError("Cannot be empty");
        } else if (arrival.length()==0){
            arrival.setError("Cannot be empty");
        } else if (departure.length()==0){
            departure.setError("Cannot be empty");
        }
        else{
            Toast.makeText(Details.this,"Reserved",Toast.LENGTH_SHORT).show();
            Intent goPayment = new Intent(this,Payment.class);
            goPayment.putExtra("aDate",aDate);
            goPayment.putExtra("dDate",dDate);
            startActivity(goPayment);
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public void datePickerArrival(View view){
        EditText date;
        date = (EditText) findViewById(R.id.arrivalDate);
        final Calendar c = Calendar.getInstance();
        int tYear = c.get(Calendar.YEAR);
        int tMonth = c.get(Calendar.MONTH);
        int tDay = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dpd = new DatePickerDialog(Details.this,R.style.DatePickerTheme,
                new DatePickerDialog.OnDateSetListener(){
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth){
                        date.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    }
                },tYear,tMonth,tDay);
        dpd.show();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void datePickerDeparture(View view){
        EditText date;
        date = (EditText) findViewById(R.id.departureDate);
        final Calendar c = Calendar.getInstance();
        int tYear = c.get(Calendar.YEAR);
        int tMonth = c.get(Calendar.MONTH);
        int tDay = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dpd = new DatePickerDialog(Details.this,R.style.DatePickerTheme,
                new DatePickerDialog.OnDateSetListener(){
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth){
                        date.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    }
                },tYear,tMonth,tDay);
        dpd.show();
    }

}