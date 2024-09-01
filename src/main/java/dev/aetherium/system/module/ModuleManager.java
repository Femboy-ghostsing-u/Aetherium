package dev.aetherium.system.module;

import dev.aetherium.client.module.movement.AutoSprint;
import dev.aetherium.client.module.visual.ClickGui;
import dev.aetherium.client.module.visual.Interface;
import lombok.Getter;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
public class ModuleManager {

    private final Map<String, Module> modulesMap;

    public ModuleManager() {
        modulesMap = new LinkedHashMap<>();
    }

    public void onInitialize() {
        addModule(new Interface());
        addModule(new AutoSprint());
        addModule(new ClickGui());
    }

    private void addModule(Module module) {
        modulesMap.put(module.getName().toLowerCase(), module);
    }

    public void toggleByKey(int key) {
        for (Map.Entry<String, Module> entry : modulesMap.entrySet()) {
            Module module = entry.getValue();
            if (key == module.getKey()) {
                module.toggleModule();
            }
        }
    }

    public List<Module> getEnabledModules() {
        return modulesMap.values().stream().filter(Module::isEnabled).collect(Collectors.toList());
    }
}
