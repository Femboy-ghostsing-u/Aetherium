package dev.aetherium.client.ui;

import dev.aetherium.client.Client;
import dev.aetherium.client.utilities.RenderUtil;
import dev.aetherium.system.module.Category;
import dev.aetherium.system.module.Module;
import dev.aetherium.system.setting.Setting;
import dev.aetherium.system.setting.impl.BooleanSetting;
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
        private double posX;
        private double posY;
        private double dragX;
        private double dragY;
        private final double width;
        private final double height;
        private boolean dragging;
        private final List<ModuleUI> moduleUIS = new ArrayList<>();
        private boolean extendModules;

        public CategoryUI(Category category, int width, int height, int inc) {
            this.category = category;
            dragging = false;
            posX = 15 + inc;
            posY = 15;
            this.width = width;
            this.height = height;
            extendModules = false;


            int increment = 25;
            for (Module module : Client.getModuleManager().getModulesByCategory(category)) {
                moduleUIS.add(new ModuleUI(module, increment));
                increment += increment;
            }
        }

        public void clicked(int mouseX, int mouseY, int button) {
            if (isHover2(mouseX, mouseY, posX, posY, width, height) && button == 0) {
                this.dragging = true;
                this.dragX = mouseX - posX;
                this.dragY = mouseY - posY;
            } else if (isHover2(mouseX, mouseY, posX, posY, width, height) && button == 1) {
                extendModules = !extendModules;
            }

            if (extendModules)
                moduleUIS.forEach(moduleUI -> moduleUI.click(mouseX, mouseY, button));
        }

        public void release() {
            dragging = false;
        }

        public void draw(int mouseX, int mouseY, float partialTicks) {
            if (dragging) {
                posX = mouseX - dragX;
                posY = mouseY - dragY;
            }

            RenderUtil.renderRect(posX, posY, width, height, Color.pink);
            mc.fontRendererObj.drawStringWithShadow(category.getCategoryName(), (float) (posX + (width / 2) - 15), (float) (posY + (height / 2) - 5), -1);

            if (extendModules) {
                int increment = 25;
                int settingHeight = 0;
                for (ModuleUI moduleUI : moduleUIS) {
                    if (moduleUI.extendSettings) {

                        for (Setting setting : moduleUI.module.getSettingsList()) {
                            if (setting instanceof BooleanSetting) {
                                settingHeight += 20;
                            }
                        }
                    }

                    moduleUI.incrementY = increment + moduleUI.settingHeight;
                    moduleUI.draw(mouseX, mouseY, partialTicks);
                    increment += 25 + settingHeight;
                }
            }
        }

        public void close() {
            dragging = false;
        }

        private class ModuleUI {
            private final Module module;

            boolean extendSettings = false;
            private int incrementY;
            private final int settingHeight;

            private ModuleUI(Module module, int incrementY) {
                this.module = module;
                this.settingHeight = 0;
                this.extendSettings = false;
                this.incrementY = incrementY;
            }

            public void draw(int mouseX, int mouseY, float partialTicks) {
                Color enabledColor = module.isEnabled() ? Color.pink : Color.gray;
                RenderUtil.renderRect(posX, posY + incrementY, width, height, enabledColor);

                mc.fontRendererObj.drawStringWithShadow(module.getName(), (float) posX, (float) (posY + incrementY), -1);

                if (extendSettings) {
                    int increment = incrementY;
                    Color enabledSettingColor;
                    for (Setting setting : module.getSettingsList()) {
                        if (setting instanceof BooleanSetting) {
                            enabledSettingColor = ((BooleanSetting) setting).isValue() ? Color.GREEN : Color.RED;
                            RenderUtil.renderRect(posX, posY + incrementY + increment, width, height, enabledSettingColor);
                        }
                    }
                }
            }

            public void click(int mouseX, int mouseY, int button) {
                if (isHover2(mouseX, mouseY, posX, posY + incrementY, width, height) && button == 0) {
                    module.toggleModule();
                } else if (isHover2(mouseX, mouseY, posX, posY + incrementY, width, height) && button == 1 && !module.EmptySettings()) {
                    extendSettings = !extendSettings;
                }
            }
        }
    }
}
