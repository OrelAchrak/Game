package game.combat;
import game.combat.MagicElement;


/**
 * Interface for magical attackers.
 */
public interface MagicAttacker {
    void calculateMagicDamage(Combatant target);
    void castSpell(Combatant target);
    MagicElement getElement();
    boolean isElementStrongerThan(MagicAttacker other);
}
