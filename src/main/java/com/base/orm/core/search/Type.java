package com.base.orm.core.search;

import org.apache.commons.lang3.math.NumberUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

public enum Type {

    String, Integer, Long, Float, Double, BigDecimal, BigInteger, Boolean, Date, Timestamp;

    public static Object convert(String str, Type type, Operator operator) {
        Object result = null;
        switch (type) {
            case Integer:
                result = NumberUtils.createInteger(str);
                break;
            case Long:
                result = NumberUtils.createLong(str);
                break;
            case Float:
                result = NumberUtils.createFloat(str);
                break;
            case Double:
                result = NumberUtils.createDouble(str);
                break;
            case BigDecimal:
                result = NumberUtils.createBigDecimal(str);
                break;
            case BigInteger:
                result = NumberUtils.createBigInteger(str);
                break;
            case Boolean:
                result = java.lang.Boolean.valueOf(str);
                break;
            case Date:
                DateTime dt = DateTime.parse(str);
                if (str.length() <= 10 && operator == Operator.LTE) {
                    dt = dt.plusDays(1);
                    dt = dt.minusMillis(1);
                }
                result = dt.toDate();
                break;
            case Timestamp:
                result = new java.sql.Timestamp(DateTime.parse(str, DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")).getMillis());
                break;
            default:
                result = str;
        }
        return result;
    }

}
