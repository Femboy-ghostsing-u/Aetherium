package dev.aetherium.client.event.render;

import dev.aetherium.system.event.Event;
import lombok.Getter;

@Getter
public class Render3DEvent extends Event {

    private final float partialTicks;

    public Render3DEvent(float partialTicks) {
        this.partialTicks = partialTicks;
    }
}
