package game.combat;

import game.map.Position;

/**
 * ממשק MeleeFighter: לוחם מטווח קצר.
 */
public interface MeleeFighter {
    void fightClose(Combatant target);
    boolean isInMeleeRange(Position self, Position target);
}
