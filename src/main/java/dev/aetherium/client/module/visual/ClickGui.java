package dev.aetherium.client.module.visual;

import dev.aetherium.client.Client;
import dev.aetherium.system.module.Category;
import dev.aetherium.system.module.Module;
import dev.aetherium.system.module.ModuleInterface;
import org.lwjgl.input.Keyboard;

@ModuleInterface(moduleName = "ClickGui", description = "ClickGui", category = Category.VISUAL, keybind = Keyboard.KEY_RSHIFT)
public class ClickGui extends Module {

    @Override
    public void onEnable() {
        mc.displayGuiScreen(Client.getMainClickGui());
        toggleModule();
        super.onEnable();
    }
}
