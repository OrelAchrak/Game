package game.characters;

import game.combat.Combatant;
import game.combat.RangedFighter;
import game.map.Position;
import java.util.concurrent.ThreadLocalRandom;

public class Archer extends PlayerCharacter implements game.combat.PhysicalAttacker, RangedFighter {
    private double accuracy;

    public Archer(String name, Position pos, double accuracy) {
        super(name, pos);
        if (accuracy < 0 || accuracy > 0.8) throw new IllegalArgumentException();
        this.accuracy = accuracy;
    }

    @Override
    public void attack(Combatant target) {
        fightRanged(target);
    }

    @Override
    public void fightRanged(Combatant target) {
        boolean evaded = target.tryEvade();
        boolean hit = !evaded && ThreadLocalRandom.current().nextDouble() < accuracy;
        if (hit) target.receiveDamage(getPower(), this);
    }

    @Override
    public boolean isCriticalHit() {
        return ThreadLocalRandom.current().nextDouble() < 0.1;
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