package com.base.springmvc.jackson.ser;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Will WM. Zhang
 * @since 2017-02-09 17:25
 */
public class DateSerializers {

    public static class DATE extends JsonSerializer<Date> {
        @Override
        public void serialize(Date value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            _serialize(value, gen, "yyyy-MM-dd");
        }
    }
    public static class TIME extends JsonSerializer<Date> {
        @Override
        public void serialize(Date value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            _serialize(value, gen, "HH:mm:ss");
        }
    }
    public static class DATE_TIME extends JsonSerializer<Date> {
        @Override
        public void serialize(Date value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            _serialize(value, gen, "yyyy-MM-dd HH:mm:ss");
        }
    }

    private static void _serialize(Date value, JsonGenerator gen, String format)
            throws IOException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        String formattedDate = dateFormat.format(value);
        gen.writeString(formattedDate);
    }
}
