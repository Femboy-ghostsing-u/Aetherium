package dev.aetherium.client.module.visual;

import dev.aetherium.client.Client;
import dev.aetherium.system.module.ModuleCategory;
import dev.aetherium.system.module.Module;
import dev.aetherium.system.module.ModuleInterface;
import org.lwjgl.input.Keyboard;

@ModuleInterface(moduleName = "ClickGui", description = "ClickGui", category = ModuleCategory.VISUAL, keybind = Keyboard.KEY_RSHIFT)
public class ClickGui extends Module {

    @Override
    public void onEnable() {
        mc.displayGuiScreen(Client.getDropDownGui());
        toggleModule();
        super.onEnable();
    }
}
