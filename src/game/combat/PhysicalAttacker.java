package game.combat;

/**
 * ממשק PhysicalAttacker: תוקף פיזי.
 */
public interface PhysicalAttacker {
    void attack(Combatant target);
    boolean isCriticalHit();
}