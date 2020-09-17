package com.zr.corningrey.test;

public class Notification {
    private MessageSender messageSender;

    public Notification(MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    public void sendMessage(String cellphone, String message) {
        this.messageSender.send(cellphone, message);
    }
}

interface MessageSender {
    void send(String cellphone, String message);
}

// 短信发送类
class SmsSender implements MessageSender {
    @Override
    public void send(String cellphone, String message) {
        //....
    }
}

// 站内信发送类
class InboxSender implements MessageSender {
    @Override
    public void send(String cellphone, String message) {
        //....
    }
}

class TestCode {
    public static void main(String[] args) {
        //使用Notification
        MessageSender messageSender = new SmsSender();
        Notification notification = new Notification(messageSender);
    }
}
