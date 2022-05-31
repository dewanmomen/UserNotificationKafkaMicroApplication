package com.dewan.eventhandler;

import com.dewan.event.Event;

public interface EventHandler<T extends Event, R extends Event> {

    R handleEvent(T event);
}