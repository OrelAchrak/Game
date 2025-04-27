package game.items;

import game.characters.PlayerCharacter;
import game.map.Position;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Potion that heals a player by a random amount.
 */
public class Potion extends GameItem implements Interactable {
    private final int increaseAmount;
    private boolean isUsed = false;

    /**
     * Constructs a Potion at the given position, with heal amount between 10 and 50.
     * @param position position on the map (must not be null)
     */
    public Potion(Position position) {
        super(position, false, "Potion");
        this.increaseAmount = ThreadLocalRandom.current().nextInt(10, 51);
    }

    /**
     * Gets the healing amount of this potion.
     */
    public int getIncreaseAmount() {
        return increaseAmount;
    }

    /**
     * Checks if this potion has been used.
     */
    public boolean isUsed() {
        return isUsed;
    }

    @Override
    public boolean interact(PlayerCharacter c) {
        if (c == null || isUsed) {
            return false;
        }
        c.heal(increaseAmount);
        isUsed = true;
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Potion)) return false;
        if (!super.equals(o)) return false;
        Potion potion = (Potion) o;
        return increaseAmount == potion.increaseAmount && isUsed == potion.isUsed;
    }

    @Override
    public String toString() {
        return "Potion{" +
                "position=" + getPosition() +
                ", increaseAmount=" + increaseAmount +
                ", isUsed=" + isUsed +
                '}';
    }
}
