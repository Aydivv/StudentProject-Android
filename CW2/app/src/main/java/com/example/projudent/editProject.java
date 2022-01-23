package com.example.projudent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_project);
        user = (User)getIntent().getSerializableExtra("User");
        ID = (String)getIntent().getStringExtra("ID");
        etTitle = findViewById(R.id.etTitleEPJ);
        etYear = findViewById(R.id.etYearEPJ);
        etDesc = findViewById(R.id.etDescriptionEPJ);
    }

    public void toWelcome(View view){
        if (etTitle.length() == 0) {
            etTitle.setError("Enter Title");
        } else if (etYear.length() == 0) {
            etYear.setError("Enter Year");
        } else if (etDesc.length() == 0){
            etDesc.setError("Enter Description");
        } else {
            editProject(ID);
            Intent intent = new Intent(this, myProjects.class);
            intent.putExtra("User", user);
            startActivity(intent);
        }
    }

    public void editProject(String eID){
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
                        Toast.makeText(editProject.this,"Project has been edited.",Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(editProject.this,"Project has been edited.",Toast.LENGTH_SHORT).show();
                    }
                });
        queue.add(request);
    }
}