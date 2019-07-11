package com.mashup.thing.util;

public enum Unit {
    BILLION(100000000, "억"),
    TEN_THOUSAND(10000, "만"),
    THOUSAND(1000, "천");

    private Integer unit;
    private String koreaUnit;

    Unit(Integer unit, String koreaUnit) {
        this.unit = unit;
        this.koreaUnit = koreaUnit;
    }

    public Boolean checkUnit(Long num) {
        return (num / unit) > 0;
    }

    public String covertUnit(Long num) {
        Long result = num / unit;
        return result.toString() + koreaUnit;

    }
}
