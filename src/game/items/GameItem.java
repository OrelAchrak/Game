package game.items;

import game.core.GameEntity;
import game.map.Position;

/**
 * Base class for all game items.
 */
public abstract class GameItem implements GameEntity {
    private Position position = new Position(0, 0);
    private boolean blocksMovement = false;
    private String description = "";
    private boolean visible = false;

    /**
     * Constructs a GameItem with given properties.
     * @param position initial position (defaults to 0,0 if null)
     * @param blocksMovement whether item blocks movement
     * @param description descriptive text (defaults to empty)
     */
    public GameItem(Position position, boolean blocksMovement, String description) {
        this.position = (position != null ? position : new Position(0, 0));
        this.blocksMovement = blocksMovement;
        this.description = (description != null ? description : "");
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void setPosition(Position newPos) {
        if (newPos != null) {
            this.position = newPos;
        }
    }

    @Override
    public String getDisplaySymbol() {
        return description.isEmpty() ? "?" : description.substring(0, 1);
    }

    @Override
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    /**
     * Checks visibility of the item.
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * Checks whether this item blocks movement.
     */
    public boolean isBlocksMovement() {
        return blocksMovement;
    }

    /**
     * Protected setter for blocksMovement for subclasses.
     * @return always true
     */
    protected boolean setBlocksMovement(boolean blocksMovement) {
        this.blocksMovement = blocksMovement;
        return true;
    }

    /**
     * Gets the description of this item.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Protected setter for description.
     * @return true if non-null, false otherwise
     */
    protected boolean setDescription(String description) {
        if (description == null) return false;
        this.description = description;
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GameItem)) return false;
        GameItem that = (GameItem) o;
        return blocksMovement == that.blocksMovement &&
                visible == that.visible &&
                position.equals(that.position) &&
                description.equals(that.description);
    }

    @Override
    public String toString() {
        return "GameItem{" +
                "position=" + position +
                ", blocksMovement=" + blocksMovement +
                ", description='" + description + '\'' +
                ", visible=" + visible +
                '}';
    }
}
