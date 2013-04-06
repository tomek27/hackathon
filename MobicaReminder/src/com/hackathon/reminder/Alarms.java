package com.hackathon.reminder;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import com.hackathon.reminder.notifications.AbstractNotification;

public class Alarms {
    public static void create(Context context, AbstractNotification notification, int requestId, long timeInterval) {
        Intent intent = new Intent(context, AlarmReceiver.class);
        intent.setAction(AlarmReceiver.ALARM_RECIVE_ACTION);
        intent.putExtra(AlarmReceiver.SERIALIZE_ID, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, requestId, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager alarm = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarm.cancel(pendingIntent);
        alarm.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() - 1, timeInterval, pendingIntent);
    }

    public static void cancel(Context context, int requestId) {
        Intent intent = new Intent(context, AlarmReceiver.class);
        intent.setAction(AlarmReceiver.ALARM_RECIVE_ACTION);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, requestId, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager alarm = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarm.cancel(pendingIntent);
    }
}
