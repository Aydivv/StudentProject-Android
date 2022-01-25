package com.example.projudent;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import java.util.ArrayList;

public class Preferences extends AppCompatActivity {
    private User user;
    private ArrayList<Boolean> prefs;
    private CheckBox create,delete,edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);
        user = (User) getIntent().getSerializableExtra("User");
        prefs = user.getPrefs();
        create = findViewById(R.id.cbCreate);
        delete = findViewById(R.id.cbDelete);
        edit = findViewById(R.id.cbEdit);


        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(create.isChecked())
                    prefs.set(0,true);
                else
                    prefs.set(0,false);
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(create.isChecked())
                    prefs.set(1,true);
                else
                    prefs.set(1,false);
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(create.isChecked())
                    prefs.set(2,true);
                else
                    prefs.set(2,false);
            }
        });
    }

    @Override
    public void onBackPressed() {
    }

    public void toWelcome(View view) {


        user.setPrefs(prefs);
        Intent intent = new Intent(Preferences.this,welcome.class);
        intent.putExtra("User",user);
        startActivity(intent);
    }

    public void toLogin(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to logout?");
        builder.setCancelable(true);

        builder.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(Preferences.this,MainActivity.class);
                        startActivity(intent);
                    }
                });

        builder.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert = builder.create();
        alert.show();
    }
}