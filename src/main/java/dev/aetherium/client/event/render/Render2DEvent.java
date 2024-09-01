package dev.aetherium.client.event.render;

import dev.aetherium.system.event.Event;
import lombok.Getter;
import net.minecraft.client.gui.ScaledResolution;

@Getter
public class Render2DEvent extends Event {

    private final ScaledResolution scaledResolution;

    public Render2DEvent(ScaledResolution scaledResolution) {
        this.scaledResolution = scaledResolution;
    }
}
