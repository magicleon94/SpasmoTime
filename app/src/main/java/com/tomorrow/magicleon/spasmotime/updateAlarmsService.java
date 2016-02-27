package com.tomorrow.magicleon.spasmotime;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class updateAlarmsService extends IntentService {
    private final String TAG = getPackageName();
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
    }
    public void onDestroy() {
        super.onDestroy();
//        looping=false;
//        Log.d("LULLINO","looping false");
    }

}

