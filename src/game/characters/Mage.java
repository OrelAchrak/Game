package game.characters;

import game.combat.MagicAttacker;
import game.combat.RangedFighter;
import game.map.Position;
import game.combat.MagicElement;

public class Mage extends PlayerCharacter implements MagicAttacker, RangedFighter {
    private MagicElement element;

    public Mage(String name, Position pos, MagicElement element) {
        super(name, pos);
        if (element == null) throw new IllegalArgumentException();
        this.element = element;
    }

    @Override
    public void calculateMagicDamage(game.combat.Combatant target) {}

    @Override
    public void castSpell(game.combat.Combatant target) {
        target.receiveDamage((int)Math.round(getPower() * 1.5), this);
    }

    @Override
    public MagicElement getElement() {
        return element;
    }

    @Override
    public boolean isElementStrongerThan(MagicAttacker other) {
        return element.isStrongerThan(other.getElement());
    }

    @Override
    public void fightRanged(game.combat.Combatant target) {
        castSpell(target);
    }

    @Override
    public int getRange() {
        return 2;
    }

    @Override
    public boolean isInRange(Position self, Position other) {
        return self.distanceTo(other) <= getRange();
    }
}