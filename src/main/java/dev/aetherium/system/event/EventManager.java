package dev.aetherium.system.event;

import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The EventManager class manages the registration and invocation of event handler methods
 * marked with the @SubscribeEvent annotation. It allows listeners to register their methods
 * and handles the dispatching of events to the appropriate methods.
 */
public class EventManager {
    // A map storing event handlers organized by the event class.
    private final Map<Class<?>, List<MethodHandler>> listeners = new ConcurrentHashMap<>();

    /**
     * Registers methods annotated with @HandleEvent in the given listener object.
     * Analyzes all declared methods in the listener's class and adds them to the listeners map
     * if they are annotated with @SubscribeEvent and take one argument.
     *
     * @param listener The object whose methods should be registered as event handlers.
     */
    public void register(Object listener) {
        for (Method method : listener.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(SubscribeEvent.class) && method.getParameterCount() == 1) {
                Class<?> eventType = method.getParameterTypes()[0];
                listeners.computeIfAbsent(eventType, k -> new ArrayList<>()).add(new MethodHandler(listener, method));
            }
        }
    }

    /**
     * Unregisters all methods annotated with @SubscribeEvent in the given listener object.
     * Removes them from the listeners map.
     *
     * @param listener The object whose methods should be unregistered.
     */
    public void unregister(Object listener) {
        for (List<MethodHandler> handlers : listeners.values()) {
            // Remove all MethodHandlers that belong to the given listener
            handlers.removeIf(handler -> handler.target.equals(listener));
        }
        // Clean up empty lists
        listeners.entrySet().removeIf(entry -> entry.getValue().isEmpty());
    }

    /**
     * Publishes an event to the appropriate handler methods.
     * Looks up the listeners map for methods handling the given event and invokes them.
     *
     * @param event The event object to be published.
     */
    public void publish(Event event) {
        List<MethodHandler> handlers = listeners.get(event.getClass());
        if (handlers != null) {
            for (MethodHandler handler : handlers) {
                handler.invoke(event);
            }
        }
    }

    /**
     * Private class for handling method invocation.
     * Stores the target object and method to be invoked.
     */
    private static class MethodHandler {
        private final Object target;
        private final Method method;

        /**
         * Constructor for MethodHandler.
         *
         * @param target The object containing the method.
         * @param method The method to be invoked.
         */
        MethodHandler(Object target, Method method) {
            this.target = target;
            this.method = method;
            this.method.setAccessible(true);
        }

        /**
         * Invokes the method on the target object with the given event.
         *
         * @param event The event to be passed as an argument to the method.
         */
        void invoke(Event event) {
            try {
                method.invoke(target, event);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
