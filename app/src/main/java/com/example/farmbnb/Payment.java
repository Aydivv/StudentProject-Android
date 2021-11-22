package com.example.farmbnb;

import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Payment extends AppCompatActivity {
    NotificationManagerCompat nMC;
    Notification reminder;
    Spinner spin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel nChannel = new NotificationChannel("ch1", "Default Channel", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manage = getSystemService(NotificationManager.class);
            manage.createNotificationChannel(nChannel);
        }
        spin = findViewById(R.id.spinnercard);
        ArrayAdapter adpt = ArrayAdapter.createFromResource(this,R.array.cards, android.R.layout.simple_spinner_item);
        adpt.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spin.setAdapter(adpt);
    }

    public void dateNotify(String arrival, String departure) {

        NotificationCompat.Builder notif = new NotificationCompat.Builder(this, "ch1")
                .setSmallIcon(android.R.drawable.stat_notify_sync)
                .setContentTitle("Booking Confirmed!")
                .setContentText("Congratulations! Your booking has been confirmed at FarmBnB from " + arrival +" to " + departure + ".");

        nMC = NotificationManagerCompat.from(this);
        reminder = notif.build();
        nMC.notify(1,reminder);
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
        Spinner cardType = (Spinner) findViewById(R.id.spinnercard);
        EditText cardNumber = (EditText) findViewById(R.id.paymentCard);
        EditText expiry = (EditText) findViewById(R.id.expiryDate);
        EditText cvv = (EditText) findViewById(R.id.cvv);
        String aDate = getIntent().getStringExtra("aDate");
        String dDate = getIntent().getStringExtra("dDate");
        if (name.length()==0){
            name.setError("Enter Full Name");
        } else if (cardNumber.length()==0){
            cardNumber.setError("Enter Card Number");
        } else if (expiry.length()==0){
            expiry.setError("Cannot be empty");
        } else if (cvv.length() != 3 && cvv.length() != 4){
            cvv.setError("Has to be three or four digits");
        } else {
            dateNotify(aDate,dDate);
            Toast.makeText(Payment.this,"Booking Confirmed.",Toast.LENGTH_SHORT).show();
            Intent goConfirm = new Intent(this, Confirmation.class);
            startActivity(goConfirm);
        }
    }

}