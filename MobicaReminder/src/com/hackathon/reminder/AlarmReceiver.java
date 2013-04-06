package com.hackathon.reminder;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.hackathon.reminder.notifications.AbstractNotification;
import com.hackathon.reminder.notifications.NotificationHelper;

public class AlarmReceiver extends BroadcastReceiver {
	
	public static final String ALARM_RECIVE_ACTION 		= "com.hackathon.reminder";
	public static final String SERIALIZE_ID 			= "abstractNotification";
	
	@Override
	public void onReceive(Context context, Intent intent) {
		Log.e(ALARM_RECIVE_ACTION, "OK");
		Bundle bundle = intent.getExtras();
		if(bundle != null){
			AbstractNotification notification = (AbstractNotification)bundle.getSerializable(SERIALIZE_ID);
			if(notification != null){
				NotificationHelper.ShowNotification(context, notification);
			}
		}
	}
}
