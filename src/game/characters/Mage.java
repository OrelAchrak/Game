package game.characters;

import game.core.GameEntity;
import game.combat.Attacker;
import game.combat.Combatant;
import game.combat.MagicAttacker;
import game.combat.MagicElement;
import game.combat.RangedFighter;
import game.map.Position;
import java.util.Random;

public class Mage extends PlayerCharacter
        implements Attacker, MagicAttacker, RangedFighter {
    private MagicElement element;

    public Mage(String name, Position position, MagicElement element) {
        super(name, position);
        this.element = element;
    }

    @Override
    public boolean canAttack(GameEntity target) {
        return isInRange(getPosition(), target.getPosition());
    }

    @Override
    public void performAttack(Combatant target) {
        castSpell(target);
    }

    @Override
    public void calculateMagicDamage(Combatant target) {
        int damage = (int) (getPower() * 1.5);
        if (element.isStrongerThan(((MagicAttacker) target).getElement())) {
            damage = (int) (damage * 1.2);
        } else if (((MagicAttacker) target).getElement().isStrongerThan(element)) {
            damage = (int) (damage * 0.8);
        }
        target.receiveDamage(damage, this);
    }

    @Override
    public void castSpell(Combatant target) {
        calculateMagicDamage(target);
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
        if (canAttack((GameEntity) target)) {
            castSpell(target);
        }
    }

    @Override
    public int getRange() {
        return 2;
    }

    @Override
    public boolean isInRange(Position self, Position target) {
        return self.distanceTo(target) <= getRange();
    }
}