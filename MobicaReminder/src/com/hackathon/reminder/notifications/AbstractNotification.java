package com.hackathon.reminder.notifications;

import java.io.Serializable;

import com.hackathon.reminder.R;

public abstract class AbstractNotification implements Serializable {

	public abstract String getURL();
	public abstract String getTitle();
	public abstract String getText();
	public abstract int getId();

	public int getRid(){
		return R.drawable.ic_launcher;
	}
}
