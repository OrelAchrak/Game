package game.items;

import game.characters.PlayerCharacter;
import game.map.Position;
import java.util.concurrent.ThreadLocalRandom;


/**
 * Treasure chest that can give various rewards when interacted.
 */
public class Treasure extends GameItem implements Interactable {
    private final int value;
    private boolean collected = false;

    /**
     * Constructs a Treasure at the given position with specified treasure value.
     * @param position position on the map (must not be null)
     * @param value base value for treasure points
     */
    public Treasure(Position position, int value) {
        super(position, false, "Treasure");
        this.value = value;
    }

    /**
     * Gets the base treasure point value.
     */
    public int getValue() {
        return value;
    }

    /**
     * Checks if this treasure has already been collected.
     */
    public boolean isCollected() {
        return collected;
    }

    @Override
    public boolean interact(PlayerCharacter c) {
        if (c == null || collected) {
            return false;
        }
        double r = ThreadLocalRandom.current().nextDouble();
        boolean success;
        if (r < 1.0/3.0) {
            // Give a health potion
            success = c.addPotion(new Potion(getPosition()));
        } else if (r < 1.0/3.0 + 1.0/2.0) {
            // Give treasure points
            int pts = ThreadLocalRandom.current().nextInt(100, 301);
            success = c.updateTreasurePoint(pts);
        } else {
            // Give a power potion
            success = c.addPowerPotion(new PowerPotion(getPosition()));
        }
        if (success) {
            collected = true;
        }
        return success;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Treasure)) return false;
        if (!super.equals(o)) return false;
        Treasure treasure = (Treasure) o;
        return value == treasure.value && collected == treasure.collected;
    }

    @Override
    public String toString() {
        return "Treasure{" +
                "position=" + getPosition() +
                ", value=" + value +
                ", collected=" + collected +
                '}';
    }
}
