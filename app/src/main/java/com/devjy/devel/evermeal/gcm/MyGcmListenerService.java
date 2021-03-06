package com.devjy.devel.evermeal.gcm;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;

import com.devjy.devel.evermeal.MainActivity;
import com.devjy.devel.evermeal.R;
import com.google.android.gms.gcm.GcmListenerService;

import java.net.URLDecoder;

public class MyGcmListenerService extends GcmListenerService
{

    private static final String TAG = "MyGcmListenerService";
    private static OnGcmMessageListener listener = null;

    public static void setOnGcmMessageListener(OnGcmMessageListener listener)
    {
        MyGcmListenerService.listener = listener;
    }

    @Override
    public void onMessageReceived(String from, Bundle data)
    {
        String title = URLDecoder.decode(data.getString("title"));
        String message = URLDecoder.decode(data.getString("message"));
        String type = URLDecoder.decode(data.getString("type"));

        if (listener == null || !listener.onMessageReceived(type, data))
        {
            sendNotification(title, message);
        }
    }

    private void sendNotification(String title, String message)
    {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.icon)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0, notificationBuilder.build());
    }
}