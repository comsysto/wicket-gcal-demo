package com.github.gcaldemo.calendar.repository.impl.model;

import com.github.gcaldemo.calendar.repository.impl.serializer.EventDateTimeDeserializer;
import com.github.gcaldemo.calendar.repository.impl.serializer.EventDateTimeSerializer;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;

/**
 * Represents timestamps for events. Currently only all-day events are supported.
 */
public final class EventDateTime {
    @JsonSerialize(using = EventDateTimeSerializer.class)
    @JsonDeserialize(using = EventDateTimeDeserializer.class)
    private Date date;

    @SuppressWarnings("unused")
    private EventDateTime() {
        //private constructor for JSON serializer
    }


    public EventDateTime(Date date) {
        this.date = new Date(date.getTime());
    }

    public Date getDate() {
        return new Date(date.getTime());
    }
}
