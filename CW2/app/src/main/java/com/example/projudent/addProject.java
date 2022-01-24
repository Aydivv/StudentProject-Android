package com.example.projudent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class addProject extends AppCompatActivity {
    private User user;
    private EditText etTitle;
    private EditText etYear;
    private EditText etDescription;
    private int pjID;
    public int SELECT_IMAGE_CODE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_project);
        user = (User)getIntent().getSerializableExtra("User");
        etTitle = findViewById(R.id.etTitle);
        etYear = findViewById(R.id.etYear);
        etDescription = findViewById(R.id.etDescription);
    }

    public void toWelcome(View view) {

        if (etTitle.length() == 0) {
            etTitle.setError("Enter Title");
        } else if (etYear.length() == 0) {
            etYear.setError("Enter Year");
        } else if (etDescription.length() == 0){
            etDescription.setError("Enter Description");
        } else{
            addProject();
            Intent intent = new Intent(this,welcome.class);
            intent.putExtra("User",user);
            startActivity(intent);
        }

    }

    public void addProject(){
        RequestQueue queue = Volley.newRequestQueue(this);
        final String URL = "http://web.socem.plymouth.ac.uk/COMP2000/api/students";
        // Post params to be sent to the server
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("StudentID", user.getStudentID());
        params.put("Title", etTitle.getText().toString());
        params.put("Description", etDescription.getText().toString());
        params.put("Year", Integer.parseInt(etYear.getText().toString()));
        params.put("First_Name", user.getFirst_Name());
        params.put("Second_Name", user.getLast_Name());

        JsonObjectRequest request_json = new JsonObjectRequest(URL, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Toast.makeText(addProject.this,"Project added.",Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
            }
        });
        queue.add(request_json);

        getprojectnumber();
    }

    private void getprojectnumber() {
        RequestQueue queue = Volley.newRequestQueue(addProject.this);
        final String url = "http://web.socem.plymouth.ac.uk/COMP2000/api/students";
        JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for(int i=0; i<response.length();i++){
                        if(response.getJSONObject(i).getInt("studentID") == user.getStudentID()){
                            pjID = response.getJSONObject(i).getInt("projectID");
                            break;
                        }
                    }
                } catch (JSONException e){
                    Toast.makeText(addProject.this,"parsing error",Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(addProject.this,"volley error",Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(req);
        uploadimage();
    }

    private void uploadimage() {
        final String url = "http://web.socem.plymouth.ac.uk/COMP2000/api/students/" + String.valueOf(pjID) + "/image";


    }


    public void selectPicture(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Title"), SELECT_IMAGE_CODE);
    }
}
