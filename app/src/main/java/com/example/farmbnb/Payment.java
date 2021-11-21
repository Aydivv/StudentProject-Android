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

public class Payment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void expiryDate(View view){
        EditText date;
        date = (EditText) findViewById(R.id.expiryDate);
        final Calendar c = Calendar.getInstance();
        int tYear = c.get(Calendar.YEAR);
        int tMonth = c.get(Calendar.MONTH);
        int tDay = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dpd = new DatePickerDialog(Payment.this,R.style.DatePickerTheme,
                new DatePickerDialog.OnDateSetListener(){
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth){
                        date.setText((monthOfYear + 1) + "/" + year);
                    }
                },tYear,tMonth,tDay);
        dpd.show();
    }

    public void goConfirmation(View view){
        EditText name = (EditText) findViewById(R.id.paymentName);
        EditText cardType = (EditText) findViewById(R.id.paymentCardType);
        EditText cardNumber = (EditText) findViewById(R.id.paymentCard);
        EditText expiry = (EditText) findViewById(R.id.expiryDate);
        EditText cvv = (EditText) findViewById(R.id.cvv);
        if (name.length()==0){
            name.setError("Enter Full Name");
        } else if (cardType.length()==0){
            cardType.setError("Enter Type of Card");
        } else if (cardNumber.length()==0){
            cardNumber.setError("Enter Card Number");
        } else if (expiry.length()==0){
            expiry.setError("Cannot be empty");
        } else if (cvv.length() != 3 && cvv.length() != 4){
            cvv.setError("Has to be three or four digits");
        } else {
            Toast.makeText(Payment.this,"Booking Confirmed.",Toast.LENGTH_SHORT).show();
            Intent goConfirm = new Intent(this, Confirmation.class);
            startActivity(goConfirm);
        }
    }

}