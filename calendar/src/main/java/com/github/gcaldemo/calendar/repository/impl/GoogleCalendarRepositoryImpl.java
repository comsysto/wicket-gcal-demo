package com.github.gcaldemo.calendar.repository.impl;

import com.github.gcaldemo.calendar.CalendarException;
import com.github.gcaldemo.calendar.model.Calendar;
import com.github.gcaldemo.calendar.model.CalendarEvent;
import com.github.gcaldemo.calendar.repository.CalendarRepository;
import com.github.gcaldemo.calendar.repository.MinimumAccessRole;
import com.github.gcaldemo.calendar.repository.impl.model.CalendarEntry;
import com.github.gcaldemo.calendar.repository.impl.model.CalendarList;
import com.github.gcaldemo.calendar.repository.impl.model.Event;
import com.github.gcaldemo.calendar.repository.impl.model.EventDateTime;
import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Calendar repository implementation for Google Calendar. For details about the API
 * see https://developers.google.com/google-apps/calendar/v3/reference/
 */
@SuppressWarnings("unused") //instantiated by dependency injection container
@Component
public class GoogleCalendarRepositoryImpl implements CalendarRepository {
    private static final String BASE_URI = "https://www.googleapis.com/calendar/v3";

    //see https://developers.google.com/google-apps/calendar/v3/reference/calendarList/list
    private static final String READ_CALENDARS_URI = BASE_URI + "/users/me/calendarList?minAccessRole={minimumRole}";

    //see https://developers.google.com/google-apps/calendar/v3/reference/events/insert
    private static final String SAVE_EVENT_URI = BASE_URI + "/calendars/{calendarId}/events";

    private final OAuth2RestOperations calendarTemplate;

    @Autowired
    public GoogleCalendarRepositoryImpl(OAuth2RestOperations calendarTemplate) {
        this.calendarTemplate = calendarTemplate;
    }

    @Override
    public List<Calendar> loadCalendars(MinimumAccessRole minimumAccessRole) {
        Preconditions.checkNotNull(minimumAccessRole, "'minimumAccessRole' is required");
        CalendarList calendarList;
        try {
            calendarList = calendarTemplate.getForObject(READ_CALENDARS_URI, CalendarList.class, minimumAccessRole.getName());
        } catch (RestClientException ex) {
            throw new CalendarException("Could not load calendars", ex);
        }

        List<Calendar> calendars = new LinkedList<>();
        for (CalendarEntry calendarEntry : calendarList.getItems()) {
            calendars.add(toCalendar(calendarEntry));
        }
        return Collections.unmodifiableList(calendars);
    }

    private Calendar toCalendar(CalendarEntry calendarEntry) {
        return new Calendar(calendarEntry.getId(), calendarEntry.getSummary());
    }

    @Override
    public String save(CalendarEvent event) {
        Preconditions.checkNotNull(event, "'event' is mandatory");
        Preconditions.checkNotNull(event.getCalendar(), "'event' has to specify a calendar");

        String calendarId = event.getCalendar().getId();
        try {
            ResponseEntity<Event> response = calendarTemplate.postForEntity(SAVE_EVENT_URI, fromCalendarEvent(event), Event.class, calendarId);
            return response.getBody().getId();
        } catch (RestClientException ex) {
            throw new CalendarException("Could not save calendar event '" + event + "'", ex);
        }
    }

    private Event fromCalendarEvent(CalendarEvent event) {
        return new Event(new EventDateTime(event.getDate()), new EventDateTime(event.getDate()), event.getTitle());
    }
}
