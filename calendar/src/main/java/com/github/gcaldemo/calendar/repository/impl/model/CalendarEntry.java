package com.github.gcaldemo.calendar.repository.impl.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * Represents calendarListEntry objects from the Google Calendar API. We map only the properties that are relevant to us.
 *
 * @see https://developers.google.com/google-apps/calendar/v3/reference/calendarList#resource
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public final class CalendarEntry {
    private String id;

    private String summary;

    @SuppressWarnings("unused")
    private CalendarEntry() {
        //private constructor JSON serializer
    }

    public String getId() {
        return id;
    }

    public String getSummary() {
        return summary;
    }
}
