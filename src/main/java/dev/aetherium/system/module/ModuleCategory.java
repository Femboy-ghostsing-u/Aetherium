package dev.aetherium.system.module;

import lombok.Getter;

@Getter
public enum ModuleCategory {

    COMBAT("Combat", 'd'),
    MOVEMENT("Movement", 'a'),
    VISUAL("Visual", 'c'),
    OTHER("Other", 'b');

    private String categoryName;
    private char categoryIcon;

    ModuleCategory(String categoryName, char a) {
        this.categoryName = categoryName;
        this.categoryIcon = a;
    }
}
