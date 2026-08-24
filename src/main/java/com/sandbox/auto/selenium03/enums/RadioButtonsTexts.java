package com.sandbox.auto.selenium03.enums;

import java.util.ArrayList;
import java.util.List;

public enum RadioButtonsTexts {
    GOLD("Gold"),
    SILVER("Silver"),
    BRONZE("Bronze"),
    SELEN("Selen");

    private final String text;

    RadioButtonsTexts(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public static List<String> getRadioButtonsTexts() {
        List<String> radioButtonsTexts = new ArrayList<>();
        for (RadioButtonsTexts item : RadioButtonsTexts.values()) {
            radioButtonsTexts.add(item.getText());
        }
        return radioButtonsTexts;
    }
}
