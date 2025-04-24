package game.engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ניהול רישום והפצת אירועים במשחק
 */
public class EventManager {
    private final Map<Class<?>, List<EventListener<?>>> listeners = new HashMap<>();

    public <E> void register(Class<E> eventType, EventListener<E> listener) {
        listeners.computeIfAbsent(eventType, k -> new ArrayList<>()).add(listener);
    }

    public <E> void unregister(Class<E> eventType, EventListener<E> listener) {
        List<EventListener<?>> ls = listeners.get(eventType);
        if (ls != null) ls.remove(listener);
    }

    @SuppressWarnings("unchecked")
    public <E> void fire(E event) {
        List<EventListener<?>> ls = listeners.get(event.getClass());
        if (ls != null) {
            for (EventListener<?> raw : ls) {
                ((EventListener<E>) raw).onEvent(event);
            }
        }
    }

    public interface EventListener<E> {
        void onEvent(E event);
    }
}