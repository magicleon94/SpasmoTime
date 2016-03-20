package com.tomorrow.magicleon.spasmotime;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {
    Intent intent;
    Switch palindromi;
    Switch doppi;
    Switch tripli;
    Switch matchDate;

    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //inizializzo i riferimenti ai componenti
        palindromi = (Switch) findViewById(R.id.palindromsSwitch);
        doppi = (Switch) findViewById(R.id.doubleSwitch);
        tripli = (Switch) findViewById(R.id.tripleSwitch);
        matchDate = (Switch) findViewById(R.id.matchDateSwitch);

        //mi prendo le sharedPreferences
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        //carico i valori di checked precedentemente impostati
        palindromi.setChecked(sharedPreferences.getBoolean(getString(R.string.sharedPreferencePalindromEnabledKey),false));
        doppi.setChecked(sharedPreferences.getBoolean(getString(R.string.sharedPreferenceDoubleEnabledKey),false));
        tripli.setChecked(sharedPreferences.getBoolean(getString(R.string.sharedPreferenceTripleEnabledKey),false));
        matchDate.setChecked(sharedPreferences.getBoolean(getString(R.string.sharedPreferenceMatchDateEnabledKey),false));

        palindromi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean(getString(R.string.sharedPreferencePalindromEnabledKey), isChecked);
                editor.commit();

                intent = new Intent(getApplicationContext(),updateAlarmsService.class);
                intent.setAction(Constants.PALINDROMS_CHANGED_ACTION);
                intent.putExtra(Constants.SWITCH_VALUE_EXTRA,isChecked);
                startService(intent);
            }
        });

        doppi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean(getString(R.string.sharedPreferenceDoubleEnabledKey), isChecked);
                editor.commit();

                intent = new Intent(getApplicationContext(),updateAlarmsService.class);
                intent.setAction(Constants.DOUBLE_CHANGED_ACTION);
                intent.putExtra(Constants.SWITCH_VALUE_EXTRA,isChecked);
                startService(intent);
            }
        });

        tripli.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean(getString(R.string.sharedPreferenceTripleEnabledKey), isChecked);
                editor.commit();

                intent = new Intent(getApplicationContext(),updateAlarmsService.class);
                intent.setAction(Constants.TRIPLE_CHANGED_ACTION);
                intent.putExtra(Constants.SWITCH_VALUE_EXTRA,isChecked);
                startService(intent);
            }
        });

        matchDate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean(getString(R.string.sharedPreferenceMatchDateEnabledKey), isChecked);
                editor.commit();

                intent = new Intent(getApplicationContext(),updateAlarmsService.class);
                intent.setAction(Constants.MATCH_DATE_CHANGED_ACTION);
                intent.putExtra(Constants.SWITCH_VALUE_EXTRA,isChecked);
                startService(intent);
            }
        });

    }
}
