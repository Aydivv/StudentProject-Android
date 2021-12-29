package com.example.recyclertest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView projectsRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("created");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ArrayList<Project> projects = new ArrayList<>();
        projects.add(new Project("Baking soda volcano","made a volcano using baking soda and shiz",2021,"https://www.treehugger.com/thmb/FxClPLBSTwKX6oINOBIwxBkYCLQ=/768x0/filters:no_upscale():max_bytes(150000):strip_icc():format(webp)/__opt__aboutcom__coeus__resources__content_migration__mnn__images__2011__03__Baking_Soda_Volcano-c9849e2f6b6f4f38b4fef6674dd61e03.jpg"));
        projects.add(new Project("Baking soda volcano 1","made a volcano using baking soda and shiz",2021,"https://www.treehugger.com/thmb/FxClPLBSTwKX6oINOBIwxBkYCLQ=/768x0/filters:no_upscale():max_bytes(150000):strip_icc():format(webp)/__opt__aboutcom__coeus__resources__content_migration__mnn__images__2011__03__Baking_Soda_Volcano-c9849e2f6b6f4f38b4fef6674dd61e03.jpg"));
        projects.add(new Project("Baking soda volcano 2","made a volcano using baking soda and shiz",2021,"https://www.treehugger.com/thmb/FxClPLBSTwKX6oINOBIwxBkYCLQ=/768x0/filters:no_upscale():max_bytes(150000):strip_icc():format(webp)/__opt__aboutcom__coeus__resources__content_migration__mnn__images__2011__03__Baking_Soda_Volcano-c9849e2f6b6f4f38b4fef6674dd61e03.jpg"));
        projects.add(new Project("Baking soda volcano 3","made a volcano using baking soda and shiz",2021,"https://www.treehugger.com/thmb/FxClPLBSTwKX6oINOBIwxBkYCLQ=/768x0/filters:no_upscale():max_bytes(150000):strip_icc():format(webp)/__opt__aboutcom__coeus__resources__content_migration__mnn__images__2011__03__Baking_Soda_Volcano-c9849e2f6b6f4f38b4fef6674dd61e03.jpg"));
        projects.add(new Project("Baking soda volcano 4","made a volcano using baking soda and shiz",2021,"https://www.treehugger.com/thmb/FxClPLBSTwKX6oINOBIwxBkYCLQ=/768x0/filters:no_upscale():max_bytes(150000):strip_icc():format(webp)/__opt__aboutcom__coeus__resources__content_migration__mnn__images__2011__03__Baking_Soda_Volcano-c9849e2f6b6f4f38b4fef6674dd61e03.jpg"));

        System.out.println(projects);

        projectsRV = findViewById(R.id.rvProjects);
        ProjectsRecViewAdapter adapter = new ProjectsRecViewAdapter();
        adapter.setProjects(projects);
        projectsRV.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        projectsRV.setAdapter(adapter);

    }
}