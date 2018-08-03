
package com.example.student.mytasks.adapter;


import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.student.mytasks.R;
import com.example.student.mytasks.activity.TasksListActivity;
import com.example.student.mytasks.models.ModelTasks;
import com.example.student.mytasks.provaider.UserProvaider;

import java.util.Calendar;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterTasks extends RecyclerView.Adapter<AdapterTasks.MyViewHolder> {

    private final Context context;
    private final List<ModelTasks> list;


    public AdapterTasks(Context context, List<ModelTasks> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.tasks_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        final ModelTasks task = UserProvaider.getListTasks().get(position);
        holder.imageView.setImageResource(task.getImageId());
        holder.texttasks.setText(task.getTextTitle());
        holder.texttima.setText(String.valueOf(task.getTextTime()[0]) + ":" +
                String.valueOf(task.getTextTime()[1]));

        holder.textdata.setText(String.valueOf(task.getTextData()[0]) + "." +
                String.valueOf(task.getTextData()[1]) + "."
                + String.valueOf(task.getTextData()[2]));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView texttasks, textdata, texttima;
        private CircleImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.tasks_image);
            textdata = itemView.findViewById(R.id.tasks_date);
            texttasks = itemView.findViewById(R.id.tasks_task);
            texttima = itemView.findViewById(R.id.tasks_time);
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Calendar calendar = Calendar.getInstance();
                        Thread.sleep(((list.get(list.size() - 1).getTextData()[2] - (calendar.getTime().getDay() - 2)) * 86400000) +
                                ((list.get(list.size() - 1).getTextTime()[0] - calendar.getTime().getHours()) * 3600000) +
                                ((list.get(list.size() - 1).getTextTime()[1] - calendar.getTime().getMinutes()) * 60000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    NotificationChannel channel = null;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        channel = new NotificationChannel("channelId", "name", NotificationManager.IMPORTANCE_DEFAULT);
                    }
                    NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, "channelId")
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setContentTitle(list.get(list.size() - 1).getTextTitle())
                            .setContentText(" jamanakn e")
                            .setPriority(NotificationCompat.PRIORITY_DEFAULT);

                    Intent resultIntent = new Intent();
                    Intent[] intents = {resultIntent};
                    PendingIntent resultPendingIntent = PendingIntent.getActivities(context, 0, intents, 0);
                    mBuilder.setContentIntent(resultPendingIntent);
                    NotificationManager notificationManager = null;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        notificationManager = ((TasksListActivity) context).getSystemService(NotificationManager.class);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            notificationManager.createNotificationChannel(channel);
                        }
                        notificationManager.notify(0, mBuilder.build());
                    }

                }
            });
            thread.start();

        }
    }
}






