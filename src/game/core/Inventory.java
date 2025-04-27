package game.core;

import game.items.GameItem;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Inventory for GameItem entities.
 * Stores items and provides methods to add, remove, and retrieve them.
 */
public class Inventory {
    private final List<GameItem> items = new ArrayList<>();

    /**
     * Adds an item to the inventory.
     * @param item the GameItem to add
     * @return true if added successfully
     */
    public boolean addItem(GameItem item) {
        if (item == null) {
            return false;
        }
        return items.add(item);
    }

    /**
     * Removes an item from the inventory.
     * @param item the GameItem to remove
     * @return true if removed successfully
     */
    public boolean removeItem(GameItem item) {
        if (item == null) {
            return false;
        }
        return items.remove(item);
    }

    /**
     * Retrieves an unmodifiable list of items currently in the inventory.
     * @return list of GameItem
     */
    public List<GameItem> getItems() {
        return Collections.unmodifiableList(items);
    }
}
