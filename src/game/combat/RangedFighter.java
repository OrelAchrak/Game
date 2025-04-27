package game.combat;

import game.map.Position;

/**
 * Interface for ranged fighters.
 */
public interface RangedFighter {
    void fightRanged(Combatant target);
    int getRange();
    boolean isInRange(Position self, Position target);
}
