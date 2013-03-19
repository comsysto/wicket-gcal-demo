package com.github.gcaldemo.calendar.repository.impl.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * Represents events from the Google Calendar API.
 *
 * @see https://developers.google.com/google-apps/calendar/v3/reference/events
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public final class Event {
    private String id;

    private String summary;

    private EventDateTime start;

    private EventDateTime end;

    @SuppressWarnings("unused")
    private Event() {
        //private constructor for JSON serializer
    }

    //clients never need to set the id, hence we provide no such constructor
    public Event(EventDateTime start, EventDateTime end, String summary) {
        this.start = start;
        this.end = end;
        this.summary = summary;
    }

    public String getId() {
        return id;
    }

    public String getSummary() {
        return summary;
    }

    public EventDateTime getStart() {
        return start;
    }

    public EventDateTime getEnd() {
        return end;
    }
}
