package dev.aetherium.client.module.movement;

import dev.aetherium.client.event.update.UpdateEvent;
import dev.aetherium.system.event.SubscribeEvent;
import dev.aetherium.system.module.Category;
import dev.aetherium.system.module.Module;
import dev.aetherium.system.module.ModuleInterface;
import org.lwjgl.input.Keyboard;

@ModuleInterface(moduleName = "AutoSprint", description = "This automatically toggle sprint!", keybind = Keyboard.KEY_C, category = Category.MOVEMENT)
public class AutoSprint extends Module {

    @SubscribeEvent
    public void onUpdate(UpdateEvent event) {
        if(!mc.thePlayer.isCollidedHorizontally && mc.thePlayer.moveForward > 0)
            mc.thePlayer.setSprinting(true);
    }
}
