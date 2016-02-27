package com.tomorrow.magicleon.spasmotime;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class ServiceStatusReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String result = intent.getAction();
        if(result.equals(Constants.SERVICE_STATUS_OK)){
            Toast.makeText(context,"Modifiche apportate con successo!",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context,"Si è verificato un errore nell'apportare le modifiche",Toast.LENGTH_SHORT).show();
        }
    }
}
