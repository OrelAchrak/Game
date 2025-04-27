package game.core;

import game.map.Position;

/**
 * Represents any entity in the game that has a position and can be displayed or hidden.
 */
public interface GameEntity {
    /**
     * Returns the current position of this entity.
     */
    Position getPosition();

    /**
     * Updates this entity's position.
     * @param newPos the new position (must not be null)
     */
    void setPosition(Position newPos);

    /**
     * Returns a single-character symbol used to represent this entity in display.
     */
    String getDisplaySymbol();

    /**
     * Sets whether this entity is visible on the map.
     */
    void setVisible(boolean visible);

    boolean isVisible();
}
