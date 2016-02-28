package com.tomorrow.magicleon.spasmotime;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AlarmReceiver extends BroadcastReceiver {
    static int notificationId = 0;
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("AlarmReceiver","ricevuto " + intent.getAction());
        Calendar calendar = Calendar.getInstance();
        String tipo = "sconosciuto";
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context);
        mBuilder.setSmallIcon(R.drawable.ic_flag_black_24dp);
        if(intent.getAction().equals(Constants.ALARM_ACTION_PALINDROM)){
            tipo="palindromo";
        }
        if(intent.getAction().equals(Constants.ALARM_ACTION_SYMMETRIC)){
            tipo="simmetrico";
        }

        mBuilder.setContentTitle("Orario " + tipo + "!");

        String currentTime = new SimpleDateFormat("HH:mm").format(new Date());
        mBuilder.setContentText(currentTime);

        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT,"Sono le " + currentTime +", " + tipo + "\u2764");
        Intent shareIntentWithChooser = Intent.createChooser(shareIntent,"Send to");
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
//        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(shareIntentWithChooser);

        PendingIntent pendingIntent = stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);


        mBuilder.addAction(R.drawable.ic_send_black_18dp,"lullifica",pendingIntent);

        mBuilder.setContentIntent(pendingIntent);
        Notification notification = mBuilder.build();
        notification.flags = Notification.DEFAULT_LIGHTS|Notification.FLAG_AUTO_CANCEL;

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(notificationId,notification);
        notificationId++;
    }


}
