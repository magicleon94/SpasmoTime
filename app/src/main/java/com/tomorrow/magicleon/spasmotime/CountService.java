package com.tomorrow.magicleon.spasmotime;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import java.sql.Time;

/**
 * Created by magicleon on 23/02/16.
 */
public class CountService extends IntentService {
    boolean looping = true;

    public CountService(){
        super("LulloService");
    }

    @Override
    public void onStart(Intent intent, int startId) {
        Log.d("Lullinino","starting");
        super.onStart(intent, startId);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d("Lullinino", "handling");
        looping = true;
       // Log.d("LULLINO","looping true");
        while (looping) {
            Intent localIntent = new Intent(Constants.BROADCAST_ACTION);
            LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(localIntent);
        //    Log.d("LULLINO","intent sent");
            SystemClock.sleep(1000);
        }
    }
    public void onDestroy() {
        super.onDestroy();
        looping=false;
        Log.d("LULLINO","looping false");

    }

}

