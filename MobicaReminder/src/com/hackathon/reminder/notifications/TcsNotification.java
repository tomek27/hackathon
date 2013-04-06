package com.hackathon.reminder.notifications;

public class TcsNotification extends AbstractNotification {
    private static final long serialVersionUID = 1L;

    @Override
    public String getURL() {
        return "http://wiki.mobica.com";
    }

    @Override
    public String getTitle() {
        return "Travel Cost Settlement";
    }

    @Override
    public String getText() {
        return "Please fill the travel cost settlement and send to admin team.";
    }

    @Override
    public int getId() {
        return 0xfffd;
    }
}
