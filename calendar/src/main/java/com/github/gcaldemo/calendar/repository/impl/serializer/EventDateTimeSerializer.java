package com.github.gcaldemo.calendar.repository.impl.serializer;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Simple serializer for Date with a custom date format.
 */
public final class EventDateTimeSerializer extends JsonSerializer<Date> {
    @Override
    public void serialize(Date value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        SimpleDateFormat formatter = new SimpleDateFormat(Constants.DATE_FORMAT);
        String formattedDate = formatter.format(value);
        jgen.writeString(formattedDate);
    }
}
