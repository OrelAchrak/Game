package game.combat;

import game.map.Position;

/**
 * ממשק RangedFighter: לוחם מטווח רחוק.
 */
public interface RangedFighter {
    void fightRanged(Combatant target);
    int getRange();
    boolean isInRange(Position self, Position target);
}