package com.epam.auto.api03.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum MenuTexts {
    HOME("HOME"),
    CONTACT_FORM("CONTACT FORM"),
    SERVICE("SERVICE"),
    METALS_COLORS("METALS & COLORS"),
    ELEMENTS_PACKS("ELEMENTS PACKS");

    private final String text;

    MenuTexts(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public static List<String> getLeftMenuTexts() {
        return Arrays.stream(MenuTexts.values())
                .map(MenuTexts::getText)
                .collect(Collectors.toList());
    }

    public static List<String> getUpperMenuTexts() {
        return Arrays.stream(MenuTexts.values())
                .filter(item -> item != ELEMENTS_PACKS)
                .map(MenuTexts::getText)
                .collect(Collectors.toList());
    }
}
