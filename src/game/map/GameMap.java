package game.map;

import game.core.GameEntity;
import java.util.*;

/**
 * Represents the game world grid, mapping positions to game entities.
 */
public class GameMap {
    private final Map<Position, List<GameEntity>> grid = new HashMap<>();

    /**
     * Adds an entity at the specified position.
     * @param position the map position (must not be null)
     * @param entity the game entity to add (must not be null)
     * @return true if added successfully, false otherwise
     */
    public boolean addEntity(Position position, GameEntity entity) {
        if (position == null || entity == null) {
            return false;
        }
        grid.computeIfAbsent(position, k -> new ArrayList<>());
        return grid.get(position).add(entity);
    }

    /**
     * Removes an entity from the specified position.
     * @param position the map position (must not be null)
     * @param entity the game entity to remove (must not be null)
     * @return true if removed successfully, false if not found or invalid input
     */
    public boolean removeEntity(Position position, GameEntity entity) {
        if (position == null || entity == null) {
            return false;
        }
        List<GameEntity> entities = grid.get(position);
        if (entities == null) {
            return false;
        }
        boolean removed = entities.remove(entity);
        if (entities.isEmpty()) {
            grid.remove(position);
        }
        return removed;
    }

    /**
     * Retrieves an unmodifiable list of entities at the given position.
     * @param position the map position (must not be null)
     * @return list of entities (empty list if none)
     */
    public List<GameEntity> getEntities(Position position) {
        if (position == null) {
            throw new IllegalArgumentException("Position must not be null");
        }
        List<GameEntity> entities = grid.get(position);
        if (entities == null) {
            return Collections.emptyList();
        }
        return Collections.unmodifiableList(entities);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GameMap)) return false;
        GameMap that = (GameMap) o;
        return grid.equals(that.grid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(grid);
    }

    @Override
    public String toString() {
        return "GameMap{" + "grid=" + grid + '}';
    }
}
