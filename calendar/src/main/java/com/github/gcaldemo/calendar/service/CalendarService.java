package com.github.gcaldemo.calendar.service;

import com.github.gcaldemo.calendar.model.Calendar;
import com.github.gcaldemo.calendar.model.CalendarEvent;

import java.util.List;

public interface CalendarService {
    /**
     * Saves a new calendar event.
     *
     * @param event The calendar event to save. Must not be null.
     * @return The unique id of the created calendar event.
     */
    String save(CalendarEvent event);

    /**
     * Loads all calendars that are writable for the user from which the application received access rights.
     *
     * @return A list of all writable calendars or an empty list if there are no writable calendars.
     */
    List<Calendar> loadAllWritableCalendars();
}
