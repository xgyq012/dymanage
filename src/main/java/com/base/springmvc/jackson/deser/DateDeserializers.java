package com.base.springmvc.jackson.deser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Will WM. Zhang
 * @since 2017-02-09 16:32
 */
public class DateDeserializers {

    public static class DATE extends JsonDeserializer<Date> {
        @Override
        public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            return _parser(p, ctxt, "yyyy-MM-dd");
        }
    }

    public static class TIME extends JsonDeserializer<Date> {
        @Override
        public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            return _parser(p, ctxt, "HH:mm:ss");
        }
    }

    public static class DATE_TIME extends JsonDeserializer<Date> {
        @Override
        public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            return _parser(p, ctxt, "yyyy-MM-dd HH:mm:ss");
        }
    }

    private static Date _parser(JsonParser p, DeserializationContext ctxt, String format) throws IOException {
        JsonToken t = p.getCurrentToken();
        if (t == JsonToken.VALUE_NUMBER_INT) {
            return new Date(p.getLongValue());
        }
        if (t == JsonToken.VALUE_NULL) {
            return null;
        }
        if (t == JsonToken.VALUE_STRING) {
            String dateStr = p.getText().trim();
            if (dateStr.length() == 0) {
                return null;
            }
            if ("null".equals(dateStr)) {
                return null;
            }
            try {
                return new SimpleDateFormat(format).parse(dateStr);
            } catch (ParseException e) {
                throw new IllegalArgumentException(String.format(
                        "Failed to parse Date value '%s': %s", dateStr, e.getMessage()));
            }
        }
        // [databind#381]
//        if (t == JsonToken.START_ARRAY && ctxt.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
//            p.nextToken();
//            final Date parsed = _parseDate(p, ctxt);
//            t = p.nextToken();
//            if (t != JsonToken.END_ARRAY) {
//                handleMissingEndArrayForSingle(p, ctxt);
//            }
//            return parsed;
//        }
        return (Date) ctxt.handleUnexpectedToken(Date.class, p);
    }

}
