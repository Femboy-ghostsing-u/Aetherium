package dev.aetherium.client;

import dev.aetherium.client.ui.MainClickGui;
import dev.aetherium.system.event.EventManager;
import dev.aetherium.system.module.ModuleManager;
import lombok.Getter;

public class Client {

    public static final String CLIENT_NAME = "Aetherium";
    public static final String VERSION = "0.0.1";
    public static final Branch BRANCH = Branch.DEVELOPMENT;

    @Getter
    private static EventManager eventManager;
    @Getter
    private static ModuleManager moduleManager;

    //ClickGuis
    @Getter private static MainClickGui mainClickGui;

    public static void onLaunch() {
        eventManager = new EventManager();
        moduleManager = new ModuleManager();
        moduleManager.onInitialize();

        mainClickGui = new MainClickGui();


    }

    public static void onShutdown() {

    }
}
