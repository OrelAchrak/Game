package game.items;

import game.characters.PlayerCharacter;
import game.map.Position;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Potion that increases a player's power by a random amount.
 */
public class PowerPotion extends GameItem implements Interactable {
    private final int increaseAmount;
    private boolean isUsed = false;

    /**
     * Constructs a PowerPotion at the given position, power increase between 1 and 5.
     * @param position position on the map (must not be null)
     */
    public PowerPotion(Position position) {
        super(position, false, "PowerPotion");
        this.increaseAmount = ThreadLocalRandom.current().nextInt(1, 6);
    }

    /**
     * Gets the power increase amount of this potion.
     */
    public int getIncreaseAmount() {
        return increaseAmount;
    }

    /**
     * Checks if this power potion has been used.
     */
    public boolean isUsed() {
        return isUsed;
    }

    @Override
    public boolean interact(PlayerCharacter c) {
        if (c == null || isUsed) {
            return false;
        }
        // Assumes PlayerCharacter has setPower/getPower methods
        c.setPower(c.getPower() + increaseAmount);
        isUsed = true;
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PowerPotion)) return false;
        if (!super.equals(o)) return false;
        PowerPotion that = (PowerPotion) o;
        return increaseAmount == that.increaseAmount && isUsed == that.isUsed;
    }

    @Override
    public String toString() {
        return "PowerPotion{" +
                "position=" + getPosition() +
                ", increaseAmount=" + increaseAmount +
                ", isUsed=" + isUsed +
                '}';
    }
}
