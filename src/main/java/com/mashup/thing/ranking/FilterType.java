package com.mashup.thing.ranking;

public enum FilterType {
    TOTAL("ranking"), SOARING("soaring");

    private String filterType;

    FilterType(String filterType) {
        this.filterType = filterType;
    }

    public String getFilterType() {
        return filterType;
    }
}
