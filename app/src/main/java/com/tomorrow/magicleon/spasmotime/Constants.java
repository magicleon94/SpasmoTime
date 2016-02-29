package com.tomorrow.magicleon.spasmotime;

public class Constants {

    //messaggio da mandare in broadcast, probabilmente è inutile
    public static final String BROADCAST_ACTION = "com.tomorrow.magicleon.spasmotime.BROADCAST";

    //messaggio da sparare con l'alarm per i palindromi
    public static final String ALARM_ACTION_PALINDROM = "com.tomorrow.magicleon.spasmotime.ALARM_PALINDROM";

    //messaggio da sparare con l'alarm per i doppi
    public static final String ALARM_ACTION_DOUBLE = "com.tomorrow.magicleon.spasmotime.ALARM_SYMMETRIC";

    //avvia il service, probabilmente è inutile
    public static final String SERVICE_START_ACTION = "com.tomorrow.magicleon.spasmotime.START_SERVICE";

    //esiti del lavoro del service
    public static final String SERVICE_STATUS_OK = "com.tomorrow.magicleon.spasmotime.STATUS_OK";
    public static final String SERVICE_STATUS_NOT_OK = "com.tomorrow.magicleon.spasmotime.STATUS_NOT_OK";


    //extras per il service
    public static final String SWITCH_PALINDROMS_EXTRA = "com.tomorrow.magicleon.spasmotime.PALINDROMS_EXTRA";
    public static final String SWITCH_SYMMETRIC_EXTRA = "com.tomorrow.magicleon.spasmotime.SYMMETRIC_EXTRA";
}
