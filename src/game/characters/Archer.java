package game.characters;

import game.core.GameEntity;
import game.combat.Attacker;
import game.combat.Combatant;
import game.combat.PhysicalAttacker;
import game.combat.RangedFighter;
import game.map.Position;
import java.util.Random;

public class Archer extends PlayerCharacter
        implements Attacker, PhysicalAttacker, RangedFighter {
    private double accuracy;
    private Random rand = new Random();

    public Archer(String name, Position position, double accuracy) {
        super(name, position);
        this.accuracy = accuracy;
    }

    @Override
    public boolean canAttack(GameEntity target) {
        return isInRange(getPosition(), target.getPosition());
    }

    @Override
    public void performAttack(Combatant target) {
        attack(target);
    }

    @Override
    public void attack(Combatant target) {
        if (rand.nextDouble() <= accuracy) {
            target.receiveDamage(getPower(), this);
        }
    }

    @Override
    public boolean isCriticalHit() {
        return rand.nextDouble() < 0.1;
    }

    @Override
    public void fightRanged(Combatant target) {
        if (canAttack((GameEntity) target)) {
            attack(target);
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
