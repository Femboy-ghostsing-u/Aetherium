package dev.aetherium.client.module.movement;

import dev.aetherium.system.module.ModuleCategory;
import dev.aetherium.system.module.Module;
import dev.aetherium.system.module.ModuleInterface;
import dev.aetherium.system.setting.impl.BooleanSetting;
import org.lwjgl.input.Keyboard;

@ModuleInterface(moduleName = "Speed", description = "Be faster than anyone!", keybind = Keyboard.KEY_B, category = ModuleCategory.MOVEMENT)
public class Speed extends Module {

    BooleanSetting lowHop = new BooleanSetting("Lowhop", true);
    BooleanSetting noBobbing = new BooleanSetting("NoBobbing", false);

    public Speed() {
        addSettings(lowHop, noBobbing);
    }
}
