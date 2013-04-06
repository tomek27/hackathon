package com.hackathon.reminder.notifications;

public class MisNotification extends AbstractNotification {

	/**
	 * Auto generated
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String getURL() {
		return "http://mis.mobica.com";
	}

	@Override
	public String getTitle() {
		return "MIS";
	}

	@Override
	public String getText() {
		return "Mobica Information System";
	}
}