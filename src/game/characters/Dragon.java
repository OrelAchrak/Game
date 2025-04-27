package game.characters;

import game.combat.*;
import game.map.Position;
import java.util.concurrent.ThreadLocalRandom;


public class Dragon extends Enemy implements PhysicalAttacker, MagicAttacker, RangedFighter, MeleeFighter {
    private MagicElement element;

    public Dragon(Position pos, MagicElement element, int loot) {
        super(pos, 100, loot);
        if (element == null) throw new IllegalArgumentException();
        this.element = element;
    }

    @Override
    public void attack(Combatant target) {
        Position selfPos = getPosition();
        Position tgtPos = ((game.core.GameEntity) target).getPosition();
        if (selfPos.distanceTo(tgtPos) <= 1) fightClose(target);
        else fightRanged(target);
    }

    @Override
    public void fightClose(Combatant target) {
        target.receiveDamage(getPower(), this);
    }

    @Override
    public void calculateMagicDamage(Combatant target) {
        // not used
    }

    @Override
    public void castSpell(Combatant target) {
        double base = getPower() * 1.5;
        if (element.isStrongerThan(((MagicAttacker) target).getElement())) base *= 1.2;
        else if (target instanceof MagicAttacker && ((MagicAttacker) target).getElement().isStrongerThan(element)) base *= 0.8;
        target.receiveDamage((int) Math.round(base), this);
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
    public void fightRanged(Combatant target) {
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

    @Override
    public boolean isInMeleeRange(Position self, Position other) {
        return self.distanceTo(other) <= 1;
    }

    @Override
    public boolean isCriticalHit() {
        return ThreadLocalRandom.current().nextDouble() < 0.1;
    }
}
