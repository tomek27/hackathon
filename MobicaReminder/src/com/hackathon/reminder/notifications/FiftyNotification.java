package com.hackathon.reminder.notifications;

public class FiftyNotification extends AbstractNotification {

	private static final long serialVersionUID = 1L;

	@Override
	public String getURL() {
		return "http://warsaw.mobica.com";
	}

	@Override
	public String getTitle() {
		return "50%";
	}

	@Override
	public String getText() {
		return "Please fill 50 percent for lower taxes!!";
	}

    @Override
    public int getId() {
        return 0xfffe;
    }
}