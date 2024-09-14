package dev.aetherium.system.setting.impl;

import dev.aetherium.system.setting.Setting;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BooleanSetting extends Setting {

    private boolean value;

    public BooleanSetting(String name, boolean value) {
        this.name = name;
        this.value = value;
    }
}
