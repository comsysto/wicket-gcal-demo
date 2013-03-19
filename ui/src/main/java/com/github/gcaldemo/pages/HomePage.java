package com.github.gcaldemo.pages;

import com.github.gcaldemo.calendar.CalendarException;
import com.github.gcaldemo.calendar.model.Calendar;
import com.github.gcaldemo.calendar.model.CalendarEvent;
import com.github.gcaldemo.calendar.service.CalendarService;
import com.github.gcaldemo.components.DateTextField;
import de.agilecoders.wicket.markup.html.bootstrap.behavior.BootstrapBaseBehavior;
import de.agilecoders.wicket.markup.html.bootstrap.common.NotificationPanel;
import org.apache.wicket.Component;
import org.apache.wicket.RestartResponseException;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

import java.util.Date;
import java.util.List;

@MountPath("events")
public class HomePage extends WebPage {
    @SpringBean
    private CalendarService calendarService;

    private final IModel<CalendarEvent> eventModel;

    public HomePage() {
        this.eventModel = createEventModel();
        add(bootstrap());
        add(form());
    }

    private IModel<CalendarEvent> createEventModel() {
        return Model.of(new CalendarEvent());
    }

    private Behavior bootstrap() {
        return new BootstrapBaseBehavior();
    }

    private Component form() {
        Form<CalendarEvent> form = new Form<CalendarEvent>("form", new CompoundPropertyModel<>(eventModel)) {
            @Override
            protected void onSubmit() {
                try {
                    calendarService.save(eventModel.getObject());
                    getSession().info("Event successfully created");
                    setResponsePage(HomePage.class);
                    // Never catch all exceptions. In this case Spring Security might throw UserRedirectRequiredException
                    // which has to be propagated further up the call stack
                } catch (CalendarException ex) {
                    error("Could not save event. Reason: '" + ex.getMessage() + "'.");
                }
            }
        };

        form.add(feedback());
        form.add(date());
        form.add(calendar());
        form.add(title());

        form.add(save());
        form.add(cancel());

        return form;
    }

    private Component feedback() {
        return new NotificationPanel("feedback");
    }

    private Component date() {
        DateTextField<Date> date = new DateTextField<>("date");
        date.setRequired(true);
        return date;
    }

    private Component calendar() {
        DropDownChoice<Calendar> calendar = new DropDownChoice<>("calendar", calendars(), calendarRenderer());
        calendar.setRequired(true);
        return calendar;
    }

    private IChoiceRenderer<Calendar> calendarRenderer() {
        return new IChoiceRenderer<Calendar>() {
            @Override
            public Object getDisplayValue(Calendar cal) {
                return cal.getName();
            }

            @Override
            public String getIdValue(Calendar cal, int index) {
                return cal.getId();
            }
        };
    }

    private IModel<List<Calendar>> calendars() {
        return new AbstractReadOnlyModel<List<Calendar>>() {
            @Override
            public List<Calendar> getObject() {
                return calendarService.loadAllWritableCalendars();
            }
        };
    }

    private Component title() {
        return new RequiredTextField<>("title");
    }

    private Component save() {
        return new SubmitLink("save", eventModel);
    }

    private Component cancel() {
        SubmitLink cancel = new SubmitLink("cancel") {
            private static final long serialVersionUID = 7542197118779770936L;

            @Override
            public void onSubmit() {
                throw new RestartResponseException(HomePage.class);
            }
        };
        cancel.setDefaultFormProcessing(false);
        return cancel;
    }
}
