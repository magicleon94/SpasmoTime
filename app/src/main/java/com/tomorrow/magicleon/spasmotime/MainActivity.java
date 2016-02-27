package com.tomorrow.magicleon.spasmotime;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.SystemClock;
import android.provider.Settings;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import java.util.Calendar;
import java.util.concurrent.atomic.AtomicInteger;

public class MainActivity extends AppCompatActivity {
    AlarmManager alarmManager;
    Intent intent;
    PendingIntent firstPendingIntent;
    PendingIntent secondPendingIntent;
    AtomicInteger atomicInteger;
    Switch palindromi;
    Switch simmetrici;
    Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        intent = new Intent(Constants.EXTENDED_DATA_STATUS);
        atomicInteger = new AtomicInteger();
        setContentView(R.layout.activity_main);
        startButton = (Button) findViewById(R.id.saveButton);
        palindromi = (Switch) findViewById(R.id.palindromsSwitch);
        simmetrici = (Switch) findViewById(R.id.simmetricsSwitch);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


//                int firstId = atomicInteger.incrementAndGet();
//                int secondId = atomicInteger.incrementAndGet();
//                intent.putExtra("intentTime","12:21");
//                firstPendingIntent = PendingIntent.getBroadcast(getApplicationContext(),firstId,intent,0);
//                secondPendingIntent = PendingIntent.getBroadcast(getApplicationContext(),secondId,intent,0);
//                Calendar firstLullo = Calendar.getInstance();
//                Calendar secondLullo = Calendar.getInstance();
//                firstLullo.set(Calendar.HOUR_OF_DAY,12);
//                firstLullo.set(Calendar.MINUTE,35);
//                firstLullo.set(Calendar.SECOND,0);
//                firstLullo.set(Calendar.MILLISECOND,0);
//                secondLullo.set(Calendar.HOUR_OF_DAY,12);
//                secondLullo.set(Calendar.MINUTE,36);
//                secondLullo.set(Calendar.SECOND,0);
//                secondLullo.set(Calendar.MILLISECOND,0);
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//                    alarmManager.setExact(AlarmManager.RTC_WAKEUP,firstLullo.getTimeInMillis(),firstPendingIntent);
//                    alarmManager.setExact(AlarmManager.RTC_WAKEUP,secondLullo.getTimeInMillis(),secondPendingIntent);
//                }
//
//                alarmManager.setExact(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+3000,firstPendingIntent);
//                Log.d("ALARM","settato con id " + String.valueOf(firstId));
//                Log.d("ALARM","settato con id " + String.valueOf(secondId));
            }
        });
    }
}
