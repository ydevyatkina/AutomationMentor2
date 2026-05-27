package System;
package com.epam.auto.selenium05.enums;

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

    public String getText() {
        return text;
    }

    public static List<String> getCheckboxesTexts() {
        List<String> checkboxesTexts = new ArrayList<>();
        for (CheckboxesTexts item : CheckboxesTexts.values()) {
            checkboxesTexts.add(item.getText());
        }
        return checkboxesTexts;
    }
}
