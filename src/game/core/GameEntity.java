package game.core;

import game.map.Position;

/**
 * ממשק GameEntity: כל ישות בעלת מיקום במפה.
 */
public interface GameEntity {
    Position getPosition();
    void setPosition(Position newPos);
    String getDisplaySymbol();
    void setVisible(boolean visible);
}