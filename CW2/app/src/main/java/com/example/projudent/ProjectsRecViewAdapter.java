package com.example.projudent;

import android.content.Context;
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

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ProjectsRecViewAdapter extends RecyclerView.Adapter<ProjectsRecViewAdapter.ViewHolder> {

    private ArrayList<Project> projects = new ArrayList<>();
    private Context context;

    public ProjectsRecViewAdapter(Context context) {
        this.context = context;
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
                Toast.makeText(context,projects.get(position).getTitle() + "Selected", Toast.LENGTH_SHORT).show();
                Project project = projects.get(position);
                project.setExpanded(!project.getExpanded());
                notifyItemChanged(position);
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

        /*Glide.with(context)
                .asBitmap()
                .load(Base64.decode(base64ImageString, Base64.DEFAULT))
                .into(holder.ivThumbnail);
        */


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
        }
    }
}














