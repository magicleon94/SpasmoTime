    package com.tomorrow.magicleon.spasmotime;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import java.util.Calendar;
import java.util.concurrent.atomic.AtomicInteger;

    public class updateAlarmsService extends IntentService {
        private final String TAG = updateAlarmsService.class.getSimpleName();
        private AtomicInteger id = new AtomicInteger();
        public updateAlarmsService(){
        super("LulloService");
        }

    @Override
    protected void onHandleIntent(Intent intent) {
        boolean palindromi = intent.getBooleanExtra(Constants.SWITCH_PALINDROMS_EXTRA,false);
        boolean simmetrici = intent.getBooleanExtra(Constants.SWITCH_SYMMETRIC_EXTRA,false);
        Log.d(TAG,"Palindromi: " + String.valueOf(palindromi));
        Log.d(TAG,"Simmetrici: " + String.valueOf(simmetrici));
        if(palindromi){
            //TODO setta tutti gli allarmi dei palindromi
            //esempio di palindromi:
            //00:00
            //01:10
            //02:20
            //03:30
            //....
            //10:01
            //11:11
            //12:21
            //....
            //22:22
            //23:32
        }
        if(simmetrici){
            Calendar palindrotime = Calendar.getInstance();
            AlarmManager alarmManager = ((AlarmManager) getApplicationContext().getSystemService(ALARM_SERVICE));
            palindrotime.set(Calendar.HOUR,0);
            palindrotime.set(Calendar.MINUTE,0);
            palindrotime.set(Calendar.SECOND,0);

            Intent palindroIntent = new Intent(Constants.ALARM_ACTION_PALINDROM);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),id.incrementAndGet(),intent,0);

            alarmManager.cancel(pendingIntent);
            // setto la ripetizione a 61 minuti * 60 secondi * 1000 millisecondi
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,palindrotime.getTimeInMillis(),61*60*1000,pendingIntent);


            //TODO setta tutti gli allarmi dei simmetrici
            //basta partire dalle 00:00 e fare un repeating alarm ogni ora e un minuto tali da avere:
            //00:00
            //01:01
            //02:02
            //03:03
            //....
            //22:22
            //23:23
        }

        //una volta finito il lavoro salvo i nuovi valori
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(getString(R.string.sharedPreferencePalindromEnabledKey),palindromi);
        editor.putBoolean(getString(R.string.sharedPreferenceSymmetricEnabledKey),simmetrici);
        editor.commit();
    }
    public void onDestroy() {
        super.onDestroy();
//        looping=false;
        Log.d(TAG,"Destroying service");
    }

}

