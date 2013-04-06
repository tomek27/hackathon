package com.hackathon.reminder.notifications;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationCompat.Builder;

public class NotificationHelper {
	private static final int REQUEST_CODE   	= 0xFFFE;
	
	private NotificationHelper(){}

	public static void ShowNotification(Context context, AbstractNotification notification){
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setData(Uri.parse(notification.getURL()));
		PendingIntent contentIntent = PendingIntent.getActivity(context, NotificationHelper.REQUEST_CODE, intent, 0);
		Builder mBuilder = new NotificationCompat.Builder(context)
		    .setSmallIcon(notification.getRid())
		    .setContentTitle(notification.getTitle())
		    .setContentText(notification.getText())
		    .setContentIntent(contentIntent)
		    .setWhen(System.currentTimeMillis())
		    .setAutoCancel(true);
		NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		notificationManager.notify(notification.getId(), mBuilder.build());
	}
}
