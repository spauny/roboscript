package com.spauny.joy.roboscript;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;

/**
 *
 * @author iulian.dafinoiu
 */
public class CustomLogAppender extends AppenderSkeleton {

    private static final List<LoggingEvent> eventsList = new ArrayList<>();

    @Override
    protected void append(LoggingEvent event) {
        eventsList.add(event);
    }

    @Override
    public synchronized void close() {
        if (this.closed) {
            return;
        }
        this.closed = true;
    }

    @Override
    public boolean requiresLayout() {
        return false;
    }

    public static List<LoggingEvent> getEventsList(boolean clearLogs) {
        List<LoggingEvent> eventsListCopy = new ArrayList<>(eventsList);
        if (clearLogs) {
            eventsList.clear();
        }
        return eventsListCopy;
    }
    
}
