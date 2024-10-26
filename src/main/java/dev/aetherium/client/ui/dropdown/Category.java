package dev.aetherium.client.ui.dropdown;

import dev.aetherium.client.Client;
import dev.aetherium.system.module.Module;
import dev.aetherium.system.module.ModuleCategory;
import dev.aetherium.system.utilities.RenderUtil;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;

import java.awt.*;
import java.util.List;

@Getter @Setter
public class Category extends GuiScreen {

    private ModuleCategory category;
    private int x, y, width, height;
    private boolean expanded;
    private List<Module> modulesList;

    public Category(ModuleCategory category, int width, int height, int increment) {
        this.category = category;
        this.width = width;
        this.height = height;
        this.x = width + increment;
        this.y = 50;
        this.expanded = false;
        this.modulesList = Client.getModuleManager().getModulesByCategory(category);
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        //TODO: Find better font manager
        RenderUtil.renderRect(x, y, width, height, new Color(0xFF93DB));
        Client.getFontManager().getFont("ROBO 18").drawStringWithShadow(category.name(), x + 2, y + 2, -1);

    }

    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {

    }

    public void mouseReleased(int mouseX, int mouseY, int state) {

    }

    public void keyTyped(char typedChar, int keyCode) {

    }

}
