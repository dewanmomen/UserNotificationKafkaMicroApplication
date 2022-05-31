package com.dewan.consumer;

import com.dewan.event.Event;

public interface EventConsumer<T extends Event> {

    void consumeEvent(T event);
}

