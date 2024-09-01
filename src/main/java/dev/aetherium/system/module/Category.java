package dev.aetherium.system.module;

import lombok.Getter;

@Getter
public enum Category {

    COMBAT("Combat", 'd'),
    MOVEMENT("Movement", 'a'),
    VISUAL("Visual", 'c'),
    OTHER("Other", 'b');

    private String categoryName;
    private char categoryIcon;

    Category(String categoryName, char a) {
    }
}
