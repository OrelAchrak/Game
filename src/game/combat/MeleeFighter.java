package game.combat;

import game.map.Position;

/**
 * Interface for melee fighters.
 */
public interface MeleeFighter {
    void fightClose(Combatant target);
    boolean isInMeleeRange(Position self, Position target);
}
