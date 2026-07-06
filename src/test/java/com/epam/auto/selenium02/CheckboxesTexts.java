package com.epam.auto.selenium02;

import java.util.ArrayList;
import java.util.List;

public enum CheckboxesTexts {
    WATER("Water"),
    WIND("Wind"),
    EARTH("Earth"),
    FIRE("Fire");

    private final String text;

    CheckboxesTexts(String text) {
        this.text = text;
    }

    public static List<String> getCheckboxesTexts() {
        List<String> checkboxesTexts = new ArrayList<>();
        for (CheckboxesTexts item : CheckboxesTexts.values()) {
            checkboxesTexts.add(item.textPrinter());
        }
        return checkboxesTexts;
    }

    public String textPrinter() {
        System.out.println(text);
        return text;
    }
}
