package dev.aetherium.client.ui;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;

import java.awt.*;
import java.io.IOException;

public class MainClickGui extends GuiScreen {

    private double posX, posY, dragX, dragY, width, height;
    private boolean dragging;

    public MainClickGui() {
        dragging = false;
        posX = 15;
        posY = 15;
    }

    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {
        dragging = false;
        super.mouseReleased(mouseX, mouseY, state);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        if (isHover2(mouseX, mouseY, posX, posY - 10, 350, 10) && mouseButton == 0) {
            this.dragging = true;
            this.dragX = mouseX - posX;
            this.dragY = mouseY - posY;
        }

        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    public void initGui() {
        super.initGui();
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        width = posX + 350;
        height = posY + 250;

        if (dragging) {
            posX = mouseX - dragX;
            posY = mouseY - dragY;
        }

        Gui.drawRect(posX, posY - 10, width, height - 250, new Color(0xA6A6FF).getRGB());
        Gui.drawRect(posX, posY, width, height, new Color(0xB32F2F47, true).getRGB());

        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}
