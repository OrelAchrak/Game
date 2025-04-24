package game.items;

import game.characters.PlayerCharacter;
import game.map.Position;
import java.util.Random;

/**
 * שיקוי כוח - מגדיל את כוח ההתקפה.
 */
public class PowerPotion extends GameItem {
    public PowerPotion(Position position) {
        super(position, false, "PowerPotion");
    }

    @Override
    public void interact(PlayerCharacter c) {
        int amount = new Random().nextInt(5) + 1;
        c.setPower(c.getPower() + amount);
    }
}