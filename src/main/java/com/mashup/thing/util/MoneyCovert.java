package com.mashup.thing.util;

public class MoneyCovert {

    private MoneyCovert() {
    }

    public static String toKoreaUnit(Long num) {
        if (Unit.BILLION.checkUnit(num)) {
            return Unit.BILLION.covertUnit(num);
        }
        if (Unit.TEN_THOUSAND.checkUnit(num)) {
            return Unit.TEN_THOUSAND.covertUnit(num);
        }
        if (Unit.THOUSAND.checkUnit(num)) {
            return Unit.THOUSAND.covertUnit(num);
        }

        return num.toString();
    }

}
