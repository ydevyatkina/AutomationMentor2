package com.epam.auto.selenium03.enums;

import java.util.ArrayList;
import java.util.List;

public enum RadioButtonsTexts {
    GOLD("Gold"),
    TILE("Tile"),
    PURPLE("Purple"),
    SELEN("Selen");

    private final String text;

    RadioButtonsTexts(String text) {
        this.text = text;
    }

    public static void printer(String text){
        System.out.println(text);
    }

    public static List<String> getRadioButtonsTexts() {
        List<String> radioButtonsTexts = new ArrayList<>();
        for (RadioButtonsTexts item : RadioButtonsTexts.values()) {
            radioButtonsTexts.add(item.getText());
        }
        return radioButtonsTexts;
    }
}
