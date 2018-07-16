package com.test.peterphchen.junittest;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

public class MyjobService extends JobService {

    private static final String TAG = "MyjobService";
    private String NOTIFICATION_CONTENT = "This notification should appear every 60 seconds," +
            "if this is not happening please contact programmer who designed it";
    @Override
    public boolean onStartJob(JobParameters job) {
        NotificationChannel channel = new NotificationChannel("mychannel","channel1",NotificationManager.IMPORTANCE_DEFAULT);
        Notification notification = new Notification.Builder(this,"mychannel")
                .setContentTitle("Test Notification")
                .setContentText(NOTIFICATION_CONTENT)
                .setSmallIcon(R.drawable.ic_notification).build();
        NotificationManager manager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        manager.createNotificationChannel(channel);
        manager.notify(1234,notification);
        jobFinished(job,true);
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters job) {
        return true;
    }
}
