package game.combat;

/**
 * Interface for physical attackers.
 */
public interface PhysicalAttacker {
    void attack(Combatant target);
    boolean isCriticalHit();
}
