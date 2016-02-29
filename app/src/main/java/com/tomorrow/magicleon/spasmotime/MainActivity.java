package com.tomorrow.magicleon.spasmotime;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

//TODO trovare un aggettivo migliore di "doppi"

public class MainActivity extends AppCompatActivity {
    Intent intent;
    Switch palindromi;
    Switch doppi;
    Button startButton;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //inizializzo i riferimenti ai componenti
        startButton = (Button) findViewById(R.id.saveButton);
        palindromi = (Switch) findViewById(R.id.palindromsSwitch);
        doppi = (Switch) findViewById(R.id.doubleSwitch);

        //mi prendo le sharedPreferences
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        //carico i valori di checked precedentemente impostati
        palindromi.setChecked(sharedPreferences.getBoolean(getString(R.string.sharedPreferencePalindromEnabledKey),false));
        doppi.setChecked(sharedPreferences.getBoolean(getString(R.string.sharedPreferenceDoubleEnabledKey),false));

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intent = new Intent(getApplicationContext(),updateAlarmsService.class);
                intent.putExtra(Constants.SWITCH_PALINDROMS_EXTRA,palindromi.isChecked());
                intent.putExtra(Constants.SWITCH_SYMMETRIC_EXTRA, doppi.isChecked());
                startService(intent);
            }
        });
    }
}
