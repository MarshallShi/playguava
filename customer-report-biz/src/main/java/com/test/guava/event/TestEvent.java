package com.test.guava.event;

import com.google.common.eventbus.EventBus;

public class TestEvent {
	
	private final int message;
    
	public TestEvent(int message) {        
        this.message = message;
        System.out.println("event message:"+message);
    }
    
	public int getMessage() {
        return message;
    }
	
	public static void main(String[] args){
		EventBus eventBus = new EventBus("test");
        EventListener listener = new EventListener();
        AnotherListener alistener = new AnotherListener();

        eventBus.register(listener);
        eventBus.register(alistener);

        eventBus.post(new TestEvent(200));
	}
}
