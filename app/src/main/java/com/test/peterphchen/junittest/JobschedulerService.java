package com.test.peterphchen.junittest;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

public class JobschedulerService extends JobService {

    private static final String TAG = "JobschedulerService";

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        //Toast.makeText(this, "Job activated", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onStartJob: Job start");
        NotificationChannel channel = new NotificationChannel("mychannel","channel1", NotificationManager.IMPORTANCE_DEFAULT);
//        Notification notification = new Notification.Builder(this,"mychannel")
//                .setContentTitle("Test Notification")
//                .setContentText("This notification should show up every fifteen minutes")
//                .setSmallIcon(R.drawable.ic_notification).build();
        Notification notification = new NotificationCompat.Builder(this,"mychannel").
                setContentTitle("Test Notification").
                setContentText("This notification should show up every fifteen minutes").
                setSmallIcon(R.drawable.ic_notification).
                setAutoCancel(true).build();
        NotificationManager manager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        manager.createNotificationChannel(channel);
        manager.notify(1234,notification);
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        Log.d(TAG, "onStopJob: Job end");
        return false;
    }
}
