package com.mindtree.fsmmindtree;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.mindtree.fsmmindtree.common.RuntimeData;
import com.mindtree.fsmmindtree.view.LoginActivity;
import com.mindtree.fsmmindtree.view.TaskListFragment;

/**
 * This class will handle the push notification messages. It extends an intent
 * service, so that the processing of message is done on worker thread. It also
 * sends notification on receiving the message.
 */
public class GcmMessageHandler extends IntentService {

    String mes;
    private Handler handler;

    public GcmMessageHandler() {
        super("GcmMessageHandler");
    }

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        handler = new Handler();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Bundle extras = intent.getExtras();

        GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);
        // The getMessageType() intent parameter must be the intent you received
        // in your BroadcastReceiver.
        String messageType = gcm.getMessageType(intent);
        String message = extras.getString("msg");

        GcmBroadcastReceiver.completeWakefulIntent(intent);

        // Send notificaiton.
        sendNotification("FSM", message);
    }

    public void showToast() {
        handler.post(new Runnable() {
            public void run() {
                Toast.makeText(getApplicationContext(), mes, Toast.LENGTH_LONG)
                        .show();
            }
        });

    }

    private void sendNotification(String title, String message) {
        // prepare intent which is triggered if the
        // notification is selected
        Intent intent = null;
        if(RuntimeData.sLoginResponseObject != null) {
            intent = new Intent(this, TaskListFragment.class);
        } else {
            intent = new Intent(this, LoginActivity.class);
        }

        // use System.currentTimeMillis() to have a unique ID for the pending intent
        PendingIntent pIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent, 0);

        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        if(alarmSound == null){
            alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
            if (alarmSound == null){
                alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            }
        }

        // build notification
        // the addAction re-use the same intent to keep the example short
        Notification n = new Notification.Builder(this)
                .setContentTitle(title)
                .setSound(alarmSound)
                .setContentText(message)
                .setSmallIcon(R.drawable.icon_technician)
                .setContentIntent(pIntent)
                .setAutoCancel(true).build();

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        notificationManager.notify(0, n);
    }
}
