package game.engine;

import game.core.GameEntity;
import game.map.Position;

/**
 * אירוע תנועה שמכיל ישות וכיוון לתנועה
 */
public class MoveEvent {
    public enum Direction { UP, DOWN, LEFT, RIGHT }
    private final GameEntity entity;
    private final Direction direction;

    public MoveEvent(GameEntity entity, Direction direction) {
        this.entity = entity;
        this.direction = direction;
    }

    public GameEntity getEntity() { return entity; }
    public Direction getDirection() { return direction; }
}