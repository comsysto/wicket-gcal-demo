package com.github.gcaldemo.calendar.repository;

import com.github.gcaldemo.calendar.model.Calendar;
import com.github.gcaldemo.calendar.model.CalendarEvent;

import java.util.List;

public interface CalendarRepository {
    /**
     * Saves a new calendar event.
     *
     * @param event The calendar event to save. Must not be null.
     * @return The unique id of the created calendar event.
     */
    String save(CalendarEvent event);

    /**
     * Loads all known calendars which satisfy the minimum access role.
     *
     * @param minimumAccessRole The minimum role that is required by the calendar. Must not be null.
     * @return An unmodifiable list of all known calendars which satisfy the minimum access role or an empty list if
     *         there are no such calendars.
     */
    List<Calendar> loadCalendars(MinimumAccessRole minimumAccessRole);
}
