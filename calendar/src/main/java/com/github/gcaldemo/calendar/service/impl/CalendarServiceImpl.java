package com.github.gcaldemo.calendar.service.impl;

import com.github.gcaldemo.calendar.model.Calendar;
import com.github.gcaldemo.calendar.model.CalendarEvent;
import com.github.gcaldemo.calendar.repository.CalendarRepository;
import com.github.gcaldemo.calendar.repository.MinimumAccessRole;
import com.github.gcaldemo.calendar.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This simple implementation does not necessarily justify a separate service layer. It is very likely though that
 * you need a separate service layer in a 'real' application.
 */
@SuppressWarnings("unused") //instantiated by dependency injection container
@Service
public class CalendarServiceImpl implements CalendarService {
    private final CalendarRepository calendarRepository;

    @Autowired
    public CalendarServiceImpl(CalendarRepository calendarRepository) {
        this.calendarRepository = calendarRepository;
    }

    @Override
    public String save(CalendarEvent event) {
        return calendarRepository.save(event);
    }

    @Override
    public List<Calendar> loadAllWritableCalendars() {
        return calendarRepository.loadCalendars(MinimumAccessRole.WRITE);
    }
}
