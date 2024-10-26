package dev.aetherium.client.module.movement;

import dev.aetherium.client.event.update.UpdateEvent;
import dev.aetherium.system.event.SubscribeEvent;
import dev.aetherium.system.module.ModuleCategory;
import dev.aetherium.system.module.Module;
import dev.aetherium.system.module.ModuleInterface;
import dev.aetherium.system.setting.impl.BooleanSetting;
import org.lwjgl.input.Keyboard;

@ModuleInterface(moduleName = "AutoSprint", description = "This automatically toggle sprint!", keybind = Keyboard.KEY_C, category = ModuleCategory.MOVEMENT)
public class AutoSprint extends Module {

    BooleanSetting omniSprintValue = new BooleanSetting("OmniSprint", false);

    public AutoSprint() {
        addSettings(omniSprintValue);
    }

    @SubscribeEvent
    public void onUpdate(UpdateEvent event) {
        if(!mc.thePlayer.isCollidedHorizontally && mc.thePlayer.moveForward > 0)
            mc.thePlayer.setSprinting(true);
    }
}
