package com.tomorrow.magicleon.spasmotime;

public class Constants {

    //messaggio da mandare in broadcast, probabilmente è inutile
    public static final String BROADCAST_ACTION = "com.tomorrow.magicleon.spasmotime.BROADCAST";

    //messaggio da sparare con l'alarm per i palindromi
    public static final String ALARM_ACTION_PALINDROM = "com.tomorrow.magicleon.spasmotime.ALARM_PALINDROM";

    //messaggio da sparare con l'alarm per i doppi
    public static final String ALARM_ACTION_DOUBLE = "com.tomorrow.magicleon.spasmotime.ALARM_DOUBLE";

    //messaggio da sparare con l'alarm per i tripli
    public static final String ALARM_ACTION_TRIPLE = "com.tomorrow.magicleon.spasmotime.ALARM_TRIPLE";

    //messaggio da sparare con l'alarm per la scala decrescente
    public static final String ALARM_ACTION_STAIR_DESC = "com.tomorrow.magicleon.spasmotime.ALARM_STAIR_DESC";

    //messaggio da sparare con l'alarm per la scala crescente
    public static final String ALARM_ACTION_STAIR_ASC = "com.tomorrow.magicleon.spasmotime.ALARM_STAIR_ASC";

    //avvia il service, probabilmente è inutile
    public static final String SERVICE_START_ACTION = "com.tomorrow.magicleon.spasmotime.START_SERVICE";

    //esiti del lavoro del service
    public static final String SERVICE_STATUS_OK = "com.tomorrow.magicleon.spasmotime.STATUS_OK";
    public static final String SERVICE_STATUS_NOT_OK = "com.tomorrow.magicleon.spasmotime.STATUS_NOT_OK";

    //extras per il service
    public static final String SWITCH_VALUE_EXTRA = "com.tomorrow.magicleon.spasmotime.VALUE_EXTRA";

    //Ora e minuto da mettere nell'intent
    public static final String HOUR_EXTRA = "com.tomorrow.magicleon.spasmotime.HOUR_EXTRA";
    public static final String MINUTE_EXTRA = "com.tomorrow.magicleon.spasmotime.MINUTE_EXTRA";

    //Identificare l'opzione che è cambiata
    public static final String PALINDROMS_CHANGED_ACTION = "com.tomorrow.magicleon.spasmotime.PALINDROM_CHANGED_ACTION";
    public static final String DOUBLE_CHANGED_ACTION = "com.tomorrow.magicleon.spasmotime.DOUBLE_CHANGED_ACTION";
    public static final String TRIPLE_CHANGED_ACTION = "com.tomorrow.magicleon.spasmotime.TRIPLE_CHANGED_ACTION";
    public static final String STAIR_DESC_CHANGED_ACTION = "com.tomorrow.magicleon.spasmotime.STAIR_DESC_CHANGED_ACTION";
    public static final String STAIR_ASC_CHANGED_ACTION = "com.tomorrow.magicleon.spasmotime.STAIR_ASC_CHANGED_ACTION";
}
