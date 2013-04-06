package com.hackathon.reminder.strategies;

import android.content.Context;
import android.content.SharedPreferences;

public interface AlarmPreferenceStrategy {
    public boolean accept(String key);
    public boolean process(Context context, SharedPreferences sharedPreferences, String key);
}
