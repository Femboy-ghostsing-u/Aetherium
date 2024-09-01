package dev.aetherium.client;

import lombok.Getter;

@Getter
public enum Branch {

    STABLE("&aStable"),
    DEVELOPMENT("&cExperimental");

    private String name;

    Branch(String name) {
    }
}
