package game.characters;

import game.core.GameEntity;
import game.combat.Attacker;
import game.combat.Combatant;
import game.combat.MeleeFighter;
import game.combat.PhysicalAttacker;
import game.map.Position;
import java.util.Random;

public class Warrior extends PlayerCharacter
        implements Attacker, PhysicalAttacker, MeleeFighter {
    private int defence;
    private Random rand = new Random();

    public Warrior(String name, Position position, int defence) {
        super(name, position);
        this.defence = defence;
    }

    @Override
    public boolean canAttack(GameEntity target) {
        return isInMeleeRange(getPosition(), target.getPosition());
    }

    @Override
    public void performAttack(Combatant target) {
        int damage = getPower();
        if (isCriticalHit()) damage *= 2;
        target.receiveDamage(damage, this);
    }

    @Override
    public void attack(Combatant target) {
        performAttack(target);
    }

    @Override
    public boolean isCriticalHit() {
        return rand.nextDouble() < 0.1;
    }

    @Override
    public void fightClose(Combatant target) {
        if (canAttack((GameEntity) target)) {
            attack(target);
        }
    }

    @Override
    public boolean isInMeleeRange(Position self, Position target) {
        return self.distanceTo(target) <= 1;
    }
}