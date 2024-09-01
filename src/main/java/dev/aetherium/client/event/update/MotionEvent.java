package dev.aetherium.client.event.update;

import dev.aetherium.system.event.Event;
import dev.aetherium.system.event.enums.Prority;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MotionEvent extends Event {

    private Prority prority;
    private float yaw, pitch;
    private double x, y, z;
    private float prevYaw, prevPitch;
    private boolean onGround;

    public MotionEvent(Prority prority, double x, double y, double z, float yaw, float pitch, float prevYaw, float prevPitch, boolean onGround) {
        this.setPrority(prority);
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
        this.prevYaw = prevYaw;
        this.prevPitch = prevPitch;
        this.onGround = onGround;
    }
}
