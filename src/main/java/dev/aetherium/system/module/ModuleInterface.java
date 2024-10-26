package dev.aetherium.system.module;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ModuleInterface {

    String moduleName();
    String description();
    int keybind() default 0;
    ModuleCategory category();
}
