package game.characters;

import game.map.Position;
import java.util.Random;

/**
 * Goblin עם יכולת התחמקות גבוהה.
 */
public class Goblin extends Enemy {
    private int agility;
    private Random rand = new Random();

    public Goblin(Position position, int agility, int loot) {
        super(position, loot);
        this.agility = agility;
    }

    @Override
    public boolean tryEvade() {
        return rand.nextDouble() < Math.min(0.8, agility / 100.0);
    }
}