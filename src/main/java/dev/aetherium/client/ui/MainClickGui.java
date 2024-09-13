package dev.aetherium.client.ui;

import dev.aetherium.client.utilities.AnimationUtil;
import dev.aetherium.client.utilities.Translate;
import dev.aetherium.system.module.Category;
import dev.aetherium.system.module.Module;
import net.minecraft.client.gui.GuiScreen;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainClickGui extends GuiScreen {

    List<CategoryUI> categoryUIS = new ArrayList<>();

    public MainClickGui() {
        int i = 0;
        for (Category category : Category.values()) {
            categoryUIS.add(new CategoryUI(category, 100, 25, i));
            i += 150;
        }
    }

    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {
        for (CategoryUI categoryUI : categoryUIS) {
            categoryUI.release();
        }
        super.mouseReleased(mouseX, mouseY, state);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        for (CategoryUI categoryUI : categoryUIS) {
            categoryUI.clicked(mouseX, mouseY, mouseButton);
        }
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    public void initGui() {
        super.initGui();
    }

    @Override
    public void onGuiClosed() {
        for (CategoryUI categoryUI : categoryUIS) {
            categoryUI.close();
        }
        super.onGuiClosed();
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {

        for (CategoryUI categoryUI : categoryUIS) {
            categoryUI.draw(mouseX, mouseY, partialTicks);
        }

        super.drawScreen(mouseX, mouseY, partialTicks);
    }


    private class CategoryUI {

        private final Category category;
        private double posX, posY, dragX, dragY, width, height;
        private final double finalWidth, finalHeight;
        private boolean dragging;
        private Translate translate = new Translate(posX, 0);

        public CategoryUI(Category category, int width, int height, int inc) {
            this.category = category;
            dragging = false;
            posX = 15 + inc;
            posY = 15;
            this.finalWidth = width;
            this.finalHeight = height;
        }

        public void clicked(int mouseX, int mouseY, int button) {
            if (isHover2(mouseX, mouseY, posX, posY, finalWidth, finalHeight) && button == 0) {
                this.dragging = true;
                this.dragX = mouseX - posX;
                this.dragY = mouseY - posY;
            }
        }

        public void release() {
            dragging = false;
        }

        public void draw(int mouseX, int mouseY, float partialTicks) {
            if (dragging) {
                posX = mouseX - dragX;
                posY = mouseY - dragY;
            }
            translate.animate(posX, posY, 1, AnimationUtil.InterpolationType.PROGRESSIVE);

            width = translate.getX() + finalWidth;
            height = translate.getY() + finalHeight;

            double drawPosX = translate.getX();
            double drawPosY = translate.getY();

            drawRect(drawPosX, drawPosY, width, height, Color.pink.getRGB());
            mc.fontRendererObj.drawStringWithShadow(category.getCategoryName(), (float) (drawPosX + (finalWidth / 2) - 15), (float) (drawPosY + (finalHeight / 2) - 5), -1);


        }

        public void close() {
            translate = new Translate(posX, (double) mc.displayHeight / 2);
            dragging = false;
        }

        private class ModuleUI {
            private final Module module;

            boolean extended = false;

            private ModuleUI(Module module) {
                this.module = module;
            }

            public void draw(int mouseX, int mouseY, float partialTicks) {

            }
        }
    }
}
