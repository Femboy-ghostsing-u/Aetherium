package dev.aetherium.client.module.movement;

import dev.aetherium.system.module.Category;
import dev.aetherium.system.module.Module;
import dev.aetherium.system.module.ModuleInterface;
import org.lwjgl.input.Keyboard;

@ModuleInterface(moduleName = "Speed", description = "Be faster than anyone!", keybind = Keyboard.KEY_B, category = Category.MOVEMENT)
public class Speed extends Module {

    public Speed() {
    }
}
