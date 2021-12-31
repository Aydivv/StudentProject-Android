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
        holder.tvTitle.setText(projects.get(position).getTitle());
        holder.tvYear.setText(String.valueOf(projects.get(position).getYear()));
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,projects.get(position).getTitle() + "Selected", Toast.LENGTH_SHORT).show();
            }
        });
        String base64ImageString = "iVBORw0KGgoAAAANSUhEUgAAABoAAAAiCAYAAABBY8kOAAAMbWlDQ1BJQ0MgUHJvZmlsZQAASImVVwdYU8kWnluSkJDQQpcSehOkE0BKCC2A9CLYCEkgocSYEFTsZVHBtYsI2NBVEcW2AmLHriyKvS8WVJR1URcbKm9CArruK9873zf3/jlz5j/lzuTeA4DmB65Eko9qAVAgLpQmRoQwRqdnMEidgAq0gQFwBuZcnkzCio+PAVAG73+XdzcAorhfdVZw/XP+v4oOXyDjAYCMhTiLL+MVQHwcALyaJ5EWAkBU6K0mF0oUeDbEulIYIMSrFDhHibcrcJYSHx6wSU5kQ3wZADUqlyvNAUDjHtQzing5kEfjM8SuYr5IDIDmcIgDeUIuH2JF7MMLCiYqcAXE9tBeAjGMBzCzvuPM+Rt/1hA/l5szhJV5DYhaqEgmyedO/T9L87+lIF8+6MMWDqpQGpmoyB/W8FbexGgFpkLcLc6KjVPUGuIPIr6y7gCgFKE8MkVpj5rwZGxYP6APsSufGxoNsQnE4eL82BiVPitbFM6BGO4WdIqokJMMsSHECwWysCSVzUbpxESVL7QhW8pmqfTnuNIBvwpfD+R5KSwV/xuhgKPixzSKhclpEFMgti4SpcZCrAGxiywvKVplM7JYyI4dtJHKExXxW0OcKBBHhCj5saJsaXiiyr60QDaYL7ZRKOLEqvC+QmFypLI+2CkedyB+mAt2WSBmpQzyCGSjYwZz4QtCw5S5Y88F4pQkFc8HSWFIonItTpHkx6vscUtBfoRCbwmxp6woSbUWTy2Em1PJj2dLCuOTlXHixbncqHhlPPgyEAPYIBQwgByOLDAR5AJRW3djN/ylnAkHXCAFOUAAT6hSM7gibWBGDK9JoBj8AZEAyIbWhQzMCkAR1H8Z0iqvziB7YLZoYEUeeApxAYgG+fC3fGCVeMhbKngCNaJ/eOfCwYPx5sOhmP/3+kHtNw0LamJUGvmgR4bmoCUxjBhKjCSGEx1wYzwQ98dj4DUYDnecifsO5vHNnvCU0E54RLhO6CDcniCaK/0hylGgA/KHq2qR9X0tcFvI6YWH4AGQHTLj+rgxcMY9oR8WHgQ9e0EtWxW3oiqMH7j/lsF3T0NlR3Ylo2QDcjDZ/seVGo4aXkMsilp/Xx9lrFlD9WYPzfzon/1d9fnwHv2jJbYQ24+dxU5g57HDWCNgYMewJqwVO6LAQ7vrycDuGvSWOBBPHuQR/cMfV+VTUUmZa51rl+tn5VyhYEqh4uCxJ0qmSkU5wkIGC74dBAyOmOcynOHu6u4GgOJdo/z7epsw8A5B9Fu/6eb9DkDAsf7+/kPfdFHHANjrA4//wW86eyYA2uoAnDvIk0uLlDpccSHAfwlNeNKMgBmwAvYwH3fgDfxBMAgDUSAOJIN0MB5WWQj3uRRMBtPBHFACysAysBpUgg1gM9gOdoF9oBEcBifAGXARXAbXwV24ezrBS9AD3oE+BEFICA2hI0aIOWKDOCHuCBMJRMKQGCQRSUcykRxEjMiR6cg8pAxZgVQim5BaZC9yEDmBnEfakdvIQ6QLeYN8QjGUiuqipqgtOgJloiw0Gk1Gx6E56CS0GJ2PLkEr0Bp0J9qAnkAvotfRDvQl2osBTB3TxywwZ4yJsbE4LAPLxqTYTKwUK8dqsHqsGT7nq1gH1o19xIk4HWfgznAHR+IpOA+fhM/EF+OV+Ha8AT+FX8Uf4j34VwKNYEJwIvgROITRhBzCZEIJoZywlXCAcBqepU7COyKRqE+0I/rAs5hOzCVOIy4mriPuJh4nthMfE3tJJJIRyYkUQIojcUmFpBLSWtJO0jHSFVIn6YOaupq5mrtauFqGmlhtrlq52g61o2pX1J6p9ZG1yDZkP3IcmU+eSl5K3kJuJl8id5L7KNoUO0oAJZmSS5lDqaDUU05T7lHeqqurW6r7qieoi9Rnq1eo71E/p/5Q/SNVh+pIZVPHUuXUJdRt1OPU29S3NBrNlhZMy6AV0pbQamknaQ9oHzToGi4aHA2+xiyNKo0GjSsarzTJmjaaLM3xmsWa5Zr7NS9pdmuRtWy12FpcrZlaVVoHtW5q9WrTtd2047QLtBdr79A+r/1ch6RjqxOmw9eZr7NZ56TOYzpGt6Kz6Tz6PPoW+ml6py5R106Xo5urW6a7S7dNt0dPR89TL1Vvil6V3hG9Dn1M31afo5+vv1R/n/4N/U8GpgYsA4HBIoN6gysG7w2HGQYbCgxLDXcbXjf8ZMQwCjPKM1pu1Gh03xg3djROMJ5svN74tHH3MN1h/sN4w0qH7Rt2xwQ1cTRJNJlmstmk1aTX1Mw0wlRiutb0pGm3mb5ZsFmu2Sqzo2Zd5nTzQHOR+SrzY+YvGHoMFiOfUcE4xeixMLGItJBbbLJos+iztLNMsZxrudvyvhXFimmVbbXKqsWqx9rcepT1dOs66zs2ZBumjdBmjc1Zm/e2drZptgtsG22f2xnaceyK7ers7tnT7IPsJ9nX2F9zIDowHfIc1jlcdkQdvRyFjlWOl5xQJ28nkdM6p/bhhOG+w8XDa4bfdKY6s5yLnOucH7rou8S4zHVpdHk1wnpExojlI86O+Orq5ZrvusX1rpuOW5TbXLdmtzfuju489yr3ax40j3CPWR5NHq89nTwFnus9b3nRvUZ5LfBq8fri7eMt9a737vKx9sn0qfa5ydRlxjMXM8/5EnxDfGf5Hvb96OftV+i3z+9Pf2f/PP8d/s9H2o0UjNwy8nGAZQA3YFNARyAjMDNwY2BHkEUQN6gm6FGwVTA/eGvwM5YDK5e1k/UqxDVEGnIg5D3bjz2DfTwUC40ILQ1tC9MJSwmrDHsQbhmeE14X3hPhFTEt4ngkITI6cnnkTY4ph8ep5fRE+UTNiDoVTY1Oiq6MfhTjGCONaR6FjooatXLUvVibWHFsYxyI48StjLsfbxc/Kf5QAjEhPqEq4WmiW+L0xLNJ9KQJSTuS3iWHJC9NvptinyJPaUnVTB2bWpv6Pi00bUVax+gRo2eMvphunC5Kb8ogZaRmbM3oHRM2ZvWYzrFeY0vG3hhnN27KuPPjjcfnjz8yQXMCd8L+TEJmWuaOzM/cOG4NtzeLk1Wd1cNj89bwXvKD+av4XYIAwQrBs+yA7BXZz3MCclbmdAmDhOXCbhFbVCl6nRuZuyH3fV5c3ra8/vy0/N0FagWZBQfFOuI88amJZhOnTGyXOElKJB2T/CatntQjjZZulSGycbKmQl34Ud8qt5f/JH9YFFhUVfRhcurk/VO0p4intE51nLpo6rPi8OJfpuHTeNNapltMnzP94QzWjE0zkZlZM1tmWc2aP6tzdsTs7XMoc/Lm/DbXde6KuX/NS5vXPN90/uz5j3+K+KmuRKNEWnJzgf+CDQvxhaKFbYs8Fq1d9LWUX3qhzLWsvOzzYt7iCz+7/Vzxc/+S7CVtS72Xrl9GXCZedmN50PLtK7RXFK94vHLUyoZVjFWlq/5aPWH1+XLP8g1rKGvkazoqYiqa1lqvXbb2c6Ww8npVSNXuapPqRdXv1/HXXVkfvL5+g+mGsg2fNoo23toUsamhxramfDNxc9Hmp1tSt5z9hflL7VbjrWVbv2wTb+vYnrj9VK1Pbe0Okx1L69A6eV3XzrE7L+8K3dVU71y/abf+7rI9YI98z4u9mXtv7Ive17Kfub/+V5tfqw/QD5Q2IA1TG3oahY0dTelN7QejDrY0+zcfOORyaNthi8NVR/SOLD1KOTr/aP+x4mO9xyXHu0/knHjcMqHl7snRJ6+dSjjVdjr69Lkz4WdOnmWdPXYu4Nzh837nD15gXmi86H2xodWr9cBvXr8daPNua7jkc6npsu/l5vaR7UevBF05cTX06plrnGsXr8deb7+RcuPWzbE3O27xbz2/nX/79Z2iO313Z98j3Cu9r3W//IHJg5rfHX7f3eHdceRh6MPWR0mP7j7mPX75RPbkc+f8p7Sn5c/Mn9U+d39+uCu86/KLMS86X0pe9nWX/KH9R/Ur+1e//hn8Z2vP6J7O19LX/W8WvzV6u+0vz79aeuN7H7wreNf3vvSD0YftH5kfz35K+/Ssb/Jn0ueKLw5fmr9Gf73XX9DfL+FKuQOfAhgcaHY2AG+2AUBLB4AO+zbKGGUvOCCIsn8dQOA/YWW/OCDeANTD7/eEbvh1cxOAPVtg+wX5NWGvGk8DINkXoB4eQ0MlsmwPdyUXFfYphAf9/W9hz0ZaCcCXZf39fTX9/V82w2Bh73hcrOxBFUKEPcNGzpesgizwb0TZn36X4493oIjAE/x4/xeo+ZDnm0ImhgAAAIplWElmTU0AKgAAAAgABAEaAAUAAAABAAAAPgEbAAUAAAABAAAARgEoAAMAAAABAAIAAIdpAAQAAAABAAAATgAAAAAAAACQAAAAAQAAAJAAAAABAAOShgAHAAAAEgAAAHigAgAEAAAAAQAAABqgAwAEAAAAAQAAACIAAAAAQVNDSUkAAABTY3JlZW5zaG908+/LXAAAAAlwSFlzAAAWJQAAFiUBSVIk8AAAAdRpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IlhNUCBDb3JlIDYuMC4wIj4KICAgPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4KICAgICAgPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIKICAgICAgICAgICAgeG1sbnM6ZXhpZj0iaHR0cDovL25zLmFkb2JlLmNvbS9leGlmLzEuMC8iPgogICAgICAgICA8ZXhpZjpQaXhlbFlEaW1lbnNpb24+MzQ8L2V4aWY6UGl4ZWxZRGltZW5zaW9uPgogICAgICAgICA8ZXhpZjpQaXhlbFhEaW1lbnNpb24+MjY8L2V4aWY6UGl4ZWxYRGltZW5zaW9uPgogICAgICAgICA8ZXhpZjpVc2VyQ29tbWVudD5TY3JlZW5zaG90PC9leGlmOlVzZXJDb21tZW50PgogICAgICA8L3JkZjpEZXNjcmlwdGlvbj4KICAgPC9yZGY6UkRGPgo8L3g6eG1wbWV0YT4KYve9/QAAABxpRE9UAAAAAgAAAAAAAAARAAAAKAAAABEAAAARAAACbtX9I/YAAAI6SURBVEgNVJQBQuUgDERLj+56G9dz7OqhivPeQH9F+4FkMpME2vHv7WOOMY4zz5zT57jHyGoeR3yM/sYSXMdYrumsefmuzOM8E1PO8f/P36yOW6hsFbiuKwSQnfKipwg6hWRmU0OFiAGeH+e6xxdCsRgHcmUfRoHz6qxgUAoFLGkETgIf+1YbvoSRQNolt0Issc/ZCgDznCkdByUj5Hol0BijYAxD/IiCLpk2liOqEfqc+C9EipSYkB0s6RKnYvBbvBU0wWPmTOIk4bvtsMRmRXe5GPIHCTbX9OB6EVeGLIsjop1AgPQsx6nn27TG1xtntAZCEOzsl7mJcGHOJhAMN0pcLoyD9sAZYf8XK0mTQS8DazMpsAH4WxXW+psdewYVjJSblyOHnnVuhnUiglMRkPF9v3+Gb6anzWhXpNPUsroTCduDoOQhWf67IvckQSyCWXMZ2HgZdim64vT9KRATMUAkdrOAdgP2DUiF+QOHmJ15ntFuH1UtSEHwPZMIAaSS4HOQb2/fPj8xibuFflezw5kzIATcHYYl0vv3shdrtxMKOc8e9/XGRtLrOOW7QbdQA22Jtuw19XzBb/J25bH3EyQ+Pwm2DrIxqlLcJsb+7WcJRNty2DLC977gp5jvkbTohNCMymHgpo+D9GBbWRdUMny4OvPlxrCFslnvEeY4dYgFGCdTiBlNYGGoUK7uRS/yOojhRZag+XnriBMI74t45N2ST1Pb6pIXExxf9sQZalZKRiC+7E1+tfcHAAD//yXI0vUAAAIwSURBVF2UAWJCIQxDP//kzuPM3WO6OwnLSwqidfPT0iZp4dseX7cxjuMY+mDtbAefoWhzXHveGsfZFGGtpzIqThWW+qydEgxqZO0uoqqWq6VBQmECLYFdFlalmlGbElQClthKt9hPIieH6p1XsQCNo9faMiahY/DSYUhPTYV17/jZa/frTzpyM9HeAakinmi3UZXKvcfsVV41w2AtcCO6jdk6oBhqsNll7z1F52kBUzkKXoDqQh1MWc0jNYrx2u/lO0Si9gG7Xa3lUzilL6USMQUgx6RRxTfMZX2JIJDL8NHBBHInzioaQF6MtOwOSbHhyyAfawoOvRMRgsS9kVzy3N0mxkDyiXPwtDHGVBDgxKSMOm21u0bn9ttZAms0HkGoAuX8QqESfEBywzwF4+puWgDb3DxcSX9c9cLu50JxGYlYMAPKS7sytHDfyjt9UeQbmSLmkn2e7U/XewLyNDtgKgAQn7jFaEyLyCIEoT/XFazHHvrZMJzq6FK/DHgujppFKvfJwfKpF9FiFF+dsUbkirGTXXIxn5FXOBWkCJu+HcUcfX15Pznt6IPfjc3w6URnD5w6+tZUoqYx580mIfNn3Z95cQFwBxK2jxINEPPf+5Mkrz12/3pDWUle4CvZRCQXEb4PnTPgZbbVwIqAEHm6YUAw8BDSkTf1NUdFKcYGLy2G8pxRgBwkwTy1MLBxFa8zK9Hbe2Rug7t9JUDhEYFneenS2DBtROzjYuKIAHvB+Ae7EFxo8cht9gAAAABJRU5ErkJggg==";
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

        private TextView tvTitle, tvYear;
        private CardView parent;
        private ImageView ivThumbnail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parent = itemView.findViewById(R.id.parent);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvYear = itemView.findViewById(R.id.tvYear);
            ivThumbnail = itemView.findViewById(R.id.ivThumbnail);
        }
    }
}














