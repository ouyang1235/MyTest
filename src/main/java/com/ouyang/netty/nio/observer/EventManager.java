package com.ouyang.netty.nio.observer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventManager {

    public static final Map<Event, List<EventListener>> listeners = new HashMap<>();

    public EventManager(){
        for (Event value : Event.values()) {
            listeners.put(value,new ArrayList<>());
        }
    }

    public void subscribe(Event eventType,EventListener listener){
        List<EventListener> list = listeners.get(eventType);
        list.add(listener);
    }



    public void notify(Event eventType,ResultForEvent result){
        List<EventListener> list = listeners.get(eventType);
        for (EventListener eventListener : list) {
            eventListener.doEvent(result);
        }
    }


}
