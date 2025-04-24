package game.engine;

import game.core.GameEntity;

/**
 * אירוע התקפה הכולל את הישות התוקפת
 */
public class AttackEvent {
    private final GameEntity entity;

    public AttackEvent(GameEntity entity) {
        this.entity = entity;
    }

    public GameEntity getEntity() { return entity; }
}
