package dev.aetherium.client.utilities;

import net.minecraft.client.Minecraft;

//TODO: make Easy In, Easy Out, Easy In & Out and Smooth to work
public class AnimationUtil {

    // Oblicza korekcję pozycji w oparciu o różnicę między bieżącą a docelową wartością
    public static float calculateCompensation(float target, float current, long delta, int speed) {
        float diff = current - target;
        if (delta < 1L) delta = 1L;
        double adjustment = Math.max(0.5D, (speed * delta) / 16.0D);

        if (Math.abs(diff) > speed) {
            current -= (float) (Math.signum(diff) * adjustment);
            if (Math.signum(diff) != Math.signum(current - target)) {
                current = target;
            }
        } else {
            current = target;
        }
        return current;
    }

    // Główna metoda animacji z regulowaną szybkością, opcjonalnie z liniową interpolacją
    public static double animate(double target, double current, double speed, boolean linear) {
        if (speed < 0.0D) speed = 0.0D;
        if (speed > 1.0D) speed = 1.0D;

        double difference = target - current;
        double factor = linear ? speed : speed * Math.abs(difference);
        factor = Math.max(factor, 0.1D);

        current += Math.signum(difference) * factor;
        if (Math.signum(difference) != Math.signum(target - current)) {
            current = target;
        }

        return current;
    }

    // Progresywna animacja z dynamicznie dostosowywaną prędkością
    public static double progressiveAnimation(double now, double desired, double speed) {
        double difference = Math.abs(now - desired);
        int fps = Minecraft.getDebugFPS();
        double factor = Math.min(10.0D, Math.max(0.05D, 144.0D / fps * difference / 10.0D * speed));

        factor = Math.min(factor, difference);
        now += Math.signum(desired - now) * factor;

        return now;
    }

    // Liniowa animacja z równą prędkością
    public static double linearAnimation(double now, double desired, double speed) {
        double difference = Math.abs(now - desired);
        int fps = Minecraft.getDebugFPS();
        double factor = Math.min(10.0D, Math.max(0.005D, 144.0D / fps * speed));

        factor = Math.min(factor, difference);
        now += Math.signum(desired - now) * factor;

        return now;
    }

    // Ease-in animation: start slowly and accelerate
    public static double easeIn(double current, double target, double speed) {
        double difference = target - current;
        return current + difference * Math.pow(speed, 3);
    }

    // Ease-out animation: start quickly and decelerate
    public static double easeOut(double current, double target, double speed) {
        double difference = target - current;
        return current + difference * (1 - Math.pow(1 - speed, 3));
    }

    // Ease-in-out animation: combine ease-in and ease-out
    public static double easeInOut(double current, double target, double speed) {
        double difference = target - current;
        if (speed < 0.5) {
            return easeIn(current, current + difference / 2, speed * 2);
        } else {
            return easeOut(current + difference / 2, target, (speed - 0.5) * 2);
        }
    }

    // Uniwersalna metoda interpolacji z obsługą różnych typów interpolacji
    public static double interpolate(double current, double target, double speed, InterpolationType type) {
        switch (type) {
            case LINEAR:
                return linearAnimation(current, target, speed);
            case PROGRESSIVE:
                return progressiveAnimation(current, target, speed);
            case EASE_IN:
                return easeIn(current, target, speed);
            case EASE_OUT:
                return easeOut(current, target, speed);
            case EASE_IN_OUT:
                return easeInOut(current, target, speed);
            default:
                return animate(target, current, speed, false);
        }
    }

    // Enum dla typów interpolacji
    public enum InterpolationType {
        LINEAR,
        PROGRESSIVE,
        SMOOTH,
        EASE_IN,
        EASE_OUT,
        EASE_IN_OUT
    }
}
