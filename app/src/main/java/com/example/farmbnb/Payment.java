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
        Intent goConfirm = new Intent(this,Confirmation.class);
        startActivity(goConfirm);
    }

}