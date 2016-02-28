    package com.tomorrow.magicleon.spasmotime;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.util.Log;

import java.util.Calendar;
import java.util.concurrent.ExecutionException;
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
        Calendar spasmoTime = Calendar.getInstance();
        Log.d(TAG,"Palindromi: " + String.valueOf(palindromi));
        Log.d(TAG,"Simmetrici: " + String.valueOf(simmetrici));
        AlarmManager alarmManager = ((AlarmManager) getApplicationContext().getSystemService(ALARM_SERVICE));
        spasmoTime.set(Calendar.SECOND,0);

        try {
            if (palindromi) {
                Intent palindroIntent = new Intent(Constants.ALARM_ACTION_PALINDROM);
                clearAlarmFor(alarmManager,Constants.ALARM_ACTION_PALINDROM);

                for(int i=0; i<24; i++){
//                    Log.d(TAG,"i=" + String.valueOf(i));
                    if(i<6 || (i>=10 && i<=15)  || i>=20) {
                        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), i, palindroIntent, 0);
                        spasmoTime.set(Calendar.HOUR_OF_DAY, i);
                        spasmoTime.set(Calendar.MINUTE, reverseInt(i));

                        if(spasmoTime.getTimeInMillis()<System.currentTimeMillis()){
                            Log.d(TAG,"Timer is in the past, adding one day, i = " + String.valueOf(i));
                            spasmoTime.roll(Calendar.DAY_OF_MONTH,1);
                        }
                        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, spasmoTime.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
//                        alarmManager.setExact(AlarmManager.RTC_WAKEUP, spasmoTime.getTimeInMillis(), pendingIntent);
                        Log.d(TAG, "Settato alarm alle " + spasmoTime.get(Calendar.HOUR_OF_DAY) + ":" + spasmoTime.get(Calendar.MINUTE) + ":" + spasmoTime.get(Calendar.SECOND));
                    }
                }
            }


            if (simmetrici) {

                Intent symmetricIntent = new Intent(Constants.ALARM_ACTION_SYMMETRIC);
                clearAlarmFor(alarmManager,Constants.ALARM_ACTION_SYMMETRIC);

                for(int i=0; i<24; i++){
//                    Log.d(TAG,"i=" + String.valueOf(i));
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), i, symmetricIntent, 0);
                    spasmoTime.set(Calendar.HOUR_OF_DAY,i);
                    spasmoTime.set(Calendar.MINUTE,i);

                    if(spasmoTime.getTimeInMillis()<System.currentTimeMillis()){
                        Log.d(TAG,"Timer is in the past, adding one day, i = " + String.valueOf(i));
                        spasmoTime.roll(Calendar.DAY_OF_MONTH,1);
                    }

                    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,spasmoTime.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);
//                    alarmManager.setExact(AlarmManager.RTC_WAKEUP,spasmoTime.getTimeInMillis(),pendingIntent);
                    Log.d(TAG, "Settato alarm alle " + spasmoTime.get(Calendar.HOUR_OF_DAY) + ":" + spasmoTime.get(Calendar.MINUTE) + ":" + spasmoTime.get(Calendar.SECOND));
                }

            }


            if (!simmetrici) {
                clearAlarmFor(alarmManager,Constants.ALARM_ACTION_SYMMETRIC);
                Log.d(TAG, "simmetrici canceled");
            }


            if (!palindromi) {
                clearAlarmFor(alarmManager,Constants.ALARM_ACTION_PALINDROM);
                Log.d(TAG, "palindromi canceled");
            }

            //una volta finito il lavoro salvo i nuovi valori
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(getString(R.string.sharedPreferencePalindromEnabledKey), palindromi);
            editor.putBoolean(getString(R.string.sharedPreferenceSymmetricEnabledKey), simmetrici);
            editor.commit();

        }catch (Exception e){
            Intent exceptionIntent = new Intent(Constants.SERVICE_STATUS_NOT_OK);
            sendBroadcast(exceptionIntent);

        }finally {
            Intent finalIntent = new Intent(Constants.SERVICE_STATUS_OK);
            sendBroadcast(finalIntent);
        }
    }
        public void onDestroy() {
            super.onDestroy();
            Log.d(TAG,"Destroying service");
        }
        //12345
        private int reverseInt (int input){
            int reversedInput = 0;
            int last_digit;
            while (input != 0) {
                last_digit = input%10;
                reversedInput = reversedInput * 10 + last_digit;
                input = input / 10;
            }
            return  reversedInput;
        }

        private void clearAlarmFor(AlarmManager alarmManager, String action) {
            Intent cancelIntent = new Intent(action);
            for (int i = 0; i < 24; i++) {
                PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), i, cancelIntent, 0);
                alarmManager.cancel(pendingIntent);
            }
        }
}

