package game.core;

import java.util.ArrayList;
import java.util.List;

/**
 * מחלקת Inventory גנרית לניהול אוספים.
 */
public class Inventory<T> {
    private List<T> items = new ArrayList<>();

    public void addItem(T item) {
        items.add(item);
    }

    public T fetchNext() {
        return items.isEmpty() ? null : items.remove(0);
    }

    public List<T> getAll() {
        return new ArrayList<>(items);
    }
}