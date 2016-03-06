    package com.tomorrow.magicleon.spasmotime;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Intent;
import android.util.Log;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

    public class updateAlarmsService extends IntentService {
        private final String TAG = updateAlarmsService.class.getSimpleName();

        public updateAlarmsService() {
            super("LulloService");
        }

        @Override
        protected void onHandleIntent(Intent intent) {

            AlarmManager alarmManager = ((AlarmManager) getApplicationContext().getSystemService(ALARM_SERVICE));
            String action = intent.getAction();
            Log.d("RECEIVER", "woke up because of " + action);

            try {
                if (action.equals(Constants.PALINDROMS_CHANGED_ACTION)) {
                    boolean palindromi = intent.getBooleanExtra(Constants.SWITCH_VALUE_EXTRA, false);
                    if (palindromi) {
                        addAlarmFor(alarmManager, Constants.PALINDROMS_CHANGED_ACTION);
                    } else {
                        clearAlarmFor(alarmManager, Constants.ALARM_ACTION_PALINDROM);
                    }
                }

                if (action.equals(Constants.DOUBLE_CHANGED_ACTION)) {
                    boolean doppi = intent.getBooleanExtra(Constants.SWITCH_VALUE_EXTRA, false);
                    if (doppi) {
                        addAlarmFor(alarmManager, Constants.DOUBLE_CHANGED_ACTION);
                    } else {
                        clearAlarmFor(alarmManager, Constants.ALARM_ACTION_DOUBLE);
                    }
                }

                if (action.equals(Constants.TRIPLE_CHANGED_ACTION)) {
                    boolean tripli = intent.getBooleanExtra(Constants.SWITCH_VALUE_EXTRA, false);
                    if (tripli) {
                        addAlarmFor(alarmManager, Constants.TRIPLE_CHANGED_ACTION);
                    } else {
                        clearAlarmFor(alarmManager, Constants.ALARM_ACTION_TRIPLE);
                    }
                }

                if (action.equals(Constants.STAIR_ASC_CHANGED_ACTION)) {
                    boolean stairs_asc = intent.getBooleanExtra(Constants.SWITCH_VALUE_EXTRA, false);
                    if (stairs_asc) {
                        addAlarmFor(alarmManager, Constants.STAIR_ASC_CHANGED_ACTION);
                    } else {
                        clearAlarmFor(alarmManager, Constants.ALARM_ACTION_STAIR_ASC);
                    }
                }

                if (action.equals(Constants.STAIR_DESC_CHANGED_ACTION)) {
                    boolean stairs_desc = intent.getBooleanExtra(Constants.SWITCH_VALUE_EXTRA, false);
                    if (stairs_desc) {
                        addAlarmFor(alarmManager, Constants.STAIR_DESC_CHANGED_ACTION);
                    } else {
                        clearAlarmFor(alarmManager, Constants.ALARM_ACTION_STAIR_DESC);
                    }
                }

            } catch (Exception e) {
                Intent exceptionIntent = new Intent(Constants.SERVICE_STATUS_NOT_OK);
                sendBroadcast(exceptionIntent);

            } finally {
                Intent finalIntent = new Intent(Constants.SERVICE_STATUS_OK);
                sendBroadcast(finalIntent);
            }
        }

        public void onDestroy() {
            super.onDestroy();
            Log.d(TAG, "Destroying service");
        }

        private int reverseInt(int input) {
            int reversedInput = 0;
            if (Math.abs(input) >= 10) {
                int last_digit;
                while (input != 0) {
                    last_digit = input % 10;
                    reversedInput = reversedInput * 10 + last_digit;
                    input = input / 10;
                }
            } else {
                reversedInput = input * 10;
            }
            return reversedInput;
        }

        private void clearAlarmFor(AlarmManager alarmManager, String action) {
            Intent cancelIntent = new Intent(action);
            for (int i = 0; i < 24; i++) {
                PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), i, cancelIntent, 0);
                alarmManager.cancel(pendingIntent);
            }
        }

        private void addAlarmFor(AlarmManager alarmManager, String type) {
            Calendar spasmoTime = Calendar.getInstance();
            spasmoTime.set(Calendar.SECOND, 0);
            spasmoTime.set(Calendar.MILLISECOND, 0);
            boolean twentyFourHour = android.text.format.DateFormat.is24HourFormat(getApplicationContext());

            Log.d(TAG, "Il sistema è a 24h? " + String.valueOf(twentyFourHour));
//            Log.d(TAG,"Il tipo di azione richiesto è " + type);
            if (type.equals(Constants.PALINDROMS_CHANGED_ACTION)) {
                if (twentyFourHour) {
                    for (int i = 0; i < 24; i++) {
                        if (i < 6 || (i >= 10 && i <= 15) || i >= 20) {
                            Intent palindroIntent = new Intent(Constants.ALARM_ACTION_PALINDROM);
                            palindroIntent.putExtra(Constants.HOUR_EXTRA, i);
                            palindroIntent.putExtra(Constants.MINUTE_EXTRA, reverseInt(i));
                            PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), i, palindroIntent, PendingIntent.FLAG_CANCEL_CURRENT);
                            spasmoTime.set(Calendar.HOUR_OF_DAY, i);
                            spasmoTime.set(Calendar.MINUTE, reverseInt(i));
                            if (spasmoTime.getTimeInMillis() < System.currentTimeMillis()) {
                                spasmoTime.add(Calendar.DAY_OF_MONTH, 1);
                                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, spasmoTime.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
                                Log.d(TAG, "Settato alarm alle " + spasmoTime.get(Calendar.HOUR_OF_DAY) + ":" + spasmoTime.get(Calendar.MINUTE) + ":" + spasmoTime.get(Calendar.SECOND) + " del " + spasmoTime.get(Calendar.DAY_OF_MONTH));
                                spasmoTime.add(Calendar.DAY_OF_MONTH, -1);
                            } else {
                                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, spasmoTime.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
                                Log.d(TAG, "Settato alarm alle " + spasmoTime.get(Calendar.HOUR_OF_DAY) + ":" + spasmoTime.get(Calendar.MINUTE) + ":" + spasmoTime.get(Calendar.SECOND) + " del " + spasmoTime.get(Calendar.DAY_OF_MONTH));
                            }
                        }
                    }
                } else {
                    for (int i = 0; i < 13; i++) {
                        if ((i >= 0 && i < 6) || i >= 10) {
                            Intent palindroIntent = new Intent(Constants.ALARM_ACTION_PALINDROM);
                            palindroIntent.putExtra(Constants.HOUR_EXTRA, i);
                            palindroIntent.putExtra(Constants.MINUTE_EXTRA, reverseInt(i));
                            PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), i, palindroIntent, PendingIntent.FLAG_CANCEL_CURRENT);
                            spasmoTime.set(Calendar.HOUR, i);
                            spasmoTime.set(Calendar.MINUTE, reverseInt(i));
                            if (spasmoTime.getTimeInMillis() < System.currentTimeMillis()) {
                                spasmoTime.add(Calendar.DAY_OF_MONTH, 1);
                                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, spasmoTime.getTimeInMillis(), AlarmManager.INTERVAL_HALF_DAY, pendingIntent);
                                Log.d(TAG, "Settato alarm alle " + spasmoTime.get(Calendar.HOUR_OF_DAY) + ":" + spasmoTime.get(Calendar.MINUTE) + ":" + spasmoTime.get(Calendar.SECOND) + " del " + spasmoTime.get(Calendar.DAY_OF_MONTH));
                                spasmoTime.add(Calendar.DAY_OF_MONTH, -1);
                            } else {
                                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, spasmoTime.getTimeInMillis(), AlarmManager.INTERVAL_HALF_DAY, pendingIntent);
                                Log.d(TAG, "Settato alarm alle " + spasmoTime.get(Calendar.HOUR_OF_DAY) + ":" + spasmoTime.get(Calendar.MINUTE) + ":" + spasmoTime.get(Calendar.SECOND) + " del " + spasmoTime.get(Calendar.DAY_OF_MONTH));
                            }
                        }
                    }
                }
            }
            if (type.equals(Constants.DOUBLE_CHANGED_ACTION)) {
//                Calendar spasmoTime = Calendar.getInstance();
//                spasmoTime.set(Calendar.SECOND,0);
                if (twentyFourHour) {
                    for (int i = 0; i < 24; i++) {
                        Intent doubleIntent = new Intent(Constants.ALARM_ACTION_DOUBLE);
                        doubleIntent.putExtra(Constants.HOUR_EXTRA, i);
                        doubleIntent.putExtra(Constants.MINUTE_EXTRA, i);
                        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), i, doubleIntent, PendingIntent.FLAG_CANCEL_CURRENT);
                        spasmoTime.set(Calendar.HOUR_OF_DAY, i);
                        spasmoTime.set(Calendar.MINUTE, i);
                        if (spasmoTime.getTimeInMillis() < System.currentTimeMillis()) {
                            spasmoTime.add(Calendar.DAY_OF_MONTH, 1);
                            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, spasmoTime.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
                            Log.d(TAG, "Settato alarm alle " + spasmoTime.get(Calendar.HOUR_OF_DAY) + ":" + spasmoTime.get(Calendar.MINUTE) + ":" + spasmoTime.get(Calendar.SECOND) + " del " + spasmoTime.get(Calendar.DAY_OF_MONTH));
                            spasmoTime.add(Calendar.DAY_OF_MONTH, -1);
                        } else {
                            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, spasmoTime.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
                            Log.d(TAG, "Settato alarm alle " + spasmoTime.get(Calendar.HOUR_OF_DAY) + ":" + spasmoTime.get(Calendar.MINUTE) + ":" + spasmoTime.get(Calendar.SECOND) + " del " + spasmoTime.get(Calendar.DAY_OF_MONTH));
                        }
                    }
                } else {
                    for (int i = 0; i <= 12; i++) {
                        Intent doubleIntent = new Intent(Constants.ALARM_ACTION_DOUBLE);
                        doubleIntent.putExtra(Constants.HOUR_EXTRA, i);
                        doubleIntent.putExtra(Constants.MINUTE_EXTRA, i);
                        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), i, doubleIntent, PendingIntent.FLAG_CANCEL_CURRENT);
                        spasmoTime.set(Calendar.HOUR, i);
                        spasmoTime.set(Calendar.MINUTE, i);
                        if (spasmoTime.getTimeInMillis() < System.currentTimeMillis()) {
                            spasmoTime.add(Calendar.DAY_OF_MONTH, 1);
                            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, spasmoTime.getTimeInMillis(), AlarmManager.INTERVAL_HALF_DAY, pendingIntent);
                            Log.d(TAG, "Settato alarm alle " + spasmoTime.get(Calendar.HOUR_OF_DAY) + ":" + spasmoTime.get(Calendar.MINUTE) + ":" + spasmoTime.get(Calendar.SECOND) + " del " + spasmoTime.get(Calendar.DAY_OF_MONTH));
                            spasmoTime.add(Calendar.DAY_OF_MONTH, -1);
                        } else {
                            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, spasmoTime.getTimeInMillis(), AlarmManager.INTERVAL_HALF_DAY, pendingIntent);
                            Log.d(TAG, "Settato alarm alle " + spasmoTime.get(Calendar.HOUR_OF_DAY) + ":" + spasmoTime.get(Calendar.MINUTE) + ":" + spasmoTime.get(Calendar.SECOND) + " del " + spasmoTime.get(Calendar.DAY_OF_MONTH));
                        }
                    }
                }
            }
            if (type.equals(Constants.TRIPLE_CHANGED_ACTION)) {
//                    Calendar spasmoTime = Calendar.getInstance();
//                    spasmoTime.set(Calendar.SECOND, 0);
                for (int i = 1; i <= 5; i++) {
                    Intent tripleIntent = new Intent(Constants.ALARM_ACTION_TRIPLE);
                    tripleIntent.putExtra(Constants.HOUR_EXTRA, i);
                    tripleIntent.putExtra(Constants.MINUTE_EXTRA, i + 10 * i);
                    if (twentyFourHour) {
                        spasmoTime.set(Calendar.HOUR_OF_DAY, i);
                    } else {
                        spasmoTime.set(Calendar.HOUR, i);
                    }
                    spasmoTime.set(Calendar.MINUTE, i + 10 * i);
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), i, tripleIntent, PendingIntent.FLAG_CANCEL_CURRENT);
                    if (spasmoTime.getTimeInMillis() < System.currentTimeMillis()) {
                        spasmoTime.add(Calendar.DAY_OF_MONTH, 1);
                        if (twentyFourHour) {
                            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, spasmoTime.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
                        } else {
                            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, spasmoTime.getTimeInMillis(), AlarmManager.INTERVAL_HALF_DAY, pendingIntent);
                        }
                        Log.d(TAG, "Settato alarm alle " + spasmoTime.get(Calendar.HOUR_OF_DAY) + ":" + spasmoTime.get(Calendar.MINUTE) + ":" + spasmoTime.get(Calendar.SECOND) + " del " + spasmoTime.get(Calendar.DAY_OF_MONTH));
                        spasmoTime.add(Calendar.DAY_OF_MONTH, -1);
                    } else {
                        if (twentyFourHour) {
                            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, spasmoTime.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
                        } else {
                            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, spasmoTime.getTimeInMillis(), AlarmManager.INTERVAL_HALF_DAY, pendingIntent);
                        }
                        Log.d(TAG, "Settato alarm alle " + spasmoTime.get(Calendar.HOUR_OF_DAY) + ":" + spasmoTime.get(Calendar.MINUTE) + ":" + spasmoTime.get(Calendar.SECOND) + " del " + spasmoTime.get(Calendar.DAY_OF_MONTH));
                    }
                }
            }
            if (type.equals(Constants.STAIR_ASC_CHANGED_ACTION)){
                if(twentyFourHour){
                    //TODO
                }else{
                    //TODO
                }
            }

            if (type.equals(Constants.STAIR_DESC_CHANGED_ACTION)){
                if(twentyFourHour){
                    //TODO
                }else{
                    //TODO
                }
            }
        }
    }

