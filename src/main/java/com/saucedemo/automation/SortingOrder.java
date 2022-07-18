package com.saucedemo.automation;

public enum SortingOrder {
    NAME_ASC("az", "Name (A to Z)"),
    NAME_DESC("za", "Name (Z to A)"),
    PRICE_ASC("lohi", "Price (low to high)"),
    PRICE_DESC("hilo", "Price (high to low)");

    private final String value;
    private final String text;

    SortingOrder(String value, String text) {
        this.value = value;
        this.text = text;
    }

    public String getValue() {
        return value;
    }

    public String getText() {
        return text;
    }
}
