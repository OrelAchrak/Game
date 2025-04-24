package game.items;

import game.characters.PlayerCharacter;
import game.map.Position;
import java.util.Random;

/**
 * שיקוי חיים - מרפא את השחקן.
 */
public class Potion extends GameItem {
    public Potion(Position position) {
        super(position, false, "Potion");
    }

    @Override
    public void interact(PlayerCharacter c) {
        int amount = new Random().nextInt(41) + 10;
        c.heal(amount);
    }
}