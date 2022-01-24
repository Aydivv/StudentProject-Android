package com.example.projudent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.FileUtils;
import android.provider.MediaStore;
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

import java.io.File;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.http.Multipart;

public class addProject extends AppCompatActivity {
    private User user;
    private EditText etTitle;
    private EditText etYear;
    private EditText etDescription;
    private int pjID = 0;
    private boolean selected = false;
    private String filepath = "";
    private Uri img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_project);
        user = (User) getIntent().getSerializableExtra("User");
        etTitle = findViewById(R.id.etTitle);
        etYear = findViewById(R.id.etYear);
        etDescription = findViewById(R.id.etDescription);
    }

    public void toWelcome(View view) {

        if (etTitle.length() == 0) {
            etTitle.setError("Enter Title");
        } else if (etYear.length() == 0) {
            etYear.setError("Enter Year");
        } else if (etDescription.length() == 0) {
            etDescription.setError("Enter Description");
        } else {
            addProject();
            if(!selected) {
                Intent intent = new Intent(this, welcome.class);
                intent.putExtra("User", user);
                startActivity(intent);
            }
        }

    }

    public void addProject() {
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
                            Toast.makeText(addProject.this, "Project added.", Toast.LENGTH_SHORT).show();
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

        RequestQueue q = Volley.newRequestQueue(addProject.this);
        final String url = "http://web.socem.plymouth.ac.uk/COMP2000/api/students";
        JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        if (response.getJSONObject(i).getInt("studentID") == user.getStudentID()) {
                            pjID = response.getJSONObject(i).getInt("projectID");
                            if(selected)
                                new uploadimage().execute(img,pjID);
                            break;
                        }
                    }
                } catch (JSONException e) {
                    Toast.makeText(addProject.this, "parsing error", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(addProject.this, "volley error", Toast.LENGTH_SHORT).show();
            }
        });
        q.add(req);
    }


    private class uploadimage extends AsyncTask<Object,String,String> {
        private String result;
        @Override
        protected String doInBackground(Object...params) {
            Uri image =(Uri) params[0];
            int ID = (int) params[1];
            final String URL = "http://web.socem.plymouth.ac.uk/COMP2000/api/students/" + String.valueOf(ID) + "/";

            File file = new File(getPath(image));
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file);
            MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), requestBody);



            Retrofit retrofit = NetworkClient.getRetrofit(URL);
            upload upload = retrofit.create(upload.class);
            Call call = upload.uploadImage(part);
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, retrofit2.Response response) {
                    result = response.toString();
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    result = t.toString();
                }
            });
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

;           Intent intent = new Intent(addProject.this, welcome.class);
            intent.putExtra("User", user);
            startActivity(intent);
        }
        public String getPath(Uri uri)
        {
            String[] projection = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
            if (cursor == null) return null;
            int column_index =             cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            String s=cursor.getString(column_index);
            cursor.close();
            return s;
        }
    }


    public void selectPicture(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 3);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            img = data.getData();
            filepath = img.getPath();
            selected = true;
        }
    }


}
