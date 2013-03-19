package com.github.gcaldemo.calendar;

/**
 * Base class for all calendar-related exceptions.
 */
public class CalendarException extends RuntimeException {
    public CalendarException(String message, Throwable cause) {
        super(message, cause);
    }

    public CalendarException(String message) {
        super(message);
    }
}
