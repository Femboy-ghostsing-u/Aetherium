package dev.aetherium.system.module;

import dev.aetherium.client.Client;
import dev.aetherium.system.utilities.Translate;
import dev.aetherium.system.setting.Setting;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.Minecraft;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter @Setter
public class Module {

    protected Minecraft mc = Minecraft.getMinecraft();

    private final String name;
    private final String description;
    private final int key;
    private final ModuleCategory category;
    private boolean enabled;
    private final Translate translate;
    private List<Setting> settingsList;
    private boolean extendSettings;

    public Module() {
        name = getClass().getAnnotation(ModuleInterface.class).moduleName();
        description = getClass().getAnnotation(ModuleInterface.class).description();
        key = getClass().getAnnotation(ModuleInterface.class).keybind();
        category = getClass().getAnnotation(ModuleInterface.class).category();
        translate = new Translate(0, 0);
        settingsList = new ArrayList<>();
    }

    public void onEnable() {
        Client.getEventManager().register(this);
    }

    public void onDisable() {
        Client.getEventManager().unregister(this);
    }

    public void toggleModule() {
        enabled = !enabled;
        if (enabled) {
            onEnable();
        } else {
            onDisable();
        }
    }

    public float getX() {
        return (float) translate.getX();
    }

    public float getY() {
        return (float) translate.getY();
    }

    public void addSettings(Setting... settings) {
        settingsList = new ArrayList<>(Arrays.asList(settings));
    }

    public boolean EmptySettings() {
        return settingsList.isEmpty();
    }
}
