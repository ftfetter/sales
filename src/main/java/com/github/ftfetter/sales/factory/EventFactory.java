package com.github.ftfetter.sales.factory;

import com.github.ftfetter.sales.factory.event.CreateEvent;
import com.github.ftfetter.sales.factory.event.DeleteEvent;
import com.github.ftfetter.sales.factory.event.DirectoryEvent;
import com.github.ftfetter.sales.factory.event.ModifyEvent;

import java.util.stream.Stream;

public class EventFactory {

    private final CreateEvent createEvent;
    private final ModifyEvent modifyEvent;
    private final DeleteEvent deleteEvent;

    public EventFactory(String directoryPath) {
        this.createEvent = new CreateEvent(directoryPath);
        this.modifyEvent = new ModifyEvent(directoryPath);
        this.deleteEvent = new DeleteEvent(directoryPath);
    }

    public Stream<DirectoryEvent> get() {
        return Stream.of(createEvent, modifyEvent, deleteEvent);
    }

}
