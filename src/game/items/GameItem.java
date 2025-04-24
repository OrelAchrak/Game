package game.items;

import game.core.GameEntity;
import game.characters.PlayerCharacter;
import game.map.Position;

/**
 * מחלקת GameItem מייצגת חפץ במשחק.
 */
public abstract class GameItem implements GameEntity, Interactable {
    protected Position position;
    protected boolean blocksMovement;
    protected String description;
    protected boolean visible;

    public GameItem(Position position, boolean blocksMovement, String description) {
        this.position = position;
        this.blocksMovement = blocksMovement;
        this.description = description;
        this.visible = true;
    }
    @Override public Position getPosition() { return position; }
    @Override public void setPosition(Position newPos) { this.position = newPos; }
    @Override public String getDisplaySymbol() { return description.substring(0, 1); }
    @Override public void setVisible(boolean visible) { this.visible = visible; }
    @Override public void interact(PlayerCharacter c) {
        // ברירת-מחדל: לא עושה כלום
    }
    public boolean isBlocksMovement() { return blocksMovement; }
    public String getDescription() { return description; }
}