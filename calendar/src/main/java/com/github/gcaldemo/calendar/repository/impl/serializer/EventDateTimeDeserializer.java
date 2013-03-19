package com.github.gcaldemo.calendar.repository.impl.serializer;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Simple deserializer for Date with a custom date format.
 */
public final class EventDateTimeDeserializer extends JsonDeserializer<Date> {
    @Override
    public Date deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        SimpleDateFormat formatter = new SimpleDateFormat(Constants.DATE_FORMAT);
        try {
            return formatter.parse(jp.getText());
        } catch (ParseException e) {
            throw new JsonParseException("Could not parse date", jp.getTokenLocation(), e);
        }
    }
}
