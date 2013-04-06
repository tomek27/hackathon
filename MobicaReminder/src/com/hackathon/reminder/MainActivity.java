package com.hackathon.reminder;

import com.hackathon.reminder.notifications.AbstractNotification;
import com.hackathon.reminder.notifications.MisNotification;

import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

@ContentView(R.layout.activity_main)
public class MainActivity extends RoboActivity implements OnClickListener {

	@InjectView(R.id.B1)
	Button mB1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		((Button)findViewById(R.id.B1)).setOnClickListener(this);
		((Button)findViewById(R.id.B2)).setOnClickListener(this);
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
			
			break;
		}
	}

	private void registerAlarm(){
		AbstractNotification notification = new MisNotification();
		Intent intent = new Intent(this, AlarmReceiver.class);
		intent.setAction(AlarmReceiver.ALARM_RECIVE_ACTION);
		intent.putExtra(AlarmReceiver.SERIALIZE_ID, notification);
		PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
		AlarmManager alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
		alarm.cancel(pendingIntent);
		alarm.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), AlarmManager.INTERVAL_FIFTEEN_MINUTES, pendingIntent);
		Log.e("register", "YES");
	}
}
