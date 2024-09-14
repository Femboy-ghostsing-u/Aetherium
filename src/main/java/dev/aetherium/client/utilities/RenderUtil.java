package dev.aetherium.client.utilities;

import net.minecraft.client.gui.Gui;

import java.awt.*;

public class RenderUtil {

    public static void renderRect(double x, double y, double width, double height, Color color) {
        renderRect(x, y, width, height, color.getRGB());
    }

    public static void renderRect(double x, double y, double width, double height, int color) {
        Gui.drawRect(x, y, x + width, y + height, color);
    }

}
