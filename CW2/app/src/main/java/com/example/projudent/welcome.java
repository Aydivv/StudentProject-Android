package com.example.projudent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class welcome extends AppCompatActivity{
    private User user;
    private TextView tvName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        user = (User)getIntent().getSerializableExtra("User");

        tvName = findViewById(R.id.tvName);
        tvName.setText(user.getFirst_Name());
    }

    @Override
    public void onBackPressed() {
    }

    public void toProjects(View view) {
        Intent intent = new Intent(this,myProjects.class);
        intent.putExtra("User",user);
        startActivity(intent);
        overridePendingTransition(R.anim.right_slide_in,R.anim.right_slide_out);
    }

    public void toPreferences(View view) {
        Intent intent = new Intent(welcome.this,Preferences.class);
        intent.putExtra("User",user);
        startActivity(intent);
    }
}