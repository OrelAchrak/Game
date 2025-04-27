package game.items;

import game.map.Position;

/**
 * Wall that blocks movement and cannot be interacted with.
 */
public class Wall extends GameItem {
    /**
     * Constructs a Wall at the specified position.
     * Walls always block movement.
     * @param position position on the map (must not be null)
     */
    public Wall(Position position) {
        super(position, true, "Wall");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Wall)) return false;
        return super.equals(o);
    }

    @Override
    public String toString() {
        return "Wall{" +
                "position=" + getPosition() +
                ", blocksMovement=" + isBlocksMovement() +
                '}';
    }
}
