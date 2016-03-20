package com.tomorrow.magicleon.spasmotime;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("AlarmReceiver","ricevuto " + intent.getAction());
        String tipo = "sconosciuto";
        String action = intent.getAction();

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context);
        mBuilder.setSmallIcon(R.drawable.ic_flag_black_24dp);

        final NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if(action.equals(Constants.ALARM_ACTION_PALINDROM)){
            tipo="palindromo";
        }
        int hours = intent.getIntExtra(Constants.HOUR_EXTRA,0);
        int minutes = intent.getIntExtra(Constants.MINUTE_EXTRA,0);

        if(action.equals(Constants.ALARM_ACTION_DOUBLE)){
            tipo="doppio";
        }
        if(action.equals(Constants.ALARM_ACTION_TRIPLE)){
            tipo="triplo";
        }
        if(action.equals(Constants.DELETE_ACTION)){
            notificationManager.cancelAll();
            return;
        }

        mBuilder.setContentTitle("Orario " + tipo + "!");

        String hoursString = (hours<10) ? "0" + String.valueOf(hours) : String.valueOf(hours);
        String minutesString = (minutes<10) ? "0" + String.valueOf(minutes) : String.valueOf(minutes);

        String currentTime = hoursString + ":" + minutesString;
        mBuilder.setContentText(currentTime);

        Intent shareIntent = new Intent();

        Intent notifIntent = new Intent(context,MainActivity.class);
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT,"Sono le " + currentTime +", " + tipo + "\u2764");
        Intent shareIntentWithChooser = Intent.createChooser(shareIntent,"Send to");

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(notifIntent);

        PendingIntent pendingIntent = stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent pendingIntentShare = PendingIntent.getActivity(context,0,shareIntentWithChooser,PendingIntent.FLAG_UPDATE_CURRENT);

        mBuilder.addAction(R.drawable.ic_send_black_18dp,"Condividi",pendingIntentShare);

        mBuilder.setContentIntent(pendingIntent);

        Notification notification = mBuilder.build();
        notification.flags = Notification.FLAG_AUTO_CANCEL | Notification.FLAG_ONLY_ALERT_ONCE;
        notification.defaults = Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE | Notification.DEFAULT_LIGHTS;
        notificationManager.notify(0,notification);

        Log.d("AlarmReveiver","id della notifica: " + String.valueOf(0));
    }


}
