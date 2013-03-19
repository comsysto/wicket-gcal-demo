package com.github.gcaldemo.calendar.model;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

import java.io.Serializable;
import java.util.Date;

public final class CalendarEvent implements Serializable {
    private Calendar calendar;
    private Date date;
    private String title;

    /**
     * Creates an empty calendar event.
     */
    public CalendarEvent() {
        setDate(new Date());
    }

    public void setCalendar(Calendar calendar) {
        Preconditions.checkNotNull(calendar, "'calendar' is mandatory");
        this.calendar = calendar;
    }

    public void setDate(Date date) {
        Preconditions.checkNotNull(date, "'date' is mandatory");
        this.date = new Date(date.getTime());
    }

    public void setTitle(String title) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(title), "'title' must not be empty");
        this.title = title;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public Date getDate() {
        return new Date(date.getTime());
    }

    public String getTitle() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CalendarEvent that = (CalendarEvent) o;

        if (calendar != null ? !calendar.equals(that.calendar) : that.calendar != null) return false;
        if (!date.equals(that.date)) return false;
        if (!title.equals(that.title)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (calendar != null) ? calendar.hashCode() : 0;
        result = 31 * result + date.hashCode();
        result = 31 * result + title.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "CalendarEvent{" +
                "calendar='" + calendar + '\'' +
                ", date=" + date +
                ", title='" + title + '\'' +
                '}';
    }
}
