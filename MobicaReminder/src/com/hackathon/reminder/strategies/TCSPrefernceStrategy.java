package com.hackathon.reminder.strategies;

import android.app.AlarmManager;
import android.content.Context;
import android.content.SharedPreferences;
import com.hackathon.reminder.Alarms;
import com.hackathon.reminder.notifications.TcsNotification;

public class TCSPrefernceStrategy implements AlarmPreferenceStrategy {
    private static final String KEY_TCS_MONTHLY = "tcs_notify_monthly_checkbox_preference";
    private static final int REQUEST_ID = 0xfffd;

    @Override
    public boolean accept(String key) {
        return key.equals(KEY_TCS_MONTHLY);
    }

    @Override
    public boolean process(Context context, SharedPreferences sharedPreferences, String key) {
        boolean checked = sharedPreferences.getBoolean(key, false);
        if(checked) {
            Alarms.create(context, new TcsNotification(), REQUEST_ID, AlarmManager.INTERVAL_DAY * 25);
        } else {
            Alarms.cancel(context, REQUEST_ID);
        }

        return true;
    }
}
