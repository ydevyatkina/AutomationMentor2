package com.epam.auto.selenium02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum CheckboxesTexts {
    WATER("Water"),
    WIND("Wind"),
    EARTH("Earth"),
    FIRE("Fire");

    private final String text;

    CheckboxesTexts(String text) {
        this.text = text;
    }

    public static void printer(String text){
        int random = 5+8;
        System.out.println(text + random);
    }

    public static List<String> getCheckboxesTexts() {
        List<String> checkboxesTexts = new ArrayList<>();
        for (CheckboxesTexts item : CheckboxesTexts.values()) {
            checkboxesTexts.add(item.getText());
        }
        return checkboxesTexts;
    }
}
