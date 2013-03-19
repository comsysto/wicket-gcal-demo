package com.github.gcaldemo.calendar.repository.impl.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Collections;
import java.util.List;

/**
 * Represents CalendarList objects from the Google Calendar API. We map here only the properties that are relevant to us.
 * Please note that for the sake of simplicity we assume that a user has only a small amount of calendars so pagination
 * is not an issue. If it is for you, please also consider the parameter 'nextPageToken'.
 *
 * @see https://developers.google.com/google-apps/calendar/v3/reference/calendarList/list
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public final class CalendarList {
    private List<CalendarEntry> items;

    @SuppressWarnings("unused")
    private CalendarList() {
        //private constructor JSON serializer
    }

    public List<CalendarEntry> getItems() {
        return Collections.unmodifiableList(items);
    }
}
