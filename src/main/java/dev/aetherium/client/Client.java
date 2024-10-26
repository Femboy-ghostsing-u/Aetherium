package dev.aetherium.client;

import dev.aetherium.client.ui.dropdown.DropDownGui;
import dev.aetherium.system.event.EventManager;
import dev.aetherium.system.font.FontManager;
import dev.aetherium.system.module.ModuleManager;
import lombok.Getter;

public class Client {

    public static final String CLIENT_NAME = "Aetherium";
    public static final String VERSION = "0.0.1";
    public static final Branch BRANCH = Branch.DEVELOPMENT;
    @Getter
    private static FontManager fontManager;


    @Getter
    private static EventManager eventManager;
    @Getter
    private static ModuleManager moduleManager;

    //ClickGuis
    @Getter private static DropDownGui dropDownGui;

    public static void onLaunch() {
        fontManager = new FontManager();
        fontManager.setup();

        eventManager = new EventManager();
        moduleManager = new ModuleManager();
        moduleManager.onInitialize();

        dropDownGui = new DropDownGui();


    }

    public static void onShutdown() {

    }
}
