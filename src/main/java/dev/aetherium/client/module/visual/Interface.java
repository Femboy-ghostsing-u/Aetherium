package dev.aetherium.client.module.visual;

import dev.aetherium.client.Client;
import dev.aetherium.client.event.render.Render2DEvent;
import dev.aetherium.client.utilities.AnimationUtil;
import dev.aetherium.system.event.SubscribeEvent;
import dev.aetherium.system.module.Category;
import dev.aetherium.system.module.Module;
import dev.aetherium.system.module.ModuleInterface;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.input.Keyboard;

import java.util.List;

@ModuleInterface(moduleName = "Interface", description = "Interface of your enabled modules, cords and MORE!", category = Category.VISUAL, keybind = Keyboard.KEY_N)
public class Interface extends Module {

    @Override
    public void onEnable() {
        super.onEnable();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    @SubscribeEvent
    public void onRender2D(Render2DEvent event) {
        mc.fontRendererObj.drawStringWithShadow(Client.CLIENT_NAME, 2, 1, -1);

        ArrayListModList(event.getScaledResolution());
    }

    private void ArrayListModList(ScaledResolution scaledResolution) {
        int width = scaledResolution.getScaledWidth();
        int height = scaledResolution.getScaledHeight();

        int positionY = 2;
        List<Module> moduleList = Client.getModuleManager().getModulesList();

        moduleList.sort((m1, m2) -> mc.fontRendererObj.getStringWidth(m2.getName()) - mc.fontRendererObj.getStringWidth(m1.getName()));
        for (Module module : moduleList) {
            float moduleNameWidth = mc.fontRendererObj.getStringWidth(module.getName());

            if (module.isEnabled()) {
                module.getTranslate().animate(width - moduleNameWidth - 2, positionY, 1, AnimationUtil.InterpolationType.PROGRESSIVE);
                positionY += mc.fontRendererObj.FONT_HEIGHT + 2;
            } else {
                module.getTranslate().animate(width + 2, positionY, 1, AnimationUtil.InterpolationType.PROGRESSIVE);
            }
            mc.fontRendererObj.drawStringWithShadow(module.getName(), module.getX(), module.getY(), -1);
        }
    }
}
