package dev.aetherium.system.setting.impl;

import dev.aetherium.system.setting.Setting;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class OptionSetting extends Setting {
    @Getter @Setter
    private List<String> optionsList;
    @Getter @Setter
    private String optionValue;
    private int modeIndex;

    public OptionSetting(List<String> optionsList) {
        this.modeIndex = 0;
        this.optionsList = optionsList;
        this.optionValue = optionsList.get(modeIndex);
    }

    public OptionSetting(String... optionsList) {
        this.modeIndex = 0;
        this.optionsList = new ArrayList<>(Arrays.asList(optionsList));
        this.optionValue = optionsList[modeIndex];
    }

    public void cycleForward() {
        modeIndex++;
        if (modeIndex >= this.optionsList.size()) modeIndex = 0;
        optionValue = optionsList.get(modeIndex);
    }

    public void cycleBack() {
        modeIndex--;
        if (modeIndex < 0) modeIndex = this.optionsList.size() - 1;
        optionValue = optionsList.get(modeIndex);
    }
}
