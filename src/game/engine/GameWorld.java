package game.engine;

import game.characters.PlayerCharacter;
import game.characters.Enemy;
import game.items.GameItem;
import game.map.GameMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Represents the game world including players, enemies, items, and the map.
 */
public class GameWorld {
    private final List<PlayerCharacter> players = new ArrayList<>();
    private final List<Enemy> enemies = new ArrayList<>();
    private final List<GameItem> items = new ArrayList<>();
    private final GameMap map;

    /**
     * Constructs a GameWorld with a provided game map.
     * @param map the game map to use (must not be null)
     */
    public GameWorld(GameMap map) {
        if (map == null) {
            throw new IllegalArgumentException("Map must not be null");
        }
        this.map = map;
    }

    /**
     * Adds a player character to the world.
     * @return true if added successfully
     */
    public boolean addPlayer(PlayerCharacter p) {
        if (p == null) return false;
        return players.add(p);
    }

    /**
     * Removes a player character from the world.
     * @return true if removed successfully
     */
    public boolean removePlayer(PlayerCharacter p) {
        if (p == null) return false;
        return players.remove(p);
    }

    /**
     * Returns an unmodifiable list of player characters.
     */
    public List<PlayerCharacter> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    /**
     * Adds an enemy to the world.
     * @return true if added successfully
     */
    public boolean addEnemy(Enemy e) {
        if (e == null) return false;
        return enemies.add(e);
    }

    /**
     * Removes an enemy from the world.
     * @return true if removed successfully
     */
    public boolean removeEnemy(Enemy e) {
        if (e == null) return false;
        return enemies.remove(e);
    }

    /**
     * Returns an unmodifiable list of enemies.
     */
    public List<Enemy> getEnemies() {
        return Collections.unmodifiableList(enemies);
    }

    /**
     * Adds an item to the world.
     * @return true if added successfully
     */
    public boolean addItem(GameItem item) {
        if (item == null) return false;
        return items.add(item);
    }

    /**
     * Removes an item from the world.
     * @return true if removed successfully
     */
    public boolean removeItem(GameItem item) {
        if (item == null) return false;
        return items.remove(item);
    }

    /**
     * Returns an unmodifiable list of items.
     */
    public List<GameItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    /**
     * Returns the game map.
     */
    public GameMap getMap() {
        return map;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GameWorld)) return false;
        GameWorld that = (GameWorld) o;
        return players.equals(that.players) &&
                enemies.equals(that.enemies) &&
                items.equals(that.items) &&
                map.equals(that.map);
    }

    @Override
    public int hashCode() {
        return Objects.hash(players, enemies, items, map);
    }

    @Override
    public String toString() {
        return "GameWorld{" +
                "players=" + players +
                ", enemies=" + enemies +
                ", items=" + items +
                ", map=" + map +
                '}';
    }
}
