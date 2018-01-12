package com.base.orm.core.search;



public enum Operator {

    EQ, NEQ, LIKE, STARTWITH, ENDWITH, GT, LT, GTE, LTE, IN, NIN, ISN, ISNN;

    public static boolean contains(String value) {
        try {
            Operator.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }

}
