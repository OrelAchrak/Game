package game.items;

import game.characters.PlayerCharacter;
import game.map.Position;
import java.util.Random;

/**
 * אוצר - מוסיף נקודות או שיקויים אקראיים.
 */
public class Treasure extends GameItem {
    public Treasure(Position position) {
        super(position, true, "Treasure");
    }

    @Override
    public void interact(PlayerCharacter c) {
        int roll = new Random().nextInt(6) + 1; // 1-6
        if (roll <= 2) {
            c.addPotion(new Potion(position));
        } else if (roll <= 5) {
            int points = new Random().nextInt(201) + 100;
            c.updateTreasurePoint(points);
        } else {
            c.addPowerPotion(new PowerPotion(position));
        }
    }
}