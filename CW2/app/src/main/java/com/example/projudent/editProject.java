package com.example.projudent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class editProject extends AppCompatActivity {
    private User user;
    private String ID;
    private EditText etTitle;
    private EditText etYear;
    private EditText etDesc;
    NotificationManagerCompat nMC;
    Notification reminder;
    private Project pj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_project);
        user = (User) getIntent().getSerializableExtra("User");
        pj = (Project) getIntent().getSerializableExtra("Project");
        etTitle = findViewById(R.id.etTitleEPJ);
        etYear = findViewById(R.id.etYearEPJ);
        etDesc = findViewById(R.id.etDescriptionEPJ);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel nChannel = new NotificationChannel("ch1", "Default Channel", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manage = getSystemService(NotificationManager.class);
            manage.createNotificationChannel(nChannel);
        }
        etTitle.setHint(pj.getTitle());
        etYear.setHint(String.valueOf(pj.getYear()));
        etDesc.setHint(pj.getDesc());
    }

    public void back(View view) {
        Intent intent = new Intent(editProject.this, myProjects.class);
        intent.putExtra("User", user);
        startActivity(intent);
    }

    public void toWelcome(View view) {
        if (etTitle.length() == 0) {
            etTitle.setError("Enter Title");
        } else if (etYear.length() != 4) {
            etYear.setError("Enter Valid Year");
        } else if (etDesc.length() == 0) {
            etDesc.setError("Enter Description");
        } else {
            editProject(String.valueOf(pj.getProjectID()));

            if (user.getPrefs().get(2)) {
                NotificationCompat.Builder notif = new NotificationCompat.Builder(editProject.this, "ch1")
                        .setSmallIcon(android.R.drawable.ic_menu_edit)
                        .setContentTitle("Project Edited!")
                        .setContentText("Project " + etTitle.getText().toString() + " has been edited!");

                nMC = NotificationManagerCompat.from(editProject.this);
                reminder = notif.build();
                nMC.notify(1, reminder);
            }


            Intent intent = new Intent(this, myProjects.class);
            intent.putExtra("User", user);
            startActivity(intent);
        }
    }

    public void editProject(String eID) {
        RequestQueue queue = Volley.newRequestQueue(this);
        final String URL = "http://web.socem.plymouth.ac.uk/COMP2000/api/students/" + eID;

        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("Title", etTitle.getText().toString());
        params.put("Description", etDesc.getText().toString());
        params.put("Year", Integer.parseInt(etYear.getText().toString()));


        JsonObjectRequest request = new JsonObjectRequest
                (Request.Method.PUT, URL, new JSONObject(params), new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(editProject.this, "Project has been edited.", Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(editProject.this, "Project has been edited.", Toast.LENGTH_SHORT).show();
                    }
                });
        queue.add(request);
    }

    @Override
    public void onBackPressed() {
    }
}
