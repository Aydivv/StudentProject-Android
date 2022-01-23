package com.example.projudent;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.TransitionManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ProjectsRecViewAdapter extends RecyclerView.Adapter<ProjectsRecViewAdapter.ViewHolder> {

    private ArrayList<Project> projects = new ArrayList<>();
    private Context context;
    private User user;

    public ProjectsRecViewAdapter(Context context, User usr) {
        this.context = context;
        user = usr;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.project_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        //Data in Card view
        holder.tvTitle.setText(projects.get(position).getTitle());
        holder.tvYear.setText(String.valueOf(projects.get(position).getYear()));
        holder.tvDesc.setText(projects.get(position).getDesc());


        //Expansion of Card view
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Project project = projects.get(position);
                project.setExpanded(!project.getExpanded());
                notifyItemChanged(position);
            }
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                builder1.setMessage("Are you sure you want to delete this project?");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                deletePJ(projects.get(position).getProjectID());
                                Intent intent = new Intent(context.getApplicationContext(),welcome.class);
                                intent.putExtra("User",user);
                                context.startActivity(intent);
                            }
                        });

                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();

            }
        });
        if (projects.get(position).getExpanded()){
            TransitionManager.beginDelayedTransition(holder.parent);
            holder.hiddenRL.setVisibility(View.VISIBLE);
            holder.down.setVisibility(View.GONE);
        } else{
            TransitionManager.beginDelayedTransition(holder.parent);
            holder.hiddenRL.setVisibility(View.GONE);
            holder.down.setVisibility(View.VISIBLE);
        }

        String base64ImageString = projects.get(position).getImage();
        Glide.with(context)
                .asBitmap()
                .load(Base64.decode(base64ImageString, Base64.DEFAULT))
                .into(holder.ivThumbnail);

    }

    @Override
    public int getItemCount() {
        return projects.size();
    }

    public void setProjects(ArrayList<Project> projects) {
        this.projects = projects;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tvTitle, tvYear, tvDesc;
        private CardView parent;
        private ImageView ivThumbnail;
        private RelativeLayout hiddenRL;
        private ImageView down,up;
        private TextView btnDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parent = itemView.findViewById(R.id.parent);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvYear = itemView.findViewById(R.id.tvYear);
            tvDesc = itemView.findViewById(R.id.tvDesc);
            ivThumbnail = itemView.findViewById(R.id.ivThumbnail);
            hiddenRL = itemView.findViewById(R.id.hiddenRL);
            down = itemView.findViewById(R.id.btnDownArrow);
            up = itemView.findViewById(R.id.btnUpArrow);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }

    public void deletePJ(int ID){
        String url = "http://web.socem.plymouth.ac.uk/COMP2000/api/students/" + String.valueOf(ID);


        RequestQueue queue = Volley.newRequestQueue(context.getApplicationContext());
        StringRequest request = new StringRequest(Request.Method.DELETE, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Toast.makeText(context.getApplicationContext(),"Project deleted.",Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context.getApplicationContext(),"error",Toast.LENGTH_SHORT).show();
                    }
                }
        );
        queue.add(request);
    }
}














