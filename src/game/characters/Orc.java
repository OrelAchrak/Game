package game.characters;

import game.map.Position;

/**
 * Orc עם עמידות בפני קסם.
 */
public class Orc extends Enemy {
    private double resistance;

    public Orc(Position position, double resistance, int loot) {
        super(position, loot);
        this.resistance = resistance;
    }

    public double getResistance() {
        return resistance;
    }
}
