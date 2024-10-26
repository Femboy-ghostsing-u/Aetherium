package dev.aetherium.system.utilities;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Translate {

    private double x;
    private double y;

    public Translate(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void animate(double newX, double newY, double speed, AnimationUtil.InterpolationType interpolationType) {
        this.x = AnimationUtil.interpolate(this.x, newX, speed, interpolationType);
        this.y = AnimationUtil.interpolate(this.y, newY, speed, interpolationType);
    }
}
