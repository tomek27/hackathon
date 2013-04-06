package com.hackathon.reminder;

import java.util.Calendar;

import com.hackathon.reminder.R;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;

@ContentView(R.layout.activity_main)
public class MainActivity extends RoboActivity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		((Button)findViewById(R.id.B1)).setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.B1:
			registerAlarm();
			break;
		case R.id.B2:
			notif();
			break;
		}
	}

	private void registerAlarm(){
		Intent intent = new Intent(this, AlarmReceiver.class);
		intent.setAction(AlarmReceiver.ALARM_RECIVE_ACTION);
		PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		AlarmManager alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
		alarm.cancel(pendingIntent);
		alarm.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_FIFTEEN_MINUTES, pendingIntent);
	}
	
	private void notif(){
		/*Notification.Builder mBuilder = new NotificationCompat.Builder(this)
		    .setSmallIcon(R.drawable.ic_launcher)
		    .setContentTitle("notification")
		    .setContentText("HACKATHON!");
		NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		notificationManager.notify(666, mBuilder.build());*/
		/*NotificationManager mNM = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
		Notification notification = new Notification(R.drawable.ic_launcher, "Name", System.currentTimeMillis());
		notification.flags |= Notification.FLAG_AUTO_CANCEL;
		Intent intent = getProperIntent(true, p);
		PendingIntent contentIntent = PendingIntent.getActivity(this, 666, intent, 0);
		notification.setLatestEventInfo(this, "Name 2", contentIntent);
		mNM.notify(666, notification);*/
	}
	}
}
