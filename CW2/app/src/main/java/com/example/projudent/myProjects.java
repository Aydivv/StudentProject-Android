package com.example.projudent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class myProjects extends AppCompatActivity {
    private RecyclerView projectsRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_projects);

        projectsRV = findViewById(R.id.rvProjects);

        ArrayList<Project> projects = new ArrayList<>();
        projects.add(new Project("Baking soda volcano","made a volcano using baking soda and shasdfadsfadsfadsfasfasfcvcascxdsacujgabsdkuycgbakud fakluisdy fkauid sfuksd fkuas ygfbkus dfgukafgysa fgsdaf ukfds iasdz",2021,"https://www.treehugger.com/thmb/FxClPLBSTwKX6oINOBIwxBkYCLQ=/768x0/filters:no_upscale():max_bytes(150000):strip_icc():format(webp)/__opt__aboutcom__coeus__resources__content_migration__mnn__images__2011__03__Baking_Soda_Volcano-c9849e2f6b6f4f38b4fef6674dd61e03.jpg"));
        projects.add(new Project("Baking soda volcano 1","made a volcano using baking soda and sh2iz",2021,"https://www.treehugger.com/thmb/FxClPLBSTwKX6oINOBIwxBkYCLQ=/768x0/filters:no_upscale():max_bytes(150000):strip_icc():format(webp)/__opt__aboutcom__coeus__resources__content_migration__mnn__images__2011__03__Baking_Soda_Volcano-c9849e2f6b6f4f38b4fef6674dd61e03.jpg"));
        projects.add(new Project("Baking soda volcano 2","made a volcano using baking soda and shi3z",2021,"https://www.treehugger.com/thmb/FxClPLBSTwKX6oINOBIwxBkYCLQ=/768x0/filters:no_upscale():max_bytes(150000):strip_icc():format(webp)/__opt__aboutcom__coeus__resources__content_migration__mnn__images__2011__03__Baking_Soda_Volcano-c9849e2f6b6f4f38b4fef6674dd61e03.jpg"));
        projects.add(new Project("Baking soda volcano 3","made a volcano using baking soda and shi4z",2021,"https://www.treehugger.com/thmb/FxClPLBSTwKX6oINOBIwxBkYCLQ=/768x0/filters:no_upscale():max_bytes(150000):strip_icc():format(webp)/__opt__aboutcom__coeus__resources__content_migration__mnn__images__2011__03__Baking_Soda_Volcano-c9849e2f6b6f4f38b4fef6674dd61e03.jpg"));
        projects.add(new Project("Baking soda volcano 4","made a volcano using baking soda and s5hiz",2021,"https://www.treehugger.com/thmb/FxClPLBSTwKX6oINOBIwxBkYCLQ=/768x0/filters:no_upscale():max_bytes(150000):strip_icc():format(webp)/__opt__aboutcom__coeus__resources__content_migration__mnn__images__2011__03__Baking_Soda_Volcano-c9849e2f6b6f4f38b4fef6674dd61e03.jpg"));

        ProjectsRecViewAdapter adapter = new ProjectsRecViewAdapter(this);
        adapter.setProjects(projects);

        projectsRV.setAdapter(adapter);
        projectsRV.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.left_slide_in,R.anim.left_slide_out);
    }

    public void toWelcome(View view) {
        Intent intent = new Intent(this, welcome.class);
        startActivity(intent);
        overridePendingTransition(R.anim.left_slide_in,R.anim.left_slide_out);
    }

    public void toAddPJ(View view) {
        Intent intent = new Intent(this, addProject.class);
        startActivity(intent);
    }
}