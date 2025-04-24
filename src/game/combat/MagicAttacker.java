package game.combat;

/**
 * ממשק MagicAttacker: תוקף קסם.
 */
public interface MagicAttacker {
    void calculateMagicDamage(Combatant target);
    void castSpell(Combatant target);
    MagicElement getElement();
    boolean isElementStrongerThan(MagicAttacker other);
}