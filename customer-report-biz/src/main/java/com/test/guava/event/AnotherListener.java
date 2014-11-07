package com.test.guava.event;

import com.google.common.eventbus.Subscribe;

public class AnotherListener {
    public int lastMessage = 0;

    @Subscribe
    public void listen(TestEvent event) {
        lastMessage = event.getMessage();
        System.out.println("Message rec2:"+lastMessage);
    }

    public int getLastMessage() {      
        return lastMessage;
    }
}
